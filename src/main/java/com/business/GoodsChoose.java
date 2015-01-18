package com.business;

import com.dao.system.LoadCode;
import com.db.DBObject;
import com.db.DataRow;
import com.db.DataTable;

public class GoodsChoose {

	public static String getGoodsinfo(String s)
	{
		try {
			
		
		String sql="select * from com_goodsclass where goodsname like '%"+s+"%'";
		DBObject db=new DBObject();
		DataTable dt=db.runSelectQuery(sql);
		
		 LoadCode ld=new LoadCode();
		
		StringBuffer sb=new StringBuffer();
		//sb.append("<select size="+dt.getRowsCount()+">");
		/*
		sb.append("选择物资：");
		sb.append("<select style=width:200px;  id='goodsinfo' name='goodsinfo'  >");
		//sb.append("<option value=''>--请选择-</option>");
		for(int i=0;i<dt.getRowsCount();i++)
		{
		DataRow r=dt.get(i);
		DataTable dt2=ld.getSelectItem("COM_GOODSCLASS");
		String parentgoodsname="";
		for(int j=0;j<dt2.getRowsCount();j++){
						if(dt2.get(j).getString("goodscode").equals(r.getString("parentgoodscode")))
						parentgoodsname=dt2.get(j).getString("goodsname");
					}
	  // sb.append("<option value='"+r.getString("table_name")+"'>"+r.getString("table_name")+"</option>");
				sb.append("<option value='"+r.getString("goodscode")+","+r.getString("goodsname")+","+r.getString("parentgoodscode")+","+parentgoodsname+"'>"+r.getString("goodsname")+"</option>");
		
		}
		sb.append("</select>");*/
		sb.append("<ul id=\"hello\" style=\"overflow-x: auto; overflow-y: auto; border:1px solid #cecece; border-top:none; width:200px; height:420px; padding:2px; margin:0px;\">");
		for(int i=0;i<dt.getRowsCount();i++)
		{
		DataRow r=dt.get(i);
		DataTable dt2=ld.getSelectItem("COM_GOODSCLASS");
		String parentgoodsname="";
		for(int j=0;j<dt2.getRowsCount();j++){
						if(dt2.get(j).getString("goodscode").equals(r.getString("parentgoodscode")))
						parentgoodsname=dt2.get(j).getString("goodsname");
					}
	  // sb.append("<option value='"+r.getString("table_name")+"'>"+r.getString("table_name")+"</option>");
				//sb.append("<option value='"+r.getString("goodscode")+","+r.getString("goodsname")+","+r.getString("parentgoodscode")+","+parentgoodsname+"'>"+r.getString("goodsname")+"</option>");
		 sb.append("<li  onMouseOver=\"this.style.backgroundColor='#D0E9ED'\" onMouseOut=\"this.style.backgroundColor=''\"  onclick=\"s('"+r.getString("goodscode")+","+r.getString("goodsname")+","+r.getString("parentgoodscode")+","+parentgoodsname+"')\">"+r.getString("goodsname")+"</li>");
		}
		sb.append("</ul>");
		return sb.toString();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
