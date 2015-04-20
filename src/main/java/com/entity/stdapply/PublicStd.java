package com.entity.stdapply;

import com.db.DBObject;
import com.db.DataTable;

public class PublicStd {
	public boolean  StdPublic(String applyid) throws Exception{
		String res="";
		String para="";
		boolean flag=true;
		DBObject db = new DBObject();
		int id=Integer.parseInt(applyid);
		DocReviseInifo revise=new DocReviseInifo();
		////////////////////////////////////////////////////////
		String doccodestring=revise.getProcessDoccode(applyid);
		String docnamestring=revise.getProcessDocname(applyid);
		DocApplyPerson applyInfo=new DocApplyPerson();
		applyInfo.UpdateDoc(id,doccodestring,docnamestring);
		////////////////////////////////////////////////////////
		DocApplyPerson applyperson=new DocApplyPerson(id);
		String applyercode=applyperson.getApplystaffcode();
		DataTable dt = db.runSelectQuery("select docno from Std_DocReviseInfo where belongdocno = 'no' and applyid='"+applyid+"'");
			for (int i=0;i<dt.getRowsCount();i++)
			{
				DocReviseInifo reviseinfo=new DocReviseInifo();
				try {
				
					if(reviseinfo.Std_Public(dt.get(i).get(0).toString(),applyid,applyercode)){
				//		int id=Integer.parseInt(applyid);
				//		DocApplyPerson applyperson=new DocApplyPerson(id);
					}else{
						flag=false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(flag){
				DocApplyPerson applyer=new DocApplyPerson();
				applyer.UpdateFlag(id);
			}
		/*	if(flag){
				res += "MessageBox.Show(null,'发布成功！',null,'LogOK',null,1,'发布成功');";

			}else{
				res += "MessageBox.Show(null,'发布失败！',null,'LogOK','Error',1,'发布失败，请与管理员联系！');";
			}*/
		return flag;
		
	}
}
