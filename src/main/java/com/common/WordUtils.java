package com.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class WordUtils {

	/**
     * 
     * @param destFile
     * @param fileCon
     */
    public static void exportDoc(String destFile,String fileCon){
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
    public static HWPFDocument replaceDoc(String srcPath, Map<String, String> map) {
        try {
            // 读取word模板
            FileInputStream fis = new FileInputStream(new File(srcPath));
            HWPFDocument doc = new HWPFDocument(fis);
            // 读取word文本内容
            Range bodyRange = (Range) doc.getRange();
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
/**
 * 根据Word模板导出单个Word文档
 * @param desPath
 * @param templatePath
 * @param map
 */
    public static void produceWord(String desPath,String templatePath,Map<String, String> map){
        HWPFDocument document=replaceDoc(templatePath, map);
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        try {
            document.write(ostream);
            //输出word文件
            OutputStream outs=new FileOutputStream(desPath);
            outs.write(ostream.toByteArray());
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
