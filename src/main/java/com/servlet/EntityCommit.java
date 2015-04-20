package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.EntityOperation;

@SuppressWarnings("serial")
public class EntityCommit extends HttpServlet implements Servlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			response.setContentType("text/html;charset=gb2312");
			request.setCharacterEncoding("GBK");
			
			
			String action = request.getParameter("action");
			EntityOperation eo = new EntityOperation();
			String res = "";
			
			
			if(action.equals("add"))
			{
			  System.out.print(action);
			  res = eo.Add(request);
			}	
			
			else if(action.equals("update"))
			{
				res = eo.Update(request);
			}
			
			System.out.print(res);
	
			// 输出页面结果
			PrintWriter out = response.getWriter();
			out.print("<HTML><head>");
			out
					.print("<META http-equiv=\"Content-Type\" content=\"text/html; charset=GB18030\">");
			out.print("<TITLE>entitycommit</TITLE></head>");
			out.print("<script src=\"../js/public/MessageBox.js\"></script>");
			out.print("<script src=\"../js/public/Dialog.js\"></script>");
			out.print("<body><script language='javascript'>");
			//out.print("alert('hehe');");
			out.print("MessageBox.Show(null,'"+res+"！',null,'LogOK',null,1,'"+res+"');");
			out.print("window.close();");
			out.print("window.dialogArguments.window.location = window.dialogArguments.window.location;");
			out.print("</script></body></html>");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
