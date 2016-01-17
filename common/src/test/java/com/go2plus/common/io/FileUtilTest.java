package com.go2plus.common.io;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class FileUtilTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Test
  public void test() {
    long free = FileUtil.diskFreeSpace("/gotwo_data");
    long total = FileUtil.diskTotalSpace("/gotwo_data");
    System.out.println(free + " / " + total);
  }

}
