package com.dia.app.support.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @Author: wuhua.wwh
 * @Date: 2018/5/7 18:55
 * @Description: Gzip工具类
 **/
public class GzipUtil {

  public static byte[] decompressZipToByte(byte[] body) {
    if (body == null || body.length == 0) {
      return null;
    }
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ByteArrayInputStream in = new ByteArrayInputStream(body);
      // 判断是否是GZIP格式
      int ss = (body[0] & 0xff) | ((body[1] & 0xff) << 8);
      if (ss == GZIPInputStream.GZIP_MAGIC) {
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[2048];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
          out.write(buffer, 0, n);
        }
        return out.toByteArray();
      } else {
        // 非gzip压缩，直接返回结果
        return body;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String decompressZipToString(byte[] body) throws IOException {
    if (body == null || body.length == 0) {
      return null;
    }
    try {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ByteArrayInputStream in = new ByteArrayInputStream(body);
      // 判断是否是GZIP格式
      int ss = (body[0] & 0xff) | ((body[1] & 0xff) << 8);
      if (ss == GZIPInputStream.GZIP_MAGIC) {
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[2048];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
          out.write(buffer, 0, n);
        }
        return out.toString();
      } else {
        // 非gzip压缩，直接返回结果
        return new String(body, "utf-8");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
