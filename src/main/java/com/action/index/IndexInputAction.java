package com.action.index;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;
import com.db.DataTable;
import com.entity.index.InputTableIndex;

public class IndexInputAction extends ActionInterface {

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
				String tableno=request.getParameter("tableno");
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("TABLENO", tableno);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
			}
			else{
				
				for(int i=0;i<itemsStrings.length;i++)
				{
					InputTableIndex inputTableIndex=new InputTableIndex();
					
					DataTable dtcount=inputTableIndex.getAllInputtableIndex(itemsStrings[i]);
					if(dtcount.getRowsCount()==0){
					Map<String,String> deletemap = new HashMap<String, String>();
					
					deletemap.put("TABLENO", itemsStrings[i]);
				    	
					eo.setDeletemap(deletemap);
					res0=eo.Delete();}
					else {
						res0="存在子记录，无法删除！！！";
					}
				}
			}
			
				res += "show('"+res0+"');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../indexmanage/inputtable.jsp?sid='+rand,'_self');";
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
