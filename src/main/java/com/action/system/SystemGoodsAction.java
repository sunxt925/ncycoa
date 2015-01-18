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
		//String res="";
		//String ccm=request.getParameter("unit_ccm");
		//System.out.println(ccm+"xdvxdvf");
		//String action=request.getParameter("act");
		//unitclass mo=null;
		Goods goods=null;
		//Org og=null;
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
			//String[] ids=request.getParameterValues("items");//批量删除
			String[] ids=request.getParameterValues("goodscode");//
			
			goods=new Goods();
			//System.out.println("运行到这里");
			
			
			
			if(goods.Delete(ids[0]))
			{
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				//res+="window.close();";
				res+="parent.window.location.reload();";
				//res += "window.open('../xtwh/goodsmanage/goods_list.jsp','_self');";
				
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，可能是有子单位请先删除子单位！');";
				res+="parent.window.location.reload();";
				//res += "window.open('../xtwh/goodsmanage/goods_list.jsp','_self');";
				
			}
		}
		else
		{

		}
		//System.out.println(res);
		return res;
		
	}
}