package com.go2plus.common.jmx;

public class VMInformation {
  private String   nodeId;
  private String   url;
  // Runtime
  private String   vmVendor;
  private String   vmName;
  private String   vmVersion;
  private String[] cmdArguments;
  private String   specVendor;
  private String   specVersion;
  private long     startTime;
  private long     upTime;
  // ClassLoading
  private long     loadedClassCount;
  private long     totalLoadedClassCount;
  // Complilation
  private long     totalCompilationTime;
  // Memory
  private long     heapUsageCommited;
  private long     heapUsageUsed;
  private long     heapUsageInit;
  private long     heapUsageMax;
  private long     nonHeapUsageCommited;
  private long     nonHeapUsageUsed;
  private long     nonHeapUsageInit;
  private long     nonHeapUsageMax;
  // MemoryPool
  private long     survivorCommited;
  private long     survivorUsed;
  private long     survivorInit;
  private long     survivorMax;
  private long     permanentCommited;
  private long     permanentUsed;
  private long     permanentInit;
  private long     permanentMax;
  private long     oldCommited;
  private long     oldUsed;
  private long     oldInit;
  private long     oldMax;
  private long     edenCommited;
  private long     edenUsed;
  private long     edenInit;
  private long     edenMax;
  private long     codeCacheCommited;
  private long     codeCacheUsed;
  private long     codeCacheInit;
  private long     codeCacheMax;
  // OperatingSystem
  private String   arch;
  private int      availableProcessors;
  private long     committedVirtualMemorySize;
  private long     freePhysicalMemorySize;
  private long     freeSwapSpaceSize;
  private String   osName;
  private double   processCpuLoad;
  private long     processCpuTime;
  private double   systemCpuLoad;
  private double   systemLoadAverage;
  private long     totalPhysicalMemorySize;
  private long     totalSwapSpaceSize;
  private String   osVersion;
  // Threading
  private long[]   allThreadIds;
  private long     currentThreadCpuTime;
  private long     currentThreadUserTime;
  private long     daemonThreadCount;
  private long     peakThreadCount;
  private long     threadCount;
  private long     totalStartedThreadCount;

  public VMInformation(String nodeId,String url) {
    this.nodeId = nodeId;
    this.url=url;
  }

  public String getVmVendor() {
    return vmVendor;
  }

  public void setVmVendor(String vmVendor) {
    this.vmVendor = vmVendor;
  }

  public String getVmName() {
    return vmName;
  }

  public void setVmName(String vmName) {
    this.vmName = vmName;
  }

  public String getVmVersion() {
    return vmVersion;
  }

  public void setVmVersion(String vmVersion) {
    this.vmVersion = vmVersion;
  }

  public String[] getCmdArguments() {
    return cmdArguments;
  }

  public void setCmdArguments(String[] cmdArguments) {
    this.cmdArguments = cmdArguments;
  }

  public String getSpecVendor() {
    return specVendor;
  }

  public void setSpecVendor(String specVendor) {
    this.specVendor = specVendor;
  }

  public String getSpecVersion() {
    return specVersion;
  }

  public void setSpecVersion(String specVersion) {
    this.specVersion = specVersion;
  }

  public long getLoadedClassCount() {
    return loadedClassCount;
  }

  public void setLoadedClassCount(long loadedClassCount) {
    this.loadedClassCount = loadedClassCount;
  }

  public long getTotalLoadedClassCount() {
    return totalLoadedClassCount;
  }

  public void setTotalLoadedClassCount(long totalLoadedClassCount) {
    this.totalLoadedClassCount = totalLoadedClassCount;
  }

  public long getTotalCompilationTime() {
    return totalCompilationTime;
  }

  public void setTotalCompilationTime(long totalCompilationTime) {
    this.totalCompilationTime = totalCompilationTime;
  }

  public long getHeapUsageCommited() {
    return heapUsageCommited;
  }

  public void setHeapUsageCommited(long heapUsageCommited) {
    this.heapUsageCommited = heapUsageCommited;
  }

  public long getHeapUsageUsed() {
    return heapUsageUsed;
  }

  public void setHeapUsageUsed(long heapUsageUsed) {
    this.heapUsageUsed = heapUsageUsed;
  }

  public long getHeapUsageInit() {
    return heapUsageInit;
  }

  public void setHeapUsageInit(long heapUsageInit) {
    this.heapUsageInit = heapUsageInit;
  }

