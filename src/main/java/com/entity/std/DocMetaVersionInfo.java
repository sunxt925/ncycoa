package com.entity.std;

import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.ftp.FtpStoreFile;
import com.ftp.FtpStore;

public class DocMetaVersionInfo {
	    private String DocNo="";              //�ĵ���ˮ��
	    private String DocCode="";           //�ĵ�����
	    private String DocVersionName="";    //�ĵ��汾����
	    private String DocVersion ="";       //�ĵ��汾
	    private String BelongDocNo ="";      //�������ĵ�
	    private String BelongMode ="";        //����ģʽ
	    private String DocVersionStatus ="";  //�汾״̬
	    private String UpdateFlag ="";        //�޶���ʶ
	    private String DocClassCode="";       //�ĵ������
	    private String DocClassName ="";      //�ĵ�������
	    private String DocContentType="";     //�ĵ��������
	    private String TempleteFlag="";       //ģ���ʶ
	    private String StoreFileFlag="";      //�ļ��洢��ʶ
	    private int PartDocCount =0;          //��������
	    private int AttachDocCount =0;          //��������
	    private String DrawUpPerson="";       //������
	    private String DrawUpOrg ="";         //���Ʋ���
	    private String DrawUpDate;         //��������
	    private String ApproveDate;        //��׼����
	    private String Approver="";           //��׼��
	    private String ValidBeginDate;     //��Ч��ʼ����
	    private String ValidEndDate;      //��Ч��������
	    private String Memo ="";              //��ע
	    private String flag="";
	 
