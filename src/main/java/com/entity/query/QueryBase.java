package com.entity.query;

import com.db.DataRow;
import com.db.DataTable;

public class QueryBase {

	//画参数
	public static String drawParameter(int queryid,DataTable dt){
		try {
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<dt.getRowsCount();i++){
			DataRow row=dt.get(i);
			String edit_type=row.getString("paracode");
			if(edit_type.equals("")||edit_type.equals("00010001")){
				//文本框
				
			}
			if(edit_type.equals("00010002")){
				// 文本框，只能录入数字
			}
			if(edit_type.equals("00010003")){
				// 文本框，日期选择
			}
			if(edit_type.equals("00010004")){
				// 下拉框
			}
			if(edit_type.equals("00010005")){
				//选择框
			}
		
		}
		return sBuilder.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		
		
	}
	
}
