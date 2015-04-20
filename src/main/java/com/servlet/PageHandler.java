package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.action.ActionInterface;
import com.common.Format;

@SuppressWarnings("serial")
public class PageHandler extends HttpServlet implements Servlet
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
			String ret_str = "";// 返回的JS页面处理方法
			// 获取页面的处理DAO类
			String action_class = Format.NullToBlank(request
					.getParameter("action_class"));
			if (!action_class.equals(""))
			{
				ActionInterface action = (ActionInterface) Class.forName(action_class)
						.newInstance();
				ret_str = action.getResult(request);				
			}
			else
			{
				ret_str = "alert (\"error!\");";
			}
			// 输出页面结果
			PrintWriter out = response.getWriter();
			out.print("<HTML><head>");
			out.print("<META http-equiv=\"Content-Type\" content=\"text/html; charset=GB18030\">");
			out.print("<TITLE>pagehandler</TITLE></head>");
			out.print("<script src=\"../js/public/MessageBox.js\"></script>");
			out.print("<script src=\"../js/public/Dialog.js\"></script>");
			out.print("<script src=\"../jscomponent/jquery/jquery-1.8.0.min.js\"></script>");
			out.print("<script src=\"../jscomponent/easyui/jquery.easyui.min.js\"></script>");
			out.print("<script src=\"../jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue\"></script>");
			out.print("<script>function show(res){alert(res);  } </script>");
			out.print("<body><script language='javascript'>");
			out.print(ret_str);
			out.print("</script></body></html>");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
