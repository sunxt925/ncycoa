<%@page import="edu.cqu.ncycoa.target.service.ResultService"%>
<%@page import="com.dao.system.MeritAnalysisDao"%>
<%@page import="com.entity.system.OrgPosition"%>
<%@page import="com.common.PageUtil"%>
<%@page import="com.common.TableUtil"%>
<%@page import="com.common.Format"%>
<%@page import="com.entity.system.UserInfo"%>
<%@page import="com.db.DataTable"%>
<%@page import="com.entity.index.Indexitem"%>
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
                  request.getServerName()+":"+
		          request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="gb2312">
<title>�Ĵ��ϳ��̲�ר��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/jscomponent/easyui/themes/icon.css">

<script type="text/javascript" src="<%=path%>/jscomponent/jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/jscomponent/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="<%=path%>/jscomponent/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
</head>
<%
  UserInfo u=(UserInfo)request.getSession().getAttribute("UserInfo");
  String archcode = request.getParameter("archcode");
  String objcode = request.getParameter("objcode");
  String startyear ="1";
   String endyear = "1";
 // String startyear = request.getParameter("startyear");
 // String endyear = request.getParameter("endyear");
  String season=request.getParameter("season");
  String result="";
  if(request.getParameter("type").equals("t")){
	  result=ResultService.getDataByYears(archcode,objcode,startyear,endyear,season);
  }else{
	  result=ResultService.getDataBySeasons(archcode,objcode,startyear,endyear);
  }
  System.out.println(result);
  String names=result.split(";")[0];
  String scores=result.split(";")[1];
 %>
 <body>
    <div id="main" style="height:400px;"></div>
    <script src="../js/echarts-2.2.2/build/dist/echarts-all.js"></script>
    <script>
        var namestring="<%=names%>";
        var names=new Array();
        names=namestring.split(",");
        var scorestring="<%=scores%>";
        var scores=new Array();
        scores=scorestring.split(",");
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
        			    title : {
        			        text: 'Ŀ��÷�չʾ',
        			        subtext: '�ϳ��̲�'
        			    },
        			    tooltip : {
        			        trigger: 'axis'
        			    },
        			    legend: {
							data:['Ŀ����ϵ�ܷ�ͬ��չʾ']
        			    //data:names
        			    },
        			    toolbox: {
        			        show : true,
        			        feature : {
        			           // mark : {show: true},
        			            //dataView : {show: true, readOnly: false},
        			            magicType : {show: true, type: ['line', 'bar']},
        			            restore : {show: true},
        			            saveAsImage : {show: true}
        			        }
        			    },
        			    calculable : true,
        			    xAxis : [
        			        {
        			            type : 'category',
        			           // data : ['1��','2��','3��','4��','5��','6��','7��','8��','9��','10��','11��','12��']
							   data: names
        			        }
        			    ],
        			    yAxis : [
        			        {
        			            type : 'value'
        			        }
        			    ],
        			    series : [
        			        {
        			            name:'�ܵ÷�',
        			            type:'bar',
        			            //data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
								data:scores,
        			            markPoint : {
        			                data : [
        			                    {type : 'max', name: '���ֵ'},
        			                    {type : 'min', name: '��Сֵ'}
        			                ]
        			            }
        			           
        			        },
        			        /*{
        			            name:'��ˮ��',
        			            type:'bar',
        			            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
        			            markPoint : {
        			                data : [
        			                    {name : '�����', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
        			                    {name : '�����', value : 2.3, xAxis: 11, yAxis: 3}
        			                ]
        			            },
        			            markLine : {
        			                data : [
        			                    {type : 'average', name : 'ƽ��ֵ'}
        			                ]
        			            }
        			        }*/
        			    ]
        }
        myChart.setOption(option);
    </script>
</body>
</html>
