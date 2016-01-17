package com.go2plus.common.json;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source code file is protected by copyright law and international
 * treaties. Unauthorized distribution of source code files, programs, or portion of the package, may result in severe civil and criminal
 * penalties, and will be prosecuted to the maximum extent under the law.
 * 
 * Gson实现的 json 转化器, 可以 Object <====> json 相互转化
 * 
 * @author gaofeng
 * @since 2015-10-30
 */
public final class Json {
  private final static Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

  public static String toJson(Object o) {
    return gson.toJson(o);
  }

  public static Object fromJson(String json, Class<? extends Object> c) {
    return gson.fromJson(json, c);
  }

  public static Object fromJson(String json, Type t) {
    return gson.fromJson(json, t);
  }

  // hashMap转json
  public static String hashMap2Json(HashMap<String, String> hashMap) {
    Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet().iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("{");
    while (iterator.hasNext()) {
      Map.Entry<String, String> entry = iterator.next();
      String key = entry.getKey();
      String value = entry.getValue();
      sb.append("\"" + key + "\"");
      sb.append(":");
      sb.append("\"" + value + "\"");
      if (iterator.hasNext())
        sb.append(",");
      else
        sb.append("");

    }
    sb.append("}");
    return sb.toString();
  }

  // json转hashMap
  public static HashMap<String, String> json2HashMap(String json) {
    HashMap<String, String> hashMap = new HashMap<>();
    // json 格式校验
    if (checkJsonFormat(json)) {
      // 去掉json的大括号
      String[] jsonArray = json.replace("{", "").replace("}", "").split(",");
      for (int i = 0; i < jsonArray.length; i++) {
        String[] kv = jsonArray[i].split(":");
        String k = kv[0].replace("\"", "");
        String v = kv[1].replace("\"", "");
        hashMap.put(k, v);
      }
      return hashMap;
    } else {
      return null;
    }
  }

  private static boolean checkJsonFormat(String json) {
    boolean isJson = false;
    // 校验大括号
    if (json.startsWith("{") && json.endsWith("}")) {

      String[] jsonArray = json.replace("{", "").replace("}", "").split(",");
      if (jsonArray.length != 0) {
        String[] jsonFirst = jsonArray[0].split(":");
        if (jsonFirst.length != 0) {
          String k = jsonFirst[0].replace("\"", "");
          String v = jsonFirst[1].replace("\"", "");
          if (k != "" && k != null && v != "" && v != null) {
            isJson = true;
          }
        }
      }
    }
    return isJson;
  }
}
