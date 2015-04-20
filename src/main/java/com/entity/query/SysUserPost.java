package com.entity.query;

import java.util.Vector;

import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;

	/**
	 * @author 林茂
	 * 
	 * TODO 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
	 */
public class SysUserPost {
		public String post_id;// 岗位id
		public String user_code="";// 单位名

		public String unit_ccm = "";//单位编码
		public String newpost_id;

		public String getPost_id() {
			return post_id;
		}

		public void setPost_id(String postId) {
			post_id = postId;
		}

		public String getUser_code() {
			return user_code;
		}

		public void setUser_code(String userCode) {
			user_code = userCode;
		}

		public String getUnit_ccm() {
			return unit_ccm;
		}

		public void setUnit_ccm(String unitCcm) {
			unit_ccm = unitCcm;
		}


		public String getNewpost_id() {
			return newpost_id;
		}

		public void setNewpost_id(String newpostId) {
			newpost_id = newpostId;
		}		

		public SysUserPost()
		{

		}

		public SysUserPost(String PK)
		{
			
			try
			{
				DBObject db = new DBObject();
				String sql = "select * from SYSTEM_USER where user_code=?";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.String(PK) };
				DataTable dt = db.runSelectQuery(sql, pp);
				if (dt != null && dt.getRowsCount() == 1)
				{
					DataRow r = dt.get(0);
					this.post_id= PK;
					this.user_code= r.getString("user_code");
					this.unit_ccm  = r.getString("unit_ccm");
				
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		/**
		 * 添入
		 * 
		 * @return
		 */
		public boolean Insert()
		{
			try
			{
				DBObject db = new DBObject();
				String sql = "insert into SYSTEM_USER_POST(post_id,user_code,unit_ccm) values (?,?,?)";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{ new Parameter.Int(Integer.parseInt(this.post_id)), new Parameter.String(this.user_code),
						new Parameter.String(unit_ccm),
					 };
				if (db.run(sql, pp))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * 更新
		 * 
		 * @return
		 */
		public boolean Update()
		{
			try
			{
				DBObject db = new DBObject();
				String sql = "update SYSTEM_USER set post_id=? ,user_code=?,unit_ccm=? where post_id=?";
				Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
				{       new Parameter.Int(Integer.parseInt(this.newpost_id)),
						new Parameter.String(this.user_code),
						new Parameter.String(this.unit_ccm),
						new Parameter.Int(Integer.parseInt(this.post_id)), 
					 };
				if (db.run(sql, pp))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}

		/**
		 * 删除
		 * 
		 * @param BmString
		 * @return
		 */
		public boolean Delete(String BmString)
		{
			try
			{
				Vector v = new Vector();
				DBObject db = new DBObject();
				if (BmString.indexOf(",") == -1)
				{
					v.add("delete from SYSTEM_USER where user_code like '" + BmString
							+ "%'");
				}
				else
				{
					String[] bm = BmString.split(",");
					for (int i = 0; i < bm.length; i++)
					{
						v.add("delete from SYSTEM_USER where user_code like '" + bm[i]
								+ "%'");
					}
				}
				if (db.executeBatch(v))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
}
	