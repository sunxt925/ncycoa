package edu.cqu.ncycoa.safety.poi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import edu.cqu.ncycoa.dao.SafetyDao;


public class ExportDocTest {
    
    public static void main(String[] args) {
        String destFile="D:\\11.doc";   
        //##################根据Word模板导出单个Word文档###################################################
        Map<String, String> map=new HashMap<String, String>();
        map.put("aaa", "bbb");
        map.put("place", "学校");
        //注意biyezheng_moban.doc文档位置,此例中为应用根目录
        HWPFDocument document=new ExportDocTest().replaceDoc("F:\\2702.doc", map);
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        try {
            document.write(ostream);
            //输出word文件
            OutputStream outs=new FileOutputStream(destFile);
            outs.write(ostream.toByteArray());
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    public static String getPath(String inPath,String dir,String type,String id){
    	String outPath=String.valueOf(new Date().getTime());
    	outPath=dir+outPath+".doc";
    	
//    	Map<String, String> map=new HashMap<String, String>();
//        map.put("time", "bbb");
//        map.put("place", "学校");
    	Map<String, String> map=SafetyDao.getDocMap(type,id);
        //注意biyezheng_moban.doc文档位置,此例中为应用根目录
        HWPFDocument document=new ExportDocTest().replaceDoc(inPath, map);
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        try {
            document.write(ostream);
            //输出word文件
            OutputStream outs=new FileOutputStream(outPath);
            outs.write(ostream.toByteArray());
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
		return outPath;
    }
    
    /**
     * 
     * @param destFile
     * @param fileCon
     */
    public void exportDoc(String destFile,String fileCon){
        try {
            //doc content
            ByteArrayInputStream bais = new ByteArrayInputStream(fileCon.getBytes());
            POIFSFileSystem fs = new POIFSFileSystem();
            DirectoryEntry directory = fs.getRoot(); 
            directory.createDocument("WordDocument", bais);
            FileOutputStream ostream = new FileOutputStream(destFile);
            fs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 读取word模板并替换变量
     * @param srcPath
     * @param map
     * @return
     */
    public HWPFDocument replaceDoc(String srcPath, Map<String, String> map) {
        try {
            // 读取word模板
            FileInputStream fis = new FileInputStream(new File(srcPath));
            HWPFDocument doc = new HWPFDocument(fis);
            // 读取word文本内容
            Range bodyRange = doc.getRange();
            // 替换文本内容
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bodyRange.replaceText("${" + entry.getKey() + "}", entry
                        .getValue());
            }
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}