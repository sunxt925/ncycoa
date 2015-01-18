
package com.entity.stdapply;

import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;  
import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;  
    

public class Office2Pdf {  
   
        public void createPDF(String officePath,String pdfPath) throws Exception {  
                ReleaseManager rm = null;  
                IDispatch app = null;  
                try {  
                        rm=new ReleaseManager();  
                        app = new IDispatch(rm, "PDFMakerAPI.PDFMakerApp"); 
                        //app.put("Visible",false);
                        app.method("CreatePDF",new Object[]{officePath,pdfPath}); 
                        
                } catch (Exception e) {  
                        throw e;  
                } finally {  
                        try {  
                                app=null;  
                                rm.release();  
                                rm = null;  
                        } catch (Exception e) {  
                                throw e;  
                        }  
                }  
        }  
   
        public static void main(String[] args) throws Exception {  
                Office2Pdf one=new Office2Pdf();  
                //one.createPDF("E:\\codigg.ppt","E:\\codigg-ppt.pdf");  
                one.createPDF("E:\\codigg.docx","E:\\codigg-doc.pdf");  
         //       one.createPDF("E:\\codigg.xls","E:\\codigg-xls.pdf");  
        }  
}   