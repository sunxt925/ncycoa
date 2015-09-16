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
        //##################����Wordģ�嵼������Word�ĵ�###################################################
        Map<String, String> map=new HashMap<String, String>();
        map.put("aaa", "bbb");
        map.put("place", "ѧУ");
        //ע��biyezheng_moban.doc�ĵ�λ��,������ΪӦ�ø�Ŀ¼
        HWPFDocument document=new ExportDocTest().replaceDoc("F:\\2702.doc", map);
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        try {
            document.write(ostream);
            //���word�ļ�
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
//        map.put("place", "ѧУ");
    	Map<String, String> map=SafetyDao.getDocMap(type,id);
        //ע��biyezheng_moban.doc�ĵ�λ��,������ΪӦ�ø�Ŀ¼
        HWPFDocument document=new ExportDocTest().replaceDoc(inPath, map);
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        try {
            document.write(ostream);
            //���word�ļ�
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
     * ��ȡwordģ�岢�滻����
     * @param srcPath
     * @param map
     * @return
     */
    public HWPFDocument replaceDoc(String srcPath, Map<String, String> map) {
        try {
            // ��ȡwordģ��
            FileInputStream fis = new FileInputStream(new File(srcPath));
            HWPFDocument doc = new HWPFDocument(fis);
            // ��ȡword�ı�����
            Range bodyRange = doc.getRange();
            // �滻�ı�����
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