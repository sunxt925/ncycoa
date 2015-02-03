<%@ page language="java"
	import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,com.db.*,java.awt.*,com.entity.system.*,com.ftp.*,com.entity.ftp.*,com.entity.stdapply.*,java.io.*"
	pageEncoding="gb2312"%>
<%@page import="com.zhuozhengsoft.pageoffice.ThemeType"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.DataRegion"%>
<%@page import="com.zhuozhengsoft.pageoffice.wordwriter.WordDocument"%>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po"%>
<%!
// �����ļ�
public void copyFile(String oldPath, String newPath){
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { //�ļ�����ʱ 
				InputStream inStream = new FileInputStream(oldPath); //����ԭ�ļ� 
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; //�ֽ��� �ļ���С 
					//System.out.println(bytesum);
					fs.write(buffer, 0, byteread); 
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("���Ƶ����ļ���������");
			e.printStackTrace();
		}

}
%>
<%

String applyid=request.getParameter("applyid");
DocApplyPerson applyer=new DocApplyPerson(Integer.valueOf(applyid));
String applyorg=applyer.getApplyapart();
String applydate=applyer.getApplydate().substring(0,10);
String applyreason=applyer.getApplyreason();
DocReviseInifo revise=new DocReviseInifo();
String doccodestring=applyer.getApplydoccode();
String docnamestring=applyer.getApplydocname();

DocApplySuggest applysuggest=new DocApplySuggest();
DataTable dt=applysuggest.getAllApplySuggest(applyid);
String technologysug="";
String weiyuansug="";
String apporgregion="";
for(int i=0;i<dt.getRowsCount();i++){
	String where=dt.get(i).getString("wheresug");
	String approstaffcode=dt.get(i).getString("sugstaffcode");
	StaffInfo staffinfo=new StaffInfo(approstaffcode);
	String sugstaffname=staffinfo.getName();
	String suggest=dt.get(i).getString("suggestion");
	String sugdate=dt.get(i).getString("sugdate");
	if(where.equals("weiyuan")){
		weiyuansug=weiyuansug+"  "+sugdate+sugstaffname+":"+suggest;
	}else{
		technologysug=technologysug+"  "+sugdate+sugstaffname+":"+suggest;
		if(where.equals("yingxiao")){
			apporgregion="Ӫ������ίԱ�����";
		}else if(where.equals("wuliu")){
			apporgregion="��������ίԱ�����";
		}else if(where.equals("zhuanmai")){
			apporgregion="ר������ίԱ�����";
		}else if(where.equals("anquan")){
			apporgregion="��ȫ����ίԱ�����";
		}else if(where.equals("jichu")){
			apporgregion="����������ίԱ�����";
		}
	}
}



String tempPath = getServletContext().getRealPath("/")+"applytabletemp";//��ʱ�ļ���·��
String tempPath2 = getServletContext().getRealPath("/")+"doc";
String tempname=tempPath+"\\��ҵ��׼�޶������.doc";
String applytablepath=tempPath2+"\\"+applyid+".doc";
copyFile(tempname,applytablepath);





//******************************׿��PageOffice�����ʹ��*******************************
		PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
	//	poCtrl1.setServerPage("poserver.do");
		poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz"); //���б���
		poCtrl1.setJsFunction_AfterDocumentOpened( "AfterDocumentOpened()"); //�ļ���֮����Ӧ��������ɾ����ʱ�ļ������ļ���
			//����Office������
	    poCtrl1.setOfficeToolbars(false);
		//���ز˵���
		poCtrl1.setCustomToolbar(true);
		poCtrl1.setMenubar(true);
		//���ý�ֹ����
		poCtrl1.setAllowCopy(false);
		//���ñ���ҳ��
		
 		WordDocument doc = new WordDocument();
		DataRegion org = doc.openDataRegion("PO_org");
		org.setValue(applyorg);
		DataRegion date = doc.openDataRegion("PO_date");
		date.setValue(applydate);
		DataRegion reason = doc.openDataRegion("PO_reason");
		reason.setValue(applyreason);
		DataRegion doccode = doc.openDataRegion("PO_doccode");
		doccode.setValue(doccodestring);
		DataRegion docname = doc.openDataRegion("PO_docname");
		docname.setValue(docnamestring);
		DataRegion po_org = doc.openDataRegion("PO_orgapp");
		po_org.setValue(apporgregion);
		DataRegion advise1 = doc.openDataRegion("PO_advise1");
		advise1.setValue(technologysug);
		DataRegion advise2 = doc.openDataRegion("PO_advise2");
		advise2.setValue(weiyuansug);
		poCtrl1.setWriter(doc);
    String filename=applyid+".doc";
    	request.getSession().setAttribute("delpath",filename);//��ɾ����ʱ�ļ���ʹ��.
    	if(filename!=null){
	    			poCtrl1.webOpen(applytablepath, OpenModeType.docNormalEdit, "����");
		}
		poCtrl1.setTagId("PageOfficeCtrl1"); //���б���	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link href="<%=request.getContextPath()%>/images/csstg.css" rel="stylesheet" type="text/css" />

    <script type="text/javascript">
        var xmlHttp;
		function CallBack(){
			if(xmlHttp.status==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}
		    function createXMLHttp(){
			if(window.XMLHttpRequest){
				xmlHttp = new XMLHttpRequest() ;
			} else {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") ;
			}
		}
			function AfterDocumentOpened() {
					    document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(3, false); // ��ֹ����
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(4, true); // ��ֹ���
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(5, true); //��ֹ��ӡ
            			document.getElementById("PageOfficeCtrl1").SetEnableFileCommand(6, true); // ��ֹҳ������
	 		   xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	 		   xmlHttp.open("POST","../servlet/DeleteDocFile",false);
	 		   xmlHttp.onreadystatechange=CallBack;
	           xmlHttp.send(null);     
        }
		 function Save() {
            document.getElementById("PageOfficeCtrl1").WebSave();    
            //alert('����ɹ���');
        }
       		//�쵼Ȧ�ġ�			
	   function CustomToolBar_HandDraw() {
			document.getElementById("PageOfficeCtrl1").HandDraw.Start();
		}
        var xmlHttp;
		function CallBack(){
			if(xmlHttp.status==4){
				if(xmlHttp.status==200){
					var text=xmlHttp.responseText;
				}
			}
		}
		  //��ʾ�ۼ�
        function ShowRevisions() {
            document.getElementById("PageOfficeCtrl1").ShowRevisions = true;
        }

        //���غۼ�
        function HiddenRevisions() {
            document.getElementById("PageOfficeCtrl1").ShowRevisions = false;
        }
        // ���������ע
        function StartRemark() {
            var appObj = document.getElementById("PageOfficeCtrl1").WordInsertComment();

        }	
    </script>

</head>
<body>
    <form id="form2">
    <div id="content">
        <div id="textcontent" style="width: auto; height: auto;">
			<!-- ****************************PageOffice ����ͻ��˱��************************************* -->

		   <!-- ****************************PageOffice ����ͻ��˱�̽���************************************* -->
		   <po:PageOfficeCtrl id="PageOfficeCtrl1" />
        </div>
    </div>

    </form>
</body>
</html>