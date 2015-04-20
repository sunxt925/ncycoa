package com.action.system;

import javax.servlet.http.*;
import com.action.*;
import com.db.DBObject;
import com.db.DataTable;
import com.entity.system.unitclass;
import com.entity.system.Org;
import com.common.*;

public class SystemUnitAction extends ActionInterface
{
	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		
		Org og=null;
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
			String[] ids=request.getParameterValues("org");//
			String para="";
			for (int i=0;i<ids.length;i++)
			{
				if (i==ids.length-1)
				{
		     		para=para+ids[i];
				}
				else
				{
					para=para+ids[i]+",";
				}
			}
			og=new Org();
			try{
				DBObject  db=new DBObject();
				String sql="select * from base_orgposition   where orgcode='"+para+"'";
				DataTable dt=db.runSelectQuery(sql);
				if(dt!=null&&dt.getRowsCount()>=1){
					String sql2="delete from base_orgposition   where orgcode='"+para+"'";
					db.run(sql2);
					if(og.Delete(para))
					{
						res += "show('删除成功');";
						res+="parent.window.location.reload();";
						
					}
					else
					{
						res += "show('删除失败，可能是有子单位请先删除子单位！');";
						res+="parent.window.location.reload();";
						
					}
				}
			}catch (Exception e){
				
			}
			
			
		}
		else
		{

		}
		return res;
	}
}