package edu.cqu.gem.ncycoa.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.entity.system.UserInfo;

import edu.cqu.gem.ncycoa.service.CommonService;
import edu.cqu.gem.ncycoa.service.SystemService;

public class SystemUtils {
	
	public final static String SESSION_USER = "UserInfo";
	public final static boolean BUTTON_AUTHORITY_CHECK = false;
	
	public static UserInfo getSessionUser() {
		return (UserInfo)getSession().getAttribute(SESSION_USER);
	}
	
	public static void setSessionUser(UserInfo user) {
		getSession().setAttribute(SESSION_USER, user);
	}
	
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return request.getRequest();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static ServletContext getServletContext() {
		return getSession().getServletContext();
	}

	public static String getContextPath() {
		String ctxPath = getRequest().getContextPath();
		if (StringUtils.isBlank(ctxPath)) {
			return "/";
		} else {
			if (ctxPath.charAt(0) != '/') {
				ctxPath = '/' + ctxPath;
			}
			if (ctxPath.charAt(ctxPath.length() - 1) != '/') {
				ctxPath = ctxPath + '/';
			}

			return ctxPath;
		}
	}
	
	public static CommonService getCommonService() {
		return getCommonService(getRequest());
	}
	
	public static SystemService getSystemService() {
		return getSystemService(getRequest());
	}
	
	public static CommonService getCommonService(HttpServletRequest request) {
		return getCommonService(request.getSession().getServletContext());
	}
	
	public static SystemService getSystemService(HttpServletRequest request) {
		return getSystemService(request.getSession().getServletContext());
	}
	
	public static CommonService getCommonService(ServletContext context) {
		return WebApplicationContextUtils.getWebApplicationContext(context).getBean("commonService", CommonService.class);
	}
	
	public static SystemService getSystemService(ServletContext context) {
		return WebApplicationContextUtils.getWebApplicationContext(context).getBean("systemService", SystemService.class);
	}
	
}
