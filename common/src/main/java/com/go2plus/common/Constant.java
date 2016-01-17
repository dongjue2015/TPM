package com.go2plus.common;

import java.io.File;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source code file is protected by copyright law and international
 * treaties. Unauthorized distribution of source code files, programs, or portion of the package, may result in severe civil and criminal
 * penalties, and will be prosecuted to the maximum extent under the law.
 * 
 * 全局常量, 使用方法 X.UTF8
 * 
 * @author gaofeng
 * @since 2015-11-3
 */
public interface Constant {
  public static final String  TIMEA                 = "yyyy-MM-dd HH:mm:ss";
  public static final String  TIMEB                 = "yy-MM-dd HH:mm:ss";
  public static final String  TIMEC                 = "yyyy.MM.dd";
  public static final String  TIMED                 = "yy-MM-dd";
  public static final String  TIMEE                 = "HH:mm:ss";
  public static final String  TIMEF                 = "yyMMddHHmmss";

  public static final String  FILE_SEPARATOR        = File.separator;
  public static final String  JOB_NAME_PREFIX       = "Job_";
  public static final String  EMPTY                 = "";
  public static final String  IN                    = "in";
  public static final String  OUT                   = "out";
  public static final String  VERIFIED              = "verified";
  public static final String  UNDERLINE             = "_";
  public static final String  ENCRYPTED             = "+";
  public static final String  DECRYPTED             = "-";
  public static final String  GBK                   = "GBK";
  public static final String  UTF16LE               = "UTF-16LE";
  public static final String  UTF16BE               = "UTF-16BE";
  public static final String  UTF8                  = "UTF-8";
  public static final String  GB2312                = "GB2312";
  public static final String  BR                    = "\n";
  public static final String  BRFORWINDOWS          = "\r\n";
  public static final String  BRHTML                = "<br>";
  public static final String  URL                   = "url";
  public static final String  FROM                  = "from";
  public static final String  TO                    = "to";
  public static final String  TYPE                  = "type";
  public static final String  CATEGORY              = "category";
  public static final String  MESSAGE               = "message";
  public static final String  SESSION_ID            = "sessionid";
  public static final String  USER                  = "user";
  public static final String  USER_NAME             = "username";
  public static final String  USER_TYPE             = "userType";
  public static final String  PWD                   = "pwd";
  public static final String  REGISTER_PAGE         = "registerPage";
  public static final String  KEY                   = "key";
  public static final String  TIME                  = "time";
  public static final String  PICTURE               = "picture";
  public static final String  START                 = "start";
  public static final String  STOP                  = "stop";
  public static final String  VALID                 = "valid";
  public static final String  INVALID               = "invalid";
  public static final String  PAGE                  = "page";
  public static final String  LOGIN                 = "login";
  public static final String  SUCCESS               = "success";
  public static final String  FAIL                  = "fail";
  public static final String  TEST                  = "test";
  public static final String  TEXT                  = "text";
  public static final String  HTML                  = "html";
  public static final String  SITE_ROOT             = "siteRoot";
  public static final String  CLIENT_IP             = "clientIP";
  public static final String  CLIENT_BROWSER        = "clientBrowser";
  public static final String  CLIENT_OS             = "clientOS";
  public static final String  CLIENT_AGENT          = "clientAgent";
  public static final String  REQUEST_URL           = "requestURL";
  public static final String  REFERER               = "referer";
  public static final String  ROOT                  = "root";
  public static final String  WEB_ROOT              = "/";
  public static final String  PAGE_NUMBER           = "_pn";
  public static final String  PAGE_SIZE             = "_ps";
  public static final String  PAGE_NAVIGATION_SIZE  = "_pns";
  public static final String  JPG                   = "jpg";
  public static final String  PAGINATION_DATA       = "paginationData";
  public static final boolean TRUE                  = true;
  public static final boolean FALSE                 = false;
  public static final String  DOT                   = ".";
  public static final String  SLASH                 = "/";
  public static final String  UNDER_LINE            = "_";
  public static final String  MULTIPLICATION        = "x";

  // FOR GO2
  public static final String  USER_PASS_PREFIX      = "go2cn|";
  public static final String  BASIC_SECRET          = "f28653b71662c5b2979bf5b29799";
  public static final String  LINK_ENCRYPT_PREFIX   = "cc59bfad1bf28653b716624e5bbb2979";
  public static final String  WAIT_ENCRYPT_SECRET   = "8653b716624ebb95cc5b2979bfad1bf2";
  public static final String  UPLOAD_ENCRYPT_SECRET = "ccad1bf2bf0aebb718f3b65b297995";
  public static final String  TIMESTAMP_OFFSET      = "2182048201";
  public static final String  SEEDS                 = "oqgaceiskm";
  
  public static final String  SERVER_NAME_DOMAIN    = "g.go2.cn";
  public static final String  PUBLISH2TAOBAO_URL    = "http://eapi.ximgs.net";
  
  public static final String  ZIP_FILE              = "zip";
  
//  图片地址
  public static final String STATIC_BIG_IMAGE_URL_PREFIX            = "http://static.ximgs.net";//静态内容(图片.大图)前缀
  public static final String STATIC_IMAGE_URL_PREFIX                = "http://thumb.ximgs.net"; //图片服务器域名(小图)
  public static final String STATIC_NEW_IMAGE_URL_PREFIX            = "http://thumb.wl.ximgs.net"; //图片服务器域名(小图)
  public static final String STATIC_TAOBAO_IMAGE_URL_PREFIX         = "http://tb.ximgs.net";    //发布到淘宝的图片链接
  
}
