<%@page import="com.entity.index.StaffAllMerit"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
    String str="";
    if(request.getParameter("class").equals("c")){//��˾��Ч
    	str=new StaffAllMerit().getMeritJson(request.getParameter("year"),request.getParameter("month"),request.getParameter("class"));
    }else if(request.getParameter("class").equals("d")){//���ż�Ч
    	str=new StaffAllMerit().getMeritJson(request.getParameter("year"),request.getParameter("month"),request.getParameter("class"),request.getParameter("company"));
    }else if(request.getParameter("class").equals("s")){//Ա����Ч
    	str=new StaffAllMerit().getMeritJson(request.getParameter("year"),request.getParameter("month"),request.getParameter("class"),request.getParameter("company"),request.getParameter("depart"));
    }else if(request.getParameter("class").equals("individual")){//���˼�Ч
    	UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
    	str=new StaffAllMerit().getIndividualMeritJson(request.getParameter("year"),request.getParameter("start_month"),request.getParameter("end_month"),u.getStaffcode());
    }else if(request.getParameter("class").equals("admin_d")){//��Ͻ���ŵ����Ŷ��¼�Ч
    	str=new StaffAllMerit().getDepartMeritJson(request.getParameter("year"),request.getParameter("start_month"),request.getParameter("end_month"),request.getParameter("companycode"),request.getParameter("objectcode"));
        
    }else if(request.getParameter("class").equals("admin_s")){//��Ͻ��Ա���˶��¼�Ч
    	str=new StaffAllMerit().getIndividualMeritJson(request.getParameter("year"),request.getParameter("start_month"),request.getParameter("end_month"),request.getParameter("objectcode"));    
    }
	response.getWriter().write(str);
	response.getWriter().flush();
	response.getWriter().close();
 %>
