package com.go2plus.common.encrypt;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class MD5Test {
//	 private static UserDao ud;
//
//	  @BeforeClass
//	  public static void setup() {
//	    ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:IOC.xml");
//	    ud = (UserDao) ac.getBean("userDao");
//
//	    assertNotNull(ud);
//	  }
	
	@Before
    public static void setUp() throws Exception {
        
    }
	
	@Test
	public static void test() throws Exception {
		String encodeStr = MD5.md5Encode("123123");
		if(MD5.md5Encode("123123").equalsIgnoreCase("9cc2253af4a2fe1a62ab842beaf7cdbe")){
			
		}
		
		assertEquals("9cc2253af4a2fe1a62ab842beaf7cdbe", encodeStr);
	}

}
