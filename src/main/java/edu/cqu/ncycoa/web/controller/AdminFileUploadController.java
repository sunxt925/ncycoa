package edu.cqu.ncycoa.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * �ϴ�ͼƬ
 * <p>
 *     ΪCKEDITOR���Ƶ�ͼƬ�ϴ����ܣ�����������չ�ϴ�������ʽ���ļ�
 *  �ϴ����ļ��Ļ���·��Ϊ: ${apache.home}/${project.name}/${project.name}/resources/upload/img/${'yyyyMMdd'}/
 *  ÿ���ļ��������500���ļ�
 * </p>
 *
 */
@Controller
@RequestMapping("/admin/upload")
public class AdminFileUploadController {
    protected final Logger logger = Logger
            .getLogger(AdminFileUploadController.class);

    private static final String FILE_UPLOAD_DIR = "/upload";
    private static final String FILE_UPLOAD_SUB_IMG_DIR = "/img";
    private static final String FOR_RESOURCES_LOAD_DIR = "/resources";
    //ÿ���ϴ���Ŀ¼������ļ��������Ŀ
    private static final int MAX_NUM_PER_UPLOAD_SUB_DIR = 500;
    //�ϴ��ļ�������ļ���С
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;
    //ϵͳĬ�Ͻ�����ʹ�õ���ʱ���ַ�����Ϊ�ļ����Ƶ�ʱ���ʽ
    private static final String DEFAULT_SUB_FOLDER_FORMAT_AUTO = "yyyyMMdd";
    //��������һ�¸�ʽ����ֹ�ֶ������Ĳ�ͳһ
    private static final String DEFAULT_SUB_FOLDER_FORMAT_NO_AUTO = "yyyy-MM-dd";

