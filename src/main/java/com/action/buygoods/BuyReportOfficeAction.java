package com.action.buygoods;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.business.BuyGoodsItem;
import com.business.BuyReportItem;
import com.common.EntityOperation;

public class BuyReportOfficeAction extends ActionInterface{
	public String getResult(HttpServletRequest request) {
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		if (action != null && action.equals("add"))
		{
			
			String entity=request.getParameter("entity");
			//String sequence=request.getParameter("sequence");
		   // eo.setSequence(sequence);
			String reportno=BuyReportItem.getReportno();
			Map<String, String> map=new HashMap<String, String>();
			map.put("REPORTNO", reportno);
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
			String reportno=request.getParameter("reportno");
				for(int i=0;i<itemsStrings.length;i++)
				{
					BuyGoodsItem.deleteReportgoods(itemsStrings[i]);
				}
				//res += "show('"+res0+"');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../buygoods/buyreportitemlist.jsp?reportno="+reportno+"&sid='+rand,'_self');";
			
			
			
		}
		//手动增加呈报物资
		else if(action != null && action.equals("add_goods"))
		{
			String[] itemsStrings=request.getParameterValues("items");
			String reportno=request.getParameter("reportno");
			res0=BuyReportItem.AddReportItem(itemsStrings, reportno);
			
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			//res += "window.open('../buygoods/buyreportitemlist.jsp?reportno="+reportno+"&sid='+rand,'_self');";
		
		}
		//归口部门审核
		else if(action != null && action.equals("audit"))
		{
			String[] itemsStrings=request.getParameterValues("items");
			res0=BuyGoodsItem.audit(itemsStrings);
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
		}
	
		
		
		return res;
	}
}
