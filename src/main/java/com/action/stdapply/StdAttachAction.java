package com.action.stdapply;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.Format;
import com.common.Util;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.std.DocMetaInfo;
import com.entity.std.DocMetaVersionInfo;
import com.entity.stdapply.DocReviseInifo;

public class StdAttachAction  extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res="";
		String action=request.getParameter("act");
		String DocNo = request.getParameter("DocNo");
		String DocClassCode = request.getParameter("DocClassCode");
		String BelongDocNo = request.getParameter("BelongDocNo");
        int AttachDocCount  = Integer.parseInt(Format.NullToZero(request.getParameter("AttachDocCount")));
        int PartDocCount  = Integer.parseInt(Format.NullToZero(request.getParameter("PartDocCount")));

        String DocCode = request.getParameter("DocCode");
		String DocVersionName = request.getParameter("DocVersionName");
		String DocVersion = request.getParameter("DocVersion");
		String DocVersionStatus = request.getParameter("DocVersionStatus");
		String UpdateFlag = request.getParameter("UpdateFlag");
		String BelongMode = request.getParameter("BelongMode");
		String DocContentType = request.getParameter("DocContentType");
		String TempleteFlag = request.getParameter("TempleteFlag");
		String StoreFileFlag = request.getParameter("StoreFileFlag");
	    String DrawUpPerson = request.getParameter("DrawUpPerson");
	    String DrawUpOrg = request.getParameter("DrawUpOrg");
		String DrawUpDate = request.getParameter("DrawUpDate");
		String ApproveDate = request.getParameter("ApproveDate");
		String Approver = request.getParameter("Approver");
		String ValidBeginDate= request.getParameter("ValidBeginDate");
		String ValidEndDate = request.getParameter("ValidEndDate");
		String Memo = request.getParameter("Memo");
	

		
		
		
		
		
		DocReviseInifo attachInfo=null;
		boolean flag = false;
		if (action!=null && action.equals("add"))
		{
			DBObject db = new DBObject();
			String sql = "select doccode from STD_DOCMETAVERSIONINFO where doccode=? and belongdocno ='no'";//主文档
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(DocCode) };
			
			String attachsql = "select doccode,docversion from STD_DOCMETAVERSIONINFO where doccode=? and belongdocno = ?";//主文档
			Parameter.SqlParameter[] pp2 = new Parameter.SqlParameter[]
			{ new Parameter.String(DocCode), new Parameter.String(BelongDocNo)};
			try {
				DataTable dt = db.runSelectQuery(sql, pp);
				DataTable attachdt = db.runSelectQuery(attachsql, pp2);
				if(dt.getRowsCount()!=0){//如果附件的编码和某一主文档编码相同，则判断已存在
					res += "show('添加失败，附件编码与某一主文档冲突！');";
					res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
				}else if(attachdt.getRowsCount()!=0){
					    if(attachdt.get(0).get(1).toString().equals(DocVersion))
					    {
					    	res += "show('添加失败，该文档该编码的附件已存在！');";
							res +="window.close();";
							res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
							  res += "parent.unittree.location.reload();";
					    }else {
					    	flag = true;
						}
				}else {
					DocMetaInfo docMetaInfo = new DocMetaInfo();
					DataTable dt2 = docMetaInfo.getMetaList(DocCode);
					if (dt2.getRowsCount()!=0) {
						 flag=true;
					}else {
						DocMetaInfo newDocMetaInfo = new DocMetaInfo();
						newDocMetaInfo.setDocCode(DocCode);
						newDocMetaInfo.setDocName(DocVersionName);
						if (newDocMetaInfo.insert()) {
							flag = true;
						}
					}
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag){
				attachInfo = new DocReviseInifo();
				attachInfo.setDocNo(Util.getSequence("标准类"));
			
				attachInfo.setDocCode(DocCode);
		    	attachInfo.setBelongDocNo(BelongDocNo);
		    	attachInfo.setDocVersion(DocVersion);
		    	attachInfo.setApproveDate(ApproveDate);
		    	attachInfo.setApprover(Approver);
		    	attachInfo.setAttachDocCount(AttachDocCount);
		    	attachInfo.setBelongMode(BelongMode);
		    	attachInfo.setDocClassCode(DocClassCode);
		    	attachInfo.setDocContentType(DocContentType);
		    	attachInfo.setDocVersion(DocVersion);
		    	attachInfo.setDocVersionName(DocVersionName);
		    	attachInfo.setDocVersionStatus(DocVersionStatus);
		    	attachInfo.setDrawUpDate(DrawUpDate);
		    	attachInfo.setDrawUpOrg(DrawUpOrg);
		    	attachInfo.setDrawUpPerson(DrawUpPerson);
		    	attachInfo.setMemo(Memo);
		    	attachInfo.setPartDocCount(PartDocCount);
		    	
		    	attachInfo.setTempleteFlag(TempleteFlag);
		    	attachInfo.setUpdateFlag(UpdateFlag);
		    	attachInfo.setValidBeginDate(ValidBeginDate);
		    	attachInfo.setValidEndDate(ValidEndDate);
		    	attachInfo.setStoreFileFlag(StoreFileFlag);
		    	attachInfo.setApplyId("");
		    	attachInfo.setSubmitFlagString("no");
		        
				if (attachInfo.Insert())
				{
					res += "show('添加成功');";
					res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
					//res += "window.open('../xtwh/system_unit/unit_list.jsp?sid='+rand+'&unitccm=001','_self');";
					//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
				   //rd.forward(request,response);
				}
				else
				{
					res += "show('添加失败，可能是由于编码重复，请检查！');";
					res +="window.close();";
					res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
					  res += "parent.unittree.location.reload();";
				}
			    
			}
		}
		else if (action!=null && action.equals("modify"))

		{
		
			attachInfo = new DocReviseInifo();
            attachInfo.setDocNo(DocNo);
            attachInfo.setDocCode(DocCode);
	    	attachInfo.setBelongDocNo(BelongDocNo);
	    	attachInfo.setDocVersion(DocVersion);
	    	attachInfo.setApproveDate(ApproveDate);
	    	attachInfo.setApprover(Approver);
	    	attachInfo.setAttachDocCount(AttachDocCount);
	    	attachInfo.setBelongMode(BelongMode);
	    	attachInfo.setDocClassCode(DocClassCode);
	    	attachInfo.setDocContentType(DocContentType);
	    	attachInfo.setDocVersion(DocVersion);
	    	attachInfo.setDocVersionName(DocVersionName);
	    	attachInfo.setDocVersionStatus(DocVersionStatus);
	    	attachInfo.setDrawUpDate(DrawUpDate);
	    	attachInfo.setDrawUpOrg(DrawUpOrg);
	    	attachInfo.setDrawUpPerson(DrawUpPerson);
	    	attachInfo.setMemo(Memo);
	    	attachInfo.setPartDocCount(PartDocCount);
	    	
	    	attachInfo.setTempleteFlag(TempleteFlag);
	    	attachInfo.setUpdateFlag(UpdateFlag);
	    	attachInfo.setValidBeginDate(ValidBeginDate);
	    	attachInfo.setValidEndDate(ValidEndDate);
	    	attachInfo.setStoreFileFlag(StoreFileFlag);
			
			if (attachInfo.Update())
			{
				res += "show('修改成功');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			    //RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "show('修改失败，可能是由于编码重复，请检查！');";
				res +="window.close();";
				res +="window.dialogArguments.window.location = window.dialogArguments.window.location;";
				  res += "parent.unittree.location.reload();";
			}	}
		else if (action!=null && action.equals("del"))
		{
			String del=request.getParameter("docno1");
			attachInfo=new DocReviseInifo();
			if(attachInfo.Delete(del))
			{
				res += "show('删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var  belongNo=\""+request.getParameter("docno")+"\";";
				res += "window.open('../stdapply/std_attachedfile.jsp?sid='+rand+'&docNo='+belongNo,'_self');";
				res += "parent.unittree.location.reload();";
				
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "show('删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var  belongNo=\""+request.getParameter("docno")+"\";";
				res += "window.open('../stdapply/std_attachedfile.jsp?sid='+rand+'&docNo='+belongNo,'_self');";
			}
		}
		else if (action!=null && action.equals("appdel"))
		{
			String del=request.getParameter("docno1");
			attachInfo=new DocReviseInifo();
			if(attachInfo.Delete(del))
			{
				res += "show('删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var  belongNo=\""+request.getParameter("docno")+"\";";//
				res +="var  docversionname=\""+request.getParameter("docversionname")+"\";";
				res += "window.open('/ncycoa/stdapply/std_appattachfile.jsp?sid='+rand+'&docNo='+belongNo+'&docversionname='+docversionname,'_self');";
				res += "parent.unittree.location.reload();";
				
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "show('删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var  belongNo=\""+request.getParameter("docno")+"\";";
				res +="var  docversionname=\""+request.getParameter("docversionname")+"\";";
				res += "window.open('/ncycoa/stdapply/std_appattachfile.jsp?sid='+rand+'&docNo='+belongNo+'&docversionname='+docversionname,'_self');";
			}
		}
		else{

		}
		return res;
	}
}
