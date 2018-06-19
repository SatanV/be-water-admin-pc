package com.dia.app.common.exception;


import com.dia.common.constants.ResponseCode;
import com.dia.common.dto.base.BaseRespDto;
import com.dia.common.util.BaseRespDtoWrapper;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Dia
 * @Description: 全局异常处理
 * @version: v1.0.0
 * @date: 2018年6月14日 下午20:05:11
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @Autowired
  @Qualifier("dataSource")
  private DataSource dataSource;

  /**
   * 参数校验的错误(有参数，但是不满足校验条件)
   */
  @ExceptionHandler(value = BindException.class)
  public BaseRespDto<Object> validExceptionHandler(BindException e) {
    StringBuilder errStr = new StringBuilder();
    List<FieldError> errors = e.getFieldErrors();
    for (FieldError fieldError : errors) {
      errStr.append(fieldError.getDefaultMessage() + ",");
    }
    errStr.deleteCharAt(errStr.length() - 1);
    return new BaseRespDtoWrapper<>().error(ResponseCode.PARAMS_ERROR, errStr.toString());
  }

  /**
   * 参数校验的错误（参数缺失）
   */
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public BaseRespDto<Object> validExceptionHandler(MethodArgumentNotValidException e) {
    StringBuilder errStr = new StringBuilder();
    List<FieldError> errors = e.getBindingResult().getFieldErrors();
    for (FieldError fieldError : errors) {
      errStr.append(fieldError.getDefaultMessage() + ",");
    }
    errStr.deleteCharAt(errStr.length() - 1);
    return new BaseRespDtoWrapper<>().error(ResponseCode.PARAMS_ERROR, errStr.toString());
  }

  @ExceptionHandler(value = Exception.class)
  public BaseRespDto<Object> handler(Exception e) {
    logger.error(e.getMessage(), e);
    logDataSourceInfo("mysqlDs", dataSource);
    return new BaseRespDtoWrapper<>().error(ResponseCode.INTERNAL_ERROR);
  }

  private void logDataSourceInfo(String name, DataSource ds) {
    logger.error("name = {}, dataSource = {}", name, ds);
    int active = ds.getActive();
    int waitCount = ds.getWaitCount();
    int numActive = ds.getNumActive();
    int idle = ds.getIdle();
    long createdCount = ds.getCreatedCount();
    int maxActive = ds.getMaxActive();
    long borrowedCount = ds.getBorrowedCount();
    long releasedCount = ds.getReleasedCount();
    long releasedIdleCount = ds.getReleasedIdleCount();
    int numIdle = ds.getNumIdle();
    int poolSize = ds.getPoolSize();
    long returnedCount = ds.getReturnedCount();
    long reconnectedCount = ds.getReconnectedCount();
    long removeAbandonedCount = ds.getRemoveAbandonedCount();
    logger.error("name={}, active={}, waitCount={}, numActive={}, idle={}, createdCount={}, maxActive={}, borrowedCount={}" +
            ", releasedCount={}, releasedIdleCount={}, reconnectedCount={}, returnedCount={}, removeAbandonedCount={}, numIdle={}, poolSize={}",
        name, active, waitCount, numActive, idle, createdCount, maxActive, borrowedCount
        , releasedCount, releasedIdleCount, reconnectedCount, returnedCount, removeAbandonedCount, numIdle, poolSize);
  }

}