  public long getHeapUsageMax() {
    return heapUsageMax;
  }

  public void setHeapUsageMax(long heapUsageMax) {
    this.heapUsageMax = heapUsageMax;
  }

  public long getNonHeapUsageCommited() {
    return nonHeapUsageCommited;
  }

  public void setNonHeapUsageCommited(long nonHeapUsageCommited) {
    this.nonHeapUsageCommited = nonHeapUsageCommited;
  }

  public long getNonHeapUsageUsed() {
    return nonHeapUsageUsed;
  }

  public void setNonHeapUsageUsed(long nonHeapUsageUsed) {
    this.nonHeapUsageUsed = nonHeapUsageUsed;
  }

  public long getNonHeapUsageInit() {
    return nonHeapUsageInit;
  }

  public void setNonHeapUsageInit(long nonHeapUsageInit) {
    this.nonHeapUsageInit = nonHeapUsageInit;
  }

  public long getNonHeapUsageMax() {
    return nonHeapUsageMax;
  }

  public void setNonHeapUsageMax(long nonHeapUsageMax) {
    this.nonHeapUsageMax = nonHeapUsageMax;
  }

  public long getSurvivorCommited() {
    return survivorCommited;
  }

  public void setSurvivorCommited(long survivorCommited) {
    this.survivorCommited = survivorCommited;
  }

  public long getSurvivorUsed() {
    return survivorUsed;
  }

  public void setSurvivorUsed(long survivorUsed) {
    this.survivorUsed = survivorUsed;
  }

  public long getSurvivorInit() {
    return survivorInit;
  }

  public void setSurvivorInit(long survivorInit) {
    this.survivorInit = survivorInit;
  }

  public long getSurvivorMax() {
    return survivorMax;
  }

  public void setSurvivorMax(long survivorMax) {
    this.survivorMax = survivorMax;
  }

  public long getPermanentCommited() {
    return permanentCommited;
  }

  public void setPermanentCommited(long permanentCommited) {
    this.permanentCommited = permanentCommited;
  }

  public long getPermanentUsed() {
    return permanentUsed;
  }

  public void setPermanentUsed(long permanentUsed) {
    this.permanentUsed = permanentUsed;
  }

  public long getPermanentInit() {
    return permanentInit;
  }

  public void setPermanentInit(long permanentInit) {
    this.permanentInit = permanentInit;
  }

  public long getPermanentMax() {
    return permanentMax;
  }

  public void setPermanentMax(long permanentMax) {
    this.permanentMax = permanentMax;
  }

  public long getOldCommited() {
    return oldCommited;
  }

  public void setOldCommited(long oldCommited) {
    this.oldCommited = oldCommited;
  }

  public long getOldUsed() {
    return oldUsed;
  }

  public void setOldUsed(long oldUsed) {
    this.oldUsed = oldUsed;
  }

  public long getOldInit() {
    return oldInit;
  }

  public void setOldInit(long oldInit) {
    this.oldInit = oldInit;
  }

  public long getOldMax() {
    return oldMax;
  }

  public void setOldMax(long oldMax) {
    this.oldMax = oldMax;
  }

  public long getEdenCommited() {
    return edenCommited;
  }

  public void setEdenCommited(long edenCommited) {
    this.edenCommited = edenCommited;
  }

  public long getEdenUsed() {
    return edenUsed;
  }

  public void setEdenUsed(long edenUsed) {
    this.edenUsed = edenUsed;
  }

  public long getEdenInit() {
    return edenInit;
  }

  public void setEdenInit(long edenInit) {
    this.edenInit = edenInit;
  }

  public long getEdenMax() {
    return edenMax;
  }

  public void setEdenMax(long edenMax) {
    this.edenMax = edenMax;
  }

  public long getCodeCacheCommited() {
    return codeCacheCommited;
  }

  public void setCodeCacheCommited(long codeCacheCommited) {
    this.codeCacheCommited = codeCacheCommited;
  }

  public long getCodeCacheUsed() {
    return codeCacheUsed;
  }

  public void setCodeCacheUsed(long codeCacheUsed) {
    this.codeCacheUsed = codeCacheUsed;
  }

  public long getCodeCacheInit() {
    return codeCacheInit;
  }

  public void setCodeCacheInit(long codeCacheInit) {
    this.codeCacheInit = codeCacheInit;
  }

