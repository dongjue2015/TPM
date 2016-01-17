package com.gf.util.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.go2plus.common.json.Json;
import com.google.gson.reflect.TypeToken;

public class JsonTest {

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  // @Test
  public void test() {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("123", new Date());
    // map.put("234",System.in);
    map.put("345", new Integer(9987234));
    System.out.println(Json.toJson(map));
  }

  // @Test
  public void test2() {
    HashMap<String, Object> l = (HashMap<String, Object>) Json.fromJson("{\"123\":\"Nov 6, 2014 5:29:53 PM\",\"345\":9987234}",
        HashMap.class);
    System.out.println(l);
  }

  /**
   * /Users/gaofeng/Downloads
   * 
   * @throws IOException
   */
  @Test
  public void test3() throws IOException{
    BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("/Users/gaofeng/Downloads/taobao.json")));
    StringBuilder sb=new StringBuilder();
    String line;
    while((line=br.readLine())!=null){
      sb.append(line).append("\n");
    }
    List<HashMap> h= (List<HashMap>)Json.fromJson(sb.toString(),new TypeToken<List<HashMap>>(){}.getType());
    double d=2.123456789E10;
    System.out.println(new Double(d).longValue());
    System.out.println(Long.parseLong("2.123"));
    System.out.println(h);
  }
}
