package com.action.system;

import javax.servlet.http.*;

import com.entity.system.SysMenuPrivilege;
import com.entity.system.TBM_MeritInputTaskPerson;
import com.action.*;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;
public class SysTBM_TaskPersonAction extends ActionInterface{

	public String getResult(HttpServletRequest request)
	{
		//System.out.println("���е�����Action");
		String res = "";
		String action = request.getParameter("act");
		String staffcodes=request.getParameter("staffcode");
		String orgcodes=request.getParameter("orgcode");
		String worktype=request.getParameter("worktype");
		//System.out.println(staffcodes);
		//System.out.println(orgcodes);
		//String[] staffcode=staffcodes.split(";");
		String[] orgcode=orgcodes.split(";");
		boolean flag=false;
		TBM_MeritInputTaskPerson tm=new TBM_MeritInputTaskPerson();
		if (action != null && action.equals("add"))
		{
			
			//for(int i=0;i<staffcode.length;i++)
			//{
			tm.Delete(staffcodes);
				for(int j=0;j<orgcode.length;j++)
				{
					tm.setStaffCode(staffcodes);
					
					tm.setOrgCode(orgcode[j]);
					tm.setTaskType(worktype);
					if(!tm.Insert())
					{
						flag=true;
					}
				}
				
			//}
			if(flag)
			{
				res += "show('�޸�Ȩ��ʧ�ܣ����������ڱ����ظ������飡');";
				res += "window.open('../xtwh/PerformanceInputRights/PerformanceInputRights.jsp','_self');";
			}
			else{
				res += "show('�޸�Ȩ�޳ɹ�');";
				res += "window.open('../xtwh/PerformanceInputRights/PerformanceInputRights.jsp','_self');";
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
				res += "MessageBox.Show(null,'����ɹ���',null,'LogOK',null,1,'�޸ĳɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/privillege/privillege.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'����ʧ�ܣ�',null,'LogOK','Error',1,'�޸�ʧ�ܣ����������ڱ����ظ������飡');";
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
				res += "MessageBox.Show(null,'ɾ���ɹ���',null,'LogOK',null,1,'ɾ���ɹ�');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/user/usermanage.jsp?sid='+rand,'_self');";
			}
			else
			{
				res += "MessageBox.Show(null,'ɾ��ʧ�ܣ�',null,'LogOK','Error',1,'ɾ��ʧ�ܣ��������Ա��ϵ��');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('../xtwh/user/usermanage.jsp?sid='+rand,'_self');";
			}*/
		}
		return res;
	}
}

