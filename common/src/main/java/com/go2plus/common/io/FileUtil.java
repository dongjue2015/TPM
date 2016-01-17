package com.go2plus.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.go2plus.common.X;

public class FileUtil {
  private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

  /**
   * 检查磁盘可用空间大小 byte
   * 
   * @param file
   * @return
   */
  public static long diskFreeSpace(String file) {
    File f = new File(file);
    if (!f.exists()) {
      return -1;
    }
    return f.getFreeSpace();
  }

  /**
   * 检查磁盘总大小 byte
   * 
   * @param file
   * @return
   */
  public static long diskTotalSpace(String file) {
    File f = new File(file);
    if (!f.exists()) {
      return -1;
    }
    return f.getTotalSpace();
  }

  /**
   * 单位换算
   * 
   * @param size
   * @return
   */
  public static String fileSizeTransfer(long size) {
    if (size < 1024) {
      return String.valueOf(size) + "B";
    } else {
      size = size / 1024;
    }

    if (size < 1024) {
      return String.valueOf(size) + "KB";
    } else {
      size = size / 1024;
    }
    if (size < 1024) {

      size = size * 100;
      return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
    } else {

      size = size * 100 / 1024;
      return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
    }
  }

  /**
   * 获取文件扩展名 自动转小写
   * 
   * @param filename
   * @return
   */
  public static String getExtension(String filename) {
    if ((filename != null) && (filename.length() > 0)) {
      int dot = filename.lastIndexOf('.');
      if (dot > -1) {
        return filename.substring(dot + 1).toLowerCase();
      }
    }
    return null;
  }

  /**
   * 获取不带扩展名的文件名
   * 
   * @param filename
   * @return
   */
  public static String getFileNameWithOutExtension(String filename) {
    if ((filename != null) && (filename.length() > 0)) {
      int dot = filename.lastIndexOf('.');
      if (dot > -1) {
        return filename.substring(0, dot);
      }
    }
    return filename;
  }

  /**
   * 从文件读取String
   * 
   * @param file
   * @return
   */
  public static String readFileToString(File file,String chartset) {
    try {
      FileInputStream fis = new FileInputStream(file);
      String content = X.stream2String(new FileInputStream(file), chartset);
      X.close(fis);
      return content;
    } catch (Exception e) {
      log.error(null, e);
      return null;
    }
  }

  /**
   * 将String 写入 文件
   * 
   * @param file
   * @param content
   * @return
   * @throws IOException
   */
  public static boolean writeStringToFile(File file, String content,String chartset) {
    try {
      FileOutputStream fos = new FileOutputStream(file);
      X.string2Stream(content, fos, chartset);
      X.close(fos);
      return X.TRUE;
    } catch (Exception e) {
      log.error(null, e);
      return X.FALSE;
    }
  }
}
