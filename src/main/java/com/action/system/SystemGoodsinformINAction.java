package com.action.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;
import com.action.*;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.Goods;
import com.entity.system.GoodsStoreEvent;
import com.entity.system.GoodsStoreInfo;
import com.entity.system.Goodsinstoreitem;
import com.entity.system.unitclass;
import com.entity.system.Org;
import com.common.*;

public class SystemGoodsinformINAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		
		if (action!=null && action.equals("addDul"))//多条记录同时录入
		{	
			String goodscodes=request.getParameter("goodscode");
			String StoreEventNo=request.getParameter("StoreEventNo");
			DBObject db = new DBObject();
			String entity=request.getParameter("entity");
			String[] goodscode=goodscodes.split(";");
			for(int i=0;i<goodscode.length;i++)
			{
				Goods goods=new Goods(goodscode[i]);
				
				Map<String,String> map=new HashMap<String,String>(); 
			
				map.put("STOREEVENTNO",StoreEventNo);
				map.put("GOODSCODE", goodscode[i]);
				map.put("GOODSNAME", goods.getGOODSNAME());
				map.put("GOODSNUMBER", "0");
				String sequence="INNO";
			    eo.setSequence(sequence);
				eo.setMap(map);
				eo.setEntity(entity);
				res0=eo.Add(request);//插入成功
				
				//初始化每一个物品的库存操作
				
				String sq2 = "select * from com_storeinfo where goodscode=?";
				Parameter.SqlParameter[] ppselectstoreinfo = new Parameter.SqlParameter[]
				{  new Parameter.String(goodscode[i])};
				try {
					DataTable dt =db.runSelectQuery(sq2, ppselectstoreinfo);
					if(dt==null||dt.getRowsCount()==0)//没有对应的库存信息，说明是第一次入库，进行插入操作
					{
						Date now = new Date(); 
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
						String time = dateFormat.format( now ); 
						
						String sq3="insert into com_storeinfo(GoodsCode,GoodsName,TotalNumber,InCount,AvailableNumber,OutNumber,outCount,FirstInDate,LastOutDate) values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'))";
						Parameter.SqlParameter[] pp3 = new Parameter.SqlParameter[]
						{ new Parameter.String(goodscode[i]), new Parameter.String(goods.getGOODSNAME()), new Parameter.String("0"),new Parameter.String("1"), new Parameter.String("0"),	new Parameter.String("0"),new Parameter.String("0"), new Parameter.String(time),new Parameter.String("")
						};
						db.run(sq3,pp3);
						
					}
					else//否则增加一次入库计数
					{
						DataRow r = dt.get(0);
						String InCount=r.getString("InCount");
						int newnumber=Integer.parseInt(InCount)+1;
						String sql = "update com_storeinfo set InCount=? where GoodsCode=?";
						Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
						{  new Parameter.Int(newnumber),new Parameter.String(goodscode[i])};
						try {
							db.run(sql, pp);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			GoodsStoreEvent gse=new GoodsStoreEvent(StoreEventNo);//修改对应的入库事件
			int number=0;
			if(gse.getGoodsItemNum()=="")
				number=0;
			else
				number=Integer.parseInt(gse.getGoodsItemNum());
			int newnumber=goodscode.length+number;
			//System.out.println(newnumber);
			
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
			String sequence="INNO";
		    eo.setSequence(sequence);
			eo.setEntity(entity);
			res0 = eo.Add(request);//插入成功
			String Inno="";
			String sqInno="select * from com_instoreitem order by inno desc";
			try {
				DataTable dtInno=db.runSelectQuery(sqInno);
				DataRow r = dtInno.get(0);
				Inno=r.getString("Inno");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			GoodsStoreEvent gse=new GoodsStoreEvent(request.getParameter("COM_INSTOREITEM.STOREEVENTNO"));//修改对应的入库事件
			//System.out.println(request.getParameter("STOREEVENTNO"));
			String number=Format.NullToZero(gse.getGoodsItemNum());
			int newnumber=Integer.parseInt(number)+1;
			//System.out.println(newnumber);
			
			String sql = "update com_storeevent set goodsitemnum=? where STOREEVENTNO=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{  new Parameter.Int(newnumber),new Parameter.String(request.getParameter("COM_INSTOREITEM.STOREEVENTNO"))};
			try {
				db.run(sql, pp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GoodsStoreInfo gsi=new GoodsStoreInfo(request.getParameter("COM_INSTOREITEM.GOODSCODE"));//修改对应物品的库存信息,
			Goodsinstoreitem goodsinitem=new Goodsinstoreitem(Inno);//本次某一具体物品信息。
			String sq2 = "select * from com_storeinfo where goodscode=?";
			Parameter.SqlParameter[] ppselectstoreinfo = new Parameter.SqlParameter[]
			{  new Parameter.String(request.getParameter("COM_INSTOREITEM.GOODSCODE"))};
			try {
				DataTable dt =db.runSelectQuery(sq2, ppselectstoreinfo);
				if(dt==null||dt.getRowsCount()==0)//没有对应的库存信息，说明是第一次入库，进行插入操作
				{
					Date now = new Date(); 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式 
					String time = dateFormat.format( now ); 
					
					String sq3="insert into com_storeinfo(GoodsCode,GoodsName,GoodsDesc,GoodsStyle,MeasureUnit,TotalNumber,InCount,AvailableNumber,OutNumber,outCount,FirstInDate,LastOutDate,Memo) values(?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?)";
					Parameter.SqlParameter[] pp3 = new Parameter.SqlParameter[]
					{ new Parameter.String(goodsinitem.getGOODSCODE()), new Parameter.String(goodsinitem.getGOODSNAME()),new Parameter.String(goodsinitem.getGOODSDESC()), new Parameter.String(goodsinitem.getGOODSSTYLE()),new Parameter.String(goodsinitem.getMEASUREUNIT()), new Parameter.String(goodsinitem.getGOODSNUMBER()),new Parameter.String("1"), new Parameter.String(goodsinitem.getGOODSNUMBER()),	new Parameter.String("0"),new Parameter.String("0"), new Parameter.String(time),new Parameter.String(""), new Parameter.String(goodsinitem.getMEMO())
					};
					db.run(sq3,pp3);
					
				}
				else//进行更新库存操作
				{
					String totalnum=Integer.toString(Integer.parseInt(gsi.getTotalNumber())+Integer.parseInt(goodsinitem.getGOODSNUMBER()));
					String incount=Integer.toString(Integer.parseInt(gsi.getInCount())+1);
					String AvailableNumber=Integer.toString(Integer.parseInt(gsi.getAvailableNumber())+Integer.parseInt(goodsinitem.getGOODSNUMBER()));
					String sq4 = "update com_storeinfo set TotalNumber=?,InCount=?,AvailableNumber=?,GoodsName=?,GoodsDesc=?,GoodsStyle=?,MeasureUnit=?,Memo=? where goodscode=?";
					Parameter.SqlParameter[] pp4 = new Parameter.SqlParameter[]
					{  new Parameter.String(totalnum),new Parameter.String(incount), new Parameter.String(AvailableNumber),new Parameter.String(goodsinitem.getGOODSNAME()),new Parameter.String(goodsinitem.getGOODSDESC()), new Parameter.String(goodsinitem.getGOODSSTYLE()),new Parameter.String(goodsinitem.getMEASUREUNIT()),new Parameter.String(goodsinitem.getMEMO()),new Parameter.String(goodsinitem.getGOODSCODE())
					};
					db.run(sq4,pp4);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			res += "show('"+res0+"');";
			res += "window.open('../xtwh/goodsmanage/goodsinformIN.jsp?StoreEventNo="+request.getParameter("COM_INSTOREITEM.STOREEVENTNO")+"','goodsinformIN');";
			res += "window.open('../xtwh/goodsmanage/goodsinformINList.jsp?detail=0&StoreEventNo="+request.getParameter("COM_INSTOREITEM.STOREEVENTNO")+"','goodsinformINList');";
			//res+="parent.window.open()";
		}
		else if (action!=null && action.equals("modify"))//更新对应的库存信息，此时只特殊处理物品的数量

		{//更新之前先减掉原来的增加的数目
			DBObject db = new DBObject();
			GoodsStoreInfo gsi=new GoodsStoreInfo(request.getParameter("COM_INSTOREITEM.GOODSCODE"));//库存信息
			
			Goodsinstoreitem goodsinitem=new Goodsinstoreitem(request.getParameter("COM_INSTOREITEM.INNO"));//本次修改物品的信息
			String totalnum=Integer.toString(Integer.parseInt(gsi.getTotalNumber())-Integer.parseInt(goodsinitem.getGOODSNUMBER()));
			//String incount=Integer.toString(Integer.parseInt(gsi.getTotalNumber())+1);
			String AvailableNumber=Integer.toString(Integer.parseInt(gsi.getAvailableNumber())-Integer.parseInt(goodsinitem.getGOODSNUMBER()));
			String sq4 = "update com_storeinfo set TotalNumber=?,AvailableNumber=?,GoodsName=?,GoodsDesc=?,GoodsStyle=?,MeasureUnit=?,Memo=? where goodscode=?";
			Parameter.SqlParameter[] pp4 = new Parameter.SqlParameter[]
			{  new Parameter.String(totalnum), new Parameter.String(AvailableNumber),new Parameter.String(goodsinitem.getGOODSNAME()),new Parameter.String(goodsinitem.getGOODSDESC()), new Parameter.String(goodsinitem.getGOODSSTYLE()),new Parameter.String(goodsinitem.getMEASUREUNIT()),new Parameter.String(goodsinitem.getMEMO()),new Parameter.String(goodsinitem.getGOODSCODE())
			};
			try {
				db.run(sq4,pp4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  String entity=request.getParameter("entity");
				
				
				eo.setEntity(entity);
				res0 = eo.Update(request);	
				//更新之后再加上更新之后的数目
				 gsi=new GoodsStoreInfo(request.getParameter("COM_INSTOREITEM.GOODSCODE"));//修改对应物品的库存信息,
				 goodsinitem=new Goodsinstoreitem(request.getParameter("COM_INSTOREITEM.INNO"));//本次某一具体物品信息。
			
				//进行更新库存操作
				//{
					 totalnum=Integer.toString(Integer.parseInt(gsi.getTotalNumber())+Integer.parseInt(goodsinitem.getGOODSNUMBER()));
					 //String incount=Integer.toString(Integer.parseInt(gsi.getInCount())+1);
					 AvailableNumber=Integer.toString(Integer.parseInt(gsi.getAvailableNumber())+Integer.parseInt(goodsinitem.getGOODSNUMBER()));
					 sq4 = "update com_storeinfo set TotalNumber=?,AvailableNumber=?,GoodsName=?,GoodsDesc=?,GoodsStyle=?,MeasureUnit=?,Memo=? where goodscode=?";
					Parameter.SqlParameter[] pp5 = new Parameter.SqlParameter[]
					{  new Parameter.String(totalnum), new Parameter.String(AvailableNumber),new Parameter.String(goodsinitem.getGOODSNAME()),new Parameter.String(goodsinitem.getGOODSDESC()), new Parameter.String(goodsinitem.getGOODSSTYLE()),new Parameter.String(goodsinitem.getMEASUREUNIT()),new Parameter.String(goodsinitem.getMEMO()),new Parameter.String(goodsinitem.getGOODSCODE())
					};
					try {
						db.run(sq4,pp5);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				res += "show('"+res0+"');";
				res+="window.close();";
				

		}
		else if (action!=null && action.equals("del"))
		{
			//String[] ids=request.getParameterValues("items");//批量删除
			String[] ids=request.getParameterValues("inno");//
			
			Goodsinstoreitem gse=new Goodsinstoreitem();
			//System.out.println("运行到这里");
			
			
			
			if(gse.Delete(ids[0]))//必须在删除之前更新库存内容
			{
				GoodsStoreEvent goodsse=new GoodsStoreEvent(request.getParameter("COM_INSTOREITEM.STOREEVENTNO"));//删除之后更新对应的事件信息
				//System.out.println(request.getParameter("STOREEVENTNO"));
				String number=Format.NullToZero(goodsse.getGoodsItemNum());
				int newnumber=Integer.parseInt(number)-1;
				//System.out.println(newnumber);
				DBObject db = new DBObject();
				String sql = "update com_storeevent set goodsitemnum=? where STOREEVENTNO=?";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{  new Parameter.Int(newnumber),new Parameter.String(request.getParameter("COM_INSTOREITEM.STOREEVENTNO"))};
				try {
					db.run(sql, pp);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				//res+="window.close();";
				res += "window.open('../xtwh/goodsmanage/goodsinformINList.jsp?detail=0&StoreEventNo="+request.getParameter("COM_INSTOREITEM.STOREEVENTNO")+"','goodsinformINList');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
				//res += "parent.unittree.location.reload();";
				//res += "window.open('../xtwh/system_unit/unit_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，可能是有子单位请先删除子单位！');";
				//res+="window.close();";
				res += "window.open('../xtwh/goodsmanage/goodsinformINList.jsp?detail=0&StoreEventNo="+request.getParameter("COM_INSTOREITEM.STOREEVENTNO")+"','goodsinformINList');";
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
				//res += "window.open('../xtwh/system_unit/unit_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
			}
		}
		else
		{

		}
//		System.out.println(res);
		return res;
	}
}