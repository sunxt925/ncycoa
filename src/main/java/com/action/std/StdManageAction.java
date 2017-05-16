package com.action.std;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.Util;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.std.DocMetaInfo;
import com.entity.std.DocMetaVersionInfo;
import com.entity.std.DocOrg;
import com.entity.std.DocOrgPost;

public class StdManageAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res="";
		String action=request.getParameter("act");
		DocMetaVersionInfo stdinfo=null;
		if (action!=null && action.equals("add"))
		{
			stdinfo=new DocMetaVersionInfo();
			stdinfo.setApproveDate(request.getParameter("ApproveDate"));
			stdinfo.setApprover(request.getParameter("Approver"));
			stdinfo.setAttachDocCount(Integer.parseInt(request.getParameter("AttachDocCount")));
			stdinfo.setBelongDocNo(request.getParameter("BelongDocNo"));
			stdinfo.setBelongMode(request.getParameter("BelongMode"));
			stdinfo.setDocClassCode(request.getParameter("DocClassCode"));
			stdinfo.setDocClassName(request.getParameter("DocClassName"));
			stdinfo.setDocVersionName(request.getParameter("DocVersionName"));
			String doccode=request.getParameter("DocCode");
			stdinfo.setFlag("�½�");
			DBObject db = new DBObject();
			String sql = "select docno from STD_DOCMETAVERSIONINFO where doccode=? and BELONGDOCNO ='no'";//�жϳ�ʼ����׼�����Ƿ������ͬ�ı�׼�ĵ�����
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(doccode) };
			String sql1 = "select docno from STD_DOCMETAVERSIONINFO where doccode=? and BELONGDOCNO <>'no'";//�жϳ�ʼ����׼�����Ƿ���ĳ�����ĵ��ı�����ͬ
			Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
			{ new Parameter.String(doccode) };
			try {
				DataTable dt = db.runSelectQuery(sql, pp);
				DataTable dt1 = db.runSelectQuery(sql1, pp1);
				if(dt.getRowsCount()!=0){    //�������ı�׼�ĵ������Ѿ�����
					stdinfo.setDocCode(doccode);
					String orgcode=request.getParameter("orgcode");
					String sql2 = "select doccode from STD_DOCORG where orgcode=?";//�ж�   ��׼-������   ���Ƿ���ĵ������뱾���������
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
					{ new Parameter.String(orgcode) };
					DataTable dt2 = db.runSelectQuery(sql2, pp2);
						int flag=0;
						for(int i=0;i<dt2.getRowsCount();i++){
							if(dt2.get(0).getString("doccode").equals(doccode)){
								flag=1;//�Ѱ�
							}
						}
						if(flag==0){//δ�󶨾Ͳ���һ����
							DocOrg docorg=new DocOrg();
							docorg.setDocCode(doccode);
							docorg.setOrgCode(orgcode);
							docorg.setRelation("ֱ��");
							docorg.insert();
						}
				}else if(dt1.getRowsCount()!=0){//�������ı�׼�ĵ������븽���ĵ�������ͬ�򱨴�
					res += "alert('���ʧ�ܣ��ĵ�������ĳ����������ͬ��');";
					res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
				}else{//����ı�׼�ĵ����벻����
					DocMetaInfo docmetainfo=new DocMetaInfo();
					docmetainfo.setDocCode(doccode);
					docmetainfo.setDocName(request.getParameter("DocVersionName"));
					docmetainfo.insert();//�ĵ�������в����µ�һ��
						stdinfo.setDocCode(doccode);
						String orgcode=request.getParameter("orgcode");
						DocOrg docorg=new DocOrg();
						docorg.setDocCode(doccode);
						docorg.setOrgCode(orgcode);
						docorg.setRelation("ֱ��");
						docorg.insert();// ��׼-������     �в���һ��
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stdinfo.setDocContentType(request.getParameter("DocContentType"));
			String docno=Util.getSequence("��׼��");
			stdinfo.setDocNo(docno);
			stdinfo.setDocVersion(request.getParameter("DocVersion"));
			stdinfo.setDocVersionStatus(request.getParameter("DocVersionStatus"));
			stdinfo.setDrawUpDate(request.getParameter("DrawUpDate"));
			stdinfo.setDrawUpOrg(request.getParameter("DrawUpOrg"));
			stdinfo.setDrawUpPerson(request.getParameter("DrawUpPerson"));
			stdinfo.setMemo(request.getParameter("Memo"));
			stdinfo.setPartDocCount(Integer.parseInt(request.getParameter("PartDocCount")));
			stdinfo.setStoreFileFlag(request.getParameter("StoreFileFlag"));
			stdinfo.setTempleteFlag(request.getParameter("TempleteFlag"));
			stdinfo.setUpdateFlag(request.getParameter("UpdateFlag"));
			stdinfo.setValidBeginDate(request.getParameter("ValidBeginDate"));
			stdinfo.setValidEndDate(request.getParameter("ValidEndDate"));
			
			
			if (stdinfo.Insert())
			{
				res += "alert('��ӳɹ�');";
				//res +="var api = frameElement.api;api.close();";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			//	res += "var rand=Math.floor(Math.random()*10000);";
			//	res +="var ccm=\""+request.getParameter("orgcode")+"\";";
			//	res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
			//	res += "window.open('../std/std_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
			
			}
			else
			{
				res += "alert('���ʧ�ܣ����������ڱ����ظ������飡');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			}
		}
		else if (action!=null && action.equals("modify"))

		{
			
			stdinfo=new DocMetaVersionInfo();
			stdinfo.setApproveDate(request.getParameter("ApproveDate"));
			stdinfo.setApprover(request.getParameter("Approver"));
			stdinfo.setAttachDocCount(Integer.parseInt(request.getParameter("AttachDocCount")));
			stdinfo.setBelongDocNo(request.getParameter("BelongDocNo"));
			stdinfo.setBelongMode(request.getParameter("BelongMode"));
			stdinfo.setDocClassCode(request.getParameter("DocClassCode"));
			stdinfo.setDocClassName(request.getParameter("DocClassName"));
			stdinfo.setDocCode(request.getParameter("DocCode"));
			stdinfo.setDocContentType(request.getParameter("DocContentType"));
			stdinfo.setDocNo(request.getParameter("DocNo"));
			stdinfo.setDocVersion(request.getParameter("DocVersion"));
			stdinfo.setDocVersionName(request.getParameter("DocVersionName"));
			stdinfo.setDocVersionStatus(request.getParameter("DocVersionStatus"));
			stdinfo.setDrawUpDate(request.getParameter("DrawUpDate"));
			stdinfo.setDrawUpOrg(request.getParameter("DrawUpOrg"));
			stdinfo.setDrawUpPerson(request.getParameter("DrawUpPerson"));
			stdinfo.setMemo(request.getParameter("Memo"));
			stdinfo.setPartDocCount(Integer.parseInt(request.getParameter("PartDocCount")));
			stdinfo.setStoreFileFlag(request.getParameter("StoreFileFlag"));
			stdinfo.setTempleteFlag(request.getParameter("TempleteFlag"));
			stdinfo.setUpdateFlag(request.getParameter("UpdateFlag"));
			stdinfo.setValidBeginDate(request.getParameter("ValidBeginDate"));
			stdinfo.setValidEndDate(request.getParameter("ValidEndDate"));
			stdinfo.setFlag(request.getParameter("flag"));
			String olddoccode=request.getParameter("olddoccode");
			String newdoccode=request.getParameter("DocCode");
			if(!olddoccode.equals(newdoccode)){
				DocMetaInfo docmetainfo=new DocMetaInfo();
				docmetainfo.setDocCode(newdoccode);
				docmetainfo.setDocName(request.getParameter("DocVersionName"));
				docmetainfo.insert();//�ĵ�������в����µ�һ��
				DocOrg docorg=new DocOrg();
				docorg.setDocCode(newdoccode);
				String orgcode=request.getParameter("orgcode");
				docorg.setOrgCode(orgcode);
				docorg.setRelation("ֱ��");
				docorg.insert();// ��׼-������     �в���һ��
				DocOrgPost docorgpost=new DocOrgPost();
				DataTable dt11=docorgpost.getpostByOrgdocCode(orgcode, olddoccode);
				for(int i=0;i<dt11.getRowsCount();i++){
					String positioncode="";
					try {
						positioncode = dt11.get(i).getString("positioncode");
						System.out.println("positioncode:::::::::::::"+positioncode);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					docorgpost.setDocCode(newdoccode);
					docorgpost.setOrgCode(orgcode);
					docorgpost.setPositionCode(positioncode);
					docorgpost.setRelation("ֱ��");
					docorgpost.insert();
				}
				docorgpost.DeleteByDocCode(olddoccode);
			}
			
			if (stdinfo.Update())
			{
				res += "alert('�޸ĳɹ�');";
				  res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
			}
			else
			{
				res += "alert('�޸�ʧ�ܣ����������ڱ����ظ������飡');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			}	
		}
		else if (action!=null && action.equals("del"))
		{
		
			String[] ids=request.getParameterValues("items");
			stdinfo=new DocMetaVersionInfo();
			boolean flag=false;
			for (int i=0;i<ids.length;i++)
			{
				flag=stdinfo.Delete(ids[i]);
			}
			String del=request.getParameter("docno");
			if(flag)
			{
				res += "alert('ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../std/std_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
				res += "parent.unittree.location.reload();";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "alert('ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../std/std_list.jsp?sid='+rand+'&unitccm='+ccm,'stdlist');";
				res += "parent.unittree.location.reload();";
			}
		}
		else if (action!=null && action.equals("searchdel"))
		{
		
			/*			String[] ids=request.getParameterValues("items");
			String para="";
			for (int i=0;i<ids.length;i++)
			{
				if (i==ids.length-1)
				{
		     		para=para+ids[i];
				}
				else
				{
					para=para+ids[i]+",";
				}
			}
			System.out.println("del     :::::"+para);*/
			String del=request.getParameter("docno");
			stdinfo=new DocMetaVersionInfo();
			if(stdinfo.Delete(del))
			{
				res += "alert('ɾ���ɹ�');";

				res += "parent.unittree.location.reload();";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "alert('ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "parent.unittree.location.reload();";
			}
		}
		else if (action!=null && action.equals("up"))
		{
			
		}
		else{}
		return res;
	}
}