  public long getCodeCacheMax() {
    return codeCacheMax;
  }

  public void setCodeCacheMax(long codeCacheMax) {
    this.codeCacheMax = codeCacheMax;
  }

  public String getArch() {
    return arch;
  }

  public void setArch(String arch) {
    this.arch = arch;
  }

  public long getCommittedVirtualMemorySize() {
    return committedVirtualMemorySize;
  }

  public void setCommittedVirtualMemorySize(long committedVirtualMemorySize) {
    this.committedVirtualMemorySize = committedVirtualMemorySize;
  }

  public long getFreePhysicalMemorySize() {
    return freePhysicalMemorySize;
  }

  public void setFreePhysicalMemorySize(long freePhysicalMemorySize) {
    this.freePhysicalMemorySize = freePhysicalMemorySize;
  }

  public long getFreeSwapSpaceSize() {
    return freeSwapSpaceSize;
  }

  public void setFreeSwapSpaceSize(long freeSwapSpaceSize) {
    this.freeSwapSpaceSize = freeSwapSpaceSize;
  }

  public double getProcessCpuLoad() {
    return processCpuLoad;
  }

  public void setProcessCpuLoad(double processCpuLoad) {
    this.processCpuLoad = processCpuLoad;
  }

  public long getProcessCpuTime() {
    return processCpuTime;
  }

  public void setProcessCpuTime(long processCpuTime) {
    this.processCpuTime = processCpuTime;
  }

  public double getSystemCpuLoad() {
    return systemCpuLoad;
  }

  public void setSystemCpuLoad(double systemCpuLoad) {
    this.systemCpuLoad = systemCpuLoad;
  }

  public double getSystemLoadAverage() {
    return systemLoadAverage;
  }

  public void setSystemLoadAverage(double systemLoadAverage) {
    this.systemLoadAverage = systemLoadAverage;
  }

  public long getTotalPhysicalMemorySize() {
    return totalPhysicalMemorySize;
  }

  public void setTotalPhysicalMemorySize(long totalPhysicalMemorySize) {
    this.totalPhysicalMemorySize = totalPhysicalMemorySize;
  }

  public long getTotalSwapSpaceSize() {
    return totalSwapSpaceSize;
  }

  public void setTotalSwapSpaceSize(long totalSwapSpaceSize) {
    this.totalSwapSpaceSize = totalSwapSpaceSize;
  }

  public long[] getAllThreadIds() {
    return allThreadIds;
  }

  public void setAllThreadIds(long[] allThreadIds) {
    this.allThreadIds = allThreadIds;
  }

  public long getCurrentThreadCpuTime() {
    return currentThreadCpuTime;
  }

  public void setCurrentThreadCpuTime(long currentThreadCpuTime) {
    this.currentThreadCpuTime = currentThreadCpuTime;
  }

  public long getCurrentThreadUserTime() {
    return currentThreadUserTime;
  }

  public void setCurrentThreadUserTime(long currentThreadUserTime) {
    this.currentThreadUserTime = currentThreadUserTime;
  }

  public long getDaemonThreadCount() {
    return daemonThreadCount;
  }

  public void setDaemonThreadCount(long daemonThreadCount) {
    this.daemonThreadCount = daemonThreadCount;
  }

  public long getPeakThreadCount() {
    return peakThreadCount;
  }

  public void setPeakThreadCount(long peakThreadCount) {
    this.peakThreadCount = peakThreadCount;
  }

  public long getThreadCount() {
    return threadCount;
  }

  public void setThreadCount(long threadCount) {
    this.threadCount = threadCount;
  }

  public long getTotalStartedThreadCount() {
    return totalStartedThreadCount;
  }

  public void setTotalStartedThreadCount(long totalStartedThreadCount) {
    this.totalStartedThreadCount = totalStartedThreadCount;
  }

  public String getOsName() {
    return osName;
  }

  public void setOsName(String osName) {
    this.osName = osName;
  }

  public String getOsVersion() {
    return osVersion;
  }

  public void setOsVersion(String osVersion) {
    this.osVersion = osVersion;
  }

  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public long getUpTime() {
    return upTime;
  }

  public void setUpTime(long upTime) {
    this.upTime = upTime;
  }

  public int getAvailableProcessors() {
    return availableProcessors;
  }

  public void setAvailableProcessors(int availableProcessors) {
    this.availableProcessors = availableProcessors;
  }

  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
