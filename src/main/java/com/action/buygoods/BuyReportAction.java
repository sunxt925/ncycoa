package com.action.buygoods;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.business.BuyReportItem;
import com.common.EntityOperation;
import com.entity.system.UserInfo;

public class BuyReportAction extends ActionInterface{
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
			map.put("FIRSTAUDITFLAG", "00");
			map.put("SECONDAUDITFLAG", "00");
			map.put("FINALAUDITFLAG", "00");
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
			
			String[] itemsStrings=request.getParameter("items").split(",");
			
				for(int i=0;i<itemsStrings.length;i++)
				{
					Map<String,String> deletemap = new HashMap<String, String>();
					
					deletemap.put("REPORTNO", itemsStrings[i]);
					
					eo.setDeletemap(deletemap);
					res0=eo.Delete();
				}
				res += "show('"+res0+"');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../buygoods/reportmanage/reportmanage.jsp?sid='+rand,'_self');";
			
			
			
		}
		else if(action !=null&&action.equals("autoget"))
		{
			res0=BuyReportItem.AutoGetreport();
			res += "show('"+res0+"');";
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../buygoods/reportmanage/reportmanage.jsp?sid='+rand,'_self');";
		}
		//呈报
		else if(action!=null&& action.equals("report"))
		{
			String[] itemsStrings=request.getParameter("items").split(",");
			String buymode=request.getParameter("buymode");
			if(BuyReportItem.Reportcheck(itemsStrings))
			{
			res0=BuyReportItem.Report(itemsStrings,buymode,"11",((UserInfo)request.getSession().getAttribute("UserInfo")).getStaffcode());
			res += "show('"+res0+"');";}
			else {
				res += "alert('呈报项目有空值，无法呈报');";
			}
			res += "var rand=Math.floor(Math.random()*10000);";
			res += "window.open('../buygoods/reportmanage/reportmanage.jsp?sid='+rand,'_self');";
		}
		//采购办审核
		else if(action!=null&& action.equals("auditByOffice"))
		{
			 String entity=request.getParameter("entity");
				//System.out.println(request.getParameter("STD_DOCMETAVERSIONINFO.STD_STOREFILEFLAG"));
			
			 String reportno=request.getParameter("COM_BUYREPORTITEM.REPORTNO");
				String secondflag=request.getParameter("COM_BUYREPORTITEM.SECONDAUDITFLAG");
					eo.setEntity(entity);
					res0 = eo.Update(request);	
					if(secondflag.equals("21")){
						BuyReportItem.modGoodsstate(reportno, "22","11");
					}else {
						BuyReportItem.modGoodsstate(reportno, "20","11");
					}
				res += "show('"+res0+"');";
				res += "window.close();";
		}
	
		else if(action!=null&&action.equals("final_audit"))
		{
			 String entity=request.getParameter("entity");
			String reportno=request.getParameter("COM_BUYREPORTITEM.REPORTNO");
			String finalflag=request.getParameter("COM_BUYREPORTITEM.FINALAUDITFLAG");
				eo.setEntity(entity);
				res0 = eo.Update(request);	
				if(finalflag.equals("31")){
					BuyReportItem.modGoodsstate(reportno, "31","22");
				}else {
					BuyReportItem.modGoodsstate(reportno, "30","22");
				}
				
				res += "show('"+res0+"');";
				res += "window.close();";
		}
		
		return res;
	}

}
