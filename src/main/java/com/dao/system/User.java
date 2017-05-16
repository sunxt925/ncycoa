/*
 * �������� 2006-7-18
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package com.dao.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.common.Format;
import com.common.UserException;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.OrgPosition;
import com.entity.system.SystemRole;
import com.entity.system.UserInfo;
import com.entity.system.UseridRoleid;

/**
 * Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class User
{
	public static void loginOut(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.removeAttribute("UserInfo"); // �ڵ�ǰ�Ự�������ǰ�û�
	}

	public static void login(String name, String pwd, HttpServletRequest request) throws Exception
	{
		DBObject db = new DBObject();
		HttpSession session = request.getSession();
		session.removeAttribute("UserInfo"); // �ڵ�ǰ�Ự�������ǰ�û�
		if (name == null) { throw new Exception("�û���Ϊ�գ�"); }
		name = name.trim();
		// ���������MD5����
		String pwd_md5 = Format.getMD5(pwd);
		// System.out.print(pwd_md5);
		// ����name�����ݿ�ȡ���û��Ļ�����Ϣ��Ϣ
		String sqlStr = "select * from system_user where user_code=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(name) };
		// String pwd = "";
		UserInfo UserInfo = null;
		DataTable dt = db.runSelectQuery(sqlStr, pp);
		if (dt != null && dt.getRowsCount() == 1)
		{
			if (!dt.get(0).getString("user_password").equals(pwd_md5))
			{
				throw new UserException("�������");
			}
			else
			{
				UserInfo = new UserInfo(name);
				UserInfo.setIp(request.getRemoteAddr());
				UserInfo.setAvailwidth(Format.NullToBlank(request.getParameter("availwidth")));
				UserInfo.setAvalilheight(Format.NullToBlank(request.getParameter("availheight")));
				
				UseridRoleid ur = new UseridRoleid();
				DataTable dtUseridRoleid = ur.getUseridDepartmentidRoleid(UserInfo.getUsername());
				if (dtUseridRoleid != null)
				{
					for (int i = 0; i < dtUseridRoleid.getRowsCount(); i++)
					{
						DataRow r1 = dtUseridRoleid.get(i);
						SystemRole role = new SystemRole(r1.getString("rolecode"));
						UserInfo.getRoles().add(role);
						
						if(UserInfo.getDefaultid() == null || UserInfo.getDefaultid().equals("")){
							UserInfo.setDefaultid(role.getRolecode());
						}
					}
				}
				
				String staffcode = UserInfo.getStaffcode();
				OrgPosition orgPosition = new OrgPosition();
				// DataTable dt2=orgPosition.getOrgposition(staffcode);
				DataTable dt2 = orgPosition.getOrgposition(staffcode);

				String orgcode = dt2.get(0).getString("orgcode");
				String staffname = dt2.get(0).getString("staffname");
				String positioncode = "";

				if (dt2.getRowsCount() == 1)
				{
					positioncode += dt2.get(0).getString("positioncode");
				}
				else
				{
					for (int i = 0; i < dt2.getRowsCount() - 1; i++)
					{

						positioncode += dt2.get(i).getString("positioncode") + ",";
					}
					positioncode += dt2.get(dt2.getRowsCount() - 1).getString("positioncode");
				}

//				com.cms.webflow.server.Util.InitServerPath(request);// ���Ի�webpath
//				com.cms.model.sysmng.login.User user = new com.cms.model.sysmng.login.User();
//				user.setZgdm(staffcode);
//				user.setZgxm(staffname);
//				user.setYybdm(orgcode);
//				user.setRolcod(positioncode);// ���һ�˶��λ��positioncode�ã��ֿ����磺0111011,011012.
//				// user.setEmail(��market@emsflow.com��);
//				request.getSession().setAttribute("USER", user);
			}
		}
		else
		{
			throw new UserException("���û������ڣ�");
		}
		// */
		// ����½�ɹ����û�����Ϊ��ǰ�û�����
		session.setMaxInactiveInterval(3600 * 100);
		session.setAttribute("UserInfo", UserInfo);
	}

	// ��ȡԱ����λ����
	public static String getOrgCode(String staffcode)
	{
		try
		{
			OrgPosition orgPosition = new OrgPosition();
			// DataTable dt2=orgPosition.getOrgposition(staffcode);
			DataTable dt2 = orgPosition.getOrgposition(staffcode);

			return dt2.get(0).getString("orgcode");
			// String staffname=dt2.get(0).getString("staffname");
		}
		catch (Exception e)
		{
			return null;
			// TODO: handle exception
		}

	}
}