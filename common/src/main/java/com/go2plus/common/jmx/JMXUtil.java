package com.go2plus.common.jmx;

public class JMXUtil {

  public static VMInformation monitor(String nodeId,String url) {
    VMInformation vmInformation = new VMInformation(nodeId,url);
    JMXFetcher jmxFetcher = new JMXFetcher();
    jmxFetcher.setUrl(url);
    jmxFetcher.setVmInformation(vmInformation);
    new Thread(jmxFetcher).start();
    return vmInformation;
  }

  // -------------------- Session ---------------
  // ObjectName managerObjName = new ObjectName("Catalina:type=Manager,*");
  // Set<ObjectName> s = mbsc.queryNames(managerObjName, null);
  // for (ObjectName obj : s) {
  // System.out.println("应用名:" + obj.getKeyProperty("path"));
  // ObjectName objname = new ObjectName(obj.getCanonicalName());
  // System.out.println("最大会话数:" + mbsc.getAttribute(objname, "maxActiveSessions"));
  // System.out.println("会话数:" + mbsc.getAttribute(objname, "activeSessions"));
  // System.out.println("活动会话数:" + mbsc.getAttribute(objname, "sessionCounter"));
  // }

  // ----------------- Thread Pool ----------------
  // ObjectName threadpoolObjName = new ObjectName("Catalina:type=ThreadPool,*");
  // Set<ObjectName> s2 = mbsc.queryNames(threadpoolObjName, null);
  // for (ObjectName obj : s2) {
  // System.out.println("端口名:" + obj.getKeyProperty("name"));
  // ObjectName objname = new ObjectName(obj.getCanonicalName());
  // System.out.println("最大线程数:" + mbsc.getAttribute(objname, "maxThreads"));
  // System.out.println("当前线程数:" + mbsc.getAttribute(objname, "currentThreadCount"));
  // System.out.println("繁忙线程数:" + mbsc.getAttribute(objname, "currentThreadsBusy"));
  // }

}
