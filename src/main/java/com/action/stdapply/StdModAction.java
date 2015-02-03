package com.action.stdapply;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.entity.stdapply.DocReviseInifo;

public class StdModAction extends ActionInterface{
	public String getResult(HttpServletRequest request)
	{
			String res="";
			String action=request.getParameter("act");
			String docno=request.getParameter("docno");
			String[] modordels=request.getParameterValues("items");
			String temppath=request.getParameter("temppath");
			String applyerid=(String)request.getSession().getAttribute("applyerid");
			String type=request.getParameter("type");
			String orgcode=request.getParameter("orgcode");
			if (action!=null && action.equals("mod"))
			{
				for (int i=0;i<modordels.length;i++){
				DocReviseInifo reviseinfo=new DocReviseInifo();
				try {
					reviseinfo.Std_ModInsert(modordels[i],applyerid,temppath,type,orgcode);
		//			res +="window.close();";
		//			  res += "parent.unittree.location.reload();";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
			return res;
	}

}
