package com.entity.stdapply;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;

import com.common.Format;
import com.common.Util;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.ftp.FtpStoreFile;
import com.entity.std.DocMetaVersionInfo;
import com.entity.std.DocOrg;
import com.entity.std.DocStoreFile;
import com.entity.system.OrgPosition;
import com.ftp.FtpStore;

public class DocReviseInifo {
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
    private String ApplyId="";
    private String submitFlag="";
    private String flag="";
    public DocReviseInifo(){
    	
    }
 public DocReviseInifo(String DocNo){
		try
	{
			
		DBObject db = new DBObject();
		String sql = "select * from STD_DOCREVISEINFO where docno=?";
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
			this.ApplyId=r.getString("ApplyId");
			this.submitFlag=r.getString("submitFlag");
			
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	 
 }
 public boolean Std_ModInsert(String docno,String applyerid,String path,String type,String orgcode) throws Exception//�޸ı�׼���ӳ�ʼ�����ݿ�����ݷ����ĵ��޶��⣬�ļ���ftp���ص�uploadtempĿ¼
 {
	 boolean flag=true;
	 DocMetaVersionInfo docmetaversioninfo=new DocMetaVersionInfo(docno);
	 //String doccode=docmetaversioninfo.getDocCode();//���ƹ�����λ���޶���׼��
	 //DBObject db = new DBObject();
	 //DataTable positiondt = db.runSelectQuery("select * from std_org_postrevise where doccode = '"+doccode+"' and orgcode= '"+orgcode+"'");
	 //DocOrgPostRevise docOrgPostRevise=new DocOrgPostRevise();
	// docOrgPostRevise.InsertDt(positiondt);//�����޶����׼������λ��
	 if(Std_ApplyInsert(docno,applyerid,path,type))
	 {
		 flag=true;
		 DataTable dt=docmetaversioninfo.HaveAttach(docno);
		 if(dt.getRowsCount()!=0)
		 {
			 for(int i=0;i<dt.getRowsCount();i++){
				 if(!Std_ApplyInsert(dt.get(i).get(0).toString(),applyerid,path,type))
					 flag=false;
			 }
		 }
	 }else{
		 flag=false;
	 }
	 return flag;
 }
public boolean Std_ApplyInsert(String docno,String applyerid,String path,String type) throws Exception
{
	boolean flag=true;
	DocMetaVersionInfo docmetaversioninfo=new DocMetaVersionInfo(docno);
	this.ApplyId=applyerid;
	this.ApproveDate=docmetaversioninfo.getApproveDate();
	this.Approver=docmetaversioninfo.getApprover();
	this.AttachDocCount=docmetaversioninfo.getAttachDocCount();
	this.BelongDocNo=docmetaversioninfo.getBelongDocNo();
	this.BelongMode=docmetaversioninfo.getBelongMode();
	this.DocClassCode=docmetaversioninfo.getDocClassCode();
	this.DocClassName=docmetaversioninfo.getDocClassName();
	this.DocCode=docmetaversioninfo.getDocCode();
	this.DocContentType=docmetaversioninfo.getDocContentType();
	this.DocNo=docmetaversioninfo.getDocNo();
	this.DocVersion=docmetaversioninfo.getDocVersion();
	this.DocVersionName=docmetaversioninfo.getDocVersionName();
	this.DocVersionStatus=docmetaversioninfo.getDocVersionStatus();
	this.DrawUpDate=docmetaversioninfo.getDrawUpDate();
	this.DrawUpOrg=docmetaversioninfo.getDrawUpOrg();
	this.DrawUpPerson=docmetaversioninfo.getDrawUpPerson();
	this.Memo=docmetaversioninfo.getMemo();
	this.PartDocCount=docmetaversioninfo.getPartDocCount();
	this.StoreFileFlag=docmetaversioninfo.getStoreFileFlag();
	this.ValidBeginDate=docmetaversioninfo.getValidBeginDate();
	this.ValidEndDate=docmetaversioninfo.getValidEndDate();
	this.submitFlag="no";
	this.flag=type;
	if(!Insert()){
		flag=false;
	}
	DocStoreFile docstorefile=new DocStoreFile();
	DataTable dt=docstorefile.HaveFile(docno);
	 if(dt.getRowsCount()!=0){
		 for(int i=0;i<dt.getRowsCount();i++){
			 FtpStoreFile storefile=new FtpStoreFile(dt.get(i).get(0).toString());
			 if((!(storefile.getFilecontenttype().equals("pdf")))&&(!(storefile.getFilecontenttype().equals("swf")))){
			 DocApplyStore applyStore=new DocApplyStore();
			 String url="";
				FtpStore ftp=new FtpStore();
		    	String frand_name=ftp.FtpDownload(storefile,path);  //��ftp���������ص�web����������ʱ�ļ��У�������Ϊ������������֡�
		    	url=path+"\\"+frand_name;
			    if(!applyStore.Insert(storefile, url))
			    	flag=false;
			 }
		 }
	 }
	return flag;
}
private int ismod=0;
private String belong="";
public boolean Std_Public(String docno,String applyid,String applyercode) throws Exception//��׼���������޶���Ų���ʼ����
{
	boolean flag=true;
	DocReviseInifo docreviseinfo0=new DocReviseInifo(docno);
	String flagString=docreviseinfo0.getFlag();
	if(flagString.equals("�ϳ�")){
        Calendar c = Calendar.getInstance();
  		 String year = "" + c.get(c.YEAR);
		 String month = "" + (c.get(c.MONTH) + 1);
		 String day = "" + c.get(c.DATE);
		 String date=year+"-"+month+"-"+day;
		DocMetaVersionInfo docmetaversioninfo=new DocMetaVersionInfo(docno);
		docmetaversioninfo.setFlag("�ϳ�");
		docmetaversioninfo.setValidEndDate(date);
		docmetaversioninfo.Update();
	}else{
		DocReviseInifo docreviseinfo=new DocReviseInifo();
		if(Std_PublicInsert(docno)){
			DataTable dt=docreviseinfo.HaveAttach(docno);
			if(dt.getRowsCount()!=0)
			{
				for(int i=0;i<dt.getRowsCount();i++){
					if(!Std_PublicInsert(dt.get(i).get(0).toString()))
						flag=false;
				}
			}
		}
		OrgPosition orgPosition = new OrgPosition();
		DataTable dTable = orgPosition.getOrgPositionCode(applyercode);//���ظ�Ա����Ӧ�Ļ�������͸�λ���루����᷵�����������ϵļ�¼��
		String orgcode = dTable.get(0).getString("orgcode");
		DocReviseInifo reviseinfo=new DocReviseInifo(docno);
		DocOrg docorg=new DocOrg();
		docorg.setDocCode(reviseinfo.getDocCode());
		docorg.setOrgCode(orgcode);
		docorg.setRelation("ֱ��");
		DataTable dt2=docorg.Have();
		if(dt2.getRowsCount()==0){
				if(!docorg.insert())
					flag=false;
		}
	}
	if(flag){
		if(!Delete(docno))
			flag=false;
	}
	this.ismod=0;
	this.belong="";
	return flag;
}
public boolean Std_PublicInsert(String docno) throws Exception{
	boolean flag=true;
	String filebelongdoc="";
	DocReviseInifo docreviseinfo=new DocReviseInifo(docno);
	DocMetaVersionInfo docmetaversioninfo=new DocMetaVersionInfo();
	DataTable dt0=docmetaversioninfo.HaveDoc(docno);
	if(dt0.getRowsCount()!=0){
		String s=Util.getSequence("��׼��");
		docmetaversioninfo.setDocNo(s);
		docmetaversioninfo.setPartDocCount(docreviseinfo.getPartDocCount()+1);
		filebelongdoc=s;
		this.ismod=1;
		String belongnum=docreviseinfo.getBelongDocNo();
		if(belongnum.equals("no")){
			this.belong=s;
		}
		DocMetaVersionInfo docmetaversioninfo2=new DocMetaVersionInfo(docno);
		docmetaversioninfo2.setDocVersionStatus("��ʷ�汾");
        Calendar c = Calendar.getInstance();
 		 String year = "" + c.get(c.YEAR);
		 String month = "" + (c.get(c.MONTH) + 1);
		 String day = "" + c.get(c.DATE);
		 String date=year+"-"+month+"-"+day;
		docmetaversioninfo2.setValidEndDate(date);
		docmetaversioninfo2.Update();
	}else{
		docmetaversioninfo.setDocNo(docreviseinfo.getDocNo());
		docmetaversioninfo.setPartDocCount(docreviseinfo.getPartDocCount());
	}
	docmetaversioninfo.setApproveDate(docreviseinfo.getApproveDate());
	docmetaversioninfo.setApprover(docreviseinfo.getApprover());
	docmetaversioninfo.setAttachDocCount(docreviseinfo.getAttachDocCount());
	if(this.ismod==1&&!(docreviseinfo.getBelongDocNo()).equals("no")){
			docmetaversioninfo.setBelongDocNo(this.belong);
	}else{
			docmetaversioninfo.setBelongDocNo(docreviseinfo.getBelongDocNo());
	}
	docmetaversioninfo.setBelongMode(docreviseinfo.getBelongMode());
	docmetaversioninfo.setDocClassCode(docreviseinfo.getDocClassCode());
	docmetaversioninfo.setDocClassName(docreviseinfo.getDocClassName());
	docmetaversioninfo.setDocCode(docreviseinfo.getDocCode());
	docmetaversioninfo.setDocContentType(docreviseinfo.getDocContentType());
	docmetaversioninfo.setDocVersion(docreviseinfo.getDocVersion());
	docmetaversioninfo.setDocVersionName(docreviseinfo.getDocVersionName());
	docmetaversioninfo.setDocVersionStatus("�����汾");
    Calendar c = Calendar.getInstance();
	 String year = "" + c.get(c.YEAR);
	 String month = "" + (c.get(c.MONTH) + 1);
	 String day = "" + c.get(c.DATE);
	 String date=year+"-"+month+"-"+day;
	docmetaversioninfo.setDrawUpDate(date);
	docmetaversioninfo.setDrawUpOrg(docreviseinfo.getDrawUpOrg());
	docmetaversioninfo.setDrawUpPerson(docreviseinfo.getDrawUpPerson());
	docmetaversioninfo.setMemo(docreviseinfo.getMemo());
	docmetaversioninfo.setStoreFileFlag(docreviseinfo.getStoreFileFlag());
	docmetaversioninfo.setTempleteFlag(docreviseinfo.getTempleteFlag());
	docmetaversioninfo.setUpdateFlag(docreviseinfo.getUpdateFlag());
	docmetaversioninfo.setValidBeginDate(docreviseinfo.getValidBeginDate());
	docmetaversioninfo.setValidEndDate(docreviseinfo.getValidEndDate());
	docmetaversioninfo.setFlag(docreviseinfo.getFlag());
	if(!docmetaversioninfo.Insert())
		flag=false;
	DocApplyStore applyStore=new DocApplyStore();
	DataTable dt=applyStore.HaveFile(docno);
	 if(dt.getRowsCount()!=0){
		 for(int i=0;i<dt.getRowsCount();i++){
			 DocApplyStore applyfile=new DocApplyStore(dt.get(i).get(0).toString());
			 FtpStoreFile file=new FtpStoreFile();
			 file.setCreatedate((applyfile.getCreatedate()).substring(0, 10));
			 file.setDocclass(applyfile.getDocClass());
			 if(filebelongdoc.equals("")){
			 file.setDocno(applyfile.getDocNo());
			 }else{
				 file.setDocno(filebelongdoc);
			 }
			 file.setFilecontenttpye(applyfile.getFileContentType());
			 file.setFilename(applyfile.getFileName());
			 file.setLastupdatedate((applyfile.getLastUpdatedate()).substring(0, 10));
			 file.setMemo(applyfile.getMemo());
			 file.setSourceflag(applyfile.getSourceFlag());
			 FileInputStream in=new FileInputStream(applyfile.getStoreDirURL());
			 DocStoreFile ftpstorefile=new DocStoreFile();
				FtpStore ftp=new FtpStore();
				 String storefileno=ftp.FtpUpload(file,in);
				 if(storefileno==""||storefileno==null)
					 flag=false;
			 if(!(applyfile.getFileContentType()).equals("pdf")&&!(applyfile.getFileContentType()).equals("vsd")){
				 FtpStoreFile pdffile=new FtpStoreFile();
				 FtpStoreFile swffile=new FtpStoreFile();
				 pdffile.setCreatedate((applyfile.getCreatedate()).substring(0, 10));
				 pdffile.setDocclass(applyfile.getDocClass());
				 swffile.setCreatedate((applyfile.getCreatedate()).substring(0, 10));
				 swffile.setDocclass(applyfile.getDocClass());
				 if(filebelongdoc.equals("")){
					 pdffile.setDocno(applyfile.getDocNo());
					 swffile.setDocno(applyfile.getDocNo());
				 }else{
					 pdffile.setDocno(filebelongdoc);
					 swffile.setDocno(filebelongdoc);
				 }
				 swffile.setFilecontenttpye("swf");
				 pdffile.setFilecontenttpye("pdf");
				 pdffile.setLastupdatedate((applyfile.getLastUpdatedate()).substring(0, 10));
				 pdffile.setMemo(applyfile.getMemo());
				 pdffile.setSourceflag(applyfile.getSourceFlag());
				 swffile.setLastupdatedate((applyfile.getLastUpdatedate()).substring(0, 10));
				 swffile.setMemo(applyfile.getMemo());
				 swffile.setSourceflag(applyfile.getSourceFlag());
				 String filename=applyfile.getFileName();
				 String type=filename.substring(filename.length()-4,filename.length());
				 String url="";
				 String url2="";
			     if(type.equals(".doc")||type.equals(".dot")||type.equals(".ppt")||type.equals(".xls")){
			    	 String pdfname=filename.substring(0, filename.length()-4)+".pdf";
			    	 String swfname=filename.substring(0, filename.length()-4)+".swf";
			    	//����תpdf����applyfile.getStoreDirURL()��pdfnameΪ����
					 pdffile.setFilename(pdfname);
					 swffile.setFilename(swfname);
					 url=(applyfile.getStoreDirURL()).substring(0, (applyfile.getStoreDirURL()).length()-4)+".pdf";
					 Office2Pdf office2Pdf=new Office2Pdf();
					 office2Pdf.createPDF(applyfile.getStoreDirURL(), url);
					 FileInputStream in2=new FileInputStream(url);
					 storefileno=ftp.FtpUpload(pdffile,in2);
					 if(storefileno==""||storefileno==null)
						 flag=false;
					 
					 url2=(applyfile.getStoreDirURL()).substring(0, (applyfile.getStoreDirURL()).length()-4)+".swf";
						PdfToSwf pdftoswf= new PdfToSwf();
					    pdftoswf.PdfSwf(url);
						 FileInputStream in3=new FileInputStream(url2);
						 storefileno=ftp.FtpUpload(swffile,in3);
						 if(storefileno==""||storefileno==null)
							 flag=false;
				  }else if(type.equals("xlsx")||type.equals("docx")||type.equals("pptx")){
				    	 String pdfname=filename.substring(0, filename.length()-4)+"pdf";
				    	 String swfname=filename.substring(0, filename.length()-4)+"swf";
				    	//����תpdf����applyfile.getStoreDirURL()��pdfnameΪ����
						 pdffile.setFilename(pdfname);
						 swffile.setFilename(swfname);
						 url=(applyfile.getStoreDirURL()).substring(0, (applyfile.getStoreDirURL()).length()-4)+"pdf";
						 Office2Pdf office2Pdf=new Office2Pdf();
						 office2Pdf.createPDF(applyfile.getStoreDirURL(), url);
						 FileInputStream in2=new FileInputStream(url);
						 storefileno=ftp.FtpUpload(pdffile,in2);
						 if(storefileno==""||storefileno==null)
							 flag=false;
						 url2=(applyfile.getStoreDirURL()).substring(0, (applyfile.getStoreDirURL()).length()-4)+"swf";
							PdfToSwf pdftoswf= new PdfToSwf();
						    pdftoswf.PdfSwf(url);
							 FileInputStream in3=new FileInputStream(url2);
							 storefileno=ftp.FtpUpload(swffile,in3);
							 if(storefileno==""||storefileno==null)
								 flag=false;
				  }
			     if(flag){
			    	 DocApplyStore applyfile2=new DocApplyStore();
			    	 applyfile2.setCreatedate(pdffile.getCreatedate());
			    	 applyfile2.setDocClass(pdffile.getDocclass());
			    	 applyfile2.setDocNo(applyfile.getDocNo());
			    	 applyfile2.setFileContentType(pdffile.getFilecontenttype());
			    	 applyfile2.setFileName(pdffile.getFilename());
			    	 applyfile2.setLastUpdatedate(pdffile.getLastupdatedate());
			    	 applyfile2.setMemo(pdffile.getMemo());
			    	 applyfile2.setSourceFlag(pdffile.getSourceflag());
			    	 applyfile2.setStoreDirURL(url);
			    	 if(!applyfile2.Insert())
			    		 flag=false;
			    	 DocApplyStore applyfile3=new DocApplyStore();
			    	 applyfile3.setCreatedate(swffile.getCreatedate());
			    	 applyfile3.setDocClass(swffile.getDocclass());
			    	 applyfile3.setDocNo(applyfile.getDocNo());
			    	 applyfile3.setFileContentType(swffile.getFilecontenttype());
			    	 applyfile3.setFileName(swffile.getFilename());
			    	 applyfile3.setLastUpdatedate(swffile.getLastupdatedate());
			    	 applyfile3.setMemo(swffile.getMemo());
			    	 applyfile3.setSourceFlag(swffile.getSourceflag());
			    	 applyfile3.setStoreDirURL(url2);
			    	 if(!applyfile3.Insert())
			    		 flag=false;
			     }
			 }
		 }
	 }
	return flag;
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

public void setApplyId(String applyid) {
	ApplyId = applyid;
}
public String getApplyId() {
	return ApplyId;
}
public void setSubmitFlagString(String submitFlagString) {
	this.submitFlag = submitFlagString;
}
public String getSubmitFlagString() {
	return submitFlag;
}

public int getAttachDocCount() {
	return AttachDocCount;
}

public void setAttachDocCount(int attachDocCount) {
	AttachDocCount = attachDocCount;
}
public String  StdPublic(String applyid,String applyercode) throws Exception{
	String res="";
	String para="";
	boolean flag=true;
	DBObject db = new DBObject();
	DataTable dt = db.runSelectQuery("select docno from Std_DocReviseInfo where belongdocno = 'no' and applyid='"+applyid+"'");
		for (int i=0;i<dt.getRowsCount();i++)
		{
			DocReviseInifo reviseinfo=new DocReviseInifo();
			try {
				int id=Integer.parseInt(applyid);
				DocApplyPerson applyperson=new DocApplyPerson();
				String doccodestring=getProcessDoccode(applyid);
				String docnamestring=getProcessDocname(applyid);
				if(reviseinfo.Std_Public(dt.get(i).get(0).toString(),applyid,applyercode)){

					if(!applyperson.UpdateDoc(id, doccodestring, docnamestring)){
						flag=false;
					}
				}else{
					flag=false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(flag){
			res +=  "alert('�����ɹ�');";

		}else{
			res +=  "alert('����ʧ��');";
		}
	return res;
	
}
//�����ĵ���ˮ�Ż�ø�������ϸ��Ϣ
public DataTable getAllAttachedStdList(String docNo)
{
	try
	{
		DBObject db = new DBObject();
		DataTable dt = db.runSelectQuery("select * from Std_DocReviseInfo where belongdocno = '"+docNo+"' "+"and submitflag='no'");
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

		String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as ѡ��,doccode as �������,DocVersionName as ��������," +
				"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">�޸�</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">ɾ��</a> <a href=\"#\" onClick=F2(\"'||docno||'\") class=\"button4\">�ϴ��ļ�</a> <a href=\"#\" onClick=F9(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�ļ��鿴</a>' as ����   from Std_DocReviseInfo where belongdocno = '"+docNo+"' "+"and submitflag='no' order by docno";
		String sql_run = Format.getFySql(base_sql, pageno, perpage);
		return db.runSelectQuery(sql_run);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public DataTable getAppAttachList(int pageno, int perpage, String docNo)
{
	try
	{
		DBObject db = new DBObject();

		String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as ѡ��,doccode as �������,DocVersionName as ��������,docno as ��������," +
				"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">�޸�</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">ɾ��</a>' as ����   from Std_DocReviseInfo where belongdocno = '"+docNo+"' "+"and submitflag='no' order by docno";
		String sql_run = Format.getFySql(base_sql, pageno, perpage);
		return db.runSelectQuery(sql_run);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public DataTable getAttachPublicList(int pageno, int perpage, String docNo)
{
	try
	{
		DBObject db = new DBObject();

		String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as ѡ��,doccode as �������,DocVersionName as ��������,docno as ��������," +
				"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">�޸�</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">ɾ��</a> ' as ����   from Std_DocReviseInfo where belongdocno = '"+docNo+"' "+"and submitflag='no' order by docno";
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

		String base_sql = "select doccode as �������,DocVersionName as ��������," +
				"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">������Ϣ</a> <a href=\"#\" onClick=F7(\"'||docno||'\",\"'||DocVersionName||'\") class=\"button4\">�����鿴</a> <a href=\"#\" onClick=F9(\"'||docno||'\",\"'||DocVersionName||'\") class=\"button4\">�ļ��鿴</a>' as ����   from Std_DocReviseInfo where belongdocno = '"+docNo+"' "+"and submitflag='no'";
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
		DataTable dt = db.runSelectQuery("select * from Std_DocReviseInfo");
		return dt;
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public DataTable getStdList(int pageno, int perpage, String applyid)
{
	try
	{
		DBObject db = new DBObject();
//<a href=\"#\" onClick=aboutpost(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�漰��λ</a>
		String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as ѡ��,doccode as ��׼���,DocVersionName as ��׼����,flag as ��������," +
				"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">�޸�</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">ɾ��</a> <a href=\"#\" onClick=F7(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�����鿴</a> <a href=\"#\" onClick=F2(\"'||docno||'\") class=\"button4\">�ϴ��ļ�</a> <a href=\"#\" onClick=F9(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�ļ��鿴</a>' as ����   from Std_DocReviseInfo where  applyid= '"+applyid+"' "+"and submitflag='no' "+"and belongdocno='no' order by docno";
		String sql_run = Format.getFySql(base_sql, pageno, perpage);
		return db.runSelectQuery(sql_run);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public DataTable getStdListapp(int pageno, int perpage, String applyid)
{
	try
	{
		DBObject db = new DBObject();
//<a href=\"#\" onClick=aboutpost(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�漰��λ</a>
		String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as ѡ��,doccode as ��׼���,DocVersionName as ��׼����,flag as ��������,docno as ��׼����," +
				"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">�޸�</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">ɾ��</a> <a href=\"#\" onClick=F7(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�����鿴</a>' as ����   from Std_DocReviseInfo where  applyid= '"+applyid+"' "+"and submitflag='no' "+"and belongdocno='no' order by docno";
		String sql_run = Format.getFySql(base_sql, pageno, perpage);
		return db.runSelectQuery(sql_run);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
public DataTable getStdPublicList(int pageno, int perpage, String applyid)
{
	try
	{
		DBObject db = new DBObject();

		String base_sql = "select '<input type=\"checkbox\" id=\"items\" name=\"items\" value=\"'||docno||'\">' as ѡ��,doccode as ��׼���,DocVersionName as ��׼����,flag as ��������,docno as ��׼����," +
				"'<a href=\"#\" onClick=F1(\"'||docno||'\") class=\"button4\">�޸�</a> <a href=\"#\" onClick=dele(\"'||docno||'\") class=\"button4\">ɾ��</a> <a href=\"#\" onClick=F7(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�����鿴</a>  <a href=\"#\" onClick=F9(\"'||docno||'\",\"'||docversionname||'\") class=\"button4\">�漰��λ</a>' as ����   from Std_DocReviseInfo where  applyid= '"+applyid+"' "+"and submitflag='no' "+"and belongdocno='no' order by docno";
		String sql_run = Format.getFySql(base_sql, pageno, perpage);
		return db.runSelectQuery(sql_run);
	}
	catch (Exception e)
	{
		e.printStackTrace();
		return null;
	}
}
//ͨ���ĵ�����õ��ñ���������
public DataTable getAllStdList(String applyid)
{
	try
	{
		DBObject db = new DBObject();
		DataTable dt = db.runSelectQuery("select * from Std_DocReviseInfo where applyid= '"+applyid+"' "+"and submitflag='no' "+"and belongdocno='no'");
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
		DataTable dt = db.runSelectQuery("select * from Std_DocReviseInfo where belongdocno= '"+docCode+"'");
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
		DataTable dt = db.runSelectQuery("select * from Std_DocReviseInfo where docno = '"+docNo+"'");
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
		String sql = "insert into Std_DocReviseInfo(DocNo,DocCode,DocVersionName,DocVersion,BelongDocNo, BelongMode,DocVersionStatus,UpdateFlag,DocClassCode,DocClassName," +
				"DocContentType,TempleteFlag,StoreFileFlag,PartDocCount,AttachDocCount,DrawUpPerson,DrawUpOrg,DrawUpDate,ApproveDate,Approver,ValidBeginDate,ValidEndDate,Memo,ApplyId,SubmitFlag,Flag)" +
				" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,to_date(?,'yyyy-mm-dd'),to_date(?,'yyyy-mm-dd'),?,?,?,?)";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
		{       new Parameter.String(this.DocNo), new Parameter.String(this.DocCode),new Parameter.String(this.DocVersionName), new Parameter.String(this.DocVersion),new Parameter.String(this.BelongDocNo), 
				new Parameter.String(this.BelongMode),new Parameter.String(this.DocVersionStatus), new Parameter.String(this.UpdateFlag),	new Parameter.String(this.DocClassCode),
				new Parameter.String(this.DocClassName), new Parameter.String(this.DocContentType),new Parameter.String(this.TempleteFlag), new Parameter.String(this.StoreFileFlag),
				new Parameter.Int(this.PartDocCount),new Parameter.Int(this.AttachDocCount), new Parameter.String(this.DrawUpPerson),new Parameter.String(this.DrawUpOrg),new Parameter.String(this.DrawUpDate),
				new Parameter.String(this.ApproveDate),new Parameter.String(this.Approver),new Parameter.String(this.ValidBeginDate),new Parameter.String(this.ValidEndDate),new Parameter.String(this.Memo),
				new Parameter.String(this.ApplyId),new Parameter.String(this.submitFlag),new Parameter.String(this.flag)
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
		String sql = "update Std_DocReviseInfo set DocCode=?,DocVersionName=?,DocVersion=?,BelongDocNo=?, BelongMode=?,DocVersionStatus=?,UpdateFlag=?,DocClassCode=?,DocClassName=?," +
				"DocContentType=?,TempleteFlag=?,StoreFileFlag=?,PartDocCount=?,AttachDocCount=?,DrawUpPerson=?,DrawUpOrg=?,DrawUpDate=to_date(?,'yyyy-mm-dd'),ApproveDate=to_date(?,'yyyy-mm-dd')," +
				"Approver=?,ValidBeginDate=to_date(?,'yyyy-mm-dd'),ValidEndDate=to_date(?,'yyyy-mm-dd'),Memo=?,ApplyId=?,SubmitFlag=?,Flag=? where docno=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
        {       new Parameter.String(this.DocCode),new Parameter.String(this.DocVersionName), new Parameter.String(this.DocVersion),new Parameter.String(this.BelongDocNo), 
				new Parameter.String(this.BelongMode),new Parameter.String(this.DocVersionStatus), new Parameter.String(this.UpdateFlag),	new Parameter.String(this.DocClassCode),
				new Parameter.String(this.DocClassName), new Parameter.String(this.DocContentType),new Parameter.String(this.TempleteFlag), new Parameter.String(this.StoreFileFlag),
				new Parameter.Int(this.PartDocCount),new Parameter.Int(this.AttachDocCount), new Parameter.String(this.DrawUpPerson),new Parameter.String(this.DrawUpOrg),new Parameter.String(this.DrawUpDate),
				new Parameter.String(this.ApproveDate),new Parameter.String(this.Approver),new Parameter.String(this.ValidBeginDate),new Parameter.String(this.ValidEndDate),
				new Parameter.String(this.Memo),new Parameter.String(this.ApplyId),new Parameter.String(this.submitFlag),new Parameter.String(this.flag),new Parameter.String(this.DocNo)
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
	String sql="select doccode from STD_DOCREVISEINFO where docno like '" +docno+"'";
	DataTable dt = db.runSelectQuery(sql);
	return dt.get(0).get(0).toString();
}
public boolean DeleteFile(String docno) throws Exception{
	DocApplyStore storefile=null;
	boolean flag=true;
    DBObject db1 = new DBObject();
	 	String sql1 = "select STOREFILENO from STD_DOCREVISEPATH where docno=?)";
	 	Parameter.SqlParameter[] pp1 = new Parameter.SqlParameter[]
                                                          { new Parameter.String(docno),				
                                                          };
	 	DataTable dt1 = db1.runSelectQuery(sql1, pp1);
	 	for (int i=0;i<dt1.getRowsCount();i++)
	 	{
	 		storefile=new DocApplyStore(dt1.get(i).get(0).toString());
	 		
	 		if(storefile.delete(dt1.get(i).get(0).toString())){
	 			String path=storefile.getStoreDirURL();
	 			File file=new File(path);
	 			file.delete();
	 		}else{
	 			flag=false;
	 		}
	 	}
	 	return flag;
}
public DataTable HaveAttach(String docno) throws Exception{
     DBObject db = new DBObject();
     String sql = "select docno from STD_DOCREVISEINFO where BELONGDOCNO = ? ";
     Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
                                                          { new Parameter.String(docno)				
                                                          };
     DataTable dt = db.runSelectQuery(sql, pp);
     return dt;
}
public boolean cicleDelete(String docno) throws Exception{
	
     DBObject db = new DBObject();
     boolean flag=true;
     DocApplyStore storefile=null;
	 DataTable dt=HaveAttach(docno);
	 if(dt.getRowsCount()!=0){
		 for(int i=0;i<dt.getRowsCount();i++){
			 String doccode=getDocCodeByDocNo(dt.get(i).get(0).toString());
			 flag=cicleDelete(dt.get(i).get(0).toString());
			 
				DocMetaVersionInfo docmeta=new DocMetaVersionInfo();
			    if((!docmeta.HaveDoccode(doccode))&&(!HaveDoccode(doccode))){
			    	docmeta.DeleteAboutDocCode(doccode);
			    }
		 }
	 }
	 if(flag){
			DBObject db1 = new DBObject();
			String sql = "select STOREFILENO from STD_DOCREVISEPATH where docno = ? ";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			                                                         { new Parameter.String(docno)			
			                                                         };
			DataTable dt2 = db1.runSelectQuery(sql, pp);
			for (int j=0;j<dt2.getRowsCount();j++)
			{
			
				storefile=new DocApplyStore(dt2.get(j).get(0).toString());
		 		if(storefile.delete(dt2.get(j).get(0).toString())){
		 			String path=storefile.getStoreDirURL();
		 			File file=new File(path);
		 			file.delete();
		 		}else{
		 			flag=false;
		 		}
			}
	 }
	 if(flag){
			DBObject db2 = new DBObject();
			String sql = "delete  from STD_DOCREVISEINFO where docno =?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[]
			{ new Parameter.String(docno) };
			db2.run(sql,pp);
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
			DocMetaVersionInfo docmeta=new DocMetaVersionInfo();
		    if((!docmeta.HaveDoccode(doccode))&&(!HaveDoccode(doccode))){
		    	docmeta.DeleteAboutDocCode(doccode);
		    }
	    }
		else
		{
		    String[] bm = docNo.split(",");
		    for (int i = 0; i < bm.length; i++)
		    {
		     	flag=cicleDelete(bm[i]);
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
	String sql="select docno from STD_DOCREVISEINFO where doccode like '" +doccode+"'";
	DataTable dt = db.runSelectQuery(sql);
	if(dt.getRowsCount()==0){
	     return false;
	}
	else {
		return true;
	}
}
public void setFlag(String flag) {
	this.flag = flag;
}
public String getFlag() {
	return flag;
}
public String getProcessDoccode(String applyid){
	String doccodeString="";
	DBObject db = new DBObject();
	String sql="select doccode from STD_DOCREVISEINFO where applyid= '" +applyid+"'";
	try {
		DataTable dt = db.runSelectQuery(sql);
		for(int i=0;i<dt.getRowsCount();i++){
			if(i==dt.getRowsCount()-1){
				doccodeString+=dt.get(i).get(0).toString();
			}else{
				doccodeString+=dt.get(i).get(0).toString()+"��";
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return doccodeString;
}
public String getProcessDocname(String applyid){
	String docnameString="";
	DBObject db = new DBObject();
	String sql="select docversionname from STD_DOCREVISEINFO where applyid= '" +applyid+"'";
	try {
		DataTable dt = db.runSelectQuery(sql);
		for(int i=0;i<dt.getRowsCount();i++){
			if(i==dt.getRowsCount()-1){
				docnameString+=dt.get(i).get(0).toString();
			}else{
				docnameString+=dt.get(i).get(0).toString()+"��";
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return docnameString;
}
}