	    public DocMetaVersionInfo(){
	    	
	    }
     public DocMetaVersionInfo(String DocNo){
 		try
		{
 			
			DBObject db = new DBObject();
			String sql = "select * from STD_DOCMETAVERSIONINFO where docno=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(DocNo) };
			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null && dt.getRowsCount() == 1)
			{
				DataRow r = dt.get(0);
				this.DocNo = DocNo;
				this.DocCode = r.getString("DocCode");
				this.DocVersionName = r.getString("DocVersionName");
				this.DocVersion = r.getString("DocVersion");
				this.BelongDocNo = r.getString("BelongDocNo");
				this.BelongMode = r.getString("BelongMode");
				this.DocVersionStatus = r.getString("DocVersionStatus");
				this.UpdateFlag = r.getString("UpdateFlag");
				this.DocClassCode = r.getString("DocClassCode");
				this.DocClassName = r.getString("DocClassName");
				this.DocContentType = r.getString("DocContentType");
				this.TempleteFlag = r.getString("TempleteFlag");
				this.StoreFileFlag = r.getString("StoreFileFlag");
				this.flag=r.getString("Flag");
				String s=r.getString("PartDocCount");
				if(s==null||s.equals(""))
					s="0";
				this.PartDocCount = Integer.parseInt(s);
				s=r.getString("AttachDocCount");
				if(s==null||s.equals(""))
					s="0";
				this.AttachDocCount = Integer.parseInt(s);
				this.DrawUpPerson = r.getString("DrawUpPerson");
				this.DrawUpOrg  = r.getString("DrawUpOrg");
				this.DrawUpDate = r.getString("DrawUpDate").substring(0, 10);
				this.ApproveDate = r.getString("ApproveDate").substring(0, 10);
				this.Approver = r.getString("Approver");
				this.ValidBeginDate = r.getString("ValidBeginDate").substring(0, 10);
				this.ValidEndDate =r.getString("ValidEndDate").substring(0, 10);
				this.Memo = r.getString("Memo");
				
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	 
     }
     public DataTable HaveDoc(String docno) throws Exception{
         DBObject db = new DBObject();
         String sql = "select docno from STD_DOCMETAVERSIONINFO where docno = ? ";
         Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
                                                              { new Parameter.String(docno)				
                                                              };
         DataTable dt = db.runSelectQuery(sql, pp);
         return dt;
    }
	public String getDocNo() {
		return DocNo;
	}

	public void setDocNo(String docNo) {
		DocNo = docNo;
	}

	public String getDocCode() {
		return DocCode;
	}

	public void setDocCode(String docCode) {
		DocCode = docCode;
	}

	public String getDocVersionName() {
		return DocVersionName;
	}

	public void setDocVersionName(String docVersionName) {
		DocVersionName = docVersionName;
	}

	public String getDocVersion() {
		return DocVersion;
	}

	public void setDocVersion(String docVersion) {
		DocVersion = docVersion;
	}

	public String getBelongDocNo() {
		return BelongDocNo;
	}

	public void setBelongDocNo(String belongDocNo) {
		BelongDocNo = belongDocNo;
	}

	public String getBelongMode() {
		return BelongMode;
	}

	public void setBelongMode(String belongMode) {
		BelongMode = belongMode;
	}

	public String getDocVersionStatus() {
		return DocVersionStatus;
	}

	public void setDocVersionStatus(String docVersionStatus) {
		DocVersionStatus = docVersionStatus;
	}

	public String getUpdateFlag() {
		return UpdateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		UpdateFlag = updateFlag;
	}

	public String getDocClassCode() {
		return DocClassCode;
	}

	public void setDocClassCode(String docClassCode) {
		DocClassCode = docClassCode;
	}

	public String getDocClassName() {
		return DocClassName;
	}

	public void setDocClassName(String docClassName) {
		DocClassName = docClassName;
	}

	public String getDocContentType() {
		return DocContentType;
	}

	public void setDocContentType(String docContentType) {
		DocContentType = docContentType;
	}

	public String getTempleteFlag() {
		return TempleteFlag;
	}

	public void setTempleteFlag(String templeteFlag) {
		TempleteFlag = templeteFlag;
	}

	public String getStoreFileFlag() {
		return StoreFileFlag;
	}

	public void setStoreFileFlag(String storeFileFlag) {
		StoreFileFlag = storeFileFlag;
	}

	public int getPartDocCount() {
		return PartDocCount;
	}

	public void setPartDocCount(int partDocCount) {
		PartDocCount = partDocCount;
	}

	public String getDrawUpPerson() {
		return DrawUpPerson;
	}

	public void setDrawUpPerson(String drawUpPerson) {
		DrawUpPerson = drawUpPerson;
	}

	public String getDrawUpOrg() {
		return DrawUpOrg;
	}

	public void setDrawUpOrg(String drawUpOrg) {
		DrawUpOrg = drawUpOrg;
	}

	public String getDrawUpDate() {
		return DrawUpDate;
	}

	public void setDrawUpDate(String drawUpDate) {
		DrawUpDate = drawUpDate;
	}

	public String getApproveDate() {
		return ApproveDate;
	}

	public void setApproveDate(String approveDate) {
		ApproveDate = approveDate;
	}

	public String getApprover() {
		return Approver;
	}

	public void setApprover(String approver) {
		Approver = approver;
	}

	public String getValidBeginDate() {
		return ValidBeginDate;
	}

	public void setValidBeginDate(String validBeginDate) {
		ValidBeginDate = validBeginDate;
	}

	public String getValidEndDate() {
		return ValidEndDate;
	}

	public void setValidEndDate(String validEndDate) {
		ValidEndDate = validEndDate;
	}

	public String getMemo() {
		return Memo;
	}

	public void setMemo(String memo) {
		Memo = memo;
	}
    
	
	
	public int getAttachDocCount() {
		return AttachDocCount;
	}

	public void setAttachDocCount(int attachDocCount) {
		AttachDocCount = attachDocCount;
	}

	//�����ĵ���ˮ�Ż�ø�������ϸ��Ϣ
	public DataTable getAllAttachedStdList(String docNo)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from std_docmetaversioninfo where belongdocno = '"+docNo+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public DataTable getAttachedStdList(int pageno, int perpage, String docNo)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select doccode as �����ĵ�����,DocVersionName as �ĵ�����,to_char(DRAWUPDATE,'yyyy-mm-dd') as ��������," +
					"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">�޸�</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">ɾ��</a>  <a href=\"#\" onClick=F2(\"'||docno||'\") class=\"button4\">�ϴ��ļ�</a> <a href=\"#\" onClick=F9(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�ļ��鿴</a>' as ����   from std_docmetaversioninfo where belongdocno = '"+docNo+"' order by DRAWUPDATE desc";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public DataTable getAttachedStdList2(int pageno, int perpage, String docNo)
	{
		try
		{
			DBObject db = new DBObject();

			String base_sql = "select doccode as �������,DocVersionName as ��������,to_char(DRAWUPDATE,'yyyy-mm-dd') as ��������,docno as ��������," +
					"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">������Ϣ</a>' as ����   from std_docmetaversioninfo where belongdocno = '"+docNo+"' order by DRAWUPDATE desc";
			String sql_run = Format.getFySql(base_sql, pageno, perpage);
			return db.runSelectQuery(sql_run);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//�õ��ñ���������
	public DataTable getAllDocList()
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from Std_DocMetaVersionInfo");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	//ͨ���ĵ�����õ��ñ���������
	public DataTable getAllDocList(String docCode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from Std_DocMetaVersionInfo where doccode= '"+docCode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//ͨ���ĵ�����õ��丽��
	public DataTable getAttachedDocList(String docCode)
	{
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from Std_DocMetaVersionInfo where belongdocno= '"+docCode+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	//�����ĵ���ˮ�ŵõ�ȫ����Ϣ
	public DataTable getByDocNo(String docNo){
		try
		{
			DBObject db = new DBObject();
			DataTable dt = db.runSelectQuery("select * from Std_DocMetaVersionInfo where docno = '"+docNo+"'");
			return dt;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}	
	}
	
	public boolean Insert()
	{
		try
		{
		
			DBObject db = new DBObject();
			String sql = "insert into Std_DocMetaVersionInfo(DocNo,DocCode,DocVersionName,DocVersion,BelongDocNo, BelongMode,DocVersionStatus,UpdateFlag,DocClassCode,DocClassName," +
					"DocContentType,TempleteFlag,StoreFileFlag,PartDocCount,AttachDocCount,DrawUpPerson,DrawUpOrg,DrawUpDate,ApproveDate,Approver,ValidBeginDate,ValidEndDate,Memo,Flag)" +
					" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?)";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{       new Parameter.String(this.DocNo), new Parameter.String(this.DocCode),new Parameter.String(this.DocVersionName), new Parameter.String(this.DocVersion),new Parameter.String(this.BelongDocNo), 
					new Parameter.String(this.BelongMode),new Parameter.String(this.DocVersionStatus), new Parameter.String(this.UpdateFlag),	new Parameter.String(this.DocClassCode),
					new Parameter.String(this.DocClassName), new Parameter.String(this.DocContentType),new Parameter.String(this.TempleteFlag), new Parameter.String(this.StoreFileFlag),
					new Parameter.Int(this.PartDocCount),new Parameter.Int(this.AttachDocCount), new Parameter.String(this.DrawUpPerson),new Parameter.String(this.DrawUpOrg),new Parameter.String(this.DrawUpDate),
					new Parameter.String(this.ApproveDate),new Parameter.String(this.Approver),new Parameter.String(this.ValidBeginDate),new Parameter.String(this.ValidEndDate),new Parameter.String(this.Memo),new Parameter.String(this.flag)
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
	public boolean Update()
	{
		try
		{
			DBObject db = new DBObject();
			String sql = "update Std_DocMetaVersionInfo set DocCode=?,DocVersionName=?,DocVersion=?,BelongDocNo=?, BelongMode=?,DocVersionStatus=?,UpdateFlag=?,DocClassCode=?,DocClassName=?," +
					"DocContentType=?,TempleteFlag=?,StoreFileFlag=?,PartDocCount=?,AttachDocCount=?,DrawUpPerson=?,DrawUpOrg=?,DrawUpDate=to_date(?,'yyyy-mm-dd'),ApproveDate=to_date(?,'yyyy-mm-dd')," +
					"Approver=?,ValidBeginDate=to_date(?,'yyyy-mm-dd'),ValidEndDate=to_date(?,'yyyy-mm-dd'),Memo=?,Flag=? where docno=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	        {       new Parameter.String(this.DocCode),new Parameter.String(this.DocVersionName), new Parameter.String(this.DocVersion),new Parameter.String(this.BelongDocNo), 
					new Parameter.String(this.BelongMode),new Parameter.String(this.DocVersionStatus), new Parameter.String(this.UpdateFlag),	new Parameter.String(this.DocClassCode),
					new Parameter.String(this.DocClassName), new Parameter.String(this.DocContentType),new Parameter.String(this.TempleteFlag), new Parameter.String(this.StoreFileFlag),
					new Parameter.Int(this.PartDocCount),new Parameter.Int(this.AttachDocCount), new Parameter.String(this.DrawUpPerson),new Parameter.String(this.DrawUpOrg),new Parameter.String(this.DrawUpDate),
					new Parameter.String(this.ApproveDate),new Parameter.String(this.Approver),new Parameter.String(this.ValidBeginDate),new Parameter.String(this.ValidEndDate),new Parameter.String(this.Memo),new Parameter.String(this.flag),new Parameter.String(this.DocNo)
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
	public String getDocCodeByDocNo(String docno) throws Exception{
		DBObject db = new DBObject();
		String sql="select doccode from Std_DocMetaVersionInfo where docno like '" +docno+"'";
		DataTable dt = db.runSelectQuery(sql);
		return dt.get(0).get(0).toString();
	}
	public boolean DeleteFile(String docno) throws Exception{
		FtpStoreFile storefile=null;
		FtpStore  Ftp=new FtpStore();
		boolean flag=true;
	    DBObject db1 = new DBObject();
   	 	String sql1 = "select STOREFILENO from SYSTEM_DOCSTOREFILE where docno=?)";
   	 	Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
	                                                          { new Parameter.String(docno),				
	                                                          };
   	 	DataTable dt1 = db1.runSelectQuery(sql1, pp1);
   	 	for (int i=0;i<dt1.getRowsCount();i++)
   	 	{
   	 		storefile=new FtpStoreFile(dt1.get(i).get(0).toString());
   	 		if(!Ftp.deleteFile(storefile)){
   	 			flag=false;
   	 		}
   	 	}
   	 	return flag;
	}
	public DataTable HaveAttach(String docno) throws Exception{
	     DBObject db = new DBObject();
	     String sql = "select docno from STD_DOCMETAVERSIONINFO where BELONGDOCNO = ? ";
	     Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
	                                                          { new Parameter.String(docno)				
	                                                          };
	     DataTable dt = db.runSelectQuery(sql, pp);
	     return dt;
	}
	public boolean cicleDelete(String docno) throws Exception{
		
	     DBObject db = new DBObject();
	     boolean flag=true;
		 FtpStoreFile storefile=null;
		 FtpStore  Ftp=new FtpStore();
		 DataTable dt=HaveAttach(docno);
		 if(dt.getRowsCount()!=0){
			 for(int i=0;i<dt.getRowsCount();i++){
				 String doccode=getDocCodeByDocNo(docno);
				 flag=cicleDelete(dt.get(i).get(0).toString());
				    if(!HaveDoccode(doccode)){
				    	DeleteAboutDocCode(doccode);
				    }
			 }
		 }
		 if(flag){
  			DBObject db1 = new DBObject();
 			String sql = "select STOREFILENO from SYSTEM_DOCSTOREFILE where docno = ? ";
 			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
 			                                                         { new Parameter.String(docno)			
 			                                                         };
 			DataTable dt2 = db1.runSelectQuery(sql, pp);
 			for (int j=0;j<dt2.getRowsCount();j++)
 			{
 			
 				storefile=new FtpStoreFile(dt2.get(j).get(0).toString());
 				if(!Ftp.deleteFile(storefile)){
 					flag=false;
 				}
 			}
		 }
		 if(flag){
			 db.runSelectQuery("delete  from Std_DocMetaVersionInfo where docno = '" + docno + "'");
		 }
		return flag;
	}
	public boolean Delete(String docNo)
	{
		try
		{
			boolean flag=true;
			if (docNo.indexOf(",") == -1)
			{
				String doccode=getDocCodeByDocNo(docNo);
			    flag=cicleDelete(docNo);
			    if(!HaveDoccode(doccode)){
			    	DeleteAboutDocCode(doccode);
			    }
		    }
			else
			{
			    String[] bm = docNo.split(",");
			    for (int i = 0; i < bm.length; i++)
			    {
			    	String doccode=getDocCodeByDocNo(bm[i]);
			     	flag=cicleDelete(bm[i]);
				    if(!HaveDoccode(doccode)){
				    	DeleteAboutDocCode(doccode);
				    }
			    }
			}
			return flag;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean HaveDoccode(String doccode) throws Exception{
		DBObject db = new DBObject();
		String sql="select docno from Std_DocMetaVersionInfo where doccode like '" +doccode+"'";
		DataTable dt = db.runSelectQuery(sql);
		if(dt.getRowsCount()==0){
		     return false;
		}
		else {
			return true;
		}
	}
	public void DeleteAboutDocCode(String doccode){
		boolean flag=true;
    	DocOrgPost docOrgPost=new DocOrgPost();
    	flag=docOrgPost.DeleteByDocCode(doccode);
    	DocOrg docorg=new DocOrg();
    	if(flag){
    		flag=docorg.DeleteByDocCode(doccode);
    	}
    	DocMetaInfo docMetaInfo=new DocMetaInfo();
    	if(flag){
    		flag=docMetaInfo.Delete(doccode);
    	}
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFlag() {
		return flag;
	}
}
