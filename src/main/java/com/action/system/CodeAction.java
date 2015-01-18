package com.action.system;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;
import com.common.ExecuteResult;
import com.common.Format;
import com.entity.system.Code;
import com.entity.system.WebFlowCode;

public class CodeAction extends ActionInterface
{

	public String getResult(HttpServletRequest request)
	{

		//String res = "";
		String res0 ="";
		//String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		
		try
		{
			request.setCharacterEncoding("gb2312");
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String res = "";
		String action = request.getParameter("act");
		// String action=request.toString();
		Code co = null;
		if (action != null && action.equals("add"))
		{
			String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
		
			res0 = eo.Add(request);
		
			//初始化公共编码
            if(Format.NullToBlank(request.getParameter("SYSTEM_TABLECODEMETA.CODE_CLASS")).equals("00020001")){
           
            Code.initialCode(request);
            }


				res += "show('"+res0+"');";
				res += "window.close();";
		}
		else if (action != null && action.equals("modify"))
		{
			
			
			  String entity=request.getParameter("entity");
				
				eo.setEntity(entity);
				res0 = eo.Update(request);	

				res += "show('"+res0+"');";
				res += "window.close();";
		}
		else if(action!=null&&action.equals("synchronous")){
			//同步流程引擎编码字典
		    WebFlowCode webFlowCode=new WebFlowCode();
			if (webFlowCode.Syn())
			{
				//res += "<script language='javascript'>";
				res += "MessageBox.Show(null,'同步成功！',null,'LogOK',null,1,'同步成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/codemanage.jsp?sid='+rand,'_self');";
				//res +="window.location.reload();";
				//res += "</script>";
			}
			else
			{
				//res += "<script language='javascript'>";
				res += "MessageBox.Show(null,'同步失败！',null,'LogOK','Error',1,'同步失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				//res +="window.location.reload();";
				res += "window.open('../xtwh/code/codemanage.jsp?sid='+rand,'_self');";
			//	res += "</script>";
			}
		}
		
		else if (action != null && action.equals("del"))
		{
			String[] ids = request.getParameterValues("items");
			String para = "";
			for (int i = 0; i < ids.length; i++)
			{
				if (i == ids.length - 1)
				{
					para = para + ids[i];
				}
				else
				{
					para = para + ids[i] + ",";
				}
			}
			co = new Code();
			if (co.Delete(para))
			{
				//res += "<script language='javascript'>";
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/codemanage.jsp?sid='+rand,'_self');";
				//res +="window.location.reload();";
				//res += "</script>";
			}
			else
			{
				//res += "<script language='javascript'>";
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				//res +="window.location.reload();";
				res += "window.open('../xtwh/code/codemanage.jsp?sid='+rand,'_self');";
			//	res += "</script>";
			}
		}
		else if (action != null && action.equals("load"))
		{
			String[] ids = request.getParameterValues("items");
			String para = "";
			for (int i = 0; i < ids.length; i++)
			{
				if (i == ids.length - 1)
				{
					para = para + ids[i];
				}
				else
				{
					para = para + ids[i] + ",";
				}
			}
			co = new Code();
			// System.out.print("======================"+para);
			if (co.Load(para))
			{
				//res += "<script language='javascript'>";
				res += "MessageBox.Show(null,'加载成功！',null,'LogOK',null,1,'加载成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/codemanage.jsp?sid='+rand,'_self');";
				//res += "</script>";
			}
			else
			{
				//res += "<script language='javascript'>";
				res += "MessageBox.Show(null,'加载失败！',null,'LogOK','Error',1,'加载失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/codemanage.jsp?sid='+rand,'_self');";
				//res += "</script>";
			}
		}
		
		else if(action.equals("addcolumn"))
		{
           String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Add(request);
			res += "show('"+res0+"');";
			if(res0.equals("插入成功"))
			{
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/code_list.jsp?code_id="
					+ Format.NullToBlank(request.getParameter("SYSTEM_TABLECODEMETA_COL.PCODE_ID"))
					+ "&table_name="
					+ Format.NullToBlank(request.getParameter("SYSTEM_TABLECODEMETA_COL.TABLE_NAME"))
					+ "&sid='+rand,'_self');";
			}
			else {
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/code_column_new.jsp?sid='+rand,'_self');";
			}

			
		}
		
		else if (action.equals("modifycolumn"))
		{
			Code code = new Code();
			
			String table_name = Format.NullToBlank(request
					.getParameter("table_name"));
			code.setTable_name(table_name);
			ExecuteResult er = null;
			er = code.UpdateColumn(request);
			if (er.isRes())
			{
				res += "MessageBox.Show(null,'修改化成功！',null,'LogOK',null,1,'修改成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/code_list.jsp?table_name="
						+ table_name + "&sid='+rand,'_self');";
				res += "window.parent.codetree.location.reload();";
			}
			else
			{
				res += "MessageBox.Show(null,'修改化失败！',null,'LogOK','Error',1,'"
						+ er.getRes_str() + "');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/code_list.jsp?table_name="
					+ table_name + "&sid='+rand,'_self');";
			    res += "parent.codetree.location.reload();";
			}
		}
		else if(action.equals("deletecolumn")){
			String[] ids = request.getParameterValues("items");
			String  para2=Format.NullToBlank(request
					.getParameter("table_name"));
			String code_id=Format.NullToBlank(request
					.getParameter("pcode_id"));
			String para = "";
			for (int i = 0; i < ids.length; i++)
			{
				if (i == ids.length - 1)
				{
					para = para + ids[i];
				}
				else
				{
					para = para + ids[i] + ",";
				}
			}
			co = new Code();
			if (co.DeleteCol(para2,para))
			{
				//res += "<script language='javascript'>";
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/code_list.jsp?code_id="+code_id+"&table_name="+para2+"&sid='+rand,'_self');";
				res += "parent.codetree.location.reload();";
				//res += "</script>";
			}
			else
			{
				//res += "<script language='javascript'>";
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/code/code_list.jsp?code_id="+code_id+"&table_name="+para2+"&sid='+rand,'_self');";
				res += "parent.codetree.location.reload();";
				//res += "</script>";
			}
		}
		else
		{

		}
		
		return res;
	}
}
