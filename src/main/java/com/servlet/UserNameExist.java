package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.entity.system.StaffInfo;

@SuppressWarnings("serial")
public class UserNameExist extends HttpServlet implements Servlet
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
			StaffInfo staffinfo = new StaffInfo();
			PrintWriter out = response.getWriter();
			
			String staffname = request.getParameter("staffname");
			
		
			int nameCount = staffinfo.isNameExist(staffname);
			
			if(nameCount == 1)
			{
			  // out.write("yes");
			   staffinfo.setByStaffname(staffname);
			   JSONObject json = new JSONObject();
			   if(json != null)
			   {
				   json.put("onlyone", "true");
				   json.put("idcard", staffinfo.getIdcard());
				   json.put("staffcode", staffinfo.getCode());
				   json.put("staffname", staffinfo.getName());
				   json.put("gender", staffinfo.getGender());
				   json.put("brithday", staffinfo.getBirthday());
				   json.put("nationalitycode", staffinfo.getNationalitycode());
				   json.put("nationality", staffinfo.getNationality());
				   json.put("nativeplace", staffinfo.getNativeplace());
				   json.put("marriage", staffinfo.getMarriage());
				   json.put("salarylevel", staffinfo.getSalarylevel());
				   json.put("begincareerdate", staffinfo.getBegincareerdate());
				   json.put("email", staffinfo.getEmail());
				   json.put("qq", staffinfo.getQq());
				   json.put("mobilephone", staffinfo.getMobilephone());
				   json.put("officephone", staffinfo.getOfficephone());
				   json.put("homephone", staffinfo.getHomephone());
				   json.put("homeaddress", staffinfo.getHomeaddress());
				   out.write(json.toString());
			   }
			}
			else if(nameCount > 1){
				JSONObject json = new JSONObject();
				if(json != null){
					json.put("onlyone", "false");
					json.put("staffname", staffinfo.getName());
					out.write(json.toString());
				}
				                 
			}
			else
			{
			   out.write("no");
			}
			
			
			

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

