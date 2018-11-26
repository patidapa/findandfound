package com.findfound.demo.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import com.findfound.demo.constant.Constants;
import com.findfound.demo.constant.StatusCode;
import com.findfound.demo.response.ErrorObject;
import com.findfound.demo.response.Response;

import ch.qos.logback.classic.Logger;

import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;

/**
 * Servlet Filter implementation class MyFilter
 */
@WebFilter("/MyFilter")
@Validated
public class MyFilter implements Filter,Constants {

    public MyFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Logger.info("in do filter");
		System.out.println("////////////////////from filter :////");
		final HttpServletRequest request1 = (HttpServletRequest) request;
		final HttpServletResponse response1 = (HttpServletResponse) response;
		
		String url =  request1.getRequestURL().toString();
		if (url.contains("swagger") || url.contains("api")) {
			chain.doFilter(request, response);
		} else {
			String xApiKey = ((HttpServletRequest) request1).getHeader(ACCESS_KEY_HEADER);
			String jsonResponse = null;
			jsonResponse = validateRequest(xApiKey);
			if (jsonResponse != null) {
				System.out.println("////////////////////from filter json object:////");
				response.getWriter().println(jsonResponse);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	
	public String validateRequest(String xApiKey) {
		Response response = new Response();
		ArrayList<ErrorObject> errorObjects = new ArrayList<ErrorObject>();
		if (errorObjects != null && !errorObjects.isEmpty()) {
			response.setStatus(StatusCode.ERROR.name());
			response.setErrors((ErrorObject[]) errorObjects.toArray(new ErrorObject[errorObjects.size()]));
		} else {
			response.setStatus(StatusCode.SUCCESS.name());
		}
		if (response != null && response.getStatus().equalsIgnoreCase(StatusCode.ERROR.name()))
			System.out.println("////////////////////from filter json return :////");
			return CommonUtils.getJson(response);
		

	}

	public void cors(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers",
				"Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,X-API-KEY");
		response.addHeader("Access-Control-Expose-Headers",
				"Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addIntHeader("Access-Control-Max-Age", 3600);

	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
