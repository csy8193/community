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

import com.together.semiprj.wrapper.EncryptWrapper;


@WebFilter(filterName="encryptFilter", urlPatterns= {"/member/login" , "/member/signup", "/member/pwupdate"})
public class EncryptFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("암호화 필터 생성");
	}
	public void destroy() {
		System.out.println("암호화 필터 제거 후 새로 생성");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest)request;
		if(req.getMethod().equals("POST")) {
			EncryptWrapper encWrapper = new EncryptWrapper(req);
			chain.doFilter(encWrapper, response);
		}
		else {
			chain.doFilter(request, response);
		}
	}
	public EncryptFilter() {
		
	}

}
