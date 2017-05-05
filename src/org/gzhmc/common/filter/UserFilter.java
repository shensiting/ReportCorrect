package org.gzhmc.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author : gcliang
 * @Date : 2016年6月23日
 */
public class UserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 取得当前访问系统根本目录对应的绝对路径
		String currentUrl = request.getRequestURI();
		String targetUrl = currentUrl.substring(currentUrl.indexOf("/", 1), currentUrl.length());
//		if (currentUrl.indexOf("/teacher/show") != -1) {			
//			HttpSession session = request.getSession();
//			session.setAttribute("userid", " ");
//			session.setMaxInactiveInterval(3600);						
//		} 
			// System.out.println("showUrl:"+showUrl);
			// 解决初次加载登陆界面时引用js，css错误
			if (currentUrl.lastIndexOf(".") != -1) {
				String checkUrl = currentUrl.substring(currentUrl.lastIndexOf("."), currentUrl.length());
				if (!".woff".equals(checkUrl) && !".ttf".equals(checkUrl) && !".svg".equals(checkUrl) && !".eot".equals(checkUrl) && !".js".equals(checkUrl) && !".css".equals(checkUrl) && !".png".equals(checkUrl)
						&& !".jpg".equals(checkUrl) && !".jpeg".equals(checkUrl)) {
					// 比较当前访问的路径是不是登录页面路径
					if (!"/index.jsp".equals(targetUrl)&&!"/login.jsp".equals(targetUrl) && !"/manage/register".equals(targetUrl)
							&& !"/manage/check.action".equals(targetUrl)) {
						// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
						HttpSession session = request.getSession(false);// false
																		// 是获取不到session时不会重新new一个
						if (session == null || session.getAttribute("userid") == null) {
							// 用户没有登录，跳到登录页面
							/*
							 * response.sendRedirect(request.getContextPath()+
							 * "/WEB-INF/login.jsp");
							 */
							response.setContentType("text/html; charset=utf8");
							PrintWriter out = response.getWriter();
							out.flush();
							out.println(
									"<script>alert('登录超时，请重新登录。');window.location.href='../login.jsp';</script>");
							out.close();
							return;
						}
					}
				}

			}

			else {
				// 比较当前访问的路径是不是登录页面路径
				if (!"/login.jsp".equals(targetUrl) && !"/manage/register".equals(targetUrl)
						&& !"/manage/check.action".equals(targetUrl)) {
					// 判断当前页是否是重定向以后的登录页面页面，如果是就不做session的判断，防止出现死循环
					HttpSession session = request.getSession(false);// false
																	// 是获取不到session时不会重新new一个
					if (session == null || session.getAttribute("userid") == null) {
						// 用户没有登录，跳到登录页面
						/*
						 * response.sendRedirect(request.getContextPath()+
						 * "/WEB-INF/login.jsp");
						 */
						response.setContentType("text/html; charset=utf8");
						PrintWriter out = response.getWriter();
						out.flush();
						out.println(
								"<script>alert('登录超时，请重新登录。');window.location.href='../login.jsp';</script>");
						out.close();
						return;
					}
				}
			}
		
		// 加入filter链继续向下执行
		filterChain.doFilter(request, response);
		/**
		 * 调用FilterChain对象的doFilter方法。Filter接口的doFilter方法取一个FilterChain对象作* 为它
		 * 的一个参数。在调用此对象的doFilter方法时，激活下一个相关的过滤器。如果没有另*
		 * 一个过滤器与servlet或JSP页面关联，则servlet或JSP页面被激活。
		 */
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
