package edu.cqu.ncycoa.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.common.Util;
import com.entity.index.AllMeritCollection;
import com.entity.system.Staff;
import com.entity.system.UserInfo;

import edu.cqu.ncycoa.common.dto.AjaxResultJson;
import edu.cqu.ncycoa.common.dto.DataGrid;
import edu.cqu.ncycoa.common.dto.QueryDescriptor;
import edu.cqu.ncycoa.common.service.CommonService;
import edu.cqu.ncycoa.common.service.SystemService;
import edu.cqu.ncycoa.common.tag.TagUtil;
import edu.cqu.ncycoa.common.util.dao.QueryUtils;
import edu.cqu.ncycoa.common.util.dao.TQOrder;
import edu.cqu.ncycoa.common.util.dao.TypedQueryBuilder;
import edu.cqu.ncycoa.domain.GetStd;
import edu.cqu.ncycoa.domain.Plan;
import edu.cqu.ncycoa.util.ConvertUtils;
import edu.cqu.ncycoa.util.Globals;
import edu.cqu.ncycoa.util.MyBeanUtils;
import edu.cqu.ncycoa.util.SystemUtils;

@Controller
@RequestMapping("/stdget")
public class StdGetController{
	
	@Resource(name="systemService")
	SystemService systemService;
	
	@RequestMapping(params="add")
	public String add(HttpServletRequest request) {
		UserInfo user=SystemUtils.getSessionUser();
		String orgname=new Staff(user.getStaffcode()).getOrgname();
		request.setAttribute("orgname", orgname);
		request.setAttribute("staffname", new Staff(user.getStaffcode()).getStaffname());
		request.setAttribute("staffcode", user.getStaffcode());
		return "std_get/stdadd";
	}
	
	@RequestMapping(params="turntostd")
	public String turntostd(HttpServletRequest request) {
		String id = request.getParameter("id");
		GetStd t = systemService.findEntityById((long) Integer.parseInt(id), GetStd.class);
		request.setAttribute("getstd", t);
		return "std_get/std_turn";
	}
	
