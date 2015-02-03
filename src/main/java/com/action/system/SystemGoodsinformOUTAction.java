package com.action.system;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.*;
import com.action.*;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.Goods;
import com.entity.system.GoodsStoreEvent;
import com.entity.system.GoodsStoreInfo;

import com.entity.system.Goodsoutstoreitem;

import com.common.*;

public class SystemGoodsinformOUTAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();

		if (action!=null && action.equals("addDul"))//������¼ͬʱ����
		{	
			String[] ids = request.getParameterValues("items");
			String StoreEventNo=request.getParameter("StoreEventNo");
			DBObject db = new DBObject();
			String entity=request.getParameter("entity");
			
			for(int i=0;i<ids.length;i++)
			{
				
				Goods goods=new Goods(ids[i]);
				
				Map<String,String> map=new HashMap<String,String>(); 
			
				map.put("STOREEVENTNO",StoreEventNo);
				map.put("GOODSCODE", ids[i]);
				map.put("GOODSNAME", goods.getGOODSNAME());
				map.put("GOODSNUMBER", "0");
				String sequence="OUTNO";
			    eo.setSequence(sequence);
				eo.setMap(map);
				eo.setEntity(entity);
				res0=eo.Add(request);//����ɹ�
				//��ʱ����һ�£����ĳ������
				String sq2 = "select * from com_storeinfo where goodscode=?";
				Parameter.SqlParameter[] ppselectstoreinfo = new Parameter.SqlParameter[]
				{  new Parameter.String(ids[i])};
				 
				try {
					DataTable dt = db.runSelectQuery(sq2, ppselectstoreinfo);
					DataRow r = dt.get(0);
					String OutCount=r.getString("OutCount");
					int newnumber=Integer.parseInt(OutCount)+1;
					String sql = "update com_storeinfo set OutCount=? where GoodsCode=?";
					Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
					{  new Parameter.Int(newnumber),new Parameter.String(ids[i])};
					try {
						db.run(sql, pp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
				
			GoodsStoreEvent gse=new GoodsStoreEvent(StoreEventNo);//�޸Ķ�Ӧ�ĳ����¼�
			
			int number=0;
			if(gse.getGoodsItemNum()=="")
				number=0;
			else
				number=Integer.parseInt(gse.getGoodsItemNum());
			int newnumber=ids.length+number;
		
			String sql = "update com_storeevent set goodsitemnum=? where STOREEVENTNO=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{  new Parameter.Int(newnumber),new Parameter.String(request.getParameter("StoreEventNo"))};
			try {
				db.run(sql, pp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			res += "show('"+res0+"');";
			res+="window.close();";
			//res += "window.open('../xtwh/goodsmanage/goodsIn_manage.jsp','goodsIn_manage');";
			//res += "window.open('../xtwh/goodsmanage/goodsinformINList.jsp?detail=0&StoreEventNo="+StoreEventNo+"','goodsinformINList');";
			
			
		}
		else if (action!=null && action.equals("add"))
		{
			DBObject db = new DBObject();
			String entity=request.getParameter("entity");
			String sequence="OUTNO";
		    eo.setSequence(sequence);
			eo.setEntity(entity);
			res0 = eo.Add(request);//����ɹ�
			String Outno="";
			String sqOutno="select * from com_outstoreitem order by outno desc";
			try {
				DataTable dtOutno=db.runSelectQuery(sqOutno);
				DataRow r = dtOutno.get(0);
				Outno=r.getString("Outno");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			GoodsStoreEvent gse=new GoodsStoreEvent(request.getParameter("COM_OUTSTOREITEM.STOREEVENTNO"));//�޸Ķ�Ӧ��chu���¼�
			//System.out.println(request.getParameter("STOREEVENTNO"));
			String number=Format.NullToZero(gse.getGoodsItemNum());
			int newnumber=Integer.parseInt(number)+1;
			//System.out.println(newnumber);
			
			String sql = "update com_storeevent set goodsitemnum=? where STOREEVENTNO=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{  new Parameter.Int(newnumber),new Parameter.String(request.getParameter("COM_OUTSTOREITEM.STOREEVENTNO"))};
			try {
				db.run(sql, pp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GoodsStoreInfo gsi=new GoodsStoreInfo(request.getParameter("COM_OUTSTOREITEM.GOODSCODE"));//�޸Ķ�Ӧ��Ʒ�Ŀ����Ϣ,
			Goodsoutstoreitem goodsoutitem=new Goodsoutstoreitem(Outno);//���γ���ĳһ������Ʒ��Ϣ��
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//���Է�����޸����ڸ�ʽ 
			String time = dateFormat.format( now ); 
				
			    //���и��¿�����
			
				String outnum=Integer.toString(Integer.parseInt(gsi.getOutNumber())+Integer.parseInt(goodsoutitem.getGOODSNUMBER()));
				String outcount=Integer.toString(Integer.parseInt(gsi.getOutCount())+1);
				String AvailableNumber=Integer.toString(Integer.parseInt(gsi.getAvailableNumber())-Integer.parseInt(goodsoutitem.getGOODSNUMBER()));
				String sq4 = "update com_storeinfo set outNumber=?,outCount=?,AvailableNumber=?,GoodsName=?,GoodsDesc=?,GoodsStyle=?,MeasureUnit=?,Memo=?,lastoutdate=to_date(?,'yyyy-mm-dd') where goodscode=?";
				Parameter.SqlParameter[] pp4 = new Parameter.SqlParameter[]
				{  new Parameter.String(outnum),new Parameter.String(outcount), new Parameter.String(AvailableNumber),new Parameter.String(goodsoutitem.getGOODSNAME()),new Parameter.String(goodsoutitem.getGOODSDESC()), new Parameter.String(goodsoutitem.getGOODSSTYLE()),new Parameter.String(goodsoutitem.getMEASUREUNIT()),new Parameter.String(goodsoutitem.getMEMO()),new Parameter.String(time),new Parameter.String(goodsoutitem.getGOODSCODE())
				};
				try {
					db.run(sq4,pp4);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
		
			
			res += "show('"+res0+"');";
			res += "window.open('../goodsmanage/goodsinformOUTList.jsp?detail=0&StoreEventNo="+request.getParameter("COM_OUTSTOREITEM.STOREEVENTNO")+"','goodsinformOUTList');";
			
		}
		else if (action!=null && action.equals("modify"))//���¶�Ӧ�Ŀ����Ϣ����ʱֻ���⴦����Ʒ������

		{//����֮ǰ�ȼ���ԭ�������ӵ���Ŀ
			DBObject db = new DBObject();
			GoodsStoreInfo gsi=new GoodsStoreInfo(request.getParameter("COM_OUTSTOREITEM.GOODSCODE"));//�����Ϣ
			Goodsoutstoreitem goodsoutitem=new Goodsoutstoreitem(request.getParameter("COM_OUTSTOREITEM.OUTNO"));//�����޸���Ʒ����Ϣ
			String outnum=Integer.toString(Integer.parseInt(gsi.getOutNumber())-Integer.parseInt(goodsoutitem.getGOODSNUMBER()));
			String AvailableNumber=Integer.toString(Integer.parseInt(gsi.getAvailableNumber())+Integer.parseInt(goodsoutitem.getGOODSNUMBER()));
			String sq4 = "update com_storeinfo set outNumber=?,AvailableNumber=?,GoodsName=?,GoodsDesc=?,GoodsStyle=?,MeasureUnit=?,Memo=? where goodscode=?";
			Parameter.SqlParameter[] pp4 = new Parameter.SqlParameter[]
			{  new Parameter.String(outnum), new Parameter.String(AvailableNumber),new Parameter.String(goodsoutitem.getGOODSNAME()),new Parameter.String(goodsoutitem.getGOODSDESC()), new Parameter.String(goodsoutitem.getGOODSSTYLE()),new Parameter.String(goodsoutitem.getMEASUREUNIT()),new Parameter.String(goodsoutitem.getMEMO()),new Parameter.String(goodsoutitem.getGOODSCODE())
			};
			try {
				db.run(sq4,pp4);
			} catch (Exception e) {
				e.printStackTrace();
			}
			  String entity=request.getParameter("entity");
				
				
				eo.setEntity(entity);
				res0 = eo.Update(request);	
				//����֮���ټ��ϸ���֮�����Ŀ
				Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//���Է�����޸����ڸ�ʽ 
				String time = dateFormat.format( now ); 
				GoodsStoreInfo gsinew=new GoodsStoreInfo(request.getParameter("COM_OUTSTOREITEM.GOODSCODE"));//�����Ϣ
				
				Goodsoutstoreitem goodsoutitemnew=new Goodsoutstoreitem(request.getParameter("COM_OUTSTOREITEM.OUTNO"));//�����޸���Ʒ����Ϣ
				String outnumnew=Integer.toString(Integer.parseInt(gsinew.getOutNumber())+Integer.parseInt(goodsoutitemnew.getGOODSNUMBER()));
				String AvailableNumbernew=Integer.toString(Integer.parseInt(gsinew.getAvailableNumber())-Integer.parseInt(goodsoutitemnew.getGOODSNUMBER()));
				String sq4new = "update com_storeinfo set outNumber=?,AvailableNumber=?,GoodsName=?,GoodsDesc=?,GoodsStyle=?,MeasureUnit=?,Memo=?,lastoutdate=to_date(?,'yyyy-mm-dd') where goodscode=?";
				Parameter.SqlParameter[] pp4new = new Parameter.SqlParameter[]
				{  new Parameter.String(outnumnew),new Parameter.String(AvailableNumbernew),new Parameter.String(goodsoutitemnew.getGOODSNAME()),new Parameter.String(goodsoutitemnew.getGOODSDESC()), new Parameter.String(goodsoutitemnew.getGOODSSTYLE()),new Parameter.String(goodsoutitemnew.getMEASUREUNIT()),new Parameter.String(goodsoutitemnew.getMEMO()),new Parameter.String(time),new Parameter.String(goodsoutitemnew.getGOODSCODE())
				};
				try {
					db.run(sq4new,pp4new);
				} catch (Exception e) {
					e.printStackTrace();
				}
				res += "show('"+res0+"');";
				res+="window.close();";
				

		}
		else if (action!=null && action.equals("del"))
		{
			String[] ids=request.getParameterValues("outno");//
			Goodsoutstoreitem gse=new Goodsoutstoreitem();
			if(gse.Delete(ids[0]))//������ɾ��֮ǰ���¿������
			{
				GoodsStoreEvent goodsse=new GoodsStoreEvent(request.getParameter("COM_OUTSTOREITEM.STOREEVENTNO"));//ɾ��֮����¶�Ӧ���¼���Ϣ
				String number=Format.NullToZero(goodsse.getGoodsItemNum());
				int newnumber=Integer.parseInt(number)-1;
				DBObject db = new DBObject();
				String sql = "update com_storeevent set goodsitemnum=? where STOREEVENTNO=?";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{  new Parameter.Int(newnumber),new Parameter.String(request.getParameter("COM_OUTSTOREITEM.STOREEVENTNO"))};
				try {
					db.run(sql, pp);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				res += "alert('ɾ���ɹ���');";
				res += "window.open('../goodsmanage/goodsinformOUTList.jsp?detail=0&StoreEventNo="+request.getParameter("COM_OUTSTOREITEM.STOREEVENTNO")+"','goodsinformOUTList');";
				
			}
			else
			{
				res += "alert('ɾ��ʧ�ܣ����������ӵ�λ����ɾ���ӵ�λ��');";
				res += "window.open('../goodsmanage/goodsinformOUTList.jsp?detail=0&StoreEventNo="+request.getParameter("COM_OUTSTOREITEM.STOREEVENTNO")+"','goodsinformOUTList');";
				}
		}
		return res;
	}
}