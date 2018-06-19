package com.dia.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Dia
 * @Description: 日期工具类
 * @version: v1.0.0
 * @date: 2018年6月14日 下午 21:04:48
 */
public class DateUtil {
  public static final String YYYY_MM_DD = "yyyy-MM-dd";
  public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

  public static Date getDate(String strDate, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    try {
      return sdf.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static String parseDate(Date date, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }
}
