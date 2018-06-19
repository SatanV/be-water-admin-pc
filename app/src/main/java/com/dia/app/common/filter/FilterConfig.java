package com.dia.app.common.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Dia
 * @Description: Filter
 * @version: v1.0.0
 * @date: 2018年6月15日10:12:52
 */
@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean Filters() {
    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
    registrationBean.setFilter(new CustomSignFilter());
    registrationBean.addUrlPatterns("/*");

    return registrationBean;

  }
}
