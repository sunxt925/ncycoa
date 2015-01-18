package com.action.index;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.action.ActionInterface;
import com.common.EntityOperation;
import com.entity.index.Indexitem;
import com.entity.index.IndexitemPara;

public class IndexItemAction extends ActionInterface{

	@Override
	public String getResult(HttpServletRequest request) {
		String res = "";
		String res0 ="";
		String action = request.getParameter("act");
		
		EntityOperation eo = new EntityOperation();
		if (action != null && action.equals("add"))
		{
			
		
			String entity=request.getParameter("entity");
			
			eo.setEntity(entity);
			res0 = eo.Add(request);
			if(res0.equals("插入成功")){
				String indexcode=request.getParameter("TBM_INDEXITEM.INDEXCODE");
				String[] indexpara=request.getParameter("indexpara").split(",");
				String[] indexparasrc=request.getParameter("indexparasrc").split(",");
				
				for(int i=0;i<indexpara.length;i++){
					if(indexparasrc[i]!=null&&!indexparasrc[i].equals("")&&indexpara[i]!=null&&!indexpara[i].equals("")){
						IndexitemPara indexitemPara=new IndexitemPara();
						indexitemPara.setIndexcode(indexcode);
						indexitemPara.setParaid("P"+(i+1));
						indexitemPara.setParacode(indexpara[i]);
						indexitemPara.setParavaluemode(indexparasrc[i]);
						IndexitemPara.insert(indexitemPara);
					}
					
				}
				
				
			}
			res += "show('"+res0+"');";
			res += "window.close();";
		}
		else if(action != null && action.equals("del"))
		{
			String entity=request.getParameter("entity");
			eo.setEntity(entity);
			
			String[] itemsStrings=request.getParameterValues("items");
			
			String index_class=request.getParameter("index_class");
			boolean flag=true;
			if(itemsStrings==null){
				String indexcode=request.getParameter("indexcode");
                if(indexcode.length()>3)
                	flag=false;
				Map<String,String> deletemap = new HashMap<String, String>();
				
				deletemap.put("INDEXCODE", indexcode);
				
				eo.setDeletemap(deletemap);
				res0=eo.Delete();
				IndexitemPara.delete(indexcode);//删除参数
				if(Indexitem.delete(indexcode)){//删除子记录
					IndexitemPara.deletesub(indexcode);
				}
				}
			else {
				for(int i=0;i<itemsStrings.length;i++)
				{
					Map<String,String> deletemap = new HashMap<String, String>();
					
					deletemap.put("INDEXCODE", itemsStrings[i]);
					 if(itemsStrings[i].length()>3)
		                	flag=false;
					eo.setDeletemap(deletemap);
					res0=eo.Delete();
					IndexitemPara.delete(itemsStrings[i]);//删除参数
					if(Indexitem.delete(itemsStrings[i])){//删除子记录
						IndexitemPara.deletesub(itemsStrings[i]);
					}
					
				}
			}
			    String url="";
			    if(flag)
			    	url="../indexmanage/indexrootlist.jsp?class="+index_class+"&sid='+rand";
			    else
			    	url="../indexmanage/indexlist.jsp?indexccm="+index_class+"&sid='+rand";
				res += "show('"+res0+"');";
				
			    //res+="$.dialog({ content: '"+res0+"',title:'提示',ok: function(){this.title('提示').time(2);return false;}, cancelVal: '关闭', cancel: true /*为true等价于function(){}*/});";
				res += "var rand=Math.floor(Math.random()*10000);";
				res += "window.open('"+url+",'_self');";
			}
		else if (action != null && action.equals("modify"))
		{
            String entity=request.getParameter("entity");
			eo.setEntity(entity);
			res0 = eo.Update(request);	
			//修改指标项日期
			if(request.getParameter("TBM_INDEXITEM.PARENTINDEXCODE").equals("-1")){
				Indexitem.update(request.getParameter("old_INDEXCODE"), request.getParameter("TBM_INDEXITEM.VALIDBEGINDATE"), request.getParameter("TBM_INDEXITEM.VALIDENDDATE"));
			}
			if(res0.equals("更改成功")){
				String indexcode=request.getParameter("old_INDEXCODE");
				
				String[] indexpara=request.getParameter("indexpara").split(",");
				String[] indexparasrc=request.getParameter("indexparasrc").split(",");
				IndexitemPara.delete(indexcode);
				for(int i=0;i<indexpara.length;i++){
					if(indexparasrc[i]!=null&&!indexparasrc[i].equals("")&&indexpara[i]!=null&&!indexpara[i].equals("")){
						IndexitemPara indexitemPara=new IndexitemPara();
						indexitemPara.setIndexcode(indexcode);
						indexitemPara.setParaid("P"+(i+1));
						indexitemPara.setParacode(indexpara[i]);
						indexitemPara.setParavaluemode(indexparasrc[i]);
						IndexitemPara.insert(indexitemPara);
					}
					
				}
				
			}
			
			res += "show('"+res0+"');";
			res += "window.close();";
		}
			
			
		
		return res;
	}

}
