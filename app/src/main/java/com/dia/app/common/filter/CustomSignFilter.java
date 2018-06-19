package com.dia.app.common.filter;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: Dia
 * @Description:
 * @version: v1.0.0
 * @date: 2018年6月15日10:12:35
 */
public class CustomSignFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    ServletRequest requestWrapper = null;
    if (request instanceof HttpServletRequest) {
      requestWrapper = new CustomHttpRequestWrapper((HttpServletRequest) request);
    }
    if (requestWrapper == null) {
      chain.doFilter(request, response);
    } else {
      chain.doFilter(requestWrapper, response);
    }

  }

  @Override
  public void destroy() {
    // do nothing
  }

}