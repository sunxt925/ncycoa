package edu.cqu.ncycoa.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.web.servlet.tags.form.OptionTag;

import com.common.CodeDictionary;
import com.common.Format;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;
import com.db.Parameter;
import com.entity.system.Staff;

import edu.cqu.ncycoa.common.service.SupplierServiceImpl;
import edu.cqu.ncycoa.domain.EvaluDefine;
import edu.cqu.ncycoa.util.SystemUtils;

public class SupplierDao {

	public static String getGoodsType(){
		DBObject db = new DBObject();
		String result="";
		String sql = "";
		sql = "select goodsname from COM_GOODSCLASS where parentgoodscode='WF.01'";
		DataTable dt = null;
		dt = db.runSelectQuery(sql);
		if (dt != null && dt.getRowsCount() >= 1)
		{
			for (int i = 0; i < dt.getRowsCount(); i++)
			{
				//DataRow r = dt.get(i);
				result=result+"<option value='"+i+"'>";
				try {
					result=result+dt.get(i).get(0);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				result=result+"</option>";
				System.out.println(result);
			}
		}
		return result;
	}

	public static String getDepart() {
		//Staff staff=new Staff(SystemUtils.getSessionUser().getStaffcode());
		String result="";
		
		try {
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGMEMBER where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<option value='"+r.getString("orgname")+"'>";
					try {
						result=result+r.getString("orgname");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</option>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//��ȡ��ǰ�û��������������ƣ�һ����
	public static String getOneDepart() {
		//Staff staff=new Staff(SystemUtils.getSessionUser().getStaffcode());
		String result="";
		
		try {
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGMEMBER where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null&& dt.getRowsCount() >= 1) {
				
					DataRow r = dt.get(0);
					result=r.getString("orgname");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	//������۵ı�
	public static String getEvaluContent(String year){
		String result="";
		DBObject db = new DBObject();
		String sql = "";
		sql = "select INDEX_NAME,INDEX_OPTION from NCYCOA_EVALU_DEFINE where EVALU_YEAR=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(year) };
		DataTable dt = null;
		int count=0;
		try {
			dt = db.runSelectQuery(sql,pp);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (dt != null && dt.getRowsCount() >= 1)
		{
			for (int i = 0; i < dt.getRowsCount(); i++)
			{
				DataRow r = dt.get(i);
				try {
					if(r.get(1).toString().equals("()=()")){
						result=result+"<tr><td colspan=2 style='background-color: rgb(250, 204, 57);text-align: center;'>���:"+r.get(0).toString()+"</td></tr>";
					}else{
						result=result+"<tr><td style='background-color:rgba(121, 154, 241, 0.94)' width: 50px;>������"+(++count)+":"+"</td><td style='background-color:rgba(152, 201, 255, 0.94)'>";
						result=result+r.get(0)+"</td>";
						String option=r.get(1).toString();
						String[] optionText=splitOption(option,0);
						String[] optionScore=splitOption(option,1);
						for(int j=0;j<optionScore.length;j++)
						{
							result=result+"<tr><td colspan=2>&nbsp;&nbsp;&nbsp;<input type='radio' name='option"+count+"' value='"+optionScore[j]+"' />";
							result=result+optionText[j]+"("+optionScore[j]+"��)";
							result=result+"</td></tr>";
						}
				
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(result);
			}
		}
		return result;
	}
	//�鿴�����۵ı�
	public static String getEvaluedContent(String year,String supplier) {
		String result="";
		DBObject db = new DBObject();
		//��ȡ��ϸ�÷��ִ�
		String detail=getEvaluedDetail(getOneDepart(), supplier, year);
		//System.out.println("detail:"+detail);
		String[] detailArray=detail.substring(0, detail.length()-1).split(",");		
		String sql = "";
		sql = "select INDEX_NAME,INDEX_OPTION from NCYCOA_EVALU_DEFINE where EVALU_YEAR=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(year) };
		DataTable dt = null;
		int count=0;
		try {
			dt = db.runSelectQuery(sql,pp);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (dt != null && dt.getRowsCount() >= 1)
		{
			for (int i = 0; i < dt.getRowsCount(); i++)
			{
				DataRow r = dt.get(i);
				try {
					if(r.get(1).toString().equals("()=()")){
						result=result+"<tr><td colspan=2 style='background-color: rgb(250, 204, 57);text-align: center;'>���:"+r.get(0).toString()+"</td></tr>";
					}else{
						result=result+"<tr><td style='background-color:rgba(121, 154, 241, 0.94)' width: 50px;>������"+(++count)+":"+"</td><td style='background-color:rgba(152, 201, 255, 0.94)'>";
						result=result+r.get(0)+"</td>";
						String option=r.get(1).toString();
						String[] optionText=splitOption(option,0);
						String[] optionScore=splitOption(option,1);
						for(int j=0;j<optionScore.length;j++)
						{
							if(optionScore[j].equals(detailArray[count-1])){
								result=result+"<tr><td colspan=2>&nbsp;&nbsp;&nbsp;<input type='radio' name='option"+count+"' value='"+optionScore[j]+"' checked />";
							}else {
								result=result+"<tr><td colspan=2>&nbsp;&nbsp;&nbsp;<input type='radio' name='option"+count+"' value='"+optionScore[j]+"' />";
							}							
							result=result+optionText[j]+"("+optionScore[j]+"��)";
							result=result+"</td></tr>";
						}
				
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(result);
			}
		}
		return result;
	}
	
	//������ݺͲ��� �鿴ÿ����Ӧ��������� ���ɵ���������
	public static String getSuppliersEvaluStatus(){
		String getDepart=getOneDepart();
		System.out.println(getDepart);
		int year=Calendar.getInstance().get(Calendar.YEAR);
		String result="";
		DBObject db = new DBObject();
		String sql = "";
		sql = "select supplier_name,supplier_id from NCYCOA_SUPPLIER t where manage_depart like'%"+getDepart+"%'";
		DataTable dt = null;
		try {
			dt = db.runSelectQuery(sql);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (dt != null && dt.getRowsCount() >= 1)
		{
			for (int i = 0; i < dt.getRowsCount(); i++)
			{
				DataRow r = dt.get(i);
				try {
					if(!r.get(0).toString().equals(null)){
						result=result+"<tr><td style=''>"+r.get(0).toString()+"</td>";
						boolean flag=isEvalued(getDepart, r.get(0).toString(), String.valueOf(year));
						if(flag){
							result+="<td>������</td>";
							result+="<td>"+getScore(getDepart, r.get(0).toString(), String.valueOf(year))+"</td>";
							result+="<td><a onclick=\"makeSeeTable(\'"+r.get(1).toString()+"\')\" class='easyui-linkbutton' data-options='iconCls:\"icon-add\",plain:true'>�鿴����</a></td>";
						}
						else {
							result+="<td>δ����</td>";
							result+="<td>--</td>";
							result+="<td><a onclick=\"makeTable(\'"+r.get(1).toString()+"\')\" class='easyui-linkbutton' data-options='iconCls:\"icon-add\",plain:true'>�������</a></td>";
						}
						result+="</tr>";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(result);
			}
		}
		return result;
	}

	private static String getScore(String depart, String supplier, String year) {
		DBObject db = new DBObject();
		String sql = "select evalu_score from NCYCOA_EVALU_RESULT where evalu_depart=? and evalu_supplier=? and evalu_year=? ";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(depart),new Parameter.String(supplier),new Parameter.String(year) };
		try {
			DataTable dt = db.runSelectQuery(sql,pp);
			if(dt != null&& dt.getRowsCount() >= 1){
				String score=dt.get(0).get(0).toString();
				return score;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String[] splitOption(String option, int i) {
		String[] temp=option.split("=");
		if(i==0){
			String text=temp[0].substring(1, temp[0].length()-1);
			String[] textStrings=text.split(",");
			return textStrings;
		}else if(i==1){
			String score=temp[1].substring(1, temp[1].length()-1);
			String[] scoreStrings=score.split(",");
			return scoreStrings;
		}
		return null;
	}

	public static String getSupplier() {
		String temp=getDepartName();
		String[] departs=temp.substring(0, temp.length()-1).split(",");

		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select supplier_name from NCYCOA_SUPPLIER t where manage_depart LIKE '%"+departs[0]+"%'";
			for(int j=1;j<departs.length;j++){
				sql=sql+" or manage_depart LIKE '%"+departs[j]+"%'";
			}
//			String sql = "select supplier_name from NCYCOA_SUPPLIER t where manage_depart='"+departs[0]+"'";
//			for(int j=1;j<departs.length;j++){
//				sql=sql+" or manage_depart='"+departs[j]+"'";
//			}
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<option value='"+r.getString("supplier_name")+"'>";
					try {
						result=result+r.getString("supplier_name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</option>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static String getDepartName() {
		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGMEMBER where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+r.getString("orgname")+",";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int getContentCount() {
		DBObject db = new DBObject();
		String sql = "";
		sql = "select INDEX_NAME,INDEX_OPTION from NCYCOA_EVALU_DEFINE where EVALU_YEAR='2015' and index_option<>'()=()'";
		DataTable dt = null;
		int count=0;
		dt = db.runSelectQuery(sql);
		count=dt.getRowsCount();
		return count;
	}

	public static void removeByName(String name) {
		DBObject db = new DBObject();
		String sql = "";
		sql = "delete from NCYCOA_SUPPLIER where supplier_name=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(name) };
		 
		try {
			db.run(sql, pp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getAllSupplier() {
		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select supplier_name from NCYCOA_SUPPLIER t ";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<option value='"+r.getString("supplier_name")+"'>";
					try {
						result=result+r.getString("supplier_name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</option>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean isEvalued(String depart, String supplier, String year) {
		DBObject db = new DBObject();
		String sql = "select * from NCYCOA_EVALU_RESULT where evalu_depart=? and evalu_supplier=? and evalu_year=? ";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(depart),new Parameter.String(supplier),new Parameter.String(year) };
		try {
			DataTable dt = db.runSelectQuery(sql,pp);
			if(dt != null&& dt.getRowsCount() >= 1)
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//�鿴ĳ��ĳ����ĳ��Ӧ�̵÷���ϸ�ַ���
	public static String getEvaluedDetail(String depart, String supplier, String year) {
		DBObject db = new DBObject();
		String result="";
		String sql = "select evalu_detail from NCYCOA_EVALU_RESULT where evalu_depart=? and evalu_supplier=? and evalu_year=? ";
		//String sql="select evalu_detail from NCYCOA_EVALU_RESULT where evalu_depart='�о֣���˾����ܿ�' and evalu_supplier='��Ӧ��9' and evalu_year='2015'";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(depart),new Parameter.String(supplier),new Parameter.String(year) };
        //System.out.println(depart+supplier+year);		
		try {
			DataTable dt = db.runSelectQuery(sql,pp);
			if(dt != null&& dt.getRowsCount() >= 1){
				result=dt.get(0).getString("evalu_detail");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void startEvalu() {
		DBObject db = new DBObject();
		String sql = "update NCYCOA_SUPPLIER_FLAG set FLAG_VALUE=? where FLAG_PARA=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String("T") ,new Parameter.String("isStartEvalu") };
        
		try {
			db.run(sql,pp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeEvalu() {
		DBObject db = new DBObject();
		String sql = "update NCYCOA_SUPPLIER_FLAG set FLAG_VALUE=? where FLAG_PARA=?";
		Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String("F") ,new Parameter.String("isStartEvalu") };
        
		try {
			db.run(sql,pp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static String getOrgNamesByCodes(String codes) {
		String[] orgs=codes.split(",");

		String result="";
		try {
			DBObject db = new DBObject();
			for(int j=0;j<orgs.length;j++){
				String sql = "select orgname from BASE_ORG t where orgcode='"+orgs[j]+"'";

				DataTable dt = db.runSelectQuery(sql);
				if (dt != null&& dt.getRowsCount() >= 1) {

					DataRow r = dt.get(0);
					if(orgs.length==1){
						result=r.getString("orgname");
					}else{
						result=result+r.getString("orgname")+",";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//ͨ����ݺ�ָ������ȡEvaluDefineʵ��
	public static EvaluDefine getIndexByYearAndCode(String year,String code) {
		EvaluDefine t=new EvaluDefine();
		try {
			DBObject db = new DBObject();
			String sql = "select * from NCYCOA_EVALU_DEFINE t where evalu_year=? and index_code=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(year),new Parameter.String(code) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null) {	
					DataRow r = dt.get(0);
					t.setEvaluYear(year);
					t.setIndexCode(code);
					t.setIndexName(r.getString("index_name"));
					t.setIndexOption(r.getString("index_option"));
					t.setIndexParentCode(r.getString("index_parentcode"));
					t.setInstruction(r.getString("instruction"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
	
	//ָ�����Ĺ�����
	public static DataTable getIndexList(String para){
		try {
			DBObject db = new DBObject();
			
			String base_sql = "select t.* from NCYCOA_EVALU_DEFINE t where t.index_code='"+para+"'";

			return db.runSelectQuery(base_sql);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//���淽������ȫģ��ʹ�ã�
	public static String getStaffNamesByCodes(String codes) {
		String[] staffs=codes.split(",");

		String result="";
		try {
			DBObject db = new DBObject();
			for(int j=0;j<staffs.length;j++){
				String sql = "select staffname from BASE_ORGMEMBER t where staffcode="+staffs[j]+"";

				DataTable dt = db.runSelectQuery(sql);
				if (dt != null&& dt.getRowsCount() >= 1) {

					DataRow r = dt.get(0);
					result=result+r.getString("staffname")+",";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getSupplierNameByID(String supplierID) {
		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select supplier_name from NCYCOA_SUPPLIER t where supplier_id=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.Long(Long.parseLong(supplierID)) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null&& dt.getRowsCount() >= 1) {
					DataRow r = dt.get(0);
					result=r.get(0).toString();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getOneDepartCode() {
		String result="";
		
		try {
			DBObject db = new DBObject();
			String sql = "select * from BASE_ORGMEMBER where staffcode=?";
			Parameter.SqlParameter[] pp = new Parameter.SqlParameter[] { new Parameter.String(SystemUtils.getSessionUser().getStaffcode()) };

			DataTable dt = db.runSelectQuery(sql, pp);
			if (dt != null&& dt.getRowsCount() >= 1) {
				
					DataRow r = dt.get(0);
					result=r.getString("orgcode");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getCodeByName(String name) {
		String result="";
		
		try {
			DBObject db = new DBObject();
			String sql = "select supplier_code from NCYCOA_SUPPLIER where supplier_name='"+name+"'";

			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				
					DataRow r = dt.get(0);
					result=r.getString("supplier_code");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean isBannedByCode(String code) {
		try {
			DBObject db = new DBObject();
			String sql = "select fobbiden_time,exit_time+0 from NCYCOA_SUPPLIER_EXIT where SUPPLIER_CODE='"+code+"'";

			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				
					DataRow r = dt.get(0);
					String fTime=r.getString("FOBBIDEN_TIME");
					System.out.println(fTime+"����");
					Date nextDate=(Date)r.get(1);
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); 
					   System.out.println("�´ε����ڣ�"+df.format(nextDate));   
					   Calendar cal = Calendar.getInstance();
					   cal.setTime(nextDate);
					   cal.add(Calendar.YEAR,Integer.valueOf(fTime));
					   Date date=cal.getTime(); 
					//nextDate.setTime((nextDate.getTime()+Integer.valueOf(fTime)*365*1000*60*60*24));
					System.out.println("��ε����ڣ�"+df.format(date)); 
					Date today=new Date();
					long days=(date.getTime()-today.getTime())/(1000*60*60*24);
					if(days>=0){
						//��û����
						return true;
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//��������ӵ�й�Ӧ���б��ɶ�ѡ
	public static String getRandomContent() {
		String temp=getDepartName();
		String[] departs=temp.substring(0, temp.length()-1).split(",");

		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select supplier_name from NCYCOA_SUPPLIER t where manage_depart LIKE '%"+departs[0]+"%'";
			for(int j=1;j<departs.length;j++){
				sql=sql+" or manage_depart LIKE '%"+departs[j]+"%'";
			}
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<label><input type='checkbox' name='supplier' value='"+r.getString("supplier_name")+"'/>";
					try {
						result=result+r.getString("supplier_name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</label><br>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//���ݴ��벿�Ŵ����ȡ��Ӧ������б�
	public static String getRandomContentByCode(String code) {
		String departs=CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", code);

		String result="";
		try {
			DBObject db = new DBObject();
			String sql = "select supplier_name from NCYCOA_SUPPLIER t where manage_depart LIKE '%"+departs+"%'";
			DataTable dt = db.runSelectQuery(sql);
			if (dt != null&& dt.getRowsCount() >= 1) {
				for (int i = 0; i < dt.getRowsCount(); i++)
				{
					DataRow r = dt.get(i);
					result=result+"<label><input type='checkbox' name='supplier' value='"+r.getString("supplier_name")+"'/>";
					try {
						result=result+r.getString("supplier_name");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					result=result+"</label><br>";
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
