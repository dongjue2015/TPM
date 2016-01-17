package com.go2plus.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.go2plus.common.mvc.Box;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source code file is protected by copyright law and international
 * treaties. Unauthorized distribution of source code files, programs, or portion of the package, may result in severe civil and criminal
 * penalties, and will be prosecuted to the maximum extent under the law.
 * 
 * 全局工具类, 使用方法 X.UTF8 X.string2int("123")
 * 
 * @author gaofeng
 * @since 2015-11-3
 */
public final class X implements Constant {
  private static final Logger                            log           = LoggerFactory.getLogger(X.class);
  private static Properties                              properties;
  private static Object                                  o             = new Object();
  private static final HashMap<String, SimpleDateFormat> TIME_FORMATER = new HashMap<String, SimpleDateFormat>();
  public static final HashMap<Character, String>         ESCAPE_TABLE  = new HashMap<Character, String>();
  public static final Random                             RANDOM        = new Random();
  static {
    init();
    loadProperties();
  }

  private static void init() {
    TIME_FORMATER.put(TIMEA, new SimpleDateFormat(TIMEA));
    TIME_FORMATER.put(TIMEB, new SimpleDateFormat(TIMEB));
    TIME_FORMATER.put(TIMEC, new SimpleDateFormat(TIMEC));
    TIME_FORMATER.put(TIMED, new SimpleDateFormat(TIMED));
    TIME_FORMATER.put(TIMEE, new SimpleDateFormat(TIMEE));
    TIME_FORMATER.put(TIMEF,new SimpleDateFormat(TIMEF));
    ESCAPE_TABLE.put('<', "&lt;");
    ESCAPE_TABLE.put('>', "&gt;");
    ESCAPE_TABLE.put('=', "#e");
    ESCAPE_TABLE.put('*', "#s");
    ESCAPE_TABLE.put('\'', "#1");
    ESCAPE_TABLE.put('\"', "&quot;");
    ESCAPE_TABLE.put('&', "&amp;");
    ESCAPE_TABLE.put('|', "&brvbar;");
  }

