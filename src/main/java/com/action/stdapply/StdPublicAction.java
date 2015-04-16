package com.action.stdapply;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.entity.stdapply.DocApplyPerson;
import com.entity.stdapply.DocReviseInifo;

public class StdPublicAction extends ActionInterface{
	public String getResult(HttpServletRequest request)
	{
			String res="";
			String[] ids=request.getParameterValues("items");
			String applyid=request.getParameter("applyid");
			String applyercode=request.getParameter("applystaffcode");
			String para="";
			boolean flag=true;
			if(ids!=null){
				for (int i=0;i<ids.length;i++)
				{
					DocReviseInifo reviseinfo=new DocReviseInifo();
					try {
					
						if(reviseinfo.Std_Public(ids[i],applyid,applyercode)){
							int id=Integer.parseInt(applyid);
							DocApplyPerson applyperson=new DocApplyPerson(id);
						}else{
							flag=false;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(flag){
					res += "show('发布成功');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var applyid=\""+applyid+"\";";
					res += "window.open('../stdapply/std_publiclist.jsp?applyid='+applyid,'_self');";
					res += "parent.unittree.location.reload();";
				}else{
					res += "show('发布失败，请与管理员联系！');";
					res += "var rand=Math.floor(Math.random()*10000);";
					res +="var applyid=\""+request.getParameter("applyid")+"\";";
					res += "window.open('../stdapply/std_publiclist.jsp?sid='+rand+'&applyid='+applyid,'stdlist');";
				}
			}else{
				res += "show('请选择要发布的标准');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var applystaffcode=\""+request.getParameter("applystaffcode")+"\";";
				res += "window.open('../stdapply/std_publiclist.jsp?sid='+rand+'&applystaffcode='+applystaffcode,'_self');";
				res += "parent.unittree.location.reload();";
			}
			return res;
	}
}
