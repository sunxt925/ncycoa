package com.action.system;

import javax.servlet.http.*;

import com.entity.system.SysMenuPrivilege;
import com.action.*;
import com.db.DBObject;
import com.db.DataTable;
import com.db.Parameter;
public class SysMenuPrivilegeAction extends ActionInterface{

	public String getResult(HttpServletRequest request)
	{
		String res = "";
		String action = request.getParameter("act");
		String result=request.getParameter("result");
		String[] temp=result.split(";");
		SysMenuPrivilege u=new SysMenuPrivilege();
		//System.out.println(result);
		//User u = null;
		if (action != null && action.equals("add"))
		{
			//System.out.println(temp.length);
			//System.out.println(temp[temp.length-1]);
			SysMenuPrivilege[] sym=new SysMenuPrivilege[temp.length-1];
			//System.out.println(sym.length);
			for(int i=0;i<temp.length-1;i++)
			{
				sym[i]=new SysMenuPrivilege();
				sym[i].setLevel_code(temp[i]);
				sym[i].setRole_id(temp[temp.length-1]);
				//System.out.println(sym[i].getRole_id());
			}
			if(u.Delete(temp[temp.length-1])&&u.Insert(sym))
			{
				res += "show('�޸�Ȩ�޳ɹ���');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var roid=\""+temp[temp.length-1]+"\";";
				res += "window.open('../xtwh/privillege/privillege.jsp?sid='+rand+'&roid='+roid,'_self');";
				//res += "window.open('../xtwh/privillege/privillege.jsp?roid='+roid+'&sid='+rand,'_self');";
			}
			else
			{
				res += "show('�޸�Ȩ��ʧ�ܣ����������ڱ����ظ������飡');";
				res += "var rand=Math.floor(Math.random()*10000);";
				res +="var roid=\""+temp[temp.length-1]+"\";";
				res += "window.open('../xtwh/privillege/privillege.jsp?sid='+rand+'&roid='+roid,'_self');";
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

