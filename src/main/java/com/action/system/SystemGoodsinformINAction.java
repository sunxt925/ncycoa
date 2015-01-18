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
		
		if (action!=null && action.equals("addDul"))//������¼ͬʱ¼��
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
				res0=eo.Add(request);//����ɹ�
				
				//��ʼ��ÿһ����Ʒ�Ŀ�����
				
				String sq2 = "select * from com_storeinfo where goodscode=?";
				Parameter.SqlParameter[] ppselectstoreinfo = new Parameter.SqlParameter[]
				{  new Parameter.String(goodscode[i])};
				try {
					DataTable dt =db.runSelectQuery(sq2, ppselectstoreinfo);
					if(dt==null||dt.getRowsCount()==0)//û�ж�Ӧ�Ŀ����Ϣ��˵���ǵ�һ����⣬���в������
					{
						Date now = new Date(); 
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//���Է�����޸����ڸ�ʽ 
						String time = dateFormat.format( now ); 
						
						String sq3="insert into com_storeinfo(GoodsCode,GoodsName,TotalNumber,InCount,AvailableNumber,OutNumber,outCount,FirstInDate,LastOutDate) values(?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'))";
						Parameter.SqlParameter[] pp3 = new Parameter.SqlParameter[]
						{ new Parameter.String(goodscode[i]), new Parameter.String(goods.getGOODSNAME()), new Parameter.String("0"),new Parameter.String("1"), new Parameter.String("0"),	new Parameter.String("0"),new Parameter.String("0"), new Parameter.String(time),new Parameter.String("")
						};
						db.run(sq3,pp3);
						
					}
					else//��������һ��������
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
			GoodsStoreEvent gse=new GoodsStoreEvent(StoreEventNo);//�޸Ķ�Ӧ������¼�
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
			res0 = eo.Add(request);//����ɹ�
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
			
			GoodsStoreEvent gse=new GoodsStoreEvent(request.getParameter("COM_INSTOREITEM.STOREEVENTNO"));//�޸Ķ�Ӧ������¼�
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
			GoodsStoreInfo gsi=new GoodsStoreInfo(request.getParameter("COM_INSTOREITEM.GOODSCODE"));//�޸Ķ�Ӧ��Ʒ�Ŀ����Ϣ,
			Goodsinstoreitem goodsinitem=new Goodsinstoreitem(Inno);//����ĳһ������Ʒ��Ϣ��
			String sq2 = "select * from com_storeinfo where goodscode=?";
			Parameter.SqlParameter[] ppselectstoreinfo = new Parameter.SqlParameter[]
			{  new Parameter.String(request.getParameter("COM_INSTOREITEM.GOODSCODE"))};
			try {
				DataTable dt =db.runSelectQuery(sq2, ppselectstoreinfo);
				if(dt==null||dt.getRowsCount()==0)//û�ж�Ӧ�Ŀ����Ϣ��˵���ǵ�һ����⣬���в������
				{
					Date now = new Date(); 
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//���Է�����޸����ڸ�ʽ 
					String time = dateFormat.format( now ); 
					
					String sq3="insert into com_storeinfo(GoodsCode,GoodsName,GoodsDesc,GoodsStyle,MeasureUnit,TotalNumber,InCount,AvailableNumber,OutNumber,outCount,FirstInDate,LastOutDate,Memo) values(?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?)";
					Parameter.SqlParameter[] pp3 = new Parameter.SqlParameter[]
					{ new Parameter.String(goodsinitem.getGOODSCODE()), new Parameter.String(goodsinitem.getGOODSNAME()),new Parameter.String(goodsinitem.getGOODSDESC()), new Parameter.String(goodsinitem.getGOODSSTYLE()),new Parameter.String(goodsinitem.getMEASUREUNIT()), new Parameter.String(goodsinitem.getGOODSNUMBER()),new Parameter.String("1"), new Parameter.String(goodsinitem.getGOODSNUMBER()),	new Parameter.String("0"),new Parameter.String("0"), new Parameter.String(time),new Parameter.String(""), new Parameter.String(goodsinitem.getMEMO())
					};
					db.run(sq3,pp3);
					
				}
				else//���и��¿�����
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
		else if (action!=null && action.equals("modify"))//���¶�Ӧ�Ŀ����Ϣ����ʱֻ���⴦����Ʒ������

		{//����֮ǰ�ȼ���ԭ�������ӵ���Ŀ
			DBObject db = new DBObject();
			GoodsStoreInfo gsi=new GoodsStoreInfo(request.getParameter("COM_INSTOREITEM.GOODSCODE"));//�����Ϣ
			
			Goodsinstoreitem goodsinitem=new Goodsinstoreitem(request.getParameter("COM_INSTOREITEM.INNO"));//�����޸���Ʒ����Ϣ
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
				//����֮���ټ��ϸ���֮�����Ŀ
				 gsi=new GoodsStoreInfo(request.getParameter("COM_INSTOREITEM.GOODSCODE"));//�޸Ķ�Ӧ��Ʒ�Ŀ����Ϣ,
				 goodsinitem=new Goodsinstoreitem(request.getParameter("COM_INSTOREITEM.INNO"));//����ĳһ������Ʒ��Ϣ��
			
				//���и��¿�����
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
			//String[] ids=request.getParameterValues("items");//����ɾ��
			String[] ids=request.getParameterValues("inno");//
			
			Goodsinstoreitem gse=new Goodsinstoreitem();
			//System.out.println("���е�����");
			
			
			
			if(gse.Delete(ids[0]))//������ɾ��֮ǰ���¿������
			{
				GoodsStoreEvent goodsse=new GoodsStoreEvent(request.getParameter("COM_INSTOREITEM.STOREEVENTNO"));//ɾ��֮����¶�Ӧ���¼���Ϣ
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
				
				
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
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
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ����������ӵ�λ����ɾ���ӵ�λ��');";
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