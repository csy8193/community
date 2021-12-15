package com.together.semiprj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

// /*: 최상위 경로 하위 모두: semi프로젝트 전체에
@WebFilter(filterName="encodingFilter", urlPatterns="/*")
public class EncodingFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		//모든 요청을 UTF-8로 변경
		
		response.setCharacterEncoding("utf-8");
		//모든 응답을 UTF-8로 변경
		chain.doFilter(request, response);
	}

}
