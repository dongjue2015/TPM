package com.go2plus.common.jmx;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

public class JMXUtilTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Test
  public void test() {
    com.go2plus.common.jmx.VMInformation vmi = JMXUtil.monitor("nodeId","git.c.ximgs.net:9501");
    assertNotNull(vmi);
  }

}
