package com.dia.app;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.lang.management.ManagementFactory;

@SpringBootApplication
@MapperScan(basePackages = {"com.dia.app.module.**.dao"})
public class AppApplication extends SpringBootServletInitializer implements CommandLineRunner {
  private static final Logger logger = LoggerFactory.getLogger(AppApplication.class);
  private static ApplicationContext applicationContext = null;


  @Autowired
  @Qualifier("dataSource")
  private DataSource dataSource;


  @Override
  public void run(String... strings) throws Exception {
    logger.info("datasource: {}", dataSource);
  }

  public static void main(String[] args) {
    String mode = args != null && args.length > 0 ? args[0] : null;

    if (logger.isDebugEnabled()) {
      logger.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application mode:" + mode + " context:" + applicationContext);
    }
    if (applicationContext != null && mode != null && "stop".equals(mode)) {
      System.exit(SpringApplication.exit(applicationContext, new ExitCodeGenerator() {
        @Override
        public int getExitCode() {
          return 0;
        }
      }));
    } else {
      SpringApplication app = new SpringApplication(AppApplication.class);
      applicationContext = app.run(args);
      if (logger.isDebugEnabled()) {
        logger.debug("PID:" + ManagementFactory.getRuntimeMXBean().getName() + " Application started context:" + applicationContext);
      }
    }
  }
}

