package edu.cqu.ncycoa.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.common.CodeDictionary;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TQRestriction;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.dao.SupplierDao;
import edu.cqu.ncycoa.domain.Notice;
import edu.cqu.ncycoa.safety.domain.CheckPlan;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Resource(name="systemService")
	SystemService systemService;
	
	private File upload;
	private String uploadContentType;
	private String uploadFileName;
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		return "notice/addnotice";
	}
	
	@RequestMapping(params="get")
	public String get(@RequestParam("upload") MultipartFile upload0,HttpServletRequest request, HttpServletResponse response) throws Exception {		
		System.out.println(upload0.getContentType());
		uploadContentType=upload0.getContentType();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// CKEditor提交的很重要的一个参数
		String callback = request.getParameter("CKEditorFuncNum");

		String expandedName = ""; // 文件扩展名
		if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {
			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		} else if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")) {
			// IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		} else if (uploadContentType.equals("image/gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals("image/bmp")) {
			expandedName = ".bmp";
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'','文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
			out.println("</script>");
			return null;
		}

		if (upload0.getSize() > 600 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",''," + "'文件大小不得大于600k');");
			out.println("</script>");
			return null;
		}
		String uploadPath = request.getRealPath("/upload/notice");
		System.out.println(uploadPath);
		String fileName = java.util.UUID.randomUUID().toString(); // 采用UUID的方式命名保证不会重复
		fileName += expandedName;
		upload0.transferTo( new java.io.File( uploadPath+"\\"+fileName ) );
/*
		InputStream is = new FileInputStream(upload);
		//String uploadPath = ServletActionContext.getServletContext().getRealPath("/");
		String uploadPath = request.getServletPath();//("/img/postImg")
//		HttpServletRequest request = ServletActionContext.getRequest(); 
//		String uploadPath =request.getContextPath(); 
		uploadPath+="/img/postImg";
		System.out.println(uploadPath);
		String fileName = java.util.UUID.randomUUID().toString(); // 采用UUID的方式命名保证不会重复
		fileName += expandedName;
		File toFile = new File(uploadPath, fileName);
		OutputStream os = new FileOutputStream(toFile);
		
		// 文件复制到指定位置
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
*/
		// 返回“图像”选项卡并显示图片
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'" + "/ncycoa/upload/notice/"+ fileName + "','')"); // 相对路径用于显示图片
		out.println("</script>");
		return null;
	}
	@RequestMapping(params="getfile")
	public String getfile(@RequestParam("upload") MultipartFile upload0,HttpServletRequest request, HttpServletResponse response) throws Exception {	
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
//		System.out.println("hou:"+upload0.getOriginalFilename().substring(upload0.getOriginalFilename().lastIndexOf(".")));
		uploadContentType=upload0.getContentType();

		// CKEditor提交的很重要的一个参数
		String callback = request.getParameter("CKEditorFuncNum");
		System.out.println(uploadContentType);
		String expandedName = upload0.getOriginalFilename().substring(upload0.getOriginalFilename().lastIndexOf("."));
	/*
		if (uploadContentType.equals("application/msword")) {
			expandedName = ".doc";
		} else if (uploadContentType.equals("application/vnd.ms-excel")) {
			expandedName = ".xls";
		} else if (uploadContentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			expandedName = ".xlsx";
		} else if (uploadContentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
			expandedName = ".docx";
		} else if (uploadContentType.equals("application/octet-stream")) {
			expandedName = ".zip";
		} else if (uploadContentType.equals("text/plain")) {
			expandedName = ".txt";
		} else if (uploadContentType.equals("application/pdf")) {
			expandedName = ".pdf";
		}else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'','不支持的附件格式');");
			out.println("</script>");
			return null;
		}
		*/
//		upload.getAbsolutePath().substring(upload.getAbsolutePath().lastIndexOf(".")); // 文件扩展名
//		System.out.println(expandedName);

		if (upload0.getSize() > 50 * 1024*1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",''," + "'文件大小不得大于50M');");
			out.println("</script>");
			return null;
		}
		String uploadPath = request.getRealPath("/upload/notice");
		System.out.println(uploadPath);
		String fileName = java.util.UUID.randomUUID().toString(); // 采用UUID的方式命名保证不会重复
		fileName += expandedName;
		upload0.transferTo( new java.io.File( uploadPath+"\\"+fileName ) );