    @RequestMapping(method = RequestMethod.GET)
    public void processUpload(ModelMap modelMap, HttpServletRequest request,
            HttpServletResponse response) {
        processUploadPost(modelMap, request, response);
        return;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void processUploadPost(ModelMap modelMap,
            HttpServletRequest request, HttpServletResponse response) {

    	System.out.println("this");
        // �ж��ύ�������Ƿ�����ļ�
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {
            return;
        }

        // ��ȡĿ¼
        File folder = buildFolder(request);
        if (null == folder) {
        	
            return;
        }
        
        try {
        	System.out.println("this2");
            response.setContentType("text/html; charset=UTF-8");
            response.setHeader("Cache-Control", "no-cache");
            PrintWriter out = response.getWriter();
            // �ϴ��ļ��ķ��ص�ַ
            String fileUrl = "";

            FileItemFactory factory = new DiskFileItemFactory();

            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            servletFileUpload.setFileSizeMax(MAX_FILE_SIZE);

            List<FileItem> fileitem = servletFileUpload.parseRequest(request);

            if (null == fileitem || 0 == fileitem.size()) {
            	System.out.println("this3");
                return;
            }

            Iterator<FileItem> fileitemIndex = fileitem.iterator();
            if (fileitemIndex.hasNext()) {
                FileItem file = fileitemIndex.next();

                if (file.isFormField()) {
                    logger.error("�ϴ��ļ��Ƿ���isFormField=true");
                }

                String fileClientName = getFileName(file.getName());
                String fileSuffix = StringUtils.substring(fileClientName,
                        fileClientName.lastIndexOf(".") + 1);
                
                if (!StringUtils.equalsIgnoreCase(fileSuffix, "jpg")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "jpeg")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "bmp")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "gif")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "png")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "txt")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "doc")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "docx")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "xls")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "xlsx")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "csv")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "ppt")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "pptx")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "pdf")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "wps")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "et")
                        && !StringUtils.equalsIgnoreCase(fileSuffix, "dps")) {
                    logger.error("�ϴ��ļ��ĸ�ʽ����=" + fileSuffix);
                    out.println("<script type=\"text/javascript\">alert('��ʽ���󣬽�֧��jpg|jpeg|bmp|gif|png|txt|doc|docx|xls|xlsx|csv|ppt|pptx|pdf|wps|et|dps��ʽ');</script>");
                    out.flush();
                    out.close();
                    return;
                }

                if (logger.isInfoEnabled()) {
                    logger.info("��ʼ�ϴ��ļ�:" + file.getName());
                }

                String fileServerName = generateFileName(folder, fileSuffix);
                // Ϊ�˿ͻ����Ѿ����ú���ͼƬ�����ڷ����������ܹ���ȷʶ�����ﲻ������
                File newfile = new File(folder, fileServerName);
                file.write(newfile);

                if (logger.isInfoEnabled()) {
                    logger.info("�ϴ��ļ�������������:" + fileServerName + ".floder:"
                            + newfile.getPath());
                }

                // ��װ����url���Ա���ckeditor��λͼƬ
                fileUrl = FOR_RESOURCES_LOAD_DIR + FILE_UPLOAD_DIR + FILE_UPLOAD_SUB_IMG_DIR + "/" + folder.getName() + "/" + newfile.getName();
                fileUrl = StringUtils.replace(fileUrl, "//", "/");
                fileUrl = request.getContextPath() + fileUrl;
    
                // ���ϴ���ͼƬ��url���ظ�ckeditor
                String callback = request.getParameter("CKEditorFuncNum");
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction("
                        + callback + ",'" + fileUrl + "',''" + ")");
                out.println("</script>");
            }

            out.flush();
            out.close();

        } catch (IOException e) {
            logger.error("�ϴ��ļ������쳣��", e);
        } catch (FileUploadException e) {
            logger.error("�ϴ��ļ������쳣��", e);
        } catch (Exception e) {
            logger.error("�ϴ��ļ������쳣��", e);
        }

        return;
    }
    
    private String generateFileName(File folder, String suffix) {
        String filename;
        File file;
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
        String base = format.format(date);
        filename = base + "." + suffix;
        file = new File(filename);
        int i = 1;
        while (file.exists()) {
            filename = String.format("%s_%d.%s", base, i, suffix);
            i++;
        }
        return filename;
    }
        
    /**
     * ��ȡ�ļ�����
     * @param str
     * @return
     */
    private String getFileName(String str){
        int index = str.lastIndexOf("//");
        if(-1 != index){
            return str.substring(index);
        } else {
            return str;
        }
    }

    /**
     * ����Ŀ¼
     * 
     * @return
     */
    private File buildFolder(HttpServletRequest request) {
        // �����չ�һ��CKEDITOR������ftl����λ�õ�ԭ���������Ҫ��freemarkerĿ¼�²��ܱ����ص�ͼƬ��������Ȼ���������ϴ���ʹ�ã�����
        // �ڿؼ����޷���������
        String realPath = request.getSession().getServletContext()
                .getRealPath(FOR_RESOURCES_LOAD_DIR);

        logger.error(realPath);

        // һ��Ŀ¼����������ڣ�����
        File firstFolder = new File(realPath + FILE_UPLOAD_DIR);
        if (!firstFolder.exists()) {
            if (!firstFolder.mkdir()) {
                return null;
            }
        }

        // ����Ŀ¼����������ڣ�����
        String folderdir = realPath + FILE_UPLOAD_DIR + FILE_UPLOAD_SUB_IMG_DIR;
        if (logger.isDebugEnabled()) {
            logger.debug("folderdir" + folderdir);
        }

        if (StringUtils.isBlank(folderdir)) {
            logger.error("·������:" + folderdir);
            return null;
        }

        File floder = new File(folderdir);
        if (!floder.exists()) {
            if (!floder.mkdir()) {
                logger.error("�����ļ��г���path=" + folderdir);
                return null;
            }

        }
        // �����µ��ļ��ж�����ʱ���ַ����������ģ����Ի�ȡ����ʱ����ļ��м���
        String[] files = floder.list();
        if (null != files && 0 < files.length) {
            // �������ļ��У����ȡ���µ�һ��
            Date oldDate = null;
            int index = -1;
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i];

                try {
                    Date thisDate = DateUtils.parseDate(fileName, new String[] {
                            DEFAULT_SUB_FOLDER_FORMAT_AUTO, DEFAULT_SUB_FOLDER_FORMAT_NO_AUTO });
                    if (oldDate == null) {
                        oldDate = thisDate;
                        index = i;
                    } else {
                        if (thisDate.after(oldDate)) {
                            // �������µ�ʱ��������е��±�
                            oldDate = thisDate;
                            index = i;
                        }
                    }
                } catch (ParseException e) {
                    // �����쳣�Ե���������ʲô���������ʧ�ܣ��Ὠ���µ��ļ��У���ֹ��Ϊ�Ľ����ļ��е��µ��쳣��
                }
            }// for

            // �жϵ�ǰ���µ��ļ������Ƿ��Ѿ������������Ŀ��ͼƬ
            if (null != oldDate && -1 != index) {
                File pointfloder = new File(folderdir + File.separator
                        + files[index]);
                if (!pointfloder.exists()) {
                    if (!pointfloder.mkdir()) {
                        logger.error("�����ļ��г���path=" + folderdir);
                        return null;
                    }
                }

                // ����ļ����µ��ļ����������ֵ����ôҲ��Ҫ�½�һ���ļ���
                String[] pointfloderFiles = pointfloder.list();
                if (null != pointfloderFiles
                        && MAX_NUM_PER_UPLOAD_SUB_DIR < pointfloderFiles.length) {
                    return buildNewFile(folderdir);
                }

                return pointfloder;
            }
            
            // ���ҵ�ǰ���ļ���ʧ�ܣ��½�һ��
            return buildNewFile(folderdir);
        } else {
            // ���������ļ��У��½�һ����ͨ��ϵͳ�״��ϴ�����������
            return buildNewFile(folderdir);
        }

    }
    
    /**
     * ����һ�����ļ�
     * @param path
     * @return
     */
    private File buildNewFile(String path){
        // ���������ļ��У��½�һ����ͨ��ϵͳ�״��ϴ�����������
        File newFile = buildFileBySysTime(path);
        if (null == newFile) {
            logger.error("�����ļ���ʧ�ܣ�newFile=" + newFile);
        }

        return newFile;
    }

    /**
     * ���ݵ�ǰ��ʱ�佨���ļ��У�ʱ���ʽyyyyMMdd
     * 
     * @param path
     * @return
     */
    private File buildFileBySysTime(String path) {
        DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
        String fileName = df.format(new Date());
        File file = new File(path + File.separator + fileName);
        if (!file.mkdir()) {
            return null;
        }
        return file;
    }
}