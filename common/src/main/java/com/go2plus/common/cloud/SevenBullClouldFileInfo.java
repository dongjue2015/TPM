package com.go2plus.common.cloud;

public class SevenBullClouldFileInfo {
  public String key;
  public long   size;
  public String mimeType;

  public SevenBullClouldFileInfo(String key, long size, String mimeType) {
    this.key = key;
    this.size = size;
    this.mimeType = mimeType;
  }

  
  @Override
  public boolean equals(Object obj) {
    return this.key.equals(((SevenBullClouldFileInfo)obj).key);
  }
}
