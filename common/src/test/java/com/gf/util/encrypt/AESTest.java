package com.gf.util.encrypt;

import org.junit.Before;
import org.junit.Test;

import com.go2plus.common.encrypt.AES;

public class AESTest {
  AES aes;

  @Before
  public void setUp() {
    aes = new AES("2016.01_go2plus_chengdu_china");
  }
  
  @Test
  public void t1(){
    System.out.println("test : "+aes.encrypt("test"));
    System.out.println("您的短信验证码为 : "+aes.encrypt("您的短信验证码为 952700 请于30分钟内使用, 谢谢. "));
    System.out.println(aes.encrypt("test"));
  }

}
