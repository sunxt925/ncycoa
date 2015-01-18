package com.action.buygoods;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.business.BuyGoodsApp;
import com.business.BuyGoodsItem;
import com.common.EntityOperation;

public class BuyGoodsInAction extends ActionInterface{

	
	public String getResult(HttpServletRequest request) {
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		if (action != null && action.equals("add"))
		{
			
		
			String entity=request.getParameter("entity");
			//String sequence=request.getParameter("sequence");
		    //eo.setSequence(sequence);
			String buyno=BuyGoodsItem.getBuyno();
			String auditorgcode=BuyGoodsItem.getAuditorgcode(request.getParameter("COM_BUYGOODSITEM.GOODSCODE"));
		    Map<String,String> map = new HashMap<String,String>();
			map.put("BUYNO", buyno);
			map.put("AUDITORGCODE", auditorgcode);
			eo.setMap(map);
			eo.setEntity(entity);
			res0 = eo.Add(request);
			
			res += "show('"+res0+"');";
			res += "window.close();";
		}
		else if (action != null && action.equals("modify"))
		{
            String entity=request.getParameter("entity");
			//System.out.println(request.getParameter("STD_DOCMETAVERSIONINFO.STD_STOREFILEFLAG"));
			eo.setEntity(entity);
			res0 = eo.Update(request);	
			res += "show('"+res0+"');";
			res += "window.close();";
		}
		else if(action != null && action.equals("del"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("items");
			
				for(int i=0;i<itemsStrings.length;i++)
				{
					Map<String,String> deletemap = new HashMap<String, String>();
					
					deletemap.put("BUYNO", itemsStrings[i]);
					
					eo.setDeletemap(deletemap);
					res0=eo.Delete();
				}
				res += "show('"+res0+"');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../buygoods/buygoodsin.jsp?sid='+rand,'_self');";
			
			
			
		}
		else if(action != null && action.equals("application"))
		{
			String[] items=request.getParameterValues("items");
			String buymode=request.getParameter("COM_BUYGOODSITEM.BUYMODE");
			String eventno=BuyGoodsApp.appGetEventno(items,buymode);
		    res0=BuyGoodsApp.app(items, eventno,buymode);
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../buygoods/buygoodsin.jsp?sid='+rand,'_self');";
		}
	
		return res;
	}

}
