package com.action.index;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;
public class ReferObjectAction extends ActionInterface{
	@Override
	public String getResult(HttpServletRequest request) {
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		if (action != null && action.equals("add"))
		{
			
		
			String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Add(request);
			
			res += "show('"+res0+"');";
			res += "window.close();";
		}
		else if(action != null && action.equals("del"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("items");
			if(itemsStrings==null){
				String recno=request.getParameter("recno");
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("RECNO", recno);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
				}
			else {
				for(int i=0;i<itemsStrings.length;i++)
				{
					Map<String,String> deletemap = new HashMap<String, String>();
					
					deletemap.put("RECNO", itemsStrings[i]);
					
					eo.setDeletemap(deletemap);
					res0=eo.Delete();
				}
			}
				res += "show('"+res0+"');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../indexmanage/referobjectquerylist.jsp?indexcode="+request.getParameter("indexcode")+"&paracode="+request.getParameter("paracode")+"&indexclass="+request.getParameter("indexclass")+"&sid='+rand,'_self');";
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
			
			
		
		return res;
	}
}
