package com.business;

import com.common.Format;
import com.common.SequenceUtil;
import com.db.DBObject;
import com.db.Parameter;

public class BuyGoodsApp {

	//汇总呈报项目
	public static String appGetEventno(String[] str,String buymode,String staffcode){
	
		try {
			
	    
		DBObject db=new DBObject(); 
		String sql="insert into com_buyreportevent (eventno,eventtype,numgoodsitem,buymode,summitflag,auditflag,handler,summitdate) values(?,?,?,?,?,?,?,to_date('"+Format.getNowtime2()+"','yyyy-mm-dd')) ";
		String eventno=getEventno("COM_BUYREPORTEVENT","BY");
		Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]{
	
			new Parameter.String(""+eventno),
			new Parameter.String("采购"),
			new Parameter.Int(str.length),
			new Parameter.String(buymode),
			
			//new Parameter.String("to_date('"+Format.getNowtime()+"','yyyy-mm-dd hh24:mi:ss')"),
			new Parameter.String("1"),
			new Parameter.String("00"),
			new Parameter.String(staffcode)
		//	new Parameter.String("12"),
		//	new Parameter.String(""+str.length),
		//	new Parameter.String("0"),
		//	new Parameter.String("ss"),
		//	new Parameter.String("li"),
		//	new Parameter.String("2010-10-10"),
		//	new Parameter.String("2010-10-11")
		};
		db.run(sql, pp);
		
		return eventno;
		
		
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public static String app(String[] str,String eventno,String buymode){
		
		try {
			
		
		DBObject db=new DBObject();
		String sql="update com_buygoodsitem set eventno=? ,summitflag=?,auditflag=?,buymode=? where buyno=?";
		for(int i=0;i<str.length;i++)
		{
			Parameter.SqlParameter[] pp=new Parameter.SqlParameter[]
			{ new Parameter.String(eventno),
					new Parameter.String("1"),
					new Parameter.String("00"),
					new Parameter.String(buymode),
					new Parameter.String(str[i])};
			db.run(sql, pp);
		}
		
		
		
		return "申报成功";
		} catch (Exception e) {
			// TODO: handle exception
			return "申报失败";
		}
		
	}
	public static String getEventno(String para1,String para2){
		
		String eventno="";
		int eventnoseq=SequenceUtil.getSequence(para1);
		int eee=eventnoseq;
		int v=0;
	    while(eee/10>=1)
	    {
	    	eee=eee/10;
	    	v++;
	    }
	    v++;
		
		if(v<7)
		{   
			StringBuilder sBuilder=new StringBuilder();
			sBuilder.append("");
			for(int i=0;i<7-v;i++){
				sBuilder.append("0");
			}
			
			eventno=para2+sBuilder.toString()+eventnoseq;
		}
		
	    return eventno;
	}
}
