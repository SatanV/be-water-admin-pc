package com.dia.app.support.stat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: wuhua.wwh
 * @Date: 2018/4/27 21:01
 * @Description:
 **/
public class StatLogConfig {

  public static final String KEY_MODULE = "module";
  public static final String KEY_ACTION = "action";

  public static final String KEY_SEPARATOR = "`";

  public final static List<String> KEYWORD_LIST = new ArrayList<String>(
      Arrays.asList(KEY_MODULE,KEY_ACTION)
  );
}
