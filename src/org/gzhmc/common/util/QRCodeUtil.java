package org.gzhmc.common.util;

import java.awt.BasicStroke;  
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.Shape;  
import java.awt.geom.RoundRectangle2D;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.util.HashMap;  
  
import javax.imageio.ImageIO;  
  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.BinaryBitmap;  
import com.google.zxing.DecodeHintType;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatReader;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.common.HybridBinarizer;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel; 

/**
 * 二维码生成工具类
 * @author stShen
 *
 */
public class QRCodeUtil {
	 /** 字符编码UTF-8 */  
    private static final String CHARSET_UTF8 = "utf-8";  
      
    /*** 
     * 生成二维码图片 
     * @param content 二维码携带文本内容 
     * @return BufferedImage 
     * @throws Exception 
     */  
    private static BufferedImage createImage(String content,int qrSize) throws Exception {  
        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET_UTF8);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrSize, qrSize, hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);  
            }  
        }  
        return image;  
    }  
    /** 
     * 图片中嵌入图片（二维码中插入logo） 
     * @param qrBi 二维码BufferedImage 
     * @param qcSize  
     * @param imgPath 
     * @param logoSize 
     * @throws Exception 
     */  
    private static void insertImage(BufferedImage qrBi, String imgPath, int logoSize) throws Exception {  
        // 判断嵌入的文件是否存在  
        File file = new File(imgPath);  
        if (!file.exists()) {  
            throw new RuntimeException("" + imgPath + "   该文件不存在！");  
        }  
          
        // 读取嵌入的图片  
        Image imgSource = ImageIO.read(new File(imgPath));  
        int width = imgSource.getWidth(null);  
        int height = imgSource.getHeight(null);  
   
        // 压缩LOGO  
        if (width > logoSize) {  
            width = logoSize;  
        }  
        if (height > logoSize) {  
            height = logoSize;  
        }  
        Image image = imgSource.getScaledInstance(width, height, Image.SCALE_SMOOTH);  
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        Graphics g = tag.getGraphics();  
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
        g.dispose();  
        imgSource = image;  
  
        // 插入LOGO  
        Graphics2D graph = qrBi.createGraphics();  
        int x = (qrBi.getWidth() - width) / 2;  
        int y = (qrBi.getHeight() - height) / 2;  
        graph.drawImage(imgSource, x, y, width, height, null);  
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
        graph.setStroke(new BasicStroke(3f));  
        graph.draw(shape);  
        graph.dispose();  
    }  
  
    /** 
     * 生成二维码（嵌入图片） 
     * @param content 二维码携带文本内容 
     * @param logoFilePath 嵌入图片路径 
     * @param qrPath  二维码图片生成文件 
     * @throws Exception 
     */  
    public static void createQRCode(String content, int qcSize,  String logoFilePath, int logoSize, String qrPath) throws Exception {  
        //1. 生成二维码图片  
        BufferedImage image = QRCodeUtil.createImage(content,qcSize);  
        //2. 二维码图片中嵌入logo  
        insertImage(image, logoFilePath,logoSize);  
        //3. 图片写入磁盘  
        ImageIO.write(image, "png", new File(qrPath));  
    }  
      
    /** 
     * 生成二维码图片（不包含图片） 
     * @param content 二维码携带文本内容 
     * @param qcSize 二维码大小 
     * @param qrPath 生成的二维码文件全路径名 
     * @throws Exception 
     */  
    public static void createQRCode(String content, int qcSize ,String qrPath) throws Exception{  
        BufferedImage image = QRCodeUtil.createImage(content,qcSize);  
        ImageIO.write(image, "png", new File(qrPath));  
          
    }  
  
    /** 
     * 解析二维码 
     *  
     * @param file 二维码图片 
     * @return 二维码携带内容 
     * @throws Exception 
     */  
    public static String parseQRCode(File file) throws Exception {  
        BufferedImage image;  
        image = ImageIO.read(file);  
        if (image == null) {  
            return null;  
        }  
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);  
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
        Result result;  
        HashMap<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET_UTF8);  
        result = new MultiFormatReader().decode(bitmap, hints);  
        String resultStr = result.getText();  
        return resultStr;  
    }  
  
    /** 
     * 解析二维码 
     *  
     * @param path 二维码所在路径 
     * @return 二维码携带文本内容 
     * @throws Exception 
     */  
    public static String parseQRCode(String path) throws Exception {  
        return QRCodeUtil.parseQRCode(new File(path));  
    }  
  

}
