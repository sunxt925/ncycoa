package com.entity.query;

import com.db.DataRow;
import com.db.DataTable;

public class QueryBase {

	//������
	public static String drawParameter(int queryid,DataTable dt){
		try {
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<dt.getRowsCount();i++){
			DataRow row=dt.get(i);
			String edit_type=row.getString("paracode");
			if(edit_type.equals("")||edit_type.equals("00010001")){
				//�ı���
				
			}
			if(edit_type.equals("00010002")){
				// �ı���ֻ��¼������
			}
			if(edit_type.equals("00010003")){
				// �ı�������ѡ��
			}
			if(edit_type.equals("00010004")){
				// ������
			}
			if(edit_type.equals("00010005")){
				//ѡ���
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