	@RequestMapping(params = "turn")
	public void turn(GetStd getstd, HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message="";
		if (getstd.getId() != null) {
			GetStd t = systemService.findEntityById(getstd.getId(), GetStd.class);
			try {
				t.setGstOperate("4");
				t.setGstStdname(getstd.getGstStdname());
				t.setGstStdtype(getstd.getGstStdtype());
				t.setGstTurntime(getstd.getGstTurntime());
				systemService.saveEntity(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				message = "转化成功";
			} catch (Exception e) {
				message = "转化失败";
			}
		} 
		
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
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
			message = "data unlawful!";
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		for(int i=0;i<ids.length;i++){
			GetStd getstd=systemService.findEntityById(ids[i], GetStd.class);
			String filepath=getstd.getGstFilepath();
			if (filepath != null && !(filepath.equals(""))) {
				String[] filepaths = getstd.getGstFilepath().split(";");
				for (int m = 0; m < filepaths.length; m++) {
					File deletefile = new File((request.getSession()
							.getServletContext().getRealPath("doc")
							+ "/getstd/" + filepaths[m]));
					deletefile.delete();
				}
			}
		}
		systemService.removeEntities(ids, GetStd.class);
		message = "delete success!";
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}
	
	@RequestMapping(params="download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
			return;
		}
		String FileUrl = request.getSession().getServletContext()
				.getRealPath("doc/getstd");
		GetStd getstd = systemService.findEntityById(ids[0], GetStd.class);
		String filepath = getstd.getGstFilepath();
		if (filepath != null && !(filepath.equals(""))) {
			String strZipName = getstd.getGstFilecode()+".zip";
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(FileUrl+"\\"+strZipName));
			String[] filepaths = filepath.split(";");
			File[] file = new File[filepaths.length];
			for (int i = 0; i < filepaths.length; i++) {
				String url = FileUrl + "\\" + filepaths[i];
				file[i] = new File(url);
			}
			byte[] buffer = new byte[1024];
			for(int i=0;i<file.length;i++) {

		           FileInputStream fis = new FileInputStream(file[i]);

		           out.putNextEntry(new ZipEntry(file[i].getName()));

		           int len;

		           //读入需要下载的文件的内容，打包到zip文件

		          while((len = fis.read(buffer))>0) {

		           out.write(buffer,0,len);

		          }

		           out.closeEntry();

		           fis.close();

		       }

		   out.close();
		   FileInputStream fin = new FileInputStream(FileUrl+"\\"+strZipName);        
		response.reset();//设置为没有缓存
		response.setContentType("application/zip;charset=GBK");
		response.setHeader("Content-Disposition", "attachment;filename="+ strZipName);
		OutputStream output = response.getOutputStream();
		 int r = 0;
		  while ((r = fin.read(buffer, 0, buffer.length)) != -1) {
		  output.write(buffer, 0, r);
		  }
		  fin.close();
		  response.getOutputStream().flush();
		  response.getOutputStream().close();
		  message = "下载成功";
		  File deletefile=new File(FileUrl+"\\"+strZipName);
		  deletefile.delete();
//		  response.getWriter().write(AllMeritCollection.getCollectionJson(message));
//			response.getWriter().flush();
//			response.getWriter().close();
		  systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}
		
		
	}
	
	@RequestMapping(params="quote0")
	public void quote0(HttpServletRequest request, HttpServletResponse response) {
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
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		GetStd getstd=systemService.findEntityById(ids[0], GetStd.class);
		//if(getstd.getGstOperate().equals("0")){
			getstd.setGstOperate("1");
			systemService.saveEntity(getstd);
			message = "作废处理成功";
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			j.setMsg(message);
		//}else{
//			message = "您选择的采标文件已经处理过";
//			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//		}
		
		SystemUtils.jsonResponse(response, j);
	}
	@RequestMapping(params="quote1")
	public void quote1(HttpServletRequest request, HttpServletResponse response) {
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
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		GetStd getstd=systemService.findEntityById(ids[0], GetStd.class);
		//if(getstd.getGstOperate().equals("0")){
			getstd.setGstOperate("2");
			systemService.saveEntity(getstd);
			message = "全文引用处理成功";
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			j.setMsg(message);
		//}else{
//			message = "您选择的采标文件已经处理过";
//			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//		}
		
		SystemUtils.jsonResponse(response, j);
	}
	@RequestMapping(params="quote2")
	public void quote2(HttpServletRequest request, HttpServletResponse response) {
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
			j.setSuccess(false);
			SystemUtils.jsonResponse(response, j);
			return;
		}
		GetStd getstd=systemService.findEntityById(ids[0], GetStd.class);
		//if(getstd.getGstOperate().equals("0")){
			getstd.setGstOperate("3");
			systemService.saveEntity(getstd);
			message = "部分引用处理成功";
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			j.setMsg(message);
		//}else{