  /**
   * load webContent/WEB-INF/classes/x.properties as default; 
   * then periodically load X.properties from /etc/jvm/x.propertis
   */
  private static void loadProperties() {
    Properties p = new Properties();
    File defaultProperties=null;
    try {
      java.net.URL u=X.class.getClassLoader().getResource("");
      java.net.URL u2=X.class.getClassLoader().getResource("/");
      defaultProperties = new File(u==null?u2.getPath():u.getPath(), "x.properties");
      p.load(new FileInputStream(defaultProperties));
      properties = new Properties(p);
    } catch (FileNotFoundException e) {
      log.error("X.loadProperties() error " + defaultProperties.getAbsolutePath() + " not found.");
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    Thread loaderThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          try {
            properties.load(new FileInputStream("/etc/jvm/x.properties"));
          } catch (Exception e) {
            log.trace(e.getMessage());
          }
          // 每10秒重新加载一次/etc/jvm/x.properties 内的配置
          X.sleep(10);
        }
      }
    }, "X.propertiesLoader");
    loaderThread.setDaemon(true);
    loaderThread.start();
  }

  /**
   * 生成uuid (大写)
   * 
   * @return
   */
  public static String uuid() {
    return UUID.randomUUID().toString().toUpperCase();
  }

  /**
   * 生成存uuid (大写 无minus)
   * 
   * @return
   */
  public static String uuidPure() {
    String uuid = UUID.randomUUID().toString();
    StringBuilder sb = new StringBuilder();
    sb.append(uuid.substring(0, 8)).append(uuid.substring(9, 13)).append(uuid.substring(14, 18)).append(uuid.substring(19, 23))
        .append(uuid.substring(24));
    return sb.toString().toUpperCase();
  }

  /**
   * 当前线程休眠x秒钟
   * 
   * @param x
   */
  public static void sleep(double x) {
    try {
      Thread.sleep((long) (1000 * x));
    } catch (InterruptedException e) {
      log.error(e.getMessage());
    }
  }

  /**
   * int转String
   * 
   * @param i
   * @return
   */
  public static String int2string(Integer i) {
    return String.valueOf(i);
  }
  
  /**
   * int转String 如果转出的String长度小于size 前面补0
   * @param i
   * @param size
   * @return
   */
  public static String int2string(Integer i,Integer size){
    String s=String.valueOf(i);
    StringBuilder result=new StringBuilder();
    for(;size>s.length();size--){
      result.append("0");
    }
    result.append(s);
    return result.toString();
  }

  /**
   * String转int 如遇NumberFormatException 返回0, 传入null 同样会导致 NumberFormatException
   * 
   * @param i
   * @return
   */
  public static int string2int(String i) {
    try {
      return Integer.parseInt(i);
    } catch (NumberFormatException e) {
      return 0;
    }
  }

  /**
   * String 转 date
   * 
   * @param time
   * @param timeType
   *          可以为TIMEA,TIMEB,TIMEC,TIMED,TIMEE
   * @return
   */
  public static Date string2date(String time, String timeType) {
    SimpleDateFormat sdf = TIME_FORMATER.get(timeType);
    synchronized (o) {
      try {
        return sdf.parse(time);
      } catch (ParseException e) {
        log.error(e.getMessage());
        return null;
      }
    }
  }

  /**
   * Date 转 String
   * 
   * @param time
   * @param timeType
   *          可以为TIMEA,TIMEB,TIMEC,TIMED,TIMEE
   * @return
   */
  public static String date2string(Date time, String timeType) {
    SimpleDateFormat sdf = TIME_FORMATER.get(timeType);
    synchronized (o) {
      return sdf.format(time);
    }
  }

  /**
   * 等同于 new Date()
   * 
   * @return
   */
  public static Date now() {
    return new Date();
  }

  public static String nowString() {
    return date2string(now(), TIMEA);
  }
  
  public static String nowSeriesString(){
    return date2string(now(), TIMEF);
  }

  /**
   * 如果目标文件夹不存在就创建目标文件夹
   * 
   * @param folder
   */
  public static void makeDir(String folder) {
    File f = new File(folder);
    if (!f.exists())
      f.mkdirs();
  }

  /**
   * 探测文件编码类型
   * 
   * @param File
   *          file
   * @return
   */
  public static final String detectCharset(File file) {
    return detectFileCharset(file.getPath());
  }

  /**
   * 探测文件编码类型
   * 
   * @param String
   *          filePath
   * @return
   */
  public static final String detectFileCharset(String filePath) {
    byte first3Bytes[];
    BufferedInputStream bis;
    first3Bytes = new byte[3];
    bis = null;
    int read = -4;
    try {
      bis = new BufferedInputStream(new FileInputStream(filePath));
      read = bis.read(first3Bytes, 0, 3);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    if (read == -1) {
      if (bis != null)
        try {
          bis.close();
        } catch (Exception e) {
          log.error(e.getMessage());
        }
      return "GBK";
    }
    if (first3Bytes[0] == -1 && first3Bytes[1] == -2) {
      if (bis != null)
        try {
          bis.close();
        } catch (Exception e) {
          log.error(e.getMessage());
        }
      return "UTF-16LE";
    }
    if (first3Bytes[0] == -2 && first3Bytes[1] == -1) {
      if (bis != null)
        try {
          bis.close();
        } catch (Exception e) {
          log.error(e.getMessage());
        }
      return "UTF-16BE";
    }
    if (first3Bytes[0] == -17 && first3Bytes[1] == -69 && first3Bytes[2] == -65) {
      if (bis != null)
        try {
          bis.close();
        } catch (Exception e) {
          log.error(e.getMessage());
        }
      return "UTF-8";
    }
    if (bis != null)
      try {
        bis.close();
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    if (bis != null)
      try {
        bis.close();
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    if (bis != null)
      try {
        bis.close();
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    return "GBK";
  }

  /**
   * 将输入流的内容读成String,以\n为换行符
   * 
   * @param is
   * @param charset
   * @return
   */
  public static final String stream2String(InputStream is, String charset) {
    StringBuilder sb = new StringBuilder();
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));
      String s;
      while ((s = br.readLine()) != null) {
        sb.append(s).append("\n");
      }
      return sb.toString();
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return null;
  }
  /**
   * 将String 写入输出流中 按 \n 换行 
   * 
   * @param is
   * @param charset
   * @return
   */
  public static final String string2Stream(String content,OutputStream os,String charset) {
    try {
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, charset));
      bw.write(content);
      bw.flush();
    } catch (Exception e) {
      log.error(e.getMessage());
    }
    return null;
  }

  /**
   * 计算两时间点的差距
   * 
   * @param begin
   * @param end
   * @return
   */
  public static String timeGap(Date begin, Date end) {
    long totalSecond = (begin.getTime() - end.getTime()) / 1000L;
    if (totalSecond < 0L)
      totalSecond *= -1L;
    long day = totalSecond / 86400L;
    long hour = (totalSecond % 86400L) / 3600L;
    long min = (totalSecond % 3600L) / 60L;
    long second = totalSecond % 60L;
    StringBuilder sb = new StringBuilder();
    if (begin.before(end))
      sb.append("-");
    sb.append(day).append("days");
    sb.append(hour).append("hours");
    sb.append(min).append("minutes");
    sb.append(second).append("seconds");
    return sb.toString();
  }

  /**
   * 流转接,把从输入流的读到的数据原样写入输出流 (缓存为8192 8K)
   * 
   * @param is
   * @param os
   */
  public static void copyStream(InputStream is, OutputStream os) {
    byte cache[] = new byte[8192];
    int length = 0;
    try {
      while ((length = is.read(cache)) > 0) {
        os.write(cache, 0, length);
      }
      os.flush();
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  /**
   * 流转接,把从输入流的读到的数据原样写入输出流并关闭流 (缓存为8192 8K)
   * 
   * @param is
   * @param os
   */
  public static void copyStreamAndClose(InputStream is, OutputStream os) {
    byte cache[] = new byte[8192];
    int length = 0;
    try {
      while ((length = is.read(cache)) > 0) {
        os.write(cache, 0, length);
      }
      os.flush();
    } catch (IOException e) {
      log.error(e.getMessage());
    } finally {
      try {
        is.close();
        os.close();
      } catch (IOException e) {
        log.error(e.getMessage());
      }
    }
  }

  /**
   * 关闭各种 closeable
   * 
   * @param closeable
   */
  public static void close(Closeable closeable) {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  /**
   * 查询X.properties的配置
   * 
   * @param key
   * @return
   */
  public static String getConfig(String key) {
    return properties.getProperty(key);
  }

  /**
   * print all getter to logger on debug mode
   * 
   * @param o
   */
  public static void debugPrint(Object o) {
    Method[] ms = o.getClass().getMethods();
    for (Method m : ms) {
      if (m.getName().startsWith("get")) {
        try {
          log.debug("{} : {} ", m.getName(), m.invoke(o));
        } catch (Exception e) {
          log.error(e.getMessage());
        }
      }
    }
  }

  public static final ExecutorService createThreadPool(int size, final String threadName, final boolean daemon) {
    ExecutorService es = Executors.newFixedThreadPool(size, new ThreadFactory() {
      int i = 0;

      @Override
      public Thread newThread(Runnable r) {
        Thread t;
        if (null == threadName || "".equals(threadName.trim())) {
          t = new Thread(r, "ThreadPool-" + (i++));
        } else {
          t = new Thread(r, threadName + "-" + (i++));
        }
        if (daemon) {
          t.setDaemon(daemon);
        }
        return t;
      }
    });
    return es;
  }

  // 获取旧的年份
  public static final Integer getPreviousYear(Integer last) {
    Calendar prevYear = Calendar.getInstance();
    prevYear.add(Calendar.YEAR, last);

    return prevYear.get(Calendar.YEAR);
  }

  /**
   * url 编码
   * 
   * @param value
   *          编码内容
   * @param charset
   *          使用字符集, 应该总是使用UTF-8,兼容性最好
   * @return
   */
  public static String urlEncode(String value, String charset) {
    try {
      return URLEncoder.encode(value, charset);
    } catch (UnsupportedEncodingException e) {
      log.error(e.getMessage());
      return null;
    }
  }

  /**
   * url 解码
   * 
   * @param value
   *          解码内容
   * @param charset
   *          使用字符集, 应该总是使用UTF-8,兼容性最好
   * @return
   */
  public static String urlDecode(String value, String charset) {
    try {
      return URLDecoder.decode(value, charset);
    } catch (UnsupportedEncodingException e) {
      log.error(e.getMessage());
      return null;
    }
  }
  
	/**
	 * 商品ID进行编码
	 * 
	 * @param id
	 * @return
	 */
	public static String encodeId(String id) {
		String str = "";
		for (int i = 0; i < id.length(); i++) {
			int index = Integer.parseInt(String.valueOf(id.charAt(i)));
			str += X.SEEDS.charAt(index);
		}
		return str;
	}

	/**
	 * 商品ID进行解码
	 * 
	 * @param code
	 * @return
	 */
	public static String decodeId(String code) {
		String id = "";
		for (int i = 1; i <= code.length(); i++) {
			id += X.SEEDS.indexOf(code.charAt(i - 1));
		}

		return id;

	}
	
	/**
	 * 
	 * @param str       明文 /密文
	 * @param isEncrypt 加密/解密
	 * @param key       密匙
	 * @return          SHA1生成密匙簿，超过300个字符使用ZLIB压缩
	 */
	public static String dencrypt(String str, boolean isEncrypt, String key){
		if(null == str || str.isEmpty()){
			return null;
		}
		//TODO 后期做一键发布的时候再完善java版本的加密规则,当前请使用聚石塔版本规则
		//isEncrypt ? 
		return null;
	}
	
	/**
	 * 检测登录状态
	 * @param box
	 * @return
	 */
	public static boolean isLogined(Box box){
	  String userId = box.$cv(X.USER);
	  String userType = box.$cv(X.USER_TYPE);
	  if(userId != null && userId != "" && userType != null && userType != ""){
	    return X.TRUE;
	  }
	  return X.FALSE;
	}
	
	public static String getTimeout(){
	  return X.getConfig("com.go2plus.globle.core.timeout");
	}
}
