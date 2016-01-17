package com.go2plus.common.icon;

import java.io.File;

import com.go2plus.common.X;
import com.go2plus.common.io.FileUtil;

public class IconUtil {

  /**
   * @param args
   */
  public static void main(String[] args) {
    File f=new File("/Users/gaofeng/Desktop/a.webloc");
    String x=FileUtil.readFileToString(f, X.UTF8);
    f.length();
    System.out.println(x);
  }

}
