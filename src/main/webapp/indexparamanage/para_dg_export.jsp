<%@ page contentType="application/vnd.ms-excel;charset=gb2312" language="java" errorPage=""%>
<%@ page import="com.performance.ParaDataHelper"%>
<%@ page import="com.entity.system.UserInfo"%>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="com.entity.index.Indexitem,net.sf.json.JSONObject"%>

<%
	response.setContentType("application/vnd.ms-excel;charset=gb2312");
	String codedFileName = null;
	OutputStream fOut = null;
	try {
		fOut = response.getOutputStream();
		JSONObject obj = JSONObject.fromObject(request.getParameter("d"));
		Indexitem item = new Indexitem(obj.getString("indexcode"));
		
		codedFileName = item.getIndexName() + "_" +ParaDataHelper.code2Name(obj.getString("periodcode")) + "_参数数据_" + java.text.DateFormat.getDateInstance().format(new java.util.Date());
		String newtitle = new String(codedFileName.getBytes("gb2312"), "ISO8859-1");
		response.setHeader("content-disposition", "attachment;filename=" + newtitle + ".xls");
		
		// 产生工作簿对象
		HSSFWorkbook workbook = ParaDataHelper.exportXls(obj);
		fOut = response.getOutputStream();
		workbook.write(fOut);
		
		fOut.flush();  
		fOut.close();  
		fOut=null;  
		response.flushBuffer();  
		out.clear();  
		out = pageContext.pushBody();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
