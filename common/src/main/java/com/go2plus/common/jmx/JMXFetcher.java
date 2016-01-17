package com.go2plus.common.jmx;

import java.lang.management.MemoryUsage;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.go2plus.common.X;

class JMXFetcher implements Runnable {
  private static Logger log = LoggerFactory.getLogger(JMXFetcher.class);
  private VMInformation vmInformation;
  private String        url;

  @Override
  public void run() {
    MBeanServerConnection mbsc;
    JMXConnector connector = null;
    StringBuilder sb = new StringBuilder("service:jmx:rmi:///jndi/rmi://");
    sb.append(url).append("/jmxrmi");
    while (true) {
      try {
        connector = JMXConnectorFactory.connect(new JMXServiceURL(sb.toString()), null);
        // 不常变化的MBean
        mbsc = connector.getMBeanServerConnection();
        // Create ObjectNames
        ObjectName classLoading = new ObjectName("java.lang:type=ClassLoading");
        ObjectName compilation = new ObjectName("java.lang:type=Compilation");
        ObjectName memory = new ObjectName("java.lang:type=Memory");
        ObjectName codeCache = new ObjectName("java.lang:type=MemoryPool,name=Code Cache");
        ObjectName eden = new ObjectName("java.lang:type=MemoryPool,name=PS Eden Space");
        ObjectName old = new ObjectName("java.lang:type=MemoryPool,name=PS Old Gen");
        ObjectName perm = new ObjectName("java.lang:type=MemoryPool,name=PS Perm Gen");
        ObjectName survivor = new ObjectName("java.lang:type=MemoryPool,name=PS Survivor Space");
        ObjectName operatingSystem = new ObjectName("java.lang:type=OperatingSystem");
        ObjectName runtime = new ObjectName("java.lang:type=Runtime");
        ObjectName threading = new ObjectName("java.lang:type=Threading");

        vmInformation.setVmVendor((String) mbsc.getAttribute(runtime, "VmVendor"));
        vmInformation.setVmName((String) mbsc.getAttribute(runtime, "VmName"));
        vmInformation.setVmVersion((String) mbsc.getAttribute(runtime, "VmVersion"));
        vmInformation.setCmdArguments((String[]) mbsc.getAttribute(runtime, "InputArguments"));
        vmInformation.setSpecVendor((String) mbsc.getAttribute(runtime, "SpecVendor"));
        vmInformation.setSpecVersion((String) mbsc.getAttribute(runtime, "SpecVersion"));
        vmInformation.setStartTime((long) mbsc.getAttribute(runtime, "StartTime"));

        vmInformation.setTotalPhysicalMemorySize((long) mbsc.getAttribute(operatingSystem, "TotalPhysicalMemorySize"));
        vmInformation.setOsName((String) mbsc.getAttribute(operatingSystem, "Name"));
        vmInformation.setOsVersion((String) mbsc.getAttribute(operatingSystem, "Version"));
        vmInformation.setArch((String) mbsc.getAttribute(operatingSystem, "Arch"));
        vmInformation.setAvailableProcessors((int) mbsc.getAttribute(operatingSystem, "AvailableProcessors"));
        while (true) {
          // classLoading
          vmInformation.setLoadedClassCount((int) mbsc.getAttribute(classLoading, "LoadedClassCount"));
          vmInformation.setTotalLoadedClassCount((long) mbsc.getAttribute(classLoading, "TotalLoadedClassCount"));
          // compilation
          vmInformation.setTotalCompilationTime((long) mbsc.getAttribute(compilation, "TotalCompilationTime"));
          // memory
          MemoryUsage heapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memory, "HeapMemoryUsage"));
          vmInformation.setHeapUsageCommited(heapMemoryUsage.getCommitted());
          vmInformation.setHeapUsageInit(heapMemoryUsage.getInit());
          vmInformation.setHeapUsageMax(heapMemoryUsage.getMax());
          vmInformation.setHeapUsageUsed(heapMemoryUsage.getUsed());
          MemoryUsage nonHeapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(memory, "NonHeapMemoryUsage"));
          vmInformation.setNonHeapUsageCommited(nonHeapMemoryUsage.getCommitted());
          vmInformation.setNonHeapUsageInit(nonHeapMemoryUsage.getInit());
          vmInformation.setNonHeapUsageMax(nonHeapMemoryUsage.getMax());
          vmInformation.setNonHeapUsageUsed(nonHeapMemoryUsage.getUsed());
          // codeCache
          MemoryUsage codeCacheUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(codeCache, "Usage"));
          vmInformation.setCodeCacheCommited(codeCacheUsage.getCommitted());
          vmInformation.setCodeCacheInit(codeCacheUsage.getInit());
          vmInformation.setCodeCacheMax(codeCacheUsage.getMax());
          vmInformation.setCodeCacheUsed(codeCacheUsage.getUsed());
          // eden
          MemoryUsage edenUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(eden, "Usage"));
          vmInformation.setEdenCommited(edenUsage.getCommitted());
          vmInformation.setEdenInit(edenUsage.getInit());
          vmInformation.setEdenMax(edenUsage.getMax());
          vmInformation.setEdenUsed(edenUsage.getUsed());
          // old
          MemoryUsage oldUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(old, "Usage"));
          vmInformation.setOldCommited(oldUsage.getCommitted());
          vmInformation.setOldInit(oldUsage.getInit());
          vmInformation.setOldMax(oldUsage.getMax());
          vmInformation.setOldUsed(oldUsage.getUsed());
          // perm
          MemoryUsage permUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(perm, "Usage"));
          vmInformation.setPermanentCommited(permUsage.getCommitted());
          vmInformation.setPermanentInit(permUsage.getInit());
          vmInformation.setPermanentMax(permUsage.getMax());
          vmInformation.setPermanentUsed(permUsage.getUsed());
          // survivor
          MemoryUsage survivorUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(survivor, "Usage"));
          vmInformation.setSurvivorCommited(survivorUsage.getCommitted());
          vmInformation.setSurvivorInit(survivorUsage.getInit());
          vmInformation.setSurvivorMax(survivorUsage.getMax());
          vmInformation.setSurvivorUsed(survivorUsage.getUsed());
          // OperatingSystem

          vmInformation.setCommittedVirtualMemorySize((long) mbsc.getAttribute(operatingSystem, "CommittedVirtualMemorySize"));
          vmInformation.setFreePhysicalMemorySize((long) mbsc.getAttribute(operatingSystem, "FreePhysicalMemorySize"));
          vmInformation.setFreeSwapSpaceSize((long) mbsc.getAttribute(operatingSystem, "FreeSwapSpaceSize"));

          vmInformation.setProcessCpuLoad((double) mbsc.getAttribute(operatingSystem, "ProcessCpuLoad"));
          vmInformation.setProcessCpuTime((long) mbsc.getAttribute(operatingSystem, "ProcessCpuTime"));
          vmInformation.setSystemCpuLoad((double) mbsc.getAttribute(operatingSystem, "SystemCpuLoad"));
          vmInformation.setSystemLoadAverage((double) mbsc.getAttribute(operatingSystem, "SystemLoadAverage"));

          vmInformation.setTotalSwapSpaceSize((long) mbsc.getAttribute(operatingSystem, "TotalSwapSpaceSize"));

          // runtime
          vmInformation.setUpTime((long) mbsc.getAttribute(runtime, "Uptime"));

          // Threading
          vmInformation.setAllThreadIds((long[]) mbsc.getAttribute(threading, "AllThreadIds"));
          vmInformation.setCurrentThreadCpuTime((long) mbsc.getAttribute(threading, "CurrentThreadCpuTime"));
          vmInformation.setCurrentThreadUserTime((long) mbsc.getAttribute(threading, "CurrentThreadUserTime"));
          vmInformation.setDaemonThreadCount((int) mbsc.getAttribute(threading, "DaemonThreadCount"));
          vmInformation.setPeakThreadCount((int) mbsc.getAttribute(threading, "PeakThreadCount"));
          vmInformation.setThreadCount((int) mbsc.getAttribute(threading, "ThreadCount"));
          vmInformation.setTotalStartedThreadCount((long) mbsc.getAttribute(threading, "TotalStartedThreadCount"));
          X.sleep(1);
        }
      } catch (Exception e) {
        log.error(e.getMessage());
        X.close(connector);
        X.sleep(2);
      }
    }
  }

  public VMInformation getVmInformation() {
    return vmInformation;
  }

  public void setVmInformation(VMInformation vmInformation) {
    this.vmInformation = vmInformation;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
