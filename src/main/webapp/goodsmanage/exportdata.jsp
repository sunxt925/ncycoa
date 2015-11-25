<%@page import="com.entity.system.GoodsInQuery"%>
<%@page import="com.entity.system.Goodsinstoreitem"%>
<%@page import="com.entity.system.GoodsOutQuery"%>
<%@page import="edu.cqu.ncycoa.util.ExportExcel"%>
<%@page import="java.io.OutputStream"%>
<%@page import="com.common.Util"%>
<%@page import="com.entity.system.Goodsoutstoreitem"%>
<%@page import="java.util.List"%>
<%@page contentType="application/html;charset=gb2312" language="java"  errorPage="" %>
<%
response.setContentType("multipart/form-data"); 

response.setHeader("Content-Disposition", "attachment;fileName="+Util.getName()+".xls"); 
OutputStream os=response.getOutputStream();  
String storeEventNo = request.getParameter("storeEventNo");
String flag = request.getParameter("flag");
if(flag.equals("out")){
	List<Goodsoutstoreitem> goods = Goodsoutstoreitem.getGoodsoutstoreitemsByOutNo(storeEventNo);

	try {

		
		String[] headers = {"序号","名称","规格型号","领用数量","剩余数量","购置日期","领用日期","领用人","备注"}; 
		ExportExcel<GoodsOutQuery> excel = new ExportExcel<GoodsOutQuery>();
		excel.exportExcel(headers, GoodsOutQuery.getGoodsOutQuerys(goods), os);
	} finally{
		if(os!=null){
			os.close();
		}
	}
}else{

	List<Goodsinstoreitem> goods = Goodsinstoreitem.getGoodsinstoreitemsByInNo(storeEventNo);

	try {

		
		String[] headers = {"序号","名称","规格型号","入库数量","剩余数量","购置日期","入库日期","处理人","备注"}; 
		ExportExcel<GoodsInQuery> excel = new ExportExcel<GoodsInQuery>();
		excel.exportExcel(headers, GoodsInQuery.getGoodsInQuerys(goods), os);
	} finally{
		if(os!=null){
			os.close();
		}
	}
}

response.flushBuffer();
out.clear();
out =pageContext.pushBody();
%>