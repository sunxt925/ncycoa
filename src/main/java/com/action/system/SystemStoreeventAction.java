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
			String[] ids=request.getParameterValues("eventid");//
			GoodsStoreEvent gse=new GoodsStoreEvent();
			if(gse.Delete(ids[0]))
			{
				res += "alert('É¾³ý³É¹¦£¡');";
				if(ids[0].startsWith("IN"))
					res += "window.open('../goodsmanage/goodsIn_manage.jsp','_self');";
				else
					res += "window.open('../goodsmanage/goodsOut_manage.jsp','_self');";
				
			}
			else
			{
				res += "alert('É¾³ýÊ§°Ü£¡');";
				if(ids[0].startsWith("IN"))
					res += "window.open('../goodsmanage/goodsIn_manage.jsp','_self');";
				else
					res += "window.open('../goodsmanage/goodsOut_manage.jsp','_self');";
			}
		}
		else
		{

		}
		return res;
	}
}