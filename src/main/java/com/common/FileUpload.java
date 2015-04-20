package com.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUpload {  
    private Map<String,String> params;  
    private Map<String,FileItem> files;  
      
    public FileUpload() {  
        params=new HashMap<String, String>();  
        files=new HashMap<String, FileItem>();  
    }  
      
    public void setMap(HttpServletRequest request){  
        // Create a factory for disk-based file items  
        FileItemFactory factory = new DiskFileItemFactory();  
        // Create a new file upload handler  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        upload.setHeaderEncoding("utf-8");  
        upload.setProgressListener(new MyProgressListener(request));//设置进度监听器  
        // Parse the request  
        try {  
            List items = upload.parseRequest(request);  
            Iterator iter = items.iterator();  
            while (iter.hasNext()) {  
                FileItem item = (FileItem) iter.next();  
                if (item.isFormField()) {  
                    String name = item.getFieldName();  
                    String value = item.getString();  
                    params.put(name, value);  
                }   
                else{  
                    String name=item.getFieldName();  
                    files.put(name, item);  
                }  
            }  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public Map<String, String> getParams() {  
        return params;  
    }  
  
    public Map<String, FileItem> getFiles() {  
        return files;  
    }  
    //用来获取文件的名字  
    public String getFileName(FileItem item){  
        String fName=item.getName();  
     //   System.out.println("fname=====>"+fName);  
        int lastIndex=fName.lastIndexOf("\\");  
        fName=fName.substring(lastIndex+1);  
      //  System.out.println("new fname=====>"+fName);  
        return fName;  
    }  
    public static void copyFile(String oldPath, String newPath) { 
        try { 
            int bytesum = 0; 
            int byteread = 0; 
            File oldfile = new File(oldPath); 
            if (oldfile.exists()) { //文件存在时 
                InputStream inStream = new FileInputStream(oldPath); //读入原文件 
                FileOutputStream fs = new FileOutputStream(newPath); 
                byte[] buffer = new byte[1444]; 
                int length; 
                while ( (byteread = inStream.read(buffer)) != -1) { 
                    bytesum += byteread; //字节数 文件大小 
                    fs.write(buffer, 0, byteread); 
                } 
                inStream.close(); 
                fs.close();
            } 
        } 
        catch (Exception e) { 
            System.out.println("复制单个文件操作出错"); 
            e.printStackTrace(); 

        } 

    } 
}  