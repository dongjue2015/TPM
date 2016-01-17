package com.gf.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.junit.Test;

import com.go2plus.common.X;
import com.go2plus.common.http.HttpAgent;
import com.go2plus.common.img.ImageScalingUtil;
import com.go2plus.common.io.FileUtil;

public class XTest {
//  long j=5000000l;
  long j=500l;
  
  //@Test
public void testEncodeId(){
  System.out.println(X.encodeId("258"));
  //258 -> qcs
  assertEquals("qcs",X.encodeId("258"));
}
  
  //@Test
  public void testDecodeId(){
    System.out.println(X.decodeId("qcs"));
    //qcs -> 258
    assertEquals("258",X.decodeId("qcs"));
  }
  
  
  //@Test
  public void testaa(){
    int x=X.string2int("111");
   // x=X.string2int("aa");
    x=X.string2int(null);
    System.out.println(x);
  }
  
//  @Test
  public void testUuidPure(){
    System.out.println(X.uuid());
    System.out.println(X.uuidPure());
    assertEquals(X.uuid().length(),36);
    assertEquals(X.uuidPure().length(),32);
  }
  
//  @Test
  public void test1() {
    String uuid;
    long t;
    t=System.currentTimeMillis();
    for(long i=0;i<j;i++){
      uuid=X.uuid();
    }
    System.out.println("XTest.test1()");
    System.out.println(System.currentTimeMillis()-t);
  }

//  @Test
  public void test2() {
    String uuid;
    long t;
    t=System.currentTimeMillis();
    for(long i=0;i<j;i++){
      uuid=X.uuid();
      uuid=uuid.replaceAll("-", "");
    }
    System.out.println("XTest.test2()");
    System.out.println(System.currentTimeMillis()-t);
  }
//  @Test
  public void test3() {
    String uuid;
    long t;
    t=System.currentTimeMillis();
    for(long i=0;i<j;i++){
      uuid=X.uuid();
      uuid=uuid.substring(0, 8)+"" + uuid.substring(9, 13)+"" + uuid.substring(14, 18)+"" + uuid.substring(19, 23)+"" + uuid.substring(24);  
    }
    System.out.println("XTest.test3()");
    System.out.println(System.currentTimeMillis()-t);
  }
  
//  @Test
  public void test4() {
    String uuid;
    StringBuilder sb;
    long t;
    t=System.currentTimeMillis();
    for(long i=0;i<j;i++){
      uuid=X.uuid();
      sb=new StringBuilder();
      sb.append(uuid.substring(0, 8)).append("");
      sb.append(uuid.substring(9, 13)).append("");
      sb.append(uuid.substring(14, 18)).append("");
      sb.append(uuid.substring(19, 23)).append("");
      sb.append(uuid.substring(24));
      uuid=sb.toString();  
    }
    System.out.println("XTest.test4()");
    System.out.println(System.currentTimeMillis()-t);
  }
  //@Test
  public void test5(){
    XTest test = new XTest(); 
    Properties prop = new Properties(); 
    InputStream in = test.getClass().getResourceAsStream("/ad.properties"); 
    try {
      prop.load(in); 
     } catch (IOException e) { 
      e.printStackTrace(); 
     } 
     System.out.println(prop.getProperty("FP_STRING1")); 
     System.out.println(prop.getProperty("matches4"));
  }
  
  
  //@Test
  public void test6(){
    File file = new File("e://pic/javaRun.txt");
    StringBuilder sb = new StringBuilder();
    try {
      FileInputStream fis = new FileInputStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(fis, "gbk"));
      String s;
      String ling = "";
      while ((s = br.readLine()) != null) {
        if(s.indexOf(".............")!=-1){
          ling = s.substring(0,s.indexOf("............."));
        }
        //下载图片
        ling = "http://7xkr0e.com2.z0.glb.qiniucdn.com/"+ling;
        download(ling,ling.substring(ling.lastIndexOf("/")) , "e://pic3");
        System.out.println(ling);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  @Test
  public void scale() {
    File file = new File("e://pic/javaRun.txt");
    FileInputStream fis = null;
    BufferedReader br = null;
    try {
       fis = new FileInputStream(file);
       br = new BufferedReader(new InputStreamReader(fis, "gbk"));
      String s;
      String ling = "";
      String fileName = "";
      String newName_220 = "";
      String newName_450 = "";
      String newName_750 = "";
      File pic = null;
      File pic_220 = null;
      File pic_450 = null;
      File pic_750 = null;
      while ((s = br.readLine()) != null) {
        if(s.indexOf(".............")!=-1){
          ling = s.substring(0,s.indexOf("............."));
        }
        fileName = ling.substring(ling.lastIndexOf("/")+1);
        newName_220 = FileUtil.getFileNameWithOutExtension(fileName)+"_220."+FileUtil.getExtension(fileName);
        newName_450 = FileUtil.getFileNameWithOutExtension(fileName)+"_450."+FileUtil.getExtension(fileName);
        newName_750 = FileUtil.getFileNameWithOutExtension(fileName)+"_750."+FileUtil.getExtension(fileName);
        pic = new File("e://pic/"+fileName);
        
        pic_220 = new File("e://pic_scale_220/"+newName_220);
        pic_450 = new File("e://pic_scale_450/"+newName_450);
        pic_750 = new File("e://pic_scale_750/"+newName_750);
        
        try {
          int rt_750 = ImageScalingUtil.resize(pic, pic_750, 750, 0.9f);
          System.out.println(fileName+"............750............"+rt_750);
        } catch (Exception e) {
          System.out.println(fileName+"...........750.............失败");
        }
        try {
          int rt_220 = ImageScalingUtil.resize(pic, pic_220, 220, 0.9f);
          System.out.println(fileName+"............220............"+rt_220);
        } catch (Exception e) {
          System.out.println(fileName+"..........220..............失败");
        }
        try {
          int rt_450 = ImageScalingUtil.resize(pic, pic_450, 450, 0.7f);
          System.out.println(fileName+"............450............"+rt_450);
        } catch (Exception e) {
          System.out.println(fileName+"...........450.............失败");
          continue;
        }
      }
    } catch (Exception e) {
        e.printStackTrace();
    }finally{
      try {
        fis.close();
        br.close();
      } catch (Exception e2) {
        
      }
    }
  }
  
  
  //@Test
  public void scale2() throws Exception {
    /*int rt = ImageScalingUtil.resize(new File("e://pic/2015082616163326521799.jpg"), new File("e://pic_scale/2015082616163326521799_750.jpg"), 750, 0.9f);
    System.out.println("........................"+rt);
    Thread.sleep(10000);*/
    int rt2 = ImageScalingUtil.resize(new File("e://q/123.jpg"),new File("e://q/123_450.jpg"), 450, 0.7f);
    System.out.println("........................"+rt2);
  }
  
  
  
  
  

  public static void download(String urlString, String filename, String savePath) throws Exception {
    // 构造URL
    URL url = new URL(urlString);
    // 打开连接
    URLConnection con = url.openConnection();
    // 设置请求超时为5s
    con.setConnectTimeout(5 * 1000);
    // 输入流
    InputStream is = con.getInputStream();

    // 1K的数据缓冲
    byte[] bs = new byte[1024];
    // 读取到的数据长度
    int len;
    // 输出的文件流
    File sf = new File(savePath);
    if (!sf.exists()) {
      sf.mkdirs();
    }
    OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
    // 开始读取
    while ((len = is.read(bs)) != -1) {
      os.write(bs, 0, len);
    }
    // 完毕，关闭所有链接
    os.close();
    is.close();
  }
  
  
  
}
