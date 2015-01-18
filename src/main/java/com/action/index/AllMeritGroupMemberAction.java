package com.action.index;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;

public class AllMeritGroupMemberAction extends ActionInterface {

	@Override
	public String getResult(HttpServletRequest request) {
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		if (action != null && action.equals("add"))
		{
			String[] objectcodes=request.getParameter("TBM_ALLMERITGROUPMEMBER.STAFFCODE").split(",");
			String[] orgcodes=request.getParameter("TBM_ALLMERITGROUPMEMBER.ORGCODE").split(",");
			String[] positioncodes=request.getParameter("TBM_ALLMERITGROUPMEMBER.POSITIONCODE").split(",");
			Map<String, String> addmapMap=new HashMap<String, String>();
			addmapMap.put("TBM_ALLMERITGROUPMEMBER.STAFFCODE", "1");
			addmapMap.put("TBM_ALLMERITGROUPMEMBER.ORGCODE", "1");
			addmapMap.put("TBM_ALLMERITGROUPMEMBER.POSITIONCODE", "1");
			eo.setAddmoreMap(addmapMap);
			String entity=request.getParameter("entity");
		    for(int i=0;i<objectcodes.length;i++){
				request.setAttribute("TBM_ALLMERITGROUPMEMBER.STAFFCODE", objectcodes[i]);
				request.setAttribute("TBM_ALLMERITGROUPMEMBER.ORGCODE", orgcodes[i]);
				request.setAttribute("TBM_ALLMERITGROUPMEMBER.POSITIONCODE", positioncodes[i]);
				eo.setEntity(entity);
				res0 = eo.Add(request);
				request.removeAttribute("TBM_ALLMERITGROUPMEMBER.STAFFCODE");
				request.removeAttribute("TBM_ALLMERITGROUPMEMBER.ORGCODE");
				request.removeAttribute("TBM_ALLMERITGROUPMEMBER.POSITIONCODE");
			}
			res += "show('"+res0+"');";
			//res +="window.close()";
			res += "var rand=Math.floor(Math.random()*10000);";
		    res += "window.open('../indexmanage/allmeritmanage/allmeritgroupmember.jsp?groupno="+request.getParameter("TBM_ALLMERITGROUPMEMBER.GROUPNO")+"&sid='+rand,'_self');";
	
		}
		else if(action != null && action.equals("del"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("items");
			if(itemsStrings==null){
				String groupno=request.getParameter("groupno");
				String staffcode=request.getParameter("staffcode");
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("GROUPNO", groupno);
				deletemap.put("STAFFCODE", staffcode);
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
			}
			else{
				
				for(int i=0;i<itemsStrings.length;i++)
				{
					Map<String,String> deletemap = new HashMap<String, String>();
					String[] ssString=itemsStrings[i].split(",");
					deletemap.put("GROUPNO", ssString[0]);
					deletemap.put("STAFFCODE", ssString[1]);
					eo.setDeletemap(deletemap);
					res0=eo.Delete();
				}
			}
			
				res += "show('"+res0+"');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../indexmanage/allmeritmanage/allmeritgroupmember.jsp?groupno="+request.getParameter("TBM_ALLMERITGROUPMEMBER.GROUPNO")+"&sid='+rand,'_self');";
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
