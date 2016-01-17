package com.go2plus.common.cloud;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.go2plus.common.X;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.UrlSafeBase64;

/**
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source code file is protected by copyright law and international
 * treaties. Unauthorized distribution of source code files, programs, or portion of the package, may result in severe civil and criminal
 * penalties, and will be prosecuted to the maximum extent under the law.
 * 
 * 七牛上传文件 工具类
 * 
 * @author gaofeng
 * @since 2015-12-21
 */
public class SevenBullUploader {
  private final static Logger log = LoggerFactory.getLogger(SevenBullUploader.class);
  private Auth                auth;
  private BucketManager       bm;
  private UploadManager       um  = new UploadManager();

  /**
   * 创建实例必须指定 accessKey 和secretKey
   * 
   * @param accessKey
   * @param secretKey
   */
  public SevenBullUploader(String accessKey, String secretKey) {
    auth = Auth.create(accessKey, secretKey);
    bm = new BucketManager(auth);
  }

  /**
   * 上传一个文件
   * 
   * @param bucket
   *          上传到指定桶
   * @param key
   *          指定上传文件的key (可以是虚拟目录 如 /product/0/2015/12/12/001.jpg)
   * @param file
   *          要上传的file对象
   */
  public boolean upload(String bucket, String key, File file) {
    String token = auth.uploadToken(bucket, null);
    try {
      Response r = um.put(file, key, token);
      if (r.isOK()) {
        return X.TRUE;
      }
    } catch (QiniuException e) {
      Response r = e.response;
      log.error(null, e);
      try {
        log.error(r.bodyString());
      } catch (QiniuException e1) {
      }
    }
    return X.FALSE;
  }

  /**
   * 上传一个字节数组
   * 
   * @param bucket
   *          上传到指定桶
   * @param key
   *          指定上传文件的key (可以是虚拟目录 如 /product/0/2015/12/12/001.jpg)
   * @param content
   *          要上传的文件所读取出的 byte[]
   */
  public boolean upload(String bucket, String key, byte[] content) {
    String token = auth.uploadToken(bucket, null);
    try {
      Response r = um.put(content, key, token);
      if (r.isOK()) {
        return X.TRUE;
      }
    } catch (QiniuException e) {
      Response r = e.response;
      log.error(null, e);
      try {
        log.error(r.bodyString());
      } catch (QiniuException e1) {
      }
    }
    return X.FALSE;
  }

  /**
   * 查询本账号下的bucket列表
   * 
   * @return String[] bucket数组
   */
  public String[] getBuckets() {
    try {
      return bm.buckets();
    } catch (QiniuException e) {
      log.error(null, e);
      return null;
    }
  }

  /**
   * 遍历bucket中的内容, 把key和fileSize 存入 队列
   * 
   * @param bucket
   * @param prefix
   * @param limit
   */
  public long getResouces(String bucket, String prefix, int limit, ConcurrentLinkedQueue<SevenBullClouldFileInfo> queue) {
    BucketManager.FileListIterator it = bm.createFileListIterator(bucket, prefix, limit, null);
    long count = 0;
    while (it.hasNext()) {
      FileInfo[] items = it.next();
      for (FileInfo fi : items) {
        count++;
        queue.offer(new SevenBullClouldFileInfo(fi.key, fi.fsize, fi.mimeType));
      }
      if (count % 10000 == 0) {
        log.info("count bucket {} on prefix {} : {}", bucket, prefix, count);
      }
    }
    log.info("------------------  List File Iterator Finished ---------------");
    return count;
  }

  /**
   * 遍历bucket中的内容, 把key和fileSize 存入 队列
   * 
   * @param bucket
   * @param prefix
   * @param limit
   */
  public long printResouces(final String bucket, final String prefix, final int limit) {
    long count = 0;
    BucketManager.FileListIterator it = bm.createFileListIterator(bucket, prefix, limit, null);
    while (it.hasNext()) {
      FileInfo[] items = it.next();
      for (FileInfo fi : items) {
        count++;
        log.debug("INDEX {} : FILE : {}   |   SIZE : {}", count, fi.key, fi.fsize);
      }
    }
    return count;
  }

  /**
   * 生成缩放并另存线上图片的URL
   * 
   * @param domain
   *          源图片所在域名
   * @param originKey
   *          源图片key
   * @param targetWidth
   *          目标图片宽度, 高度会等比自动缩放
   * @param targetQuality
   *          目标图片的品质, 0~100 70以上晃眼看不出来.
   * @param targetBucket
   *          目标图片的存储bucket
   * @return 样例: <<< 7xkq77.com2.z0.glb.qiniucdn.com/0/100030/20141011/20141011121002638.jpg?imageView2/2/w/750/q/90|saveas/
   *         Z290d28tdGh1bWJuYWlsOjAvMTAwMDMwLzIwMTQxMDExLzIwMTQxMDExMTIxMDAyNjM4Xzc1MC5qcGc
   *         =/sign/UIgBw7sNUlZ1ANb0GpLeydDrY8T59cpRGTDut0c2:IsSqErpuSFX96KOaIoTaXFdZOmg= >>>
   */
  public String getScaleUrl(String domain, String originKey, int targetWidth, int targetQuality, String targetBucket) {
    StringBuilder encodedEntryURI = new StringBuilder(targetBucket);
    encodedEntryURI.append(":").append(originKey.substring(0, originKey.lastIndexOf(".")));
    encodedEntryURI.append("_").append(targetWidth).append(originKey.substring(originKey.lastIndexOf(".")));
    StringBuilder sb = new StringBuilder(domain);
    sb.append(originKey).append("?imageView2/2/w/").append(targetWidth).append("/q/").append(targetQuality);
    sb.append("|saveas/");
    sb.append(UrlSafeBase64.encodeToString(encodedEntryURI.toString()));
    String signed = auth.sign(sb.toString());
    sb.append("/sign/").append(signed);
    sb.append("&v=").append(X.RANDOM.nextInt(100));
    return sb.toString();
  }

