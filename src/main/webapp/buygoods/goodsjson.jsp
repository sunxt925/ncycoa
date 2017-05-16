<%@page import="com.business.BuyGoodsItem"%>
<%@page import="com.common.Format"%>
<%@page contentType="application/json;charset=gb2312" language="java"  errorPage="" %>
<%
	response.setContentType("application/json;charset=gb2312");
   
    if(request.getParameter("eventno").equals("null")){
    	response.getWriter().write(new BuyGoodsItem().getBuygoodsItemJson(Integer.parseInt(request.getParameter("flag")),"null"));
        
    }else{
    	response.getWriter().write(new BuyGoodsItem().getBuygoodsItemJson(Integer.parseInt(request.getParameter("flag"))));
    }
	response.getWriter().flush();
	response.getWriter().close();
 %>
