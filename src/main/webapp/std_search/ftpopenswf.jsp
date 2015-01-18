<%@ page language="java" import="java.util.*,com.ftp.*,com.entity.ftp.*,java.io.*" pageEncoding="gb2312"%>
<%
request.setCharacterEncoding("gbk");
    String storefileno=request.getParameter("storefileno");
    String path = getServletContext().getRealPath("/")+"doc";

	FtpStoreFile file=new FtpStoreFile(storefileno);
	String contenttype=file.getFilecontenttype();
	String tittle=file.getFilename();
			FtpStore ftp=new FtpStore();
    	String frand_name=ftp.FtpDownload(file,path);  //从ftp服务器下载到web服务器的临时文件夹，并命名为随机产生的名字。
    	String urls=path+"\\"+frand_name;
    	urls=urls.replace('\\', '/');
    	String url="../doc"+"/"+frand_name;
    	request.setAttribute("swfpath",url);
    	request.getSession().setAttribute("delpath",frand_name);//供删除临时文件夹使用.
%>
<html>
<head>
    <title><%=tittle %></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1,user-scalable=no,maximum-scale=1,width=device-width" />
    <style type="text/css" media="screen">
        html, body	{ height:100%; }
        body { margin:0; padding:0; overflow:auto; }
        #flashContent { display:none; }
    </style>

    <link rel="stylesheet" type="text/css" href="../css/flexpaper.css" />
    <script type="text/javascript" src="../js/flexpaper/jquery.min.js"></script>
    <script type="text/javascript" src="../js/flexpaper/flexpaper.js"></script>
    <script type="text/javascript" src="../js/flexpaper/flexpaper_handlers.js"></script>
</head>
<body>
<!--<div id="documentViewer" class="flexpaper_viewer" style="text-align:width:100%;height:100%;display:block"></div>-->
<div id="documentViewer" class="flexpaper_viewer"style="text-align: center;position: absolute; top: 0%; height:100%; width:100%; border:3px ">

<script type="text/javascript">
    function getDocumentUrl(document){
        return "php/services/view.php?doc={doc}&format={format}&page={page}".replace("{doc}",document);
    }
    
        var startDocument = "Paper";

    $('#documentViewer').FlexPaperViewer(
            { config : {

                SWFFile : '<%=request.getAttribute("swfpath")%>',

                Scale : 1,
                ZoomTransition : 'easeOut',
                ZoomTime : 0.5,
                ZoomInterval : 0.2,
                FitPageOnLoad : true,
                FitWidthOnLoad : true,
                FullScreenAsMaxWindow : true,
                ProgressiveLoading : false,
                MinZoomSize : 0.2,
                MaxZoomSize : 5,
                SearchMatchAll : false,
                InitViewMode : 'Portrait',
                RenderingOrder : 'flash',
                StartAtPage : '',

                ViewModeToolsVisible : true,
                ZoomToolsVisible : true,
                NavToolsVisible : true,
                CursorToolsVisible : true,
                SearchToolsVisible : true,
                WMode : 'window',
                localeChain: 'en_US'
            }}
    );  
    
        var xmlHttp;
        		function createXMLHttp(){
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest() ;
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") ;
			}
		}
		function CallBack(){
			if(xmlHttp.readyState==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}

  

        $( function()  {
                	alert("请等待。。。。（flash显示出来后点击确定）");
	 		  // xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		  createXMLHttp();
	 		   xmlHttp.open("POST","../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        });
</script>
</div>
</body>
</html>