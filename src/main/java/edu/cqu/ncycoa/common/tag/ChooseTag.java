package edu.cqu.ncycoa.common.tag;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("serial")
public class ChooseTag extends TagSupport {
	protected String hiddenName;
	protected String textname;//显示文本框字段
	protected String icon;
	protected String title;
	protected String url;
	protected String top;
	protected String left;
	protected String width;
	protected String height;
	protected String name;
	protected String hiddenid;// 隐藏框取值ID
	protected Boolean isclear = false;
	protected String fun;//自定义函数
	protected String inputTextname;

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
		String methodname = UUID.randomUUID().toString().replaceAll("-", "");
		StringBuffer sb = new StringBuffer();
		sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"" + icon + "\" onClick=\"choose_"+methodname+"()\">选择</a>");
		if (isclear&&StringUtils.isNotEmpty(inputTextname)) {
			sb.append("<a href=\"#\" class=\"easyui-linkbutton\" plain=\"true\" icon=\"icon-redo\" onClick=\"clearAll_"+methodname+"();\">清空</a>");
		}
		sb.append("<script type=\"text/javascript\">");
		sb.append("var windowapi = frameElement.api, W = windowapi.opener;");
		sb.append("function choose_"+methodname+"(){");
		sb.append("if(typeof(windowapi) == 'undefined'){");
			sb.append("$.dialog({");
			sb.append("content: \'url:"+url+"\',");
			sb.append("data:" + "returnobjValue_"+methodname+"," );
			sb.append("zIndex: 2100,");
			if (title != null) {
				sb.append("title: \'" + title + "\',");
			}
			sb.append("lock : true,");
			if (width != null) {
				sb.append("width :\'" + width + "\',");
			} else {
				sb.append("width :800,");
			}
			if (height != null) {
				sb.append("height :\'" + height + "\',");
			} else {
				sb.append("height :600,");
			}
			if (left != null) {
				sb.append("left :\'" + left + "\',");
			} else {
				sb.append("left :'50%',");
			}
			if (top != null) {
				sb.append("top :\'" + top + "\',");
			} else {
				sb.append("top :'45%',");
			}
			sb.append("opacity : 0.4,");
			sb.append("button : [ {");
			sb.append("name : \'确认\',");
			sb.append("callback : clickcallback_"+methodname+",");
			sb.append("focus : true");
			sb.append("}, {");
			sb.append("name : \'取消\',");
			sb.append("callback : function() {");
			sb.append("}");
			sb.append("} ]");
			sb.append("});");
		sb.append("}else{");
			sb.append("$.dialog({");
			sb.append("content: \'url:"+url+"\',");
			sb.append("data:" + "returnobjValue_"+methodname+"," );
			sb.append("zIndex: 2100,");
			if (title != null) {
				sb.append("title: \'" + title + "\',");
			}
			sb.append("lock : true,");
			sb.append("parent:windowapi,");
			if (width != null) {
				sb.append("width :\'" + width + "\',");
			} else {
				sb.append("width :800,");
			}
			if (height != null) {
				sb.append("height :\'" + height + "\',");
			} else {
				sb.append("height :600,");
			}
			if (left != null) {
				sb.append("left :\'" + left + "\',");
			} else {
				sb.append("left :'50%',");
			}
			if (top != null) {
				sb.append("top :\'" + top + "\',");
			} else {
				sb.append("top :'45%',");
			}
			sb.append("opacity : 0.4,");
			sb.append("button : [ {");
			sb.append("name : \'确认\',");
			sb.append("callback : clickcallback_"+methodname+",");
			sb.append("focus : true");
			sb.append("}, {");
			sb.append("name : \'取消\',");
			sb.append("callback : function() {");
			sb.append("}");
			sb.append("} ]");
			sb.append("});");
			sb.append("}");
		sb.append("}");
		clearAll(sb,methodname);
		callback(sb,methodname);
		sb.append("</script>");
		return sb;
	}
	/**
	 * 清除
	 * @param sb
	 */
	private void clearAll(StringBuffer sb,String methodname) {
		String[] inputTextnames=null;
		if(StringUtils.isNotEmpty(inputTextname)){
			inputTextnames = inputTextname.split(",");
		}
		
		if (isclear&&inputTextnames!=null&&inputTextnames.length > 0) {
			sb.append("function clearAll_"+methodname+"(){");
			for (int i = 0; i < inputTextnames.length; i++) {
				inputTextnames[i]=inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
				sb.append("if($(\'#" + inputTextnames[i] + "\').length>=1){");
				sb.append("$(\'#" + inputTextnames[i] + "\').val('');");
				sb.append("$(\'#" + inputTextnames[i] + "\').blur();");
				sb.append("}");
				sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
				sb.append("$(\"input[name='" + inputTextnames[i] + "']\").val('');");
				sb.append("$(\"input[name='" + inputTextnames[i] + "']\").blur();");
				sb.append("}");
			}
			sb.append("$(\'#" + hiddenName + "\').val(\"\");");
			sb.append("}");
			}
	}
	/**
	 * 点击确定回填
	 * @param sb
	 */
	private void callback(StringBuffer sb,String methodname) {
		sb.append("function clickcallback_"+methodname+"(){");
		sb.append("var iframe = this.iframe.contentWindow;");
		sb.append("$('#btn_ok', iframe.document).click();");
		sb.append("}");
		
		sb.append("function returnobjValue_"+methodname+"(data){");
		sb.append("data = data.code;");
		String[] textnames=null;
		String[] inputTextnames=null;
		if(StringUtils.isNotEmpty(textname))
		{
			textnames = textname.split(",");
			if(StringUtils.isNotEmpty(inputTextname)){
				inputTextnames = inputTextname.split(",");
			}else{
				inputTextnames = textnames;
			}
			for (int i = 0; i < textnames.length; i++) {
				inputTextnames[i]=inputTextnames[i].replaceAll("\\[", "\\\\\\\\[").replaceAll("\\]", "\\\\\\\\]").replaceAll("\\.", "\\\\\\\\.");
				sb.append("var " + textnames[i] + "= \"\";");
				sb.append("if(!data.length){");
				sb.append(textnames[i] + "= data[\'" + textnames[i] + "\'];");
				sb.append("} else {");
				sb.append("   for(var j=0; j<data.length; j++){");
				sb.append(     textnames[i] + "+= data[j][\'" + textnames[i] +"\'];");
				sb.append("    if(j<data.length - 1){" + textnames[i] + "+=\',\';}");
				sb.append("   }");
				sb.append("}");
				sb.append("if($(\'#" + inputTextnames[i] + "\').length>=1){");
				sb.append("  $(\'#" + inputTextnames[i] + "\').val(" + textnames[i] + ");");
				sb.append("  $(\'#" + inputTextnames[i] + "\').blur();");
				sb.append("}");
				sb.append("if($(\"input[name='" + inputTextnames[i] + "']\").length>=1){");
				sb.append("  $(\"input[name='" + inputTextnames[i] + "']\").val(" + textnames[i] + ");");
				sb.append("  $(\"input[name='" + inputTextnames[i] + "']\").blur();");
				sb.append("}");
			}
		}
		if(StringUtils.isNotEmpty(hiddenName)){
			sb.append("var id = \"\";");
			sb.append("if(!data.length){");
			sb.append("  id = data[\'" + hiddenid + "\'];");
			sb.append("} else {");
			sb.append("   for(var j=0; j<data.length; j++){");
			sb.append(     "id += data[j][\'" + hiddenid +"\'];");
			sb.append("    if(j<data.length - 1){id+=\',\';}");
			sb.append("   }");
			sb.append("}");
			sb.append("if (id!== undefined &&id!=\"\"){");
			sb.append("  $(\'#" + hiddenName + "\').val(id);");
			sb.append("}");
		}
		if(StringUtils.isNotEmpty(fun))
		{
		sb.append(""+fun+"(data);");//执行自定义函数
		}
		
		sb.append("}");
	}

	public void setHiddenName(String hiddenName) {
		this.hiddenName = hiddenName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setTextname(String textname) {
		this.textname = textname;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setIsclear(Boolean isclear) {
		this.isclear = isclear;
	}

	public void setHiddenid(String hiddenid) {
		this.hiddenid = hiddenid;
	}
	public void setFun(String fun) {
		this.fun = fun;
	}

	public String getInputTextname() {
		return inputTextname;
	}

	public void setInputTextname(String inputTextname) {
		this.inputTextname = inputTextname;
	}

}
