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
			//String[] ids=request.getParameterValues("items");//����ɾ��
			String[] ids=request.getParameterValues("goodscode");//
			
			goods=new Goods();
			//System.out.println("���е�����");
			
			
			
			if(goods.Delete(ids[0]))
			{
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				//res+="window.close();";
				res+="parent.window.location.reload();";
				//res += "window.open('../xtwh/goodsmanage/goods_list.jsp','_self');";
				
			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ����������ӵ�λ����ɾ���ӵ�λ��');";
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