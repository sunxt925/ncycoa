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
			stdinfo.setFlag("新建");
			DBObject db = new DBObject();
			String sql = "select docno from STD_DOCMETAVERSIONINFO where doccode=? and BELONGDOCNO ='no'";//判断初始化标准表中是否存在相同的标准文档编码
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(doccode) };
			String sql1 = "select docno from STD_DOCMETAVERSIONINFO where doccode=? and BELONGDOCNO <>'no'";//判断初始化标准表中是否与某附件文档的编码相同
			Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
			{ new Parameter.String(doccode) };
			try {
				DataTable dt = db.runSelectQuery(sql, pp);
				DataTable dt1 = db.runSelectQuery(sql1, pp1);
				if(dt.getRowsCount()!=0){    //如果输入的标准文档编码已经存在
					stdinfo.setDocCode(doccode);
					String orgcode=request.getParameter("orgcode");
					String sql2 = "select doccode from STD_DOCORG where orgcode=?";//判断   标准-机构表   中是否此文档编码与本机构编码绑定
					Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
					{ new Parameter.String(orgcode) };
					DataTable dt2 = db.runSelectQuery(sql2, pp2);
						int flag=0;
						for(int i=0;i<dt2.getRowsCount();i++){
							if(dt2.get(0).getString("doccode").equals(doccode)){
								flag=1;//已绑定
							}
						}
						if(flag==0){//未绑定就插入一条绑定
							DocOrg docorg=new DocOrg();
							docorg.setDocCode(doccode);
							docorg.setOrgCode(orgcode);
							docorg.setRelation("直接");
							docorg.insert();
						}
				}else if(dt1.getRowsCount()!=0){//如果输入的标准文档编码与附件文档编码相同则报错
					res += "alert('添加失败，文档编码与某附件编码相同！');";
					res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
				}else{//输入的标准文档编码不存在
					DocMetaInfo docmetainfo=new DocMetaInfo();
					docmetainfo.setDocCode(doccode);
					docmetainfo.setDocName(request.getParameter("DocVersionName"));
					docmetainfo.insert();//文档编码表中插入新的一条
						stdinfo.setDocCode(doccode);
						String orgcode=request.getParameter("orgcode");
						DocOrg docorg=new DocOrg();
						docorg.setDocCode(doccode);
						docorg.setOrgCode(orgcode);
						docorg.setRelation("直接");
						docorg.insert();// 标准-机构表     中插入一条
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stdinfo.setDocContentType(request.getParameter("DocContentType"));
			String docno=Util.getSequence("标准类");
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
				res += "alert('添加成功');";
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
				res += "alert('添加失败，可能是由于编码重复，请检查！');";
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
				docmetainfo.insert();//文档编码表中插入新的一条
				DocOrg docorg=new DocOrg();
				docorg.setDocCode(newdoccode);
				String orgcode=request.getParameter("orgcode");
				docorg.setOrgCode(orgcode);
				docorg.setRelation("直接");
				docorg.insert();// 标准-机构表     中插入一条
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
					docorgpost.setRelation("直接");
					docorgpost.insert();
				}
				docorgpost.DeleteByDocCode(olddoccode);
			}
			
			if (stdinfo.Update())
			{
				res += "alert('修改成功');";
				  res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
			}
			else
			{
				res += "alert('修改失败，可能是由于编码重复，请检查！');";
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
				res += "alert('删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../std/std_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
				res += "parent.unittree.location.reload();";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "alert('删除失败，请与管理员联系！');";
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
				res += "alert('删除成功');";

				res += "parent.unittree.location.reload();";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "alert('删除失败，请与管理员联系！');";
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