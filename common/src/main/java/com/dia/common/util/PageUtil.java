package com.dia.common.util;

/**
 * @author: Dia
 * @Description: 分页限制类
 * @version: v1.0.0
 * @date: 2018年6月14日 下午 20:06:28
 */
public class PageUtil {
  private static final int MAX_PAGE_SIZE = 100;
  private static final int MIN_PAGE = 1;

  public static int getPageLimit(int page) {
    return Math.max(page, MIN_PAGE);
  }

  public static int pageSizeLimit(int pageSize) {
    return Math.min(pageSize, MAX_PAGE_SIZE);
  }
}
