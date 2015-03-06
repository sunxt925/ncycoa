package edu.cqu.ncycoa.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.entity.system.UserInfo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		
		System.out.print(request.getRequestURL());
		System.out.print(request.getRequestURI());
		
		String requestPath = request.getRequestURI();
		if( StringUtils.isNotEmpty(request.getQueryString()) ) {
			requestPath += "?" + request.getQueryString();
		}
		
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		
		if (requestPath.indexOf(".") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("."));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		
		if(getExcludeUrls().contains(requestPath)) {
			return true;
		} else {
			UserInfo user = (UserInfo)request.getSession().getAttribute("UserInfo");
			if(user != null){
				if(!hasMenuAuth(requestPath, user)) {
					response.sendRedirect("login.htm?noauth");
					return false;
				}
				return true;
			} else {
				request.getRequestDispatcher("/timeout.jsp").forward(request, response);
				return false;
			}
		}
	}
	
	private boolean hasMenuAuth(String requestPath, UserInfo user){
		// TODO 权限控制
		return true;
	}
	
	private List<String> excludeUrls;
	
	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
	
}
