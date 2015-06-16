package edu.cqu.ncycoa.common.tag;

import java.io.IOException;
import java.util.Random;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class UploadTag extends TagSupport {
	private static final long serialVersionUID = 1L;
	protected String id;// ID
	protected String uploader;//url
	protected String name;//�ؼ�����
	protected String formData;//��������
	protected String extend="";//�ϴ��ļ�����չ��
	protected String buttonText="���";//��ť�ı�
	protected boolean multi=true;//�Ƿ���ļ�
	protected String queueID="filediv";//�ļ�����ID
	protected boolean dialog=true;//�Ƿ��ǵ�������ģʽ
	protected String callback;
	protected boolean auto=false;//�Ƿ��Զ��ϴ�
	protected String onUploadSuccess;//�ϴ��ɹ�������
	protected boolean view=false;//���ɲ鿴ɾ������
	public void setView(boolean view) {
		this.view = view;
	}
	public void setOnUploadSuccess(String onUploadSuccess) {
		this.onUploadSuccess = onUploadSuccess;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}
	public void setQueueID(String queueID) {
		this.queueID = queueID;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	public void setMulti(boolean multi) {
		this.multi = multi;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}
	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	public StringBuffer end() {
		StringBuffer sb = new StringBuffer();
		if("pic".equals(extend))
		{
			extend="*.jpg;*,jpeg;*.png;*.gif;*.bmp;*.ico;*.tif";
		}
		if(extend.equals("office"))
		{
			extend="*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm";
		}
		sb.append("<link rel=\"stylesheet\" href=\"jscomponent/uploadify/uploadify.css\" type=\"text/css\"></link>");
		sb.append("<script type=\"text/javascript\" src=\"jscomponent/uploadify/jquery.uploadify.min.js?").append(new Random().nextInt()).append("\"></script>");
		sb.append("<script type=\"text/javascript\" src=\"jscomponent/tools/Map.js\"></script>");
		sb.append("<script type=\"text/javascript\">"
				+"var flag = false;"
				+"var fileitem=\"\";"
				+"var fileKey=\"\";"
				+"var serverMsg=\"\";"
				+"var m = new Map();"
				+ "$(function(){"
				+"$(\'#"+id+"\').uploadify({"
				+"buttonText:\'"+buttonText+"\',"
				+"auto:"+auto+","
				+"progressData:\'speed\'," 
				+"multi:"+multi+","
				+"height:25,"
				+"overrideEvents:[\'onDialogClose\']," 
				+"fileTypeDesc:\'�ļ���ʽ:\'," 
				+"queueID:\'"+queueID+"\',"
				+"fileTypeExts:\'"+extend+"\',"
				+"fileSizeLimit:\'15MB\',"
				+"swf:\'jscomponent/uploadify/uploadify.swf\',	"
				+"uploader:\'"+getUploader()+
				"onUploadStart : function(file) { ");	
				if (formData!=null) {
					String[] paramnames=formData.split(",");				
					for (int i = 0; i < paramnames.length; i++) {
						String paramname=paramnames[i];
						sb.append("var "+paramname+"=$(\'#"+paramname+"\').val();");
					}				 
			        sb.append("$(\'#"+id+"\').uploadify(\"settings\", \"formData\", {");
			        for (int i = 0; i < paramnames.length; i++) {
						String paramname=paramnames[i];
						if (i==paramnames.length-1) {
							sb.append("'"+paramname+"':"+paramname+"");	
						}else{
							sb.append("'"+paramname+"':"+paramname+",");
						}
					}
			        sb.append("});");
				}; 
		       sb.append("} ," 	          
				+"onQueueComplete : function(queueData) { ");
				if(dialog)
				{
				sb.append("var win = frameElement.api.opener;"  	  
	            +"win.reloadTable();"
	            +"win.tip(serverMsg);"
	            +"frameElement.api.close();");
				}
				else
				{
				  if(callback!=null)
				  sb.append(""+callback+"(queueData);");
				}
				if(view)
				{
				sb.append("$(\"#viewmsg\").html(m.toString());");
				sb.append("$(\"#fileKey\").val(fileKey);");
				}
				sb.append("},");
				//�ϴ��ɹ�������
				sb.append("onUploadSuccess : function(file, data, response) {").append("\n");
				sb.append("var d=$.parseJSON(data);").append("\n");
				if(view)
				{
				sb.append("var fileitem=\"<span id=\'\"+d.attributes.id+\"\'><a href=\'#\' onclick=openwindow(\'�ļ��鿴\',\'\"+d.attributes.viewhref+\"\',\'70%\',\'80%\') title=\'�鿴\'>\"+d.attributes.name+\"</a><img border=\'0\' onclick=confuploadify(\'\"+d.attributes.delurl+\"\',\'\"+d.attributes.id+\"\') title=\'ɾ��\' src=\'jscomponent/uploadify/uploadify-cancel.png\' widht=\'15\' height=\'15\'>&nbsp;&nbsp;</span>\";");
				sb.append("m.put(d.attributes.id,fileitem);");
				sb.append("fileKey=d.attributes.fileKey;");
				}
				if(onUploadSuccess!=null)
				{
				sb.append(onUploadSuccess+"(d,file,response);");
				}
				sb.append("if(d.success){");
				sb.append("var win = frameElement.api.opener;");
//				sb.append("win.tip(d.msg);");
				sb.append("serverMsg = d.msg;");
				sb.append("}");
				sb.append("},");
				sb.append("onFallback : function(){tip(\"��δ��װFLASH�ؼ����޷��ϴ�ͼƬ���밲װFLASH�ؼ�������\")},");
				sb.append("onSelectError : function(file, errorCode, errorMsg){");
				sb.append("switch(errorCode) {");
				sb.append("case -100:");
				sb.append("tip(\"�ϴ����ļ������Ѿ�����ϵͳ���Ƶ�\"+$(\'#"+id+"\').uploadify(\'settings\',\'queueSizeLimit\')+\"���ļ���\");");
				sb.append("break;");
				sb.append("case -110:"
						+"tip(\"�ļ� [\"+file.name+\"] ��С����ϵͳ���Ƶ�\"+$(\'#"+id+"\').uploadify(\'settings\',\'fileSizeLimit\')+\"��С��\");"
						+"break;"
						+"case -120:"
						+"tip(\"�ļ� [\"+file.name+\"] ��С�쳣��\");"
						+"break;"
						+"case -130:"
						+"tip(\"�ļ� [\"+file.name+\"] ���Ͳ���ȷ��\");"
						+"break;"
						+"}");
		       sb.append("},"
				+"onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { "
				//+"tip('<span>�ļ��ϴ��ɹ�:'+totalBytesUploaded/1024 + ' KB ���ϴ� ,����' + totalBytesTotal/1024 + ' KB.</span>');"    	  	             
				+"}"
	   			+"});"
				+"});"
				+"function upload() {"
				+"	$(\'#"+id+"\').uploadify('upload', '*');"
				+"		return flag;"
				+"}"
				+"function cancel() {"
				+"$(\'#"+id+"\').uploadify('cancel', '*');"				
				+"}"				
				+"</script>");	
		       sb.append("<span id=\""+id+"span\"><input type=\"file\" name=\""+name+"\" id=\""+id+"\" /></span>");
		       if(view)
		       {
		       sb.append("<span id=\"viewmsg\"></span>");
		       sb.append("<input type=\"hidden\" name=\"fileKey\" id=\"fileKey\" />");
		       }
		        
		return sb;
	}
	
	/**
	 * ��ȡ�ϴ�·��,�޸�jsessionid��������̨��bug jueyue --- 20130916
	 * @return
	 */
	private String getUploader() {
		return uploader+"&sessionId="+pageContext.getSession().getId()+"',";
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setFormData(String formData) {
		this.formData = formData;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
}
