package com.entity.system;

import javax.servlet.http.HttpServletRequest;

import com.common.*;
import com.db.*;

public class SystemEntity
{
	public String entity_code = "";
	public String entity_name = "";
	public String entity_comment = "";

	public SystemEntity()
	{

	}

	public SystemEntity(String entity_code)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db
					.runSelectQuery("select * from system_entity where entity_code='"
							+ entity_code + "'");
			if (dt != null && dt.getRowsCount() == 1)
			{
				this.setEntity_code(entity_code);
				this.setEntity_name(dt.get(0).getString("entity_name"));
				this.setEntity_comment(dt.get(0).getString("entity_comment"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public ExecuteResult Update()
	{
		ExecuteResult er = new ExecuteResult();
		try
		{
			DBObject db = new DBObject();
			String sql = "update system_entity set entity_name=?,entity_comment=? where entity_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.entity_name),
					new Parameter.String(this.entity_comment),
					new Parameter.String(this.entity_code) };
			db.run(sql, pp);
			String db_comment = this.entity_name + "#" + this.entity_comment;
			// 更新数据库表的COMMENT
			db.run("comment on table " + this.entity_code + " is '"
					+ db_comment + "'");
			er.setRes(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			er.setRes(false);
			er.setRes_str(e.getMessage());
		}
		return er;
	}

	public ExecuteResult UpdateColumn(HttpServletRequest request)
	{
		ExecuteResult er = new ExecuteResult();
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db
					.runSelectQuery("select column_code from system_entity_column where entity_code='"
							+ this.entity_code + "'");
			if (dt != null && dt.getRowsCount() > 0)
			{
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					String column_code = dt.get(i).getString("column_code");
					String column_name = Format.NullToBlank(request
							.getParameter("column_name_" + column_code));
					String column_comment = Format.NullToBlank(request
							.getParameter("column_comment_" + column_code));
					String code_entity = Format.NullToBlank(request
							.getParameter("code_entity_" + column_code));
					String edit_code = Format.NullToBlank(request
							.getParameter("edit_code_" + column_code));
					String check_code = Format.NullToBlank(request
							.getParameter("check_code_" + column_code));
					String check_parameter = Format.NullToBlank(request
							.getParameter("check_parameter_" + column_code));
					// 更新system_entity_column
					String sql = "update system_entity_column set column_name=?,column_comment=?,code_entity=?,edit_code=?,check_code=?,check_parameter=? where entity_code=? and column_code=?";
					Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
					{ new Parameter.String(column_name),
							new Parameter.String(column_comment),
							new Parameter.String(code_entity),
							new Parameter.String(edit_code),
							new Parameter.String(check_code),
							new Parameter.String(check_parameter),
							new Parameter.String(this.entity_code),
							new Parameter.String(column_code) };
					db.run(sql, pp);
					String db_comment = column_name + "#" + column_comment;
					// 更新数据库字段的COMMENT
					db.run("comment on column " + this.entity_code + "."
							+ column_code + " is '" + db_comment + "'");
					er.setRes(true);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			er.setRes(false);
			er.setRes_str(e.getMessage());
		}
		return er;
	}

	public DataTable getEntityList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();
			String base_sql = "select '选择' as 选择,entity_code,entity_code as 实体表名,entity_name as 实体名称,entity_comment as 实体说明,'操作' as 操作  from system_entity";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			// System.out.print(sql_run);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	// 得到该表所有数据
	public DataTable getAllEntityList()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from system_entity");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public DataTable getEntityColumn(String entity_code)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db
					.runSelectQuery("select * from system_entity_column where entity_code='"
							+ entity_code + "'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public ExecuteResult initEntity(HttpServletRequest request)
	{
		ExecSql comm = null;
		String sql = "";
		Parameter.SqlParameter[] pp = null;
		ExecuteResult er = new ExecuteResult();
		try
		{
			String entity_name = Format.NullToBlank(
					request.getParameter("init_entity_name")).toUpperCase();
			DBObject db = new DBObject();
			// 判断当前是否有这个实体
			String sqla = "select 1 from user_tables where table_name='"
					+ entity_name + "'";
			// 判断当前实例是否已经存在
			String sqlb = "select 1 from system_entity where entity_code='"
					+ entity_name + "'";
			// 获取这张表的comments
			String entity_cname = "";
			String entity_ccomment = "";
			String column_cname = "";
			String column_ccomment = "";
			if (db.getRowsCount(sqla) == 1)
			{
				String entity_comments = db
						.executeOneValue("select comments from user_tab_comments where table_name='"
								+ entity_name + "'");
				DataTable dt_entity_column = db
						.runSelectQuery("select column_name,data_type,data_length,nullable from user_tab_cols where table_name='"
								+ entity_name + "'");
				if (entity_comments.indexOf("#") > -1)
				{
					entity_cname = entity_comments.substring(0, entity_comments
							.indexOf("#"));
					entity_ccomment = entity_comments.substring(entity_comments
							.indexOf("#") + 1, entity_comments.length());
				}
				else
				{
					entity_cname = entity_comments;
				}
				if (db.getRowsCount(sqlb) == 1)
				{
					// 更新相应的字段
					sql = "update system_entity set entity_name=?,entity_comment=? where entity_code=?";
					pp = new Parameter.SqlParameter[]
					{ new Parameter.String(entity_cname),
							new Parameter.String(entity_ccomment),
							new Parameter.String(entity_name) };
					comm = SqlHelper.helper().createCommand(sql, pp);
					comm.beginTransaction();
					comm.execute();
					// 更新相应的字段记录，有就更新，没有就新增
					if (dt_entity_column != null
							&& dt_entity_column.getRowsCount() > 0)
					{
						for (int i = 0; i < dt_entity_column.getRowsCount(); i++)
						{
							DataRow r = dt_entity_column.get(i);
							String column_code = r.getString("column_name");
							String column_db_comment = db
									.executeOneValue("select comments from user_col_comments where table_name='"
											+ entity_name
											+ "' and column_name='"
											+ column_code + "'");
							if (column_db_comment.indexOf("#") > -1)
							{
								column_cname = column_db_comment.substring(0,
										column_db_comment.indexOf("#"));
								column_ccomment = column_db_comment.substring(
										column_db_comment.indexOf("#") + 1,
										column_db_comment.length());
							}
							else
							{
								column_cname = column_db_comment;
								column_ccomment = "";
							}
							// 判断是不是主键
							String isPrimaryKey = "N";
							if (db
									.getRowsCount("select 1 from user_constraints a,user_cons_columns b where a.constraint_name=b.constraint_name and a.constraint_type='P' and a.table_name='"
											+ entity_name
											+ "' and b.column_name='"
											+ column_code + "'") == 1)
							{
								isPrimaryKey = "Y";
							}
							// 获取外键引用表
							String foreign_entity = "";
							DataTable dt_f = db
									.runSelectQuery("select TABLE_NAME from user_constraints where CONSTRAINT_NAME = (select a.r_constraint_name from user_constraints a, user_cons_columns b where a.constraint_name = b.constraint_name and a.constraint_type = 'R' and a.table_name = '"
											+ entity_name
											+ "' and b.column_name = '"
											+ column_code + "')");
							if (dt_f.getRowsCount() == 1)
							{
								foreign_entity = dt_f.get(0).getString(
										"TABLE_NAME");
							}
							// 判断是否有这个字段记录
							if (db
									.getRowsCount("select 1 from system_entity_column where entity_code='"
											+ entity_name
											+ "' and column_code='"
											+ column_code + "'") == 0)
							{
								sql = "insert into system_entity_column (entity_code,column_code,column_name,column_comment,column_type,column_length,IS_PRIMARYKEY,foreign_entity,nullable) values (?,?,?,?,?,?,?,?,?)";
								pp = new Parameter.SqlParameter[]
								{
										new Parameter.String(entity_name),
										new Parameter.String(column_code),
										new Parameter.String(column_cname),
										new Parameter.String(column_ccomment),
										new Parameter.String(r
												.getString("data_type")),
										new Parameter.String(r
												.getString("data_length")),
										new Parameter.String(isPrimaryKey),
										new Parameter.String(foreign_entity),
										new Parameter.String(r
												.getString("nullable")) };
							}
							else
							{
								sql = "update system_entity_column set column_name=?,column_comment=?,column_type=?,column_length=?,is_primarykey=?,foreign_entity=?,nullable=? where entity_code=? and column_code=?";
								pp = new Parameter.SqlParameter[]
								{
										new Parameter.String(column_cname),
										new Parameter.String(column_ccomment),
										new Parameter.String(r
												.getString("data_type")),
										new Parameter.String(r
												.getString("data_length")),
										new Parameter.String(isPrimaryKey),
										new Parameter.String(foreign_entity),
										new Parameter.String(r
												.getString("nullable")),
										new Parameter.String(entity_name),
										new Parameter.String(column_code) };
							}
							comm.setCommand(sql, pp);
							comm.execute();
						}
					}
				}
				else
				{
					// 增加一条主记录
					sql = "insert into system_entity(entity_code,entity_name,entity_comment) values (?,?,?)";
					pp = new Parameter.SqlParameter[]
					{ new Parameter.String(entity_name),
							new Parameter.String(entity_cname),
							new Parameter.String(entity_ccomment) };
					comm = SqlHelper.helper().createCommand(sql, pp);
					comm.beginTransaction();
					comm.execute();
					// 增加相应的字段记录
					if (dt_entity_column != null
							&& dt_entity_column.getRowsCount() > 0)
					{
						for (int i = 0; i < dt_entity_column.getRowsCount(); i++)
						{
							DataRow r = dt_entity_column.get(i);
							String column_code = r.getString("column_name");
							String column_db_comment = db
									.executeOneValue("select comments from user_col_comments where table_name='"
											+ entity_name
											+ "' and column_name='"
											+ column_code + "'");
							if (column_db_comment.indexOf("#") > -1)
							{
								column_cname = column_db_comment.substring(0,
										column_db_comment.indexOf("#"));
								column_ccomment = column_db_comment.substring(
										column_db_comment.indexOf("#") + 1,
										column_db_comment.length());
							}
							else
							{
								column_cname = column_db_comment;
								column_ccomment = "";
							}
							// 判断是不是主键
							String isPrimaryKey = "N";
							if (db
									.getRowsCount("select 1 from user_constraints a,user_cons_columns b where a.constraint_name=b.constraint_name and a.constraint_type='P' and a.table_name='"
											+ entity_name
											+ "' and b.column_name='"
											+ column_code + "'") == 1)
							{
								isPrimaryKey = "Y";
							}
							// 获取外键引用表
							String foreign_entity = "";
							DataTable dt_f = db
									.runSelectQuery("select TABLE_NAME from user_constraints where CONSTRAINT_NAME = (select a.r_constraint_name from user_constraints a, user_cons_columns b where a.constraint_name = b.constraint_name and a.constraint_type = 'R' and a.table_name = '"
											+ entity_name
											+ "' and b.column_name = '"
											+ column_code + "')");
							if (dt_f.getRowsCount() == 1)
							{
								foreign_entity = dt_f.get(0).getString(
										"TABLE_NAME");
							}
							sql = "insert into system_entity_column (entity_code,column_code,column_name,column_comment,column_type,column_length,IS_PRIMARYKEY,foreign_entity) values (?,?,?,?,?,?,?,?)";
							pp = new Parameter.SqlParameter[]
							{
									new Parameter.String(entity_name),
									new Parameter.String(column_code),
									new Parameter.String(column_cname),
									new Parameter.String(column_ccomment),
									new Parameter.String(r
											.getString("data_type")),
									new Parameter.String(r
											.getString("data_length")),
									new Parameter.String(isPrimaryKey),
									new Parameter.String(foreign_entity) };
							comm.setCommand(sql, pp);
							comm.execute();
						}
					}
				}
				comm.commit();
				er.setRes(true);
				er.setRes_str("初始化成功！");
			}
			else
			{
				er.setRes(false);
				er.setRes_str("系统中不存在这张表！");
			}
		}
		catch (Exception e)
		{
			try
			{
				if (comm != null)
				{
					comm.rollback();
				}
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
			}
			e.printStackTrace();
			er.setRes(false);
			er.setRes_str("执行错误：" + e.getMessage());
		}
		finally
		{
			if (comm != null)
			{
				comm.Dispose();
			}
		}
		return er;
	}

	public String getEntity_code()
	{
		return entity_code;
	}

	public void setEntity_code(String entityCode)
	{
		entity_code = entityCode;
	}

	public String getEntity_name()
	{
		return entity_name;
	}

	public void setEntity_name(String entityName)
	{
		entity_name = entityName;
	}

	public String getEntity_comment()
	{
		return entity_comment;
	}

	public void setEntity_comment(String entityComment)
	{
		entity_comment = entityComment;
	}

}
