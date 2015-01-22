<%@ page language="java" contentType="text/html;charset=gb2312" pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
</head>
<body>
<script type="text/javascript">
    //判断如果当前页面不为主框架，则将主框架进行跳转
  	var tagert_URL = "<%=request.getContextPath()%>/login.jsp";
    if(self==top){
    	window.location.href = tagert_URL;
    }else{
    	top.location.href = tagert_URL;
    }
</script>
</body>
</html>