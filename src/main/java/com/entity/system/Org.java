package com.entity.system;
import java.util.Vector;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.performance.ReviewableObj;
public class Org implements ReviewableObj {
	private String OrgCode = "";
	
	private String OrgName = "";
	private String OrgSimpleName = "";
	private String OrgDesc = "";
	private String MemberCount = "";
	private String PositionCount = "";
	private String OffcieAddress = "";
	private String PostCode = "";
	private String ContactInfo = "";
	private String OrgClass = "";
	private String Createdate = "";
	private String NonLeafFlag = "";
	private String ParentOrgCode = "";
	private String adminClass="";
	
	private String type="depart";
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAdminClass() {
		return adminClass;
	}
	public void setAdminClass(String adminClass) {
		this.adminClass = adminClass;
	}
	public String getParentOrgCode() {
		return ParentOrgCode;
	}
	public void setParentOrgCode(String parentOrgCode) {
		ParentOrgCode = parentOrgCode;
	}
	public String getParentOrgName() {
		return ParentOrgName;
	}
	public void setParentOrgName(String parentOrgName) {
		ParentOrgName = parentOrgName;
	}

	private String ParentOrgName = "";
	private String BlongAdminOrgCode = "";
	private String BlongAdminOrgName = "";
	private String Memo = "";
	
