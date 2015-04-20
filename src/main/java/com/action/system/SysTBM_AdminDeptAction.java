package com.action.system;

import javax.servlet.http.*;
import com.entity.system.TBM_AdminDept;
import com.action.*;

public class SysTBM_AdminDeptAction extends ActionInterface{

	public String getResult(HttpServletRequest request)
	{
		//System.out.println("运行到这里Action");
		String res = "";
		String action = request.getParameter("act");
		String staffcodes=request.getParameter("staffcode");
		String orgcodes=request.getParameter("orgcode");
		
		//System.out.println(staffcodes);
		//System.out.println(orgcodes);
		//String[] staffcode=staffcodes.split(";");
		String[] orgcode=orgcodes.split(";");
		boolean flag=false;
		TBM_AdminDept tm=new TBM_AdminDept();
		if (action != null && action.equals("add"))
		{
			
			//for(int i=0;i<staffcode.length;i++)
			//{
			tm.Delete(staffcodes);
				for(int j=0;j<orgcode.length;j++)
				{
					tm.setStaffCode(staffcodes);
					
					tm.setOrgCode(orgcode[j]);
					if(!tm.Insert())
					{
						flag=true;
					}
				}
				
			//}
			if(flag)
			{
				res += "MessageBox.Show(null,'修改权限失败！',null,'LogOK','Error',1,'修改权限失败，可能是由于编码重复，请检查！');";
				res += "window.open('../xtwh/leaderDepartment/PerformanceInputRights.jsp','_self');";
			}
			else{
				res += "MessageBox.Show(null,'修改权限成功！',null,'LogOK',null,1,'修改权限成功');";
				res += "window.open('../xtwh/leaderDepartment/PerformanceInputRights.jsp','_self');";
			}
				
			
			
		}
		else if (action != null && action.equals("modify"))
		{
			/*u=new User();
			u.setUsername(Format.NullToBlank(request.getParameter("username")));
			u.setChinesename(Format.NullToBlank(request.getParameter("chinesename")));
			u.setPassword(Format.NullToBlank(request.getParameter("password")));

			if (u.Update())
			{
				res += "MessageBox.Show(null,'保存成功！',null,'LogOK',null,1,'修改成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/privillege/privillege.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'保存失败！',null,'LogOK','Error',1,'修改失败，可能是由于编码重复，请检查！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/privillege/privillege.jsp?sid='+rand,'_self');";
			}*/
		}
		if (action != null && action.equals("modify"))
		{
			
            /*String role_id=request.getParameter("role_id");
            DBObject db=new DBObject();
			String sql="delete  from SYSTEM_MENU_PRIVILLEGE where role_id like ?";
			 Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			   { new Parameter.String(role_id) };
			 try {
				DataTable dt = db.runSelectQuery(sql, pp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String[] ids = request.getParameterValues("items");
			String para = "";
			for (int i = 0; i < ids.length; i++)
			{
				if (i == ids.length - 1)
				{
					para = para + ids[i];
				}
				else
				{
					para = para + ids[i] + ",";
				}
			}
			SysMenuPrivilege u=new SysMenuPrivilege();
			if (u.Delete(para,role_id))
			{
				res += "MessageBox.Show(null,'删除成功！',null,'LogOK',null,1,'删除成功');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/user/usermanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'删除失败！',null,'LogOK','Error',1,'删除失败，请与管理员联系！');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/user/usermanage.jsp?sid='+rand,'_self');";
			}*/
		}
		return res;
	}
}