  /**
   * 生成 转存bucket的url
   * 
   * @param domain
   *          源图片所在域名
   * @param originKey
   *          源图片key
   * @param targetWidth
   *          目标图片宽度, 高度会等比自动缩放
   * @param targetBucket
   *          目标图片的存储bucket
   * @return 样例: <<< 7xkq77.com2.z0.glb.qiniucdn.com/0/100030/20141011/20141011121002638.jpg?imageView2/2/w/750/q/90|saveas/
   *         Z290d28tdGh1bWJuYWlsOjAvMTAwMDMwLzIwMTQxMDExLzIwMTQxMDExMTIxMDAyNjM4Xzc1MC5qcGc
   *         =/sign/UIgBw7sNUlZ1ANb0GpLeydDrY8T59cpRGTDut0c2:IsSqErpuSFX96KOaIoTaXFdZOmg= >>>
   */
  public String getCloneUrl(String domain, String originKey, int targetWidth, String targetBucket) {
    StringBuilder encodedEntryURI = new StringBuilder(targetBucket);
    encodedEntryURI.append(":").append(originKey.substring(0, originKey.lastIndexOf(".")));
    encodedEntryURI.append("_").append(targetWidth).append(originKey.substring(originKey.lastIndexOf(".")));
    StringBuilder sb = new StringBuilder(domain);
    sb.append(originKey);
    sb.append("?saveas/");
    sb.append(UrlSafeBase64.encodeToString(encodedEntryURI.toString()));
    String signed = auth.sign(sb.toString());
    sb.append("/sign/").append(signed);
    sb.append("&v=").append(X.RANDOM.nextInt(100));
    return sb.toString();
  }

  public void compare(ConcurrentLinkedQueue<SevenBullClouldFileInfo> src, ConcurrentLinkedQueue<SevenBullClouldFileInfo> dist) {
    StringBuilder sb;
    String prefix, suffix, s750, s450, s220;
    // for(SevenBullClouldFileInfo sbcfi:src){
    // prefix=sbcfi.key.substring(0,sbcfi.key.lastIndexOf("."));
    // suffix=sbcfi.key.substring(sbcfi.key.lastIndexOf("."));
    // sb=new StringBuilder(prefix).append("_").append(750).append(suffix);
    // if(!dist.contains(new SevenBullClouldFileInfo(sb.toString(), 0, null))){
    // System.out.println(sbcfi.key +"    ___ size : "+sbcfi.size +">>>>>>>>> 750   missing");
    // }
    // sb=new StringBuilder(prefix).append("_").append(450).append(suffix);
    // if(!dist.contains(new SevenBullClouldFileInfo(sb.toString(), 0, null))){
    // System.out.println(sbcfi.key +"    ___ size : "+sbcfi.size +">>>>>>>>> 450   missing");
    // }
    // sb=new StringBuilder(prefix).append("_").append(220).append(suffix);
    // if(!dist.contains(new SevenBullClouldFileInfo(sb.toString(), 0, null))){
    // System.out.println(sbcfi.key +"    ___ size : "+sbcfi.size +">>>>>>>>> 220   missing");
    // }
    // }

    for (SevenBullClouldFileInfo sbcfi : src) {
      boolean s7 = false, s4 = false, s2 = false;
      prefix = sbcfi.key.substring(0, sbcfi.key.lastIndexOf("."));
      suffix = sbcfi.key.substring(sbcfi.key.lastIndexOf("."));
      s750 = new StringBuilder(prefix).append("_").append(750).append(suffix).toString();
      s450 = new StringBuilder(prefix).append("_").append(450).append(suffix).toString();
      s220 = new StringBuilder(prefix).append("_").append(220).append(suffix).toString();
      for (SevenBullClouldFileInfo sbcfiDist : dist) {
        if (sbcfiDist.key.equals(s750)) {
          s7 = true;
          dist.remove(sbcfiDist);
        }
        if (sbcfiDist.key.equals(s450)) {
          s4 = true;
          dist.remove(sbcfiDist);
        }
        if (sbcfiDist.key.equals(s220)) {
          s2 = true;
          dist.remove(sbcfiDist);
        }
        if (s7 && s4 && s2) {
          break;
        }
      }
      if (!s7) {
        System.out.println(sbcfi.key + "    ___ size : " + sbcfi.size + ">>>>>>>>> 750   missing");
      }
      if (!s4) {
        System.out.println(sbcfi.key + "    ___ size : " + sbcfi.size + ">>>>>>>>> 450   missing");
      }
      if (!s2) {
        System.out.println(sbcfi.key + "    ___ size : " + sbcfi.size + ">>>>>>>>> 220   missing");
      }
    }
  }

  /**
   * 从命名文件 将key中的 "!" 用 "_" 替代.
   * 
   * @param srcBucket
   * @param key
   * @return
   */
  public String rename(String srcBucket, String key) {
    try {
      String newKey = key.replaceAll("!", "_");
      bm.move(srcBucket, key, srcBucket, newKey);
      return newKey;
    } catch (QiniuException e) {
      log.error(null, e);
      return null;
    }
  }
}
