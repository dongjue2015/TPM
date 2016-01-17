package com.go2plus.common.img;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageScalingUtil {
  private static final Logger log = LoggerFactory.getLogger(ImageScalingUtil.class);

  public static int resize(File originalFile, File resizedFile, int targetWidth, float quality) throws Exception {
    long t = System.currentTimeMillis();
    BufferedImage bi = ImageIO.read(originalFile);
    if(null==bi){
      return -1; 
    }
    int w = bi.getWidth();
    int h = bi.getHeight();
    float r = targetWidth * 1.0f / w;
    int targetHeight = (int) (h * r);
    Image itemp = bi.getScaledInstance(targetWidth, targetHeight, BufferedImage.SCALE_SMOOTH);
    BufferedImage bufferedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = bufferedImage.createGraphics();
    g.drawImage(itemp, 0, 0, null);
    g.dispose();
    FileOutputStream out = new FileOutputStream(resizedFile);
    ImageWriter imageWriter = (ImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
    ImageOutputStream ios = ImageIO.createImageOutputStream(out);
    imageWriter.setOutput(ios);
    JPEGImageWriteParam jpegParams = null;
    if (quality >= 0 && quality <= 1) {
      jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
      jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
      jpegParams.setCompressionQuality(quality);
    }
    imageWriter.write(null, new IIOImage(bufferedImage, null, null), jpegParams);
    ios.close();
    out.flush();
    out.close();
    imageWriter.dispose();
    log.info("targetWidth : {}  quality : {}  time: {}", targetWidth, quality, (System.currentTimeMillis() - t));
    return targetHeight;
  }

  public static void main(String[] args) throws Exception {
    File originalImage = new File("/Users/gaofeng/Desktop/img/x.jpg");
    for (int i = 100; i >= 90; i -= 2) {
      resize(originalImage, new File("/Users/gaofeng/Desktop/img/x750_" + i + ".jpg"), 750, (float) i / 100);
    }
  }
}
