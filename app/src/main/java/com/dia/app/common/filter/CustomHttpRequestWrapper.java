package com.dia.app.common.filter;

import org.jboss.logging.Logger;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: Dia
 * @Description:
 * @version: v1.0.0
 * @date: 2018-6-15 10:12:22
 */
public class CustomHttpRequestWrapper extends HttpServletRequestWrapper {
  private final Logger log = Logger.getLogger(CustomHttpRequestWrapper.class);
  private byte[] requestBody = null;//用于将流保存下来

  public CustomHttpRequestWrapper(HttpServletRequest request) {
    super(request);
    try {
      requestBody = StreamUtils.copyToByteArray(request.getInputStream());
    } catch (IOException e) {
      log.error("Wrap requestBody failed");
    }
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {
    if (requestBody == null) {
      requestBody = new byte[0];
    }
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(requestBody);
    return new ServletInputStream() {

      @Override
      public int read() throws IOException {
        return byteArrayInputStream.read();
      }

      @Override
      public void setReadListener(ReadListener listener) {
        // do nothing
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public boolean isFinished() {
        return false;
      }
    };
  }

  @Override//对外提供读取流的方法
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

}