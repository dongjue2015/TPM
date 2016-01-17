package com.go2plus.common.cloud;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.go2plus.common.X;
import com.go2plus.common.encrypt.BASE64;
import com.go2plus.common.json.Json;
import com.qiniu.util.Base64;
import com.qiniu.util.UrlSafeBase64;

public class SevenBullUploaderTest {
  static SevenBullUploader sbu;
  static SevenBullUploader sbu2;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    sbu = new SevenBullUploader(X.getConfig("sevenbull.test.accessKey"), X.getConfig("sevenbull.test.secretKey"));
    sbu2 = new SevenBullUploader("UIgBw7sNUlZ1ANb0GpLeydDrY8T59cpRGTDut0c2", "9uk-Ap8N9D7zQL2aOvEGygEYZTa4Cruu_yj1LXEO");
  }

  // @Test
  public void t1() {
    System.out.println("middle :" + System.currentTimeMillis());
    for (int i = 0; i < 20; i++) {
      sbu.upload("bucketa", null, new File("/Users/gaofeng/Desktop/img/xs.jpg"));
    }
    System.out.println("end :" + System.currentTimeMillis());
  }

  // @Test
  public void t2() {
    String[] buckets = sbu2.getBuckets();
    System.out.println(Json.toJson(buckets));
  }

  // @Test
  public void t3() {
    String bucket = "gotwo-thumbnail:0/101150/20150525/2015052517442692838357_750.jpg";
    System.out.println(BASE64.encrypt(bucket));
    System.out.println(Base64.encodeToString(bucket.getBytes(), Base64.DEFAULT));
    System.out.println(UrlSafeBase64.encodeToString(bucket));
  }

  @Test
  public void t4() {
    String originKey = "8/10388/20120314/main_201203140931211460.jpg";
    String url = sbu2.getScaleUrl("7xkq77.com2.z0.glb.qiniucdn.com/", originKey, 750, 90, "gotwo-thumbnail");
    System.out.println("ScaleUrl : ");
    System.out.println(url);
  }

  @Test
  public void t5() {
    String originKey = "8/10388/20120314/main!201203140931211460.jpg";
    String url = sbu2.getCloneUrl("7xkq77.com2.z0.glb.qiniucdn.com/", originKey, 450, "gotwo-thumbnail");
    System.out.println("CloneUrl : ");
    System.out.println(url);
  }

  // @Test
  public void count() {
    ConcurrentLinkedQueue<SevenBullClouldFileInfo> srcQueue = new ConcurrentLinkedQueue<SevenBullClouldFileInfo>();
    String prefix = "0/3";
    long t = System.currentTimeMillis();
    long count = sbu2.getResouces("gotwo-imagefiles", prefix, 1000, srcQueue);
    System.out.println(count);
    System.out.println("TOTAL Times : " + (System.currentTimeMillis() - t) / 1000);
  }

  // @Test
  public void compare() {
    ConcurrentLinkedQueue<SevenBullClouldFileInfo> srcQueue = new ConcurrentLinkedQueue<SevenBullClouldFileInfo>();
    ConcurrentLinkedQueue<SevenBullClouldFileInfo> distQueue = new ConcurrentLinkedQueue<SevenBullClouldFileInfo>();
    String prefix = "0/1000090";
    long count = sbu2.getResouces("go2mm-imagefiles", prefix, 1000, srcQueue);
    long count2 = sbu2.getResouces("go2mm-thumbnail", prefix, 1000, distQueue);
    System.out.println(count * 3 + "/ " + count2);
    sbu2.compare(srcQueue, distQueue);
    System.out.println("FINISHED ..........");
  }

}
