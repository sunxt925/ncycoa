<%@ page language="java" contentType="text/html;charset=gb2312" pageEncoding="gb2312"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=gb2312">
</head>
<body>
<script type="text/javascript">
    //�ж������ǰҳ�治Ϊ����ܣ�������ܽ�����ת
  	var tagert_URL = "<%=request.getContextPath()%>/login.jsp";
    if(self==top){
    	window.location.href = tagert_URL;
    }else{
    	top.location.href = tagert_URL;
    }
</script>
</body>
</html>