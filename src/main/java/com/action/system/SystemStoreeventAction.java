package com.action.system;

import javax.servlet.http.*;
import com.action.*;
import com.db.DBObject;
import com.db.Parameter;
import com.entity.system.GoodsStoreEvent;
import com.entity.system.unitclass;
import com.entity.system.Org;
import com.common.*;

public class SystemStoreeventAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		
		Org og=null;
		if (action!=null && action.equals("add"))
		{
			
		}
		else if (action!=null && action.equals("modify"))

		{

			  String entity=request.getParameter("entity");
				
				eo.setEntity(entity);
				res0 = eo.Update(request);	

				res += "show('"+res0+"');";
		}
		else if (action!=null && action.equals("del"))
		{
			//String[] ids=request.getParameterValues("items");//批量删除
			String[] ids=request.getParameterValues("eventid");//
			
			GoodsStoreEvent gse=new GoodsStoreEvent();
			//System.out.println("运行到这里");
			
			
			
			if(gse.Delete(ids[0]))
			{
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				if(ids[0].startsWith("IN"))
					res += "window.open('../xtwh/goodsmanage/goodsIn_manage.jsp','_self');";
				else
					res += "window.open('../xtwh/goodsmanage/goodsOut_manage.jsp','_self');";
				
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
				//res += "parent.unittree.location.reload();";
				//res += "window.open('../xtwh/system_unit/unit_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
				//RequestDispatcher rd=request.getRequestDispatcher("unitmanage.jsp");
			    //rd.forward(request,response);
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，可能是有子单位请先删除子单位！');";
				if(ids[0].startsWith("IN"))
					res += "window.open('../xtwh/goodsmanage/goodsIn_manage.jsp','_self');";
				else
					res += "window.open('../xtwh/goodsmanage/goodsOut_manage.jsp','_self');";
				
				//res += "var rand=Math.floor(Math.random()*10000);";
				//res +="var ccm=\""+request.getParameter("ParentOrgCode")+"\";";
				//res += "window.open('../xtwh/system_unit/unit_list.jsp?sid='+rand+'&unitccm='+ccm,'_self');";
			}
		}
		else
		{

		}
		return res;
	}
}