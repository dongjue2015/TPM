package com.go2plus.common.img;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.go2plus.common.Constant;
import com.go2plus.common.X;
import com.go2plus.common.http.HttpAgent;
import com.go2plus.common.io.FileUtil;

/**
 * 
 * Copyright (C) 2015 GO2.CN. All rights reserved. This computer program source code file is protected by copyright law and international
 * treaties. Unauthorized distribution of source code files, programs, or portion of the package, may result in severe civil and criminal
 * penalties, and will be prosecuted to the maximum extent under the law.
 * 
 * go2相对地址和绝对地址互转
 * 
 * @author gzh
 * @since 20151230
 */

public class ImgAddressConvert implements Constant {

  /**
   * 老图片位于http://image.go2.cn
   * 
   * 根据相对地址转成go2图片地址
   */
  public static String relativeURLToGo2(String inStr) {
    // 匹配
    String imgPatt = "src=\"/+(\\d+/[\\w!]+.(jpg|png|gif|jpeg))\"";
    // 忽略大小写
    Pattern pattern = Pattern.compile(imgPatt, Pattern.CASE_INSENSITIVE);
    Matcher m = pattern.matcher(inStr);
    StringBuffer stringBuffer = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(stringBuffer, "src=\"http://image.go2.cn/" + m.group(1) + "\"");
    }
    return stringBuffer.toString();
    //http://image.go2.cn/13556/2015100623003990678591.jpg
  }

  /**
   * 产品详情页地址转换
   * 
   * 替换jpg/png等图片地址
   * 
   * 修改尺寸
   * 
   * @param inString
   * @return
   */
  public static String convertImgSize(String inString) {
    // jpg,png替换图片尺寸
    String imgPatt = "http://[image|img]+.go2.cn/+(\\d+)/([\\w!]+).(jpg|png|jpeg)";
    Pattern pattern = Pattern.compile(imgPatt, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(inString);

    // 当前时间
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMDD");
    Long now = System.currentTimeMillis();
    String today = dateFormat.format(now);

    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, STATIC_BIG_IMAGE_URL_PREFIX + "/imgs/in/0/" + matcher.group(1) + "/0/" + today + "/" + matcher.group(2)
          + "_750X750." + matcher.group(3));
    }
    return sb.toString();
    //http://image.go2.cn/13556/2015100623003990678591.jpg
    //http://static.ximgs.net /imgs/in/0/ 13556/ 0/  20160109/  2015100623003990678591.jpg
  }

  /**
   * 1b端产品编辑页面
   * 
   * 相对地址转绝对地址
   */

  public static String editedProductAddrConvert(String inStr) {
    String p = "src=\"/+(\\d*+/[\\w!]+.jpg|png|gif|jpeg)\"";
    Pattern pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(inStr);
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, "src=\"http://content.ximgs.net/" + matcher.group(1) + "\"");
    }
    return sb.toString();
  }
  
  /**
   * 替换为图片网络地址(临时使用,待七牛云切换完成后切换代码)
   * @param content    需要替换的字符串
   * @param sellerId  卖家用户id
   * @param type       图片资源类型
   * @param taobao     
   * @param size       图片尺寸
   * @param flag       替换   or 返回图片地址列表
   * @return
   */
	public static String commonConvertImgUrl(String content, String sellerId,
			String type, String taobao, String size, boolean flag) {
		List<String> urlList = new ArrayList<String>();
		List<String> absoluteUrlList = new ArrayList<String>();
		String imgHost = "";
		// 替换为网络地址
		if (null != taobao && !taobao.equals("") && sellerId != null)
			imgHost = X.STATIC_TAOBAO_IMAGE_URL_PREFIX + "/imgs/in/"
					+ sellerId;
		else
			imgHost = X.STATIC_IMAGE_URL_PREFIX + X.SLASH;
		
		if (flag) {
			

			if (type.equals("thumb")) {
				Matcher matcher = Pattern.compile(
						"/+(\\d+/[\\w!]+.(jpg|png|gif|jpeg))",
						Pattern.CASE_INSENSITIVE).matcher(content);

				StringBuilder url = new StringBuilder(
						"http://p.go2.cn:8080/v2/checker.php?file=");
				while (matcher.find()) {
					urlList.add(matcher.group());
					url.append(matcher.group()).append(",");
				}

				String[] arr = HttpAgent.get(url.toString()).split(",");

				String str = "";
				for (int i = 0; i < urlList.size(); i++) {
					if (arr[i].equals("1")) {
						if (sellerId != null) {
							Matcher m = Pattern.compile(
									"/+(\\d+)/([\\w!]+).(jpg|png|jpeg)",
									Pattern.CASE_INSENSITIVE).matcher(
									urlList.get(i));
							StringBuffer bf = new StringBuffer();
							while (m.find()) {
								m.appendReplacement(
										bf,
										imgHost
												+ X.SLASH
												+ m.group(1)
												+ "/0/"
												+ new SimpleDateFormat(
														"yyyyMMDD").format(System
														.currentTimeMillis())
												+ X.SLASH
												+ m.group(2) 
												+ X.UNDER_LINE + size + X.DOT
												+ m.group(3));
								absoluteUrlList.add(bf.toString());
								str += bf.toString() + "||";
							}
						} else {

						}
					}
				}

				return str;

				// 返回图片地址列表
			} else {
				return null;
			}
		} else {
			Matcher m = Pattern.compile(
					"/+(\\d+)/([\\w!]+).(jpg|png|jpeg)",
					Pattern.CASE_INSENSITIVE).matcher(content);
		    StringBuffer sb = new StringBuffer();
		    while (m.find()) {
		    	String test = m.group();
		    	String url = imgHost
						+ X.SLASH
						+ m.group(1)
						+ "/0/"
						+ new SimpleDateFormat(
								"yyyyMMDD").format(System
								.currentTimeMillis())
						+ X.SLASH
						+ m.group(2) 
						+ X.UNDER_LINE + size + X.DOT
						+ m.group(3);
		    	m.appendReplacement(sb, url);
		    }
		    return sb.toString();
		}

	}
}
