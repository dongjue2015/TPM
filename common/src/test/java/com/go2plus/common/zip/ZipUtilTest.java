package com.go2plus.common.zip;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.go2plus.common.X;

public class ZipUtilTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Test
  public void test() throws IOException {
     ZipUtil.zip("/Users/gaofeng/Desktop/mac", "/Users/gaofeng/Desktop/1/g.zip",X.GBK);
     ZipUtil.zip("/Users/gaofeng/Desktop/mac", "/Users/gaofeng/Desktop/1/u.zip",X.UTF8);
  }

  // @Test
  public void testNormalDecompression() {
    try {
      // 优先按照windows平台的编码方式解压zip
      ZipUtil.unzip("/Users/gaofeng/Desktop/烦烦烦_macos.zip", "/Users/gaofeng/Desktop/mac", X.GBK);
    } catch (IllegalArgumentException e) {
      if ("MALFORMED".equals(e.getMessage())) {
        try {
          // 遇到编码问题,尝试使用mac/linux 编码方式解压 zip
          ZipUtil.unzip("/Users/gaofeng/Desktop/烦烦烦_macos.zip", "/Users/gaofeng/Desktop/mac", X.UTF8);
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // @Test
  public void testFlatDecompression() {
    try {
      // 优先按照windows平台的编码方式解压zip
      ZipUtil.unzipToSingleFolder("/Users/gaofeng/Desktop/烦烦烦_macos.zip", "/Users/gaofeng/Desktop/mac", X.GBK);
    } catch (IllegalArgumentException e) {
      if ("MALFORMED".equals(e.getMessage())) {
        try {
          // 遇到编码问题,尝试使用mac/linux 编码方式解压 zip
          ZipUtil.unzipToSingleFolder("/Users/gaofeng/Desktop/烦烦烦_macos.zip", "/Users/gaofeng/Desktop/mac", X.UTF8);
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
