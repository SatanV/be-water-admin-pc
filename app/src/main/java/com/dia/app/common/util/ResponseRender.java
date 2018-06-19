package com.dia.app.common.util;

import com.dia.app.support.net.HttpConfig;
import com.dia.common.util.JsonUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ResponseRender {

  public static void renderJson(HttpServletResponse response, Object obj) throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType(HttpConfig.CONTENT_TYPE_JSON);
    PrintWriter writer = response.getWriter();
    writer.write(JsonUtil.toJson(obj));
    writer.close();
  }
}
