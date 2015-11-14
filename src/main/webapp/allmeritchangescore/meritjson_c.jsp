<%@page import="com.entity.index.StaffAllMerit"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    String str="";
    if(request.getParameter("class").equals("c")){//公司绩效
    	str=new StaffAllMerit().getMeritJson(request.getParameter("year"),request.getParameter("month"),request.getParameter("class"));
    }else if(request.getParameter("class").equals("d")){//部门绩效
    	str=new StaffAllMerit().getMeritJson(request.getParameter("year"),request.getParameter("month"),request.getParameter("class"),request.getParameter("company"));
    }else if(request.getParameter("class").equals("s")){//员工绩效
    	str=new StaffAllMerit().getMeritJson(request.getParameter("year"),request.getParameter("month"),request.getParameter("class"),request.getParameter("company"),request.getParameter("depart"));
    }else if(request.getParameter("class").equals("individual")){//个人绩效
    	UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    	str=new StaffAllMerit().getIndividualMeritJson(request.getParameter("year"),request.getParameter("start_month"),request.getParameter("end_month"),u.getStaffcode());
    }else if(request.getParameter("class").equals("admin_d")){//所辖部门单部门多月绩效
    	str=new StaffAllMerit().getDepartMeritJson(request.getParameter("year"),request.getParameter("start_month"),request.getParameter("end_month"),request.getParameter("companycode"),request.getParameter("objectcode"));
        
    }else if(request.getParameter("class").equals("admin_s")){//所辖人员单人多月绩效
    	str=new StaffAllMerit().getIndividualMeritJson(request.getParameter("year"),request.getParameter("start_month"),request.getParameter("end_month"),request.getParameter("objectcode"));    
    }
	response.getWriter().write(str);
	response.getWriter().flush();
	response.getWriter().close();
 %>
