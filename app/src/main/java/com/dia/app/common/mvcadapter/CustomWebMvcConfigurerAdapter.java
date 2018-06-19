package com.dia.app.common.mvcadapter;


import com.dia.app.common.interceptor.CORSInterceptor;
import com.dia.app.common.interceptor.TokenInterceptor;
import com.dia.app.common.util.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CustomWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {


  private final TokenManager tokenManager;



  @Autowired
  public CustomWebMvcConfigurerAdapter(TokenManager tokenManager1) {

    this.tokenManager = tokenManager1;

  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    super.addInterceptors(registry);
    registry.addInterceptor(new CORSInterceptor());
    registry.addInterceptor(new TokenInterceptor( tokenManager))
        .excludePathPatterns("/system.login")
        .excludePathPatterns("/enterprise.login")
        .excludePathPatterns("/admin.login")
        .excludePathPatterns("/swagger-resources/**")
        .excludePathPatterns("/error")
        .excludePathPatterns("/v2/api-docs")
        .excludePathPatterns("/activationCodeInfo.activate")
        .excludePathPatterns("/resource.add")
        .excludePathPatterns("/resource.upload")
        .excludePathPatterns("/basicdata.getCompanyByMachineCode")
        .excludePathPatterns("/system.login.remote")
    ;
  }

}