//			message = "您选择的采标文件已经处理过";
//			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
//		}
		
		SystemUtils.jsonResponse(response, j);
	}

	@RequestMapping(params="upfile")
	public void upfile(MultipartHttpServletRequest multipartRequest,
			HttpServletResponse response) throws Exception {
		String Id=multipartRequest.getParameter("id");
		GetStd getstd=new GetStd();
		
		getstd.setGstFilecode(new String(multipartRequest.getParameter("gstFilecode").getBytes("ISO-8859-1"),"UTF-8"));
		getstd.setGstFilelevel(new String(multipartRequest.getParameter("gstFilelevel").getBytes("ISO-8859-1"),"UTF-8"));
		getstd.setGstFilename(new String(multipartRequest.getParameter("gstFilename").getBytes("ISO-8859-1"),"UTF-8"));
		getstd.setGstFiletype(new String(multipartRequest.getParameter("gstFiletype").getBytes("ISO-8859-1"),"UTF-8"));
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		String publictime=multipartRequest.getParameter("gstPublictime");
		getstd.setGstYear(publictime.substring(0,4));
		Date date = fmt.parse(publictime);
		getstd.setGstPublictime(date);
		getstd.setGstStaffcode(new String(multipartRequest.getParameter("gstStaffcode").getBytes("ISO-8859-1"),"UTF-8"));
		getstd.setGstStaffname(new String(multipartRequest.getParameter("gstStaffname").getBytes("ISO-8859-1"),"UTF-8"));
		getstd.setGstStafforg(new String(multipartRequest.getParameter("gstStafforg").getBytes("ISO-8859-1"),"UTF-8"));
		getstd.setGstOperate("0");
		AjaxResultJson j = new AjaxResultJson();
		String message;
//		if (Id != null&&!(Id.equals(""))) {
//			getstd.setId((long) Integer.parseInt(Id));
//			message = "采标更新成功";
//			GetStd t = systemService.findEntityById(getstd.getId(), GetStd.class);
//			try {
//				MyBeanUtils.copyBeanNotNull2Bean(getstd, t);
//				systemService.saveEntity(t);
//				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
//			} catch (Exception e) {
//				message = "采标更新失败";
//			}
//		} else {
			message = "采标添加成功";

	//	}
		

		
		response.setContentType("text/html;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取多个file
		int i=100;
		String filepath="";
		for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = (String) it.next();
			MultipartFile imgFile = multipartRequest.getFile(key);
			if (imgFile.getOriginalFilename().length() > 0) {
				String originalName = imgFile.getOriginalFilename();
				String fileName = getstd.getGstFilecode()+i+originalName.substring(originalName.lastIndexOf("."),originalName.length());
				filepath=filepath+fileName+";";
				i++;
				// 改成自己的对象哦！
				// Constant.UPLOAD_GOODIMG_URL 是一个配置文件路径
				try {
					String uploadFileUrl = multipartRequest.getSession()
							.getServletContext()
							.getRealPath("doc/getstd");
					File _apkFile = saveFileFromInputStream(
							imgFile.getInputStream(), uploadFileUrl, fileName);
//					if (_apkFile.exists()) {
//						FileInputStream fis = new FileInputStream(_apkFile);
//					} else
//						throw new FileNotFoundException("apk write failed:"
//								+ fileName);
					result.put("success", true);
				} catch (Exception e) {
					result.put("success", false);
					e.printStackTrace();
				}
			}
		}
		getstd.setGstFilepath(filepath);
		systemService.addEntity(getstd);
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		SystemUtils.jsonResponse(response, j);
	}

	// 保存文件
	private File saveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		File file = new File(path + "/" + filename);
		FileOutputStream fs = new FileOutputStream(file);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
		return file;
	}

	@RequestMapping(params="dgview")
	public String dgView(HttpServletRequest request) {
		return "std_get/std_getlist";
	}
	
	@RequestMapping(params="dgdoview")
	public String dgDoView(HttpServletRequest request) {
		return "std_get/std_getdolist";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params="dgdata")
	@ResponseBody
	public void dgData(GetStd getstd, DataGrid dg, HttpServletRequest request, HttpServletResponse response) {
		QueryDescriptor<GetStd> cq = new QueryDescriptor<GetStd>(GetStd.class, dg);
		CommonService commonService = SystemUtils.getCommonService(request);
		//查询条件组装器
		TypedQueryBuilder<GetStd> tqBuilder = QueryUtils.getTQBuilder(getstd, request.getParameterMap());
		if (StringUtils.isNotEmpty(dg.getSort())) {
			tqBuilder.addOrder(new TQOrder(tqBuilder.getRootAlias() + "." + dg.getSort(), dg.getOrder().equals("asc")));
		}
		cq.setTqBuilder(tqBuilder);
		commonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dg);
	}
	
	@RequestMapping(params="dgview_audit")
	public String dgViewAudit(HttpServletRequest request) {
		return "plan/plan_audit_list";
	}
	@RequestMapping(params="detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		AjaxResultJson j = new AjaxResultJson();
		String message;
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)) {
			return null;
		}

		GetStd getstd=systemService.findEntityById((long) Integer.parseInt(id), GetStd.class);
		request.setAttribute("getstd", getstd);
		return "std_get/std_getdetail";
	}
}
