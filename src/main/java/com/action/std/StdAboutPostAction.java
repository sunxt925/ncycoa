package com.action.std;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.db.DataTable;
import com.entity.std.DocOrg;
import com.entity.std.DocOrgPost;
import com.entity.system.OrgPosition;

public class StdAboutPostAction extends ActionInterface {
	public String getResult(HttpServletRequest request)
	{
		String res="";
		String action=request.getParameter("act");
		DocOrgPost docorgpost=null;
		if (action!=null && action.equals("add"))
		{
			String orgcode=request.getParameter("orgcode");
			String doccode=request.getParameter("doccode");
			DocOrgPost docorgpost2=new DocOrgPost();
			DataTable dt=docorgpost2.getpostByOrgdocCode(orgcode,doccode);
			String[] postcodes=request.getParameterValues("items");
			docorgpost=new DocOrgPost();
			boolean flag=true,f=true;
			for (int i=0;i<postcodes.length;i++)
			{
				flag=true;
				for(int j=0;j<dt.getRowsCount();j++){
					String post="";
					try {
						post = dt.get(j).get(0).toString();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(post.equals(postcodes[i])){
						flag=false;
					}
				}
				if(flag){
					String all=request.getParameter("all");
					if(all.equals("all")){//从全局添加
						OrgPosition orgPosition=new OrgPosition();
						DataTable dataTable=orgPosition.getOrgcodebyPost(postcodes[i]);
						if(dataTable.getRowsCount()>0){
						try {
							String orgcodeall=dataTable.get(0).get(0).toString();
							DocOrg docorg=new DocOrg();
							docorg.setDocCode(doccode);
							docorg.setOrgCode(orgcodeall);
							docorg.setRelation("间接");
							docorg.insert();
							
							docorgpost.setDocCode(doccode);
							docorgpost.setOrgCode(orgcodeall);
							docorgpost.setPositionCode(postcodes[i]);
							docorgpost.setRelation("直接");
							docorgpost.insert();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					}
					else{
						docorgpost.setDocCode(doccode);
						docorgpost.setOrgCode(orgcode);
						docorgpost.setPositionCode(postcodes[i]);
						docorgpost.setRelation("直接");
						docorgpost.insert();
					}
				}else{
						f=false;
				}
			}

			if (f)
			{
				res += "show('全部添加成功');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
				//res += "window.open('../xtwh/system_unit/unit_list.jsp?sid='+rand+'&unitccm=001','_self');";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
				//rd.forward(request,response);
			}else
			{
				res += "show('部分添加成功，部分岗位已存在');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			}
		}
		else if (action!=null && action.equals("del"))
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
			}*/
			String del=request.getParameter("recid");
			String orgcode=request.getParameter("orgcode");
			docorgpost=new DocOrgPost();
			if(docorgpost.Delete(del,orgcode))
			{
				res += "show('删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("docno")+"\";";
				res +="var versionname=\""+request.getParameter("versionname")+"\";";
				res +="var orgcode=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../std/std_aboutpost.jsp?docno='+ccm+'&docversionname='+versionname+'&orgcode='+orgcode,'_self');";
				res += "parent.unittree.location.reload();";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "show('删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var ccm=\""+request.getParameter("docno")+"\";";
				res +="var versionname=\""+request.getParameter("versionname")+"\";";
				res +="var orgcode=\""+request.getParameter("orgcode")+"\";";
				res += "window.open('../std/std_aboutpost.jsp?docno='+ccm+'&docversionname='+versionname+'&orgcode='+orgcode,'_self');";
			}
		}
		else if (action!=null && action.equals("addfromall"))
		{
		}
		return res;
	}
}
