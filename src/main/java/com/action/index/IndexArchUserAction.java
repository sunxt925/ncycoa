package com.action.index;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;

public class IndexArchUserAction extends ActionInterface{

	@Override
	public String getResult(HttpServletRequest request) {
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		if (action != null && action.equals("add"))
		{
			String entity=request.getParameter("entity");
			String[] objectcodes=request.getParameter("TBM_INDEXARCHUSER.OBJECTCODE").split(",");
			String indexarchcode=request.getParameter("TBM_INDEXARCHUSER.INDEXARCHCODE");
			String[] stafforgs=null;
			Map<String, String> addmapMap=new HashMap<String, String>();
			addmapMap.put("TBM_INDEXARCHUSER.OBJECTCODE", "1");
			if(indexarchcode.substring(0, 1).equals("S")){
			     stafforgs=request.getParameter("TBM_INDEXARCHUSER.STAFFORG").split(",");
			     addmapMap.put("TBM_INDEXARCHUSER.STAFFORG", "1");
			 }
			eo.setAddmoreMap(addmapMap);
			for(int i=0;i<objectcodes.length;i++){
				request.setAttribute("TBM_INDEXARCHUSER.OBJECTCODE", objectcodes[i]);
				
				if(indexarchcode.substring(0, 1).equals("S")){
					request.setAttribute("TBM_INDEXARCHUSER.STAFFORG", stafforgs[i]);
				}
				eo.setEntity(entity);
				res0 = eo.Add(request);
				request.removeAttribute("TBM_INDEXARCHUSER.OBJECTCODE");
				if(indexarchcode.substring(0, 1).equals("S")){
					request.removeAttribute("TBM_INDEXARCHUSER.STAFFORG");
				}
				   
			}
			
			
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
		    res += "window.open('../indexmanage/indexdefuserlist.jsp?indexclass="+request.getParameter("indexclass")+"&indexarchcode="+request.getParameter("TBM_INDEXARCHUSER.INDEXARCHCODE")+"&sid='+rand,'_self');";
		}
		else if(action != null && action.equals("del"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("items");
			
			if(itemsStrings==null){
				String indexarchcode=request.getParameter("indexarchcode");
				String objectcode=request.getParameter("objectcode");
				 Map<String,String> deletemap = new HashMap<String, String>();
                deletemap.put("INDEXARCHCODE", indexarchcode);
                deletemap.put("OBJECTCODE", objectcode);
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
				}
			else {
				for(int i=0;i<itemsStrings.length;i++)
				{
					Map<String,String> deletemap = new HashMap<String, String>();
					String[] sssStrings=itemsStrings[i].split(",");
					deletemap.put("INDEXARCHCODE", sssStrings[0]);
					deletemap.put("OBJECTCODE", sssStrings[1]);
					eo.setDeletemap(deletemap);
					res0=eo.Delete();
				}
			}
				res += "show('"+res0+"');";
				res += "var rand=Math.floor(Math.random()*10000);";
			    res += "window.open('../indexmanage/indexdefuserlist.jsp?indexclass="+request.getParameter("indexclass")+"&indexarchcode="+request.getParameter("indexarchcode")+"&sid='+rand,'_self');";
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
