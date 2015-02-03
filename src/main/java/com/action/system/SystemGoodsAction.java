package com.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;
import com.action.*;
import com.entity.system.GoodsStoreEvent;
import com.entity.system.unitclass;
import com.entity.system.Goods;
import com.entity.system.Org;
import com.common.*;

public class SystemGoodsAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		EntityOperation eo = new EntityOperation();
		Goods goods=null;
		if (action!=null && action.equals("add"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			res0 = eo.Add(request);
			res += "show('"+res0+"');";
			res+="window.close();";
		}
		else if (action!=null && action.equals("modify"))
		{

			  String entity=request.getParameter("entity");
				
				eo.setEntity(entity);
				res0 = eo.Update(request);	

				res += "show('"+res0+"');";
				res+="window.close();";

		}
		else if (action!=null && action.equals("del"))
		{
			String[] ids=request.getParameterValues("goodscode");
			goods=new Goods();
			if(goods.Delete(ids[0]))
			{
				res += "alert('É¾³ý³É¹¦!');";
				res += "window.open('../goodsmanage/goods_list.jsp','_self');";
				
			}
			else
			{
				res += "alert('É¾³ýÊ§°Ü£¡');";
				
			}
		}
		else
		{

		}
		//System.out.println(res);
		return res;
		
	}
}