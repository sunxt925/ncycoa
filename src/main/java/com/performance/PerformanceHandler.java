package com.performance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.common.Format;
import com.common.IndexCode;
import com.db.ConnectionPool;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.entity.index.Indexitem;

@SuppressWarnings("serial")
public class PerformanceHandler extends HttpServlet implements Servlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		resp.setContentType("application/json;charset=gb2312");
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);
		
		JSONObject obj = JSONObject.parseObject(req.getParameter("d"));
		//=========================开始构建指标树==========================================
		String archcode = obj.getString("indexcode");
		List<Indexitem> indexlist = new ArrayList<Indexitem>();
		Indexitem arch = null;
		try {
			String sql = "select * from tbm_indexitem where indexcode like '" + archcode + "%' order by indexcode";
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null) {
				for (int i = 0; i < dt.getRowsCount(); i++) {
					DataRow r = dt.get(i);
					Indexitem indexitem = new Indexitem();
					indexitem.setIndexCode(r.getString("indexcode"));
					indexitem.setUniIndexCode(r.getString("uniindexcode"));
					indexitem.setIndexName(r.getString("indexname"));
					indexitem.setIndexDesc(r.getString("indexdesc"));
					indexitem.setIndexCountLimit(Integer.parseInt(Format.NullToZero(r.getString("indexcountlimit"))));
					indexitem.setArchCode(r.getString("archcode"));
					indexitem.setIsParent(r.getString("isparent"));
					indexitem.setParentIndexCode(r.getString("parentindexcode"));
					indexitem.setValueComputingType(r.getString("valuecomputingtype"));
					indexitem.setValueScoreSourceType(r.getString("valuescoresourcetype"));
					indexitem.setNumPara(Integer.parseInt(Format.NullToZero(r.getString("numpara"))));
					indexitem.setValueFunc(r.getString("valuefunc"));
					indexitem.setValueUnit(r.getString("valueunit"));
					indexitem.setSCoreFuncType(r.getString("scorefunctype"));
					indexitem.setScoreFunc(r.getString("scorefunc"));
					indexitem.setIsRequired(Integer.parseInt(Format.NullToZero(r.getString("isrequired"))));
					indexitem.setScorePeriod(r.getString("scoreperiod"));
					indexitem.setScoreDefault(Double.parseDouble(Format.NullToZero(r.getString("scoredefault"))));
					indexitem.setScoreSumLow(Double.parseDouble(Format.NullToZero(r.getString("scoresumLow"))));
					indexitem.setScoreSumMax(Double.parseDouble(Format.NullToZero(r.getString("scoresummax"))));
					indexitem.setUpperSumWeight(Double.parseDouble(Format.NullToZero(r.getString("uppersumweight"))));
					indexitem.setValidBeginDate(Format.strToDate(r.getString("validbegindate")));
					indexitem.setValidEndDate(Format.strToDate(r.getString("validenddate")));
					indexitem.setEnabled(Integer.parseInt(Format.NullToZero(r.getString("enabled"))));
					indexitem.setMemo(r.getString("memo"));
					indexitem.setIndexorder(r.getString("indexorder"));
					indexitem.setStandardscore(Double.parseDouble(Format.NullToZero(r.getString("standardscore"))));
					indexlist.add(indexitem);
					
					if(indexitem.getIndexCode().equals(archcode)){
						arch = indexitem;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Queue<Indexitem> q = new LinkedList<Indexitem>();
		q.offer(arch);
		while(q.peek() != null){
			Indexitem parent = q.remove();
			for(int i=0; i<indexlist.size(); i++){
				Indexitem item = indexlist.get(i);
				if(item.getParentIndexCode().equals(parent.getIndexCode())){
					parent.children.add(item);
					item.parent = parent;
					if(item.getIsParent().equals("1")){
						q.offer(item);
					}
				}
			}
		}
		
		IndexResult indexScore = new IndexResult(arch);
		Queue<IndexResult> qq = new LinkedList<IndexResult>();
		qq.offer(indexScore);
		while(qq.peek() != null){
			IndexResult parent = qq.remove();
			for(int i=0; i<indexlist.size(); i++){
				Indexitem item = indexlist.get(i);
				if(item.getParentIndexCode().equals(parent.getIndex().getIndexCode())){
					IndexResult score = new IndexResult(item);
					parent.getChildren().add(score);
					if(item.getIsParent().equals("1")){
						qq.offer(score);
					}
				}
			}
		}
	  //=========================构建指标树完成==========================================
		List<ReviewEntity> entities = new ArrayList<ReviewEntity>();
		String objType = obj.getString("objtype");
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for(Object objcode : obj.keySet()){
			if(objcode.toString().startsWith("obj_")){
				ReviewEntity e = new ReviewEntity(objcode.toString().substring(4), objType, indexScore);
				entities.add(e);
				sb.append(e.getObjReviewed().toString()).append(",");
				count++;
			}
		}
		if(sb.length() > 0){
			sb.delete(sb.length() - 1, sb.length());
		}
		
		int status = 0;
		String msg = "";
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement psscore = null;
		try{
			conn = ConnectionPool.getConnection();
			if(conn == null){
				//返回错误
				status = 1; //获取数据库连接失败
			}
			
			//考虑到event和eventdetail及scoredetail数据的一致性，将他们做成一个事务
			conn.setAutoCommit(false);
			
			
			ps = conn.prepareStatement("insert into tbm_meritvupdlog(eventno, objecttype, indexarch, relateyear, relateperiod, objectlist, objectnum) values(?,?,?,?,?,?,?)");
			psscore = conn.prepareStatement("insert into tbm_indexscoredetail(recno,eventno,objectcode,objecttype,indexcode,indexfunc,indexvalue,inputindexstr,"
					+"scorevalue,scorefunc,scoreyear,scoreperiod,sumdealflag) "
					+"values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
			String eventno = IndexCode.getRecno("ite");
			String relateyear = obj.getString("relateyear");
			String indexcode = obj.getString("indexcode");
			String periodcode = obj.getString("periodcode");
			
			conn.createStatement().execute("delete from tbm_indexscoredetail where indexcode like '"+ indexcode +"%' and scoreyear='" + relateyear + "' and scoreperiod='"
						+ periodcode + "' and objectcode in (" + sb.toString() + ") and objecttype='" + objType +"'");
			
			ps.setString(1, eventno);
			ps.setString(2, objType);
			ps.setString(3, indexcode);
			ps.setString(4, relateyear);
			ps.setString(5, periodcode);
			ps.setString(6, sb.toString());
			ps.setInt(7, count);
			ps.execute();
			
			for(ReviewEntity e : entities){
				for(Object objcode : obj.keySet()){
					String tmp = objcode.toString().substring(4);
					if(e.getObjReviewed().equals(tmp)){
						JSONObject indexdata = obj.getJSONObject(objcode.toString());
						e.review(indexdata);
						
						qq.offer(e.getIndexScore());
						while(qq.peek() != null){
							IndexResult parent = qq.remove();
							psscore.setString(1, IndexCode.getRecno("isd"));//recno
							psscore.setString(2, eventno);					//tableno
							psscore.setString(3, e.getObjReviewed().toString());         //objectcode
							psscore.setString(4, e.getObjType());					//objecttype
							psscore.setString(5, parent.getIndex().getIndexCode()); //indexcode
							psscore.setString(6, parent.getIndex().getValueFunc()); //indexfunc
							psscore.setDouble(7, parent.value);						//indexvalue
							psscore.setString(8, parent.getInputStr());						//inputindexstr
							psscore.setDouble(9, parent.score);						//scorevalue
							psscore.setString(10, parent.getIndex().getScoreFunc());//scorefunc
							psscore.setString(11, relateyear);        //scoreyear
							psscore.setString(12, periodcode);        //scoreperiod
							psscore.setInt(13, 0);
							psscore.execute();
							
							for(int i=0; i<parent.getChildren().size(); i++){
								qq.offer(parent.getChildren().get(i));
							}
						}
						
						break;
					}
				}
			}
			
			conn.commit();
		}catch(ReviewException e){
			e.printStackTrace();
			try{
				status = 3; //插入数据失败
				if(e.getCause() != null){
					msg = e.getCause().getMessage();
				} else {
					msg = e.getMessage();
				}
				conn.rollback(); //回滚事务
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}catch (Exception e){
			e.printStackTrace();
			try{
				status = 3; //插入数据失败
				if(e.getCause() != null){
					msg = e.getCause().getMessage();
				} else {
					msg = e.getMessage();
				}
				conn.rollback(); //回滚事务
			}
			catch(Exception ee){
				ee.printStackTrace();
			}
		}finally{
			
			resp.getWriter().write("{\"status\":"+status+",\"msg\":\""+msg+"\"}");
			resp.getWriter().flush();
			resp.getWriter().close();
			
			try{
				if(ps != null){
					ps.close();
				}
				if(psscore != null){
					psscore.close();
				}
				if(conn != null){
					conn.close();
				}
			}
			catch (Exception e){
				ps = null;
				psscore = null;
				conn = null;
			}
		}
	}
}