/*
		InputStream is = new FileInputStream(upload);
		//String uploadPath = ServletActionContext.getServletContext().getRealPath("/");
		String uploadPath = request.getRealPath("/img/postImg");
//		HttpServletRequest request = ServletActionContext.getRequest(); 
//		String uploadPath =request.getContextPath(); 
//		uploadPath+="/img/postImg";
		System.out.println(uploadPath);
		//String fileName = java.util.UUID.randomUUID().toString(); // 采用UUID的方式命名保证不会重复
		String fileName=uploadFileName;
		//fileName += expandedName;
		File toFile = new File(uploadPath, fileName);
		OutputStream os = new FileOutputStream(toFile);
		
		// 文件复制到指定位置
		byte[] buffer = new byte[1024];
		int length = 0;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		is.close();
		os.close();
*/
		// 返回“图像”选项卡并显示图片
		System.out.println(fileName);
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",'" + "/ncycoa/upload/notice/"+ fileName + "','')"); // 相对路径用于显示图片
		out.println("</script>");
		return null;
	}
	
	@RequestMapping(params="del")
	@ResponseBody
	public void del(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return;
		}
		Long[] ids;
		try {
			ids = ConvertUtils.toLongs(id.split(","));
		} catch (Exception e) {
			message = "数据不合法";
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		
		systemService.removeEntities(ids, Notice.class);
		message = "删除成功";
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="update")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		Notice notice = systemService.findEntityById(Long.parseLong(id), Notice.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/addnotice");
		mav.addObject("notice",notice);
		mav.addObject("orgname",CodeDictionary.syscode_traslate("base_org","orgcode", "orgname", notice.getDepart()));
		return mav;
	}
	
	@RequestMapping(params="seeContent")
    public ModelAndView seeContent(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id"); 
		Notice notice = systemService.findEntityById(Long.parseLong(id), Notice.class);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/seenotice");
		mav.addObject("notice",notice);
		return mav;
	}
	
	@RequestMapping(params = "save")
	@ResponseBody
	public void save(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		if (notice.getId() != null) {
			message = "会议室更新成功";
			Notice t = systemService.findEntityById(notice.getId(), Notice.class);
			try {
				MyBeanUtils.copyBeanNotNull2Bean(notice, t);
				
				systemService.saveEntity(t);
				
			} catch (Exception e) {
				message = "会议室更新失败";
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} else {
			message = "会议室添加成功";
			notice.setDepart(SupplierDao.getOneDepartCode());
			systemService.addEntity(notice);
			
		}
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	
	@RequestMapping(params="dgview_add")
	public String dgViewA(HttpServletRequest request) {	
		return "notice/mynoticelist";
	}
	@RequestMapping(params="dgview_see")
	public String dgViewS(HttpServletRequest request) {	
		return "notice/allnoticelist";
	}
	@RequestMapping(params="dgview_del")
	public String dgViewD(HttpServletRequest request) {	
		return "notice/adminnoticelist";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_add")
	@ResponseBody
	public void dgDataA(Notice notice, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Notice> cq = new QueryDescriptor<Notice>(Notice.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<Notice> tqBuilder = QueryUtils.getTQBuilder(notice, request.getParameterMap());
		tqBuilder.addRestriction(new TQRestriction( "depart", "in", Arrays.asList(SupplierDao.getOneDepartCode())));

		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata_all")
	@ResponseBody
	public void dgDataS(Notice notice, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<Notice> cq = new QueryDescriptor<Notice>(Notice.class, dg);
		
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<Notice> tqBuilder = QueryUtils.getTQBuilder(notice, request.getParameterMap());
		
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
}
