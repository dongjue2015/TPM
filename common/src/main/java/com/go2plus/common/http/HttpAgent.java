package com.go2plus.common.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source code file is protected by copyright law and international
 * treaties. Unauthorized distribution of source code files, programs, or portion of the package, may result in severe civil and criminal
 * penalties, and will be prosecuted to the maximum extent under the law.
 * 
 * HTTP 协议简单实现, 用作发送 GET/POST 请求.
 * 
 * @author gaofeng
 * @since 2015-11-13
 */
public class HttpAgent {
  /**
   * 发送一个POST请求
   * 
   * @param url
   * @param content
   * @return
   */
  public static String post(String url, String content) {
    StringBuilder sb = new StringBuilder();
    try {
      HttpURLConnection httpURLConnection = getConnectionForPost(url);
      BufferedWriter br = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()));
      br.write(content);
      br.flush();
      br.close();
      sb = new StringBuilder();
      //httpURLConnection.connect();
      if (httpURLConnection.getResponseCode() == 200) {
        // 建立实际的连接
        // 获取所有响应头字段
        // Map<String, List<String>> map = httpURLConnection.getHeaderFields();
        // 遍历所有的响应头字段
        // for (String key : map.keySet()) {
        // System.out.println(key + "--->" + map.get(key));
        // }
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
          sb.append(line).append("\n");
        }
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

  /**
   * 发送一个GET 请求
   * 
   * @param url
   * @return 返回请求的响应内容
   */
  public static String get(String url) {
    StringBuilder sb = new StringBuilder();
    try {
      HttpURLConnection httpURLConnection = getConnectionForGet(url);
      if (httpURLConnection.getResponseCode() == 200) {
        // 建立实际的连接
        httpURLConnection.connect();
        // 获取所有响应头字段
//        Map<String, List<String>> map = httpURLConnection.getHeaderFields();
        // 遍历所有的响应头字段
//        for (String key : map.keySet()) {
//          System.out.println(key + "--->" + map.get(key));
//        }
        // 定义 BufferedReader输入流来读取URL的响应
        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
          sb.append(line).append("\n");
        }
      }else{
        httpURLConnection.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        String line;
        while ((line = in.readLine()) != null) {
          sb.append(line).append("\n");
        }
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

  /**
   * 创建POST 的HTTP connection
   * 
   * @param url
   * @return
   */
  private static HttpURLConnection getConnectionForPost(String url) {
    HttpURLConnection connection = getConnectionForGet(url);
    try {
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
    } catch (ProtocolException e) {
      e.printStackTrace();
      return null;
    }

    return connection;
  }

  /**
   * 创建一个GET 的HTTP connection
   * 
   * @param url
   * @return
   */
  private static HttpURLConnection getConnectionForGet(String url) {
    HttpURLConnection connection = null;
    try {
      URL u = new URL(url);
      connection = (HttpURLConnection) (u.openConnection());
      connection.setRequestProperty("Accept-Charset", "utf-8");
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return connection;
  }
  public static void main(String[] args) {
    String re=HttpAgent.get("http://7xkr0e.com2.z0.glb.qiniucdn.com/1/1013531/20150925/2015092509539271898431.jpg?imageView2/2/w/750/q/90|saveas/Z28ybW0tdGh1bWJuYWlsOjEvMTAxMzUzMS8yMDE1MDkyNS8yMDE1MDkyNTA5NTM5MjcxODk4NDMxXzc1MC5qcGc=/sign/UIgBw7sNUlZ1ANb0GpLeydDrY8T59cpRGTDut0c2:T-HmnA4I3Nc4FCuaAZgs77LZlWw=&v=15");
    System.out.println(re);
  }
}
