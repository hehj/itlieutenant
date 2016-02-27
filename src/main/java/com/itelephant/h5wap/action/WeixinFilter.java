package com.itelephant.h5wap.action;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class WeixinFilter implements Filter {

	public void destroy() {
	}

	
	public void init(FilterConfig config) throws ServletException {
	}

	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		/*HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = ((HttpServletRequest) req).getSession();*/
	
		chain.doFilter(req, res);
		
	}
}
