package edu.cqu.ncycoa.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class FormValidationTag extends TagSupport {
	
	protected String formid = "formobj";	// ��FORM ID
	protected Boolean refresh = true;
	protected String callback;				// �ص�����
	protected String beforeSubmit;			// �ύǰ������
	protected String btnsub = "btn_sub";	// ��IDΪ��Ǵ����ύ�¼�
	protected String btnreset = "btn_reset";// ��IDΪ��Ǵ����ύ�¼�
	protected String layout = "div";		// ������
	protected String usePlugin;				// ������
	protected boolean dialog = true;		// �Ƿ��ǵ�������ģʽ
	protected String action;				// ���ύ·��
	protected String tabtitle;				// ��ѡ�
	protected String tiptype = "4";			// У�鷽ʽ

	public int doStartTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			if ("div".equals(layout)) {
				sb.append("<div id=\"content\">");
				sb.append("<div id=\"wrapper\">");
				sb.append("<div id=\"steps\">");
			}
			sb.append("<form id=\"" + formid + "\" action=\"" + action + "\" name=\"" + formid + "\" method=\"post\">");
			if ("btn_sub".equals(btnsub) && dialog)
				sb.append("<input type=\"hidden\" id=\"" + btnsub + "\" class=\"" + btnsub + "\"/>");
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			if (layout.equals("div")) {
				sb.append("<link rel=\"stylesheet\" href=\"jscomponent/validform/css/divform.css\" type=\"text/css\"/>");
				if (tabtitle != null)
					sb.append("<script type=\"text/javascript\" src=\"jscomponent/validform/js/form.js\"></script>");
			}
			sb.append("<link rel=\"stylesheet\" href=\"jscomponent/validform/css/style.css\" type=\"text/css\"/>");
			sb.append("<link rel=\"stylesheet\" href=\"jscomponent/validform/css/tableform.css\" type=\"text/css\"/>");
			sb.append("<script type=\"text/javascript\" src=\"jscomponent/validform/js/Validform_v5.3.1_ncr_min.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"jscomponent/validform/js/Validform_Datatype.js\"></script>");
			sb.append("<script type=\"text/javascript\" src=\"jscomponent/validform/js/datatype.js\"></script>");
			if (usePlugin != null) {
				if (usePlugin.indexOf("jqtransform") >= 0) {
					sb.append("<link rel=\"stylesheet\" href=\"jscomponent/validform/plugin/jqtransform/jqtransform.css\" type=\"text/css\"></link>");
					sb.append("<script type=\"text/javascript\" src=\"jscomponent/validform/plugin/jqtransform/jquery.jqtransform.js\"></script>");
				}
				if (usePlugin.indexOf("password") >= 0) {
					sb.append("<script type=\"text/javascript\" src=\"jscomponent/validform/plugin/passwordStrength/passwordStrength-min.js\"></script>");
				}
			}
			sb.append("<script type=\"text/javascript\">");
			sb.append("$(function(){");
			sb.append("$(\"#" + formid + "\").Validform({");
			sb.append("tiptype:"+ tiptype +",");
			sb.append("btnSubmit:\"#" + btnsub + "\",");
			sb.append("btnReset:\"#" + btnreset + "\",");
			sb.append("ajaxPost:true,");
			if (StringUtils.isNotEmpty(beforeSubmit)) {
				sb.append("beforeSubmit:function(curform){var tag=false;");
				sb.append("return " + beforeSubmit );
				if(beforeSubmit.indexOf("(") < 0){
					sb.append("(curform);");
				}
				sb.append("},");
			}
			if (usePlugin != null) {
				StringBuffer passsb = new StringBuffer();
				if (usePlugin.indexOf("password") >= 0) {
					passsb.append("passwordstrength:{");
					passsb.append("  minLen:6,");
					passsb.append("  maxLen:18,");
					passsb.append("  trigger:function(obj,error){");
					passsb.append("    if(error){");
					passsb.append("      obj.parent().next().find(\".Validform_checktip\").show();");
					passsb.append("      obj.find(\".passwordStrength\").hide();");
					passsb.append("    } else {");
					passsb.append("      $(\".passwordStrength\").show();");
					passsb.append("      obj.parent().next().find(\".Validform_checktip\").hide();");
					passsb.append("    }");
					passsb.append("  }");
					passsb.append("}");
				}
				StringBuffer jqsb = new StringBuffer();
				if (usePlugin.indexOf("jqtransform") >= 0) {
					if (usePlugin.indexOf("password") >= 0) {
						sb.append(",");
					}
					jqsb.append("jqtransform :{selector:\"select\"}");
				}
				sb.append("usePlugin:{");
				if (usePlugin.indexOf("password") >= 0) {
					sb.append(passsb);
				}
				if (usePlugin.indexOf("jqtransform") >= 0) {
					sb.append(jqsb);
				}
				sb.append("},");
			}
			sb.append("callback:function(data){");
			if (dialog) {
				if(callback != null && callback.contains("@Override")){//��дĬ��callback
					sb.append(callback.replaceAll("@Override", "") + "(data);");
				} else {
					// ���ж��Ƿ�ɹ����ɹ���ˢ�¸�ҳ�棬����return false    
					// ������ɹ�������ֵ����ʹ��data.msg. ԭ�е�data.responseText�ᱨnull 
					sb.append("var win = frameElement.api.opener;");
					sb.append("if(data.success==true){");
					sb.append("  frameElement.api.close();");
					sb.append("  win.tip(data.msg);");
					sb.append("} else { ");
					sb.append("  if(data.responseText==''||data.responseText==undefined){");
					sb.append("    $.messager.alert('����', data.msg);$.Hidemsg();");
					sb.append("  } else { ");
					sb.append("    try{");
					sb.append("      var emsg = data.responseText.substring(data.responseText.indexOf('��������'),data.responseText.indexOf('������Ϣ'));");
					sb.append("      $.messager.alert('����',emsg);$.Hidemsg();");
					sb.append("    } catch(ex) {");
					sb.append("      $.messager.alert('����',data.responseText+\"\");$.Hidemsg();");
				    sb.append("    }");
				    sb.append("  }");
				    sb.append("  return false;");
				    sb.append("}");
					
					if (refresh) {
						sb.append("win.reloadTable();");
					}
					if (StringUtils.isNotEmpty(callback)) {
						sb.append("win."+ callback + "(data);");
					}
				}
			} else {
				sb.append("" + callback + "(data);");
			}
			sb.append("}" + "});" + "});" + "</script>");
			sb.append("");
			sb.append("</form>");
			if ("div".equals(layout)) {
				sb.append("</div>");
				if (tabtitle != null) {
					String[] tabtitles = tabtitle.split(",");
					sb.append("<div id=\"navigation\" style=\"display: none;\">");
					sb.append("<ul>");
					for (String string : tabtitles) {
						sb.append("<li>");
						sb.append("<a href=\"#\">" + string + "</a>");
						sb.append("</li>");
					}
					sb.append("</ul>");
					sb.append("</div>");
				}
				sb.append("</div></div>");
			}
			out.print(sb.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	public void setTabtitle(String tabtitle) {
		this.tabtitle = tabtitle;
	}

	public void setDialog(boolean dialog) {
		this.dialog = dialog;
	}

	public void setBtnsub(String btnsub) {
		this.btnsub = btnsub;
	}

	public void setRefresh(Boolean refresh) {
		this.refresh = refresh;
	}

	public void setBtnreset(String btnreset) {
		this.btnreset = btnreset;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setUsePlugin(String usePlugin) {
		this.usePlugin = usePlugin;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setBeforeSubmit(String beforeSubmit) {
		this.beforeSubmit = beforeSubmit;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getTiptype() {
		return tiptype;
	}

	public void setTiptype(String tiptype) {
		this.tiptype = tiptype;
	}
	
}