	public Org()
	{
		
	}
	public Org(String PK)
	{
		try
		{
			DBObject db = new DBObject();
			String sql="select to_char(createdate,'yyyy-mm-dd') aa,t.* from base_org t where t.orgcode=?";
			
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(PK) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.OrgCode = PK;
				this.OrgName = r.getString("OrgName");
				this.OrgSimpleName = r.getString("OrgSimpleName");
				this.OrgDesc = r.getString("OrgDesc");
				this.MemberCount = r.getString("MemberCount");
				this.PositionCount = r.getString("PositionCount");
				this.PostCode = r.getString("PostCode");
				this.ContactInfo = r.getString("ContactInfo");
				this.OrgClass = r.getString("OrgClass");
				this.Createdate = r.getString("aa");
				this.NonLeafFlag = r.getString("NonLeafFlag");
				this.ParentOrgCode = r.getString("PARENTORGCODE");
				this.ParentOrgName = r.getString("PARENTORGNAME");
				this.BlongAdminOrgCode = r.getString("BlongAdminOrgCode");
				this.BlongAdminOrgName = r.getString("BlongAdminOrgName");
				this.adminClass=r.getString("adminclass");
				this.Memo = r.getString("Memo");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean Insert()
	{
		try
		{
			
			DBObject db = new DBObject();
			String sql = "insert into base_org(OrgCode,OrgName,OrgSimpleName,OrgDesc,MemberCount,PositionCount,OffcieAddress,PostCode,ContactInfo,OrgClass,Createdate,NonLeafFlag,ParentOrgCode,ParentOrgName,BlongAdminOrgCode,BlongAdminOrgName,Memo,adminclass) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(this.OrgCode), new Parameter.String(this.OrgName),new Parameter.String(this.OrgSimpleName), new Parameter.String(this.OrgDesc),new Parameter.String(this.MemberCount), new Parameter.String(this.PositionCount),new Parameter.String(this.OffcieAddress), new Parameter.String(this.PostCode),	new Parameter.String(this.ContactInfo),new Parameter.String(this.OrgClass), new Parameter.String(this.Createdate),new Parameter.String(this.NonLeafFlag), new Parameter.String(this.ParentOrgCode),	new Parameter.String(this.ParentOrgName),new Parameter.String(this.BlongAdminOrgCode), new Parameter.String(this.BlongAdminOrgName),new Parameter.String(this.Memo),new Parameter.String(this.adminClass)
			};
			if (db.run(sql, pp))
			{
				Org ogparent=new Org(this.OrgCode);
				String sqltemp="update base_org set NonLeafFlag=1 where orgcode="+ogparent.ParentOrgCode;
				if (db.run(sqltemp))
				{
					return true;
				}
				else
				{
					return false;
				}
				//return true;
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
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update base_org set OrgName=?,OrgSimpleName=?,OrgDesc=?,MemberCount=?,PositionCount=?,OffcieAddress=?,PostCode=?,ContactInfo=?,OrgClass=?,Createdate=?,NonLeafFlag=?,ParentOrgCode=?,ParentOrgName=?,BlongAdminOrgCode=?,BlongAdminOrgName=?,Memo=?,adminclass=? where OrgCode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		{  new Parameter.String(this.OrgName),new Parameter.String(this.OrgSimpleName), new Parameter.String(this.OrgDesc),new Parameter.String(this.MemberCount), new Parameter.String(this.PositionCount),new Parameter.String(this.OffcieAddress), new Parameter.String(this.PostCode),	new Parameter.String(this.ContactInfo),new Parameter.String(this.OrgClass), new Parameter.String(this.Createdate),new Parameter.String(this.NonLeafFlag), new Parameter.String(this.ParentOrgCode),	new Parameter.String(this.ParentOrgName),new Parameter.String(this.BlongAdminOrgCode), new Parameter.String(this.BlongAdminOrgName),new Parameter.String(this.Memo),new Parameter.String(this.adminClass), new Parameter.String(this.OrgCode)
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
	public boolean Delete(String BmString)
	{
		try
		{
			String unitccm="";
			Vector v = new Vector();
			DBObject db = new DBObject();
			//System.out.println(getByParentcode(BmString).getRowsCount()+"sdsd");
			if (BmString.indexOf(",") == -1)
			{     
				if(getByParentcode(BmString).getRowsCount()!=0) return false;
			
				else {
					unitccm=BmString;
					v.add("delete from BASE_ORG where ORGCODE like '" + BmString
						+ "%'");
			}}
			else
			{
				String[] bm = BmString.split(",");
				for (int i = 0; i < bm.length; i++)
				{
					if(getByParentcode(bm[i]).getRowsCount()!=0) return false;
					else
					{
						unitccm=bm[0];
				      	v.add("delete from BASE_ORG where ORGCODE like '" + bm[i]+ "%'");
					}
				}
			}
			if (db.executeBatch(v))
			{
				
				String parentcode=unitccm.substring(0, (unitccm.length()-4));
				//System.out.println(parentcode);
				//Org ogparent=new Org(this.OrgCode);
				String sqltemp="select * from base_org  where orgcode like '"+parentcode+"____'";
				DataTable dt=db.runSelectQuery(sqltemp);
				//System.out.println(dt.get(0).get(0));
				if(dt.getRowsCount()==0)
				{
					sqltemp="update base_org set NonLeafFlag=0 where orgcode="+parentcode;
				}
				
				if (db.run(sqltemp))
				{
					return true;
				}
				else
				{
					return false;
				}
				//return true;
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
	public DataTable getOrgList(int pageno, int perpage)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||OrgCode||'\">' as 选择,OrgCode as 机构编码,OrgName as 机构全称,OrgSimpleName as 机构简称,OrgDesc as 机构职能,MemberCount as 成员数,PositionCount as 岗位数,OffcieAddress as 办公地址,PostCode as 邮政编码,ContactInfo as 联系方式,OrgClass as 机构类别,adminclass as 行政类别,Createdate as 创建日期,NonLeafFlag as 非叶节点标志,ParentOrgCode as 上级部门编码,ParentOrgName as 上级部门名称,BlongAdminOrgCode as 挂靠行政机构,BlongAdminOrgName as 挂靠行政机构名,Memo as 备注,'<a href=\"unit_mod.jsp?bm='||OrgCode||'\" class=\"button4\">修 改</a> <a href=\"entity_column.jsp?bm='||OrgCode||'\" class=\"button4\">明 细</a>' as 操作  from base_org";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getOrgList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||OrgCode||'\">' as 选择,OrgCode as 机构编码,OrgName as 机构全称,OrgSimpleName as 机构简称,OrgDesc as 机构职能,MemberCount as 成员数,PositionCount as 岗位数,OffcieAddress as 办公地址,PostCode as 邮政编码,ContactInfo as 联系方式,Memo as 备注,'<a href=\"#\" onclick=F1(\"'||OrgCode||'\") class=\"button4\">修 改</a><a href=\"#\" onClick=dele(\"'||OrgCode||'\") class=\"button4\">删除</a>' as 操作   from base_org where PARENTORGCODE ='"+orgcode+"'order by orgcode";
			//String base_sql = "select '选择' as 选择,OrgCode as 机构编码,OrgName as 机构全称,OrgSimpleName as 机构简称,OrgDesc as 机构职能,MemberCount as 成员数,PositionCount as 岗位数,OffcieAddress as 办公地址,PostCode as 邮政编码,ContactInfo as 联系方式,Memo as 备注,'操作' as 操作   from base_org where PARENTORGCODE ='"+orgcode+"'order by orgcode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getOrgList2(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||OrgCode||'\">' as 选择,OrgCode as 机构编码,OrgName as 机构全称,OrgSimpleName as 机构简称,PositionCount as 岗位数   from base_org where PARENTORGCODE ='"+orgcode+"'order by orgcode";
			//String base_sql = "select '选择' as 选择,OrgCode as 机构编码,OrgName as 机构全称,OrgSimpleName as 机构简称,OrgDesc as 机构职能,MemberCount as 成员数,PositionCount as 岗位数,OffcieAddress as 办公地址,PostCode as 邮政编码,ContactInfo as 联系方式,Memo as 备注,'操作' as 操作   from base_org where PARENTORGCODE ='"+orgcode+"'order by orgcode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getOrgListAddName(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			

			String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||OrgCode||','||OrgName||'\">' as 选择,OrgCode as 机构编码,OrgName as 机构全称,OrgSimpleName as 机构简称,OrgDesc as 机构职能,MemberCount as 成员数,PositionCount as 岗位数,OffcieAddress as 办公地址,PostCode as 邮政编码,ContactInfo as 联系方式,Memo as 备注    from base_org where PARENTORGCODE ='"+orgcode+"'order by orgcode";
			//String base_sql = "select '选择' as 选择,OrgCode as 机构编码,OrgName as 机构全称,OrgSimpleName as 机构简称,OrgDesc as 机构职能,MemberCount as 成员数,PositionCount as 岗位数,OffcieAddress as 办公地址,PostCode as 邮政编码,ContactInfo as 联系方式,Memo as 备注,'操作' as 操作   from base_org where PARENTORGCODE ='"+orgcode+"'order by orgcode";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getOrgRoleList(int pageno, int perpage,String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			//System.out.println(rolecode);
			String base_sql = "select '<input type=\"hidden\" id=\"items\" name=\"items\" value=\"'||OrgCode||'\">' ,OrgCode as 机构编码,OrgName as 机构全称,OrgSimpleName as 机构简称,OrgDesc as 机构职能,MemberCount as 成员数,OrgClass as 机构类别,adminclass as 行政类别,'<a href=\"unit_positionlist.jsp?OrgCode='||OrgCode||'\" class=\"button4\">添加岗位</a>' as 操作   from base_org where OrgCode like'"+orgcode+"____'";

			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	//得到该表所有数据
	public DataTable getAllOrgList()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from base_org");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	


	public DataTable getAllOrgList(String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from base_org where PARENTORGCODE ='"+orgcode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTable getOrgName(String orgcode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from base_org where OrgCode ='"+orgcode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

public DataTable getTopList(){
		
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from BASE_ORG where PARENTORGCODE = 'NC' order by ORGCODE");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
public DataTable getByOrgcode(String orgcode){
	try
	{
		DBObject db = new DBObject();
		DataTable dt = db.runSelectQuery("select * from BASE_ORG where orgcode ='"+orgcode+"'");
		return dt;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}	
}
public DataTable getByParentcode(String Parentcode){
	try
	{
		DBObject db = new DBObject();
		DataTable dt = db.runSelectQuery("select * from BASE_ORG where PARENTORGCODE ='"+Parentcode+"'order by orgcode");
		return dt;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}	
}
public String getTrack(String orgcode,String res) throws Exception{
	
	DataTable dt=getByOrgcode(orgcode);
	//String code=dt.get(0).getString("ORDCODE");
	res=dt.get(0).getString("ORGNAME")+"&gt;&gt;"+res;
	String parentcode =dt.get(0).getString("PARENTORGCODE");
	if(!parentcode.equals("")){
		return getTrack(parentcode,res);
	}
	else
		return res;
	
}

public String getCode() {
	return OrgCode;
}

public void setCode(String orgCode) {
	OrgCode = orgCode;
}

public String getName() {
	return OrgName;
}

public void setName(String orgName) {
	OrgName = orgName;
}

public String getOrgSimpleName() {
	return OrgSimpleName;
}

public void setOrgSimpleName(String orgSimpleName) {
	OrgSimpleName = orgSimpleName;
}

public String getOrgDesc() {
	return OrgDesc;
}

public void setOrgDesc(String orgDesc) {
	OrgDesc = orgDesc;
}

public String getMemberCount() {
	return MemberCount;
}

public void setMemberCount(String memberCount) {
	MemberCount = memberCount;
}

public String getPositionCount() {
	return PositionCount;
}

public void setPositionCount(String positionCount) {
	PositionCount = positionCount;
}

public String getOffcieAddress() {
	return OffcieAddress;
}

public void setOffcieAddress(String offcieAddress) {
	OffcieAddress = offcieAddress;
}

public String getPostCode() {
	return PostCode;
}

public void setPostCode(String postCode) {
	PostCode = postCode;
}

public String getContactInfo() {
	return ContactInfo;
}

public void setContactInfo(String contactInfo) {
	ContactInfo = contactInfo;
}

public String getOrgClass() {
	return OrgClass;
}

public void setOrgClass(String orgClass) {
	OrgClass = orgClass;
}

public String getCreatedate() {
	return Createdate;
}

public void setCreatedate(String createdate) {
	Createdate = createdate;
}

public String getNonLeafFlag() {
	return NonLeafFlag;
}

public void setNonLeafFlag(String nonLeafFlag) {
	NonLeafFlag = nonLeafFlag;
}



public String getBlongAdminOrgCode() {
	return BlongAdminOrgCode;
}

public void setBlongAdminOrgCode(String blongAdminOrgCode) {
	BlongAdminOrgCode = blongAdminOrgCode;
}

public String getBlongAdminOrgName() {
	return BlongAdminOrgName;
}

public void setBlongAdminOrgName(String blongAdminOrgName) {
	BlongAdminOrgName = blongAdminOrgName;
}

public String getMemo() {
	return Memo;
}

public void setMemo(String memo) {
	Memo = memo;
}



public DataTable getAllStdList(String orgcode,String begin,String end,String docname,String doccode,String drawupperson,String gettype)
{
	try
	{
		DBObject db = new DBObject();
		String sql="";
		String searchdoccode="";
		String condition="";
		String nameselect="";
		String drawupdate="";
		String personcondition="";
		String getstdtypecondition="";
		if(doccode.equals("")){
			searchdoccode="";
		}else {
			searchdoccode=" and doccode like '%"+doccode+"%' ";
		}
		if((begin.equals(""))&&(end.equals(""))){
			drawupdate="";
		}else if((!begin.equals(""))&&(!end.equals(""))){
			drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
		}else if((!begin.equals(""))&&(end.equals(""))){
			drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') ";
		}else if((begin.equals(""))&&(!end.equals(""))){
			drawupdate=" and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
		}
		if(!docname.equals("")){//'<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,
			nameselect=" and DocVersionName like '%"+docname+"%' ";
		}
		if(!drawupperson.equals("")){
			personcondition=" and drawupperson like '%"+drawupperson+"%' ";
		}
		if(!gettype.equals("")){
			if(gettype.equals("gl")){
				getstdtypecondition=" and doccode like 'Q/NCYC.GL%' ";
			}else if(gettype.equals("gz")){
				getstdtypecondition=" and doccode like 'Q/NCYC.GZ%' ";
			}else if(gettype.equals("js")){
				getstdtypecondition=" and doccode like 'Q/NCYC.JS%' ";
			}
				
		}
		condition=condition+getstdtypecondition+nameselect+searchdoccode+drawupdate+personcondition;
		sql="select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"+orgcode+"')"+" and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+condition+" order by docno";
		DataTable dt = db.runSelectQuery(sql);
		return dt;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public DataTable getStdList(int pageno, int perpage,String orgcode,String begin,String end,String docname,String doccode,String sorttype,String drawupperson,String gettype)
{
	try
	{
		DBObject db = new DBObject();
		String condition="";
		String base_sql="";
		String searchdoccode="";
		String sort="";
		String nameselect="";
		String drawupdate="";
		String personcondition="";
		String getstdtypecondition="";
		if(doccode.equals("")){
			searchdoccode="";
		}else {
			searchdoccode=" and doccode like '%"+doccode+"%' ";
		}
		if(sorttype.equals("code")){
			sort=" order by doccode asc";
		}else{
			sort=" order by drawupdate desc";
		}
		if((begin.equals(""))&&(end.equals(""))){
			drawupdate="";
		}else if((!begin.equals(""))&&(!end.equals(""))){
			drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
		}else if((!begin.equals(""))&&(end.equals(""))){
			drawupdate=" and drawupdate>=to_date('"+begin+"','yyyy-MM-dd') ";
		}else if((begin.equals(""))&&(!end.equals(""))){
			drawupdate=" and drawupdate<=to_date('"+end+"','yyyy-MM-dd') ";
		}
		if(!docname.equals("")){//'<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,
			nameselect=" and DocVersionName like '%"+docname+"%' ";
		}
		if(!drawupperson.equals("")){
			personcondition=" and drawupperson like '%"+drawupperson+"%' ";
		}
		if(!gettype.equals("")){
			if(gettype.equals("gl")){
				getstdtypecondition=" and doccode like 'Q/NCYC.GL%' ";
			}else if(gettype.equals("gz")){
				getstdtypecondition=" and doccode like 'Q/NCYC.GZ%' ";
			}else if(gettype.equals("js")){
				getstdtypecondition=" and doccode like 'Q/NCYC.JS%' ";
			}
				
		}//,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">修改</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> <a href=\"#\" onClick=F2(\"'||docno||'\") class=\"button4\">上传文件</a> <a href=\"#\" onClick=F7(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">附件查看</a> <a href=\"#\" onClick=F8(\"'||docno||'\",\"'||docversionname||'\",\""+orgcode+"\") class=\"button4\">涉及岗位</a> <a href=\"#\" onClick=F9(\"'||docno||'\",\"'||DocVersionName||'\") class=\"button4\">文件查看</a>' as 操作
		condition=condition+nameselect+getstdtypecondition+searchdoccode+drawupdate+personcondition+sort;
		base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as 选择,doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,docno as 有无附件    from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"+orgcode+"')"+" and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+condition;

		String sql_run = Format.getFySql(base_sql, pageno, perpage);
		return db.runSelectQuery(sql_run);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}

public DataTable getAllStdListSearch(String begin,String end,String type)
{
	try
	{	
		DBObject db = new DBObject();
		String sql="";
		if(type.equals("his")){
			if((!begin.equals(""))&&(!end.equals(""))){
				String validenddate="validenddate>=to_date('"+begin+"','yyyy-MM-dd') and validenddate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS='历史版本' and "+validenddate+" order by docno";
			}else if((!begin.equals(""))&&(end.equals(""))){
				String validenddate="validenddate>=to_date('"+begin+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS='历史版本' and "+validenddate+" order by docno";
			}else if((begin.equals(""))&&(!end.equals(""))){
				String validenddate=" validenddate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS='历史版本' and "+validenddate+" order by docno";
			}else{
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS='历史版本' order by docno";
			}
		}else if(type.equals("del")){
			if((!begin.equals(""))&&(!end.equals(""))){
				String validenddate="validenddate>=to_date('"+begin+"','yyyy-MM-dd') and validenddate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='废除' and "+validenddate+" order by docno";
			}else if((!begin.equals(""))&&(end.equals(""))){
				String validenddate="validenddate>=to_date('"+begin+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='废除' and "+validenddate+" order by docno";
			}else if((begin.equals(""))&&(!end.equals(""))){
				String validenddate=" validenddate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='废除' and "+validenddate+" order by docno";
			}else{
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='废除' order by docno";
			}
		}else if(type.equals("new")){
			if((!begin.equals(""))&&(!end.equals(""))){
				String drawupdate="drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='新建' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' order by docno";
			}else if((!begin.equals(""))&&(end.equals(""))){
				String drawupdate="drawupdate>=to_date('"+begin+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='新建' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' order by docno";
			}else if((begin.equals(""))&&(!end.equals(""))){
				String drawupdate=" drawupdate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='新建' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' order by docno";
			}else{
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='新建' and DOCVERSIONSTATUS<>'历史版本' order by docno";
			}
		}else if(type.equals("mod")){
			if((!begin.equals(""))&&(!end.equals(""))){
				String drawupdate="drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='修订' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' order by docno";
			}else if((!begin.equals(""))&&(end.equals(""))){
				String drawupdate="drawupdate>=to_date('"+begin+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='修订' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' order by docno";
			}else if((begin.equals(""))&&(!end.equals(""))){
				String drawupdate=" drawupdate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='修订' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' order by docno";
			}else{
				sql="select * from std_docmetaversioninfo where belongdocno = 'no' and Flag='修订' and DOCVERSIONSTATUS<>'历史版本' order by docno";
			}
		}
		DataTable dt = db.runSelectQuery(sql);
		return dt;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public DataTable getStdListSearch(int pageno, int perpage,String begin,String end,String type,String sorttype)
{
	try
	{
		DBObject db = new DBObject();
		String sql="";
		String sort="";
		if(sorttype.equals("code")){
			sort=" order by doccode asc";
		}else{
			if(type.equals("his")){
				sort=" order by validenddate desc";
			}else if(type.equals("del")){
				sort=" order by validenddate desc";
			}else if(type.equals("new")){
				sort=" order by DRAWUPDATE desc";
			}else if(type.equals("mod")){
				sort=" order by DRAWUPDATE desc";
			}
		}
		if(type.equals("his")){
			if((!begin.equals(""))&&(!end.equals(""))){
				String validenddate="validenddate>=to_date('"+begin+"','yyyy-MM-dd') and validenddate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,validenddate as 生效结束日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS='历史版本' and "+validenddate+sort;
			}else if((!begin.equals(""))&&(end.equals(""))){
				String validenddate="validenddate>=to_date('"+begin+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,validenddate as 生效结束日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS='历史版本' and "+validenddate+sort;
			}else if((begin.equals(""))&&(!end.equals(""))){
				String validenddate=" validenddate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,validenddate as 生效结束日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS='历史版本' and "+validenddate+sort;
			}else{
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,validenddate as 生效结束日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and DOCVERSIONSTATUS='历史版本' "+sort;
			}
		}else if(type.equals("del")){
			if((!begin.equals(""))&&(!end.equals(""))){
				String validenddate="validenddate>=to_date('"+begin+"','yyyy-MM-dd') and validenddate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,validenddate as 废除日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='废除' and "+validenddate+sort;
			}else if((!begin.equals(""))&&(end.equals(""))){
				String validenddate="validenddate>=to_date('"+begin+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,validenddate as 废除日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='废除' and "+validenddate+sort;
			}else if((begin.equals(""))&&(!end.equals(""))){
				String validenddate=" validenddate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,validenddate as 废除日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='废除' and "+validenddate+sort;
			}else{
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,validenddate as 废除日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">删除</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='废除' "+sort;
			}
		}else if(type.equals("new")){
			if((!begin.equals(""))&&(!end.equals(""))){
				String drawupdate="drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='新建' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' "+sort;
			}else if((!begin.equals(""))&&(end.equals(""))){
				String drawupdate="drawupdate>=to_date('"+begin+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='新建' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' "+sort;
			}else if((begin.equals(""))&&(!end.equals(""))){
				String drawupdate=" drawupdate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='新建' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' "+sort;
			}else{
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 编制日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='新建'  and DOCVERSIONSTATUS<>'历史版本' "+sort;
			}
		}else if(type.equals("mod")){
			if((!begin.equals(""))&&(!end.equals(""))){
				String drawupdate="drawupdate>=to_date('"+begin+"','yyyy-MM-dd') and drawupdate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 修订日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='修订' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' "+sort;
			}else if((!begin.equals(""))&&(end.equals(""))){
				String drawupdate="drawupdate>=to_date('"+begin+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 修订日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='修订' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' "+sort;
			}else if((begin.equals(""))&&(!end.equals(""))){
				String drawupdate=" drawupdate<=to_date('"+end+"','yyyy-MM-dd')";
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 修订日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='修订' and "+drawupdate+"  and DOCVERSIONSTATUS<>'历史版本' "+sort;
			}else{
				sql="select doccode as 标准编号,DocVersionName as 标准名称,to_char(DRAWUPDATE,'yyyy-mm-dd') as 修订日期,docno as 有无附件,'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">标准信息</a> ' as 操作  from std_docmetaversioninfo where belongdocno = 'no' and Flag='修订' and DOCVERSIONSTATUS<>'历史版本' "+sort;
			}
		}
		String sql_run = Format.getFySql(sql, pageno, perpage);
		return db.runSelectQuery(sql_run);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
@SuppressWarnings("null")
public DataTable getStdListStatistics(int pageno,int perpage,String type)
{
	try
	{	
		DBObject db = new DBObject();
		String columnNameSql="select rowids as 序号,orgname as 部门名称,totalnum as 标准总数,gznum as 工作标准数量,glnum as 管理标准数量,jsnum as 技术标准数量   from std_statistics";
		
		DataTable dTable = db.runSelectQuery(columnNameSql);
		String orgsql="select orgcode,orgname from base_org";
		
		//String sql_run = Format.getFySql(orgsql, pageno, perpage);
		DataTable orgtable= db.runSelectQuery(orgsql);
		
		String likeGZ=" and doccode like 'Q/NCYC.GZ%' ";
		String likeGL=" and doccode like 'Q/NCYC.GL%' ";
		String likeJS=" and doccode like 'Q/NCYC.JS%' ";
		int j=1,k=0;
		for(int i=0;i<orgtable.getRowsCount()&&j<=perpage;i++){
			DataRow row=new DataRow(dTable);
			String orgcode=orgtable.get(i).get(0).toString();
			if(type.equals("cansee")){
				String totalsql="select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"+orgcode+"')"+" and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";;
				DataTable totaltable = db.runSelectQuery(totalsql);
				int totalcount=totaltable.getRowsCount();
				if(totalcount>0){
					k++;
						if (k > pageno * perpage) {
							row.set(0, pageno * perpage + j);
							j++;
							row.set(1, orgtable.get(i).get(1));
							row.set(2, totalcount);
							String GZsql = "select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"
									+ orgcode
									+ "')"
									+ " and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "
									+ likeGZ + " order by docno";
							;
							DataTable GZtable = db.runSelectQuery(GZsql);
							int GZcount = GZtable.getRowsCount();
							row.set(3, GZcount);
							String GLsql = "select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"
									+ orgcode
									+ "')"
									+ " and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "
									+ likeGL + " order by docno";
							;
							DataTable GLtable = db.runSelectQuery(GLsql);
							int GLcount = GLtable.getRowsCount();
							row.set(4, GLcount);
							String JSsql = "select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"
									+ orgcode
									+ "')"
									+ " and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "
									+ likeJS + " order by docno";
							;
							DataTable JStable = db.runSelectQuery(JSsql);
							int JScount = JStable.getRowsCount();
							row.set(5, JScount);
							dTable.addRow(row);
						}
				}
			}else if(type.equals("mydraw")){
				String totalsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";;
				DataTable totaltable = db.runSelectQuery(totalsql);
				int totalcount=totaltable.getRowsCount();
				if(totalcount>0){
					k++;
					if (k > pageno * perpage) {
					row.set(0, pageno*perpage+j);
					j++;
					row.set(1, orgtable.get(i).get(1));
					row.set(2,totalcount);
					String GZsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+likeGZ+" order by docno";;
					DataTable GZtable = db.runSelectQuery(GZsql);
					int GZcount=GZtable.getRowsCount();
					row.set(3,GZcount);
					String GLsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+likeGL+" order by docno";;
					DataTable GLtable = db.runSelectQuery(GLsql);
					int GLcount=GLtable.getRowsCount();
					row.set(4,GLcount);
					String JSsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+likeJS+" order by docno";;
					DataTable JStable = db.runSelectQuery(JSsql);
					int JScount=JStable.getRowsCount();
					row.set(5,JScount);
					dTable.addRow(row);
					}
				}
			}
		}
		return dTable;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}

public int getAllStdListStatistics(String type)
{
	try
	{	
		DBObject db = new DBObject();
		String orgsql="select orgcode,orgname from base_org";
		DataTable orgtable= db.runSelectQuery(orgsql);
		int j=0;
		for(int i=0;i<orgtable.getRowsCount();i++){
			String orgcode=orgtable.get(i).get(0).toString();
			if(type.equals("cansee")){
				String totalsql="select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"+orgcode+"')"+" and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";;
				DataTable totaltable = db.runSelectQuery(totalsql);
				int totalcount=totaltable.getRowsCount();
				if(totalcount>0){
					j++;
				}
			}else if(type.equals("mydraw")){
				String totalsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";;
				DataTable totaltable = db.runSelectQuery(totalsql);
				int totalcount=totaltable.getRowsCount();
				if(totalcount>0){
					j++;
				}
			}
		}
		return j;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return 0;
	}
}


@SuppressWarnings("null")
public DataTable getStdListStatistics(int pageno,int perpage,String type,String orgcode)
{
	try
	{	
		DBObject db = new DBObject();
		String columnNameSql="select rowids as 序号,orgname as 部门名称,totalnum as 标准总数,gznum as 工作标准数量,glnum as 管理标准数量,jsnum as 技术标准数量   from std_statistics";
		
		DataTable dTable = db.runSelectQuery(columnNameSql);
		
		String likeGZ=" and doccode like 'Q/NCYC.GZ%' ";
		String likeGL=" and doccode like 'Q/NCYC.GL%' ";
		String likeJS=" and doccode like 'Q/NCYC.JS%' ";
		Org org=new Org(orgcode);
		String orgnameString=org.getName();
			DataRow row=new DataRow(dTable);
			if(type.equals("cansee")){
				String totalsql="select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"+orgcode+"')"+" and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";;
				DataTable totaltable = db.runSelectQuery(totalsql);
				int totalcount=totaltable.getRowsCount();
				if(totalcount>0){
							row.set(0, "1");
							row.set(1, orgnameString);
							row.set(2, totalcount);
							String GZsql = "select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"
									+ orgcode
									+ "')"
									+ " and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "
									+ likeGZ + " order by docno";
							;
							DataTable GZtable = db.runSelectQuery(GZsql);
							int GZcount = GZtable.getRowsCount();
							row.set(3, GZcount);
							String GLsql = "select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"
									+ orgcode
									+ "')"
									+ " and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "
									+ likeGL + " order by docno";
							;
							DataTable GLtable = db.runSelectQuery(GLsql);
							int GLcount = GLtable.getRowsCount();
							row.set(4, GLcount);
							String JSsql = "select * from std_docmetaversioninfo where doccode in (select doccode from std_docorg where orgcode='"
									+ orgcode
									+ "')"
									+ " and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "
									+ likeJS + " order by docno";
							;
							DataTable JStable = db.runSelectQuery(JSsql);
							int JScount = JStable.getRowsCount();
							row.set(5, JScount);
							dTable.addRow(row);
				}
			}else if(type.equals("mydraw")){
				String totalsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' order by docno";;
				DataTable totaltable = db.runSelectQuery(totalsql);
				int totalcount=totaltable.getRowsCount();
				if(totalcount>0){
					row.set(0, "1");
					row.set(1, orgnameString);
					row.set(2,totalcount);
					String GZsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+likeGZ+" order by docno";;
					DataTable GZtable = db.runSelectQuery(GZsql);
					int GZcount=GZtable.getRowsCount();
					row.set(3,GZcount);
					String GLsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+likeGL+" order by docno";;
					DataTable GLtable = db.runSelectQuery(GLsql);
					int GLcount=GLtable.getRowsCount();
					row.set(4,GLcount);
					String JSsql="select * from std_docmetaversioninfo where drawuporg ='"+orgcode+"' and belongdocno = 'no' and DOCVERSIONSTATUS<>'历史版本' and flag<>'废除' "+likeJS+" order by docno";;
					DataTable JStable = db.runSelectQuery(JSsql);
					int JScount=JStable.getRowsCount();
					row.set(5,JScount);
					dTable.addRow(row);
					}
			}
		return dTable;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}


}
