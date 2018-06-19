package com.dia.app.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;


public class RequestWrapper extends HttpServletRequestWrapper {
  private static Logger logger = LoggerFactory.getLogger(RequestWrapper.class);

  private final byte[] body;
  private long id = 0;
  private long requestBeginTime = 0;

  public RequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    body = getBodyString(request).getBytes(Charset.forName("UTF-8"));
  }

  public RequestWrapper(long reqId, long requestTime, HttpServletRequest request) throws IOException {
    super(request);
    id = reqId;
    requestBeginTime = requestTime;
    body = getBodyString(request).getBytes(Charset.forName("UTF-8"));
  }

  @Override
  public BufferedReader getReader() throws IOException {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() throws IOException {

    //这里从body里面直接读了，没有去读inputStream了，很巧妙的方式
    final ByteArrayInputStream bais = new ByteArrayInputStream(body);

    return new ServletInputStream() {

      @Override
      public int read() throws IOException {
        return bais.read();
      }

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }
    };
  }


  public String getBodyString(ServletRequest request) {
    StringBuilder sb = new StringBuilder();
    InputStream inputStream = null;
    BufferedReader reader = null;
    try {
      inputStream = request.getInputStream();
      reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
      String line = "";
      while ((line = reader.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      logger.warn("处理异常", e);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          logger.warn("处理异常", e);
        }
      }
      if (reader != null) {
        try {
          reader.close();
        } catch (IOException e) {
          logger.warn("处理异常", e);
        }
      }
    }
    return sb.toString();
  }

  public long getId() {
    return id;
  }

  public byte[] getBody() {
    return body;
  }

  public long getRequestBeginTime() {
    return requestBeginTime;
  }
}