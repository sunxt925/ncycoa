package com.action.stdapply;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.Util;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.std.DocMetaInfo;
import com.entity.stdapply.DocReviseInifo;

public class StdManageAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res="";
		String action=request.getParameter("act");
		DocReviseInifo stdinfo=null;
		if (action!=null && action.equals("add"))
		{
			stdinfo=new DocReviseInifo();
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
			String sql = "select docno from STD_DOCMETAVERSIONINFO where doccode=? and BELONGDOCNO ='no'";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(doccode) };
			String sql1 = "select docno from STD_DOCMETAVERSIONINFO where doccode=? and BELONGDOCNO <>'no'";
			Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
			{ new Parameter.String(doccode) };
			try {
				DataTable dt = db.runSelectQuery(sql, pp);
				DataTable dt1 = db.runSelectQuery(sql1, pp1);
				if(dt.getRowsCount()!=0){
					stdinfo.setDocCode(doccode);
				}else if(dt1.getRowsCount()!=0){
					res += "MessageBox.Show(null,'添加失败！',null,'LogOK','Error',1,'添加失败，文档编码与某附件编码相同！');";
					res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
				}else{
					DocMetaInfo docmetainfo=new DocMetaInfo();
					docmetainfo.setDocCode(doccode);
					docmetainfo.setDocName(request.getParameter("DocVersionName"));
					docmetainfo.insert();
						stdinfo.setDocCode(doccode);
				}
			}catch (Exception e) {
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
			stdinfo.setApplyId(request.getParameter("applyid"));
			stdinfo.setSubmitFlagString("no");
			
			if (stdinfo.Insert())
			{
				res += "MessageBox.Show(null,'添加成功！',null,'LogOK',null,1,'添加成功');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			
			}
			else
			{
				res += "MessageBox.Show(null,'添加失败！',null,'LogOK','Error',1,'添加失败，可能是由于编码重复，请检查！');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			}
		}
		else if (action!=null && action.equals("modify"))

		{
			
			stdinfo=new DocReviseInifo();
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
			stdinfo.setApplyId(request.getParameter("applyid"));
			stdinfo.setSubmitFlagString("no");
			stdinfo.setFlag(request.getParameter("flag"));
			
			if (stdinfo.Update())
			{
				res += "MessageBox.Show(null,'修改成功！',null,'LogOK',null,1,'修改成功');";
				  res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
			}
			else
			{
				res += "MessageBox.Show(null,'修改失败！',null,'LogOK','Error',1,'修改失败，可能是由于编码重复，请检查！');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			}	
		}
		else if (action!=null && action.equals("del"))
		{
	
			String del=request.getParameter("docno");
			System.out.println("del    :"+del);
			stdinfo=new DocReviseInifo();
			if(stdinfo.Delete(del))
			{
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var applyid=\""+request.getParameter("applyid")+"\";";
				res += "window.open('../stdapply/std_list.jsp?sid='+rand+'&applyid='+applyid,'_self');";
				res += "parent.unittree.location.reload();";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var applyid=\""+request.getParameter("applyid")+"\";";
				res += "window.open('../stdapply/std_list.jsp?sid='+rand+'&applyid='+applyid,'_self');";
				res += "parent.unittree.location.reload();";
			}
		}
		else if (action!=null && action.equals("appdel"))
		{
	
			String del=request.getParameter("docno");
			System.out.println("del    :"+del);
			stdinfo=new DocReviseInifo();
			if(stdinfo.Delete(del))
			{
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var applyid=\""+request.getParameter("applyid")+"\";";
				res += "window.open('/ncycoa/stdapply/std_list.jsp?sid='+rand+'&applyid='+applyid,'_self');";
				res += "parent.unittree.location.reload();";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var applyid=\""+request.getParameter("applyid")+"\";";
				res += "window.open('/ncycoa/stdapply/std_list.jsp?sid='+rand+'&applyid='+applyid,'_self');";
				res += "parent.unittree.location.reload();";
			}
		}
		else
		{

		}
		return res;
	}
}