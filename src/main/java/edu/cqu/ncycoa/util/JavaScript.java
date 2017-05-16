package edu.cqu.ncycoa.util;

import java.util.ArrayList;
import java.util.List;  

import javax.script.ScriptEngine;  
import javax.script.ScriptEngineManager;  
import javax.script.ScriptException;  
  
public class JavaScript {  
    static ScriptEngineManager factory = new ScriptEngineManager();  
    static ScriptEngine engine = factory.getEngineByName("JavaScript");  
      
    public static Double getMathValue(List<MapJ> map,String option){  
        double d = 0;  
        try {  
            for(int i=0; i<map.size();i++){  
                MapJ mapj = map.get(i);  
                option = option.replaceAll(mapj.getKey(), mapj.getValue());  
            }  
            Object o = engine.eval(option); 
            d = Double.parseDouble(o.toString());  
        } catch (ScriptException e) {  
            System.out.println("无法识别表达式");  
            return null;  
        }  
        return d;  
    }

	public static boolean isTrueOrFalse(String sbt, String planv, String realv) {
		System.out.println(sbt);
		List<MapJ> all = new ArrayList<MapJ>();  
        all.add(new MapJ("P",planv));  
        all.add(new MapJ("R",realv));
        if(sbt.contains(">")){
        	if(getMathValue(all,sbt.split(">")[0])>Double.parseDouble(sbt.split(">")[1])){
        		return true;
        	}else{
        		return false;
        	}
        }else if(sbt.contains("<")){
        	if(getMathValue(all,sbt.split("<")[0])<Double.parseDouble(sbt.split("<")[1])){
        		return true;
        	}else{
        		return false;
        	}
        }else if(sbt.contains("=")){
        	if(getMathValue(all,sbt.split("=")[0])==Double.parseDouble(sbt.split("=")[1])){
        		return true;
        	}else{
        		return false;
        	}
        }
		return false;
	}  
}  
