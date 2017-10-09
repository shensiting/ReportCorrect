package org.gzhmc.common.util;

import java.awt.Color;  
import java.awt.Font;  
import java.awt.Graphics;  
import java.awt.image.BufferedImage;  
import java.util.Random;  


@SuppressWarnings("restriction")
public final class CaptchaUtil
{
	 private BufferedImage image;// 图像  
	    private String str;// 验证码  
	    private static char code[] = "0123456789".toCharArray();  
	    private static char mark[] = "+-*".toCharArray();  
	    public static final String SESSION_CODE_NAME="code";  
	      
	    private CaptchaUtil() {  
	        init();// 初始化属性  
	    }  	  
	    /* 
	     * 取得RandomNumUtil实例 
	     */  
	    public static CaptchaUtil Instance() {  
	        return new CaptchaUtil();  
	    }  
	  
	    /* 
	     * 取得验证码图片 
	     */  
	    public BufferedImage getImage() {  
	        return this.image;  
	    }  
	  
	    /* 
	     * 取得图片的验证码 
	     */  
	    public String getString() {  
	        return this.str;  
	    }  
	  
	    private void init() {  
	        // 在内存中创建图象  
	        int width = 115, height = 35;  
	        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
	        // 获取图形上下文  
	        Graphics g = image.getGraphics();  
	        // 生成随机类  
	        Random random = new Random();  
	        // 设定背景色  
	        g.setColor(getRandColor(200, 250));  
	        g.fillRect(0, 0, width, height);  
	        // 设定字体  
	        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));  
	        // 随机产生180条干扰线，使图象中的认证码不易被其它程序探测到  
	        g.setColor(getRandColor(160, 200));  
	        for (int i = 0; i < 180; i++) {  
	            int x = random.nextInt(width);  
	            int y = random.nextInt(height);  
	            int xl = random.nextInt(35);  
	            int yl = random.nextInt(35);  
	            g.drawLine(x, y, x + xl, y + yl);  
	        }  
	        // 取随机产生的认证码 (此处更改为数学公式验证码)
	        String sRand = ""; 
	        String rand;
	        for (int i = 0; i < 5; i++) {  
	        	if(i%2==0)
	             rand = String.valueOf(code[random.nextInt(code.length)]);  
	        	else
	        		rand = String.valueOf(mark[random.nextInt(mark.length)]);  
	            sRand += rand;  
	            // 将认证码显示到图象中  
	            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));  
	            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成  
	            g.drawString(rand, 13 * i +6, random.nextInt(20)+10);  
	        } 
	        str=sRand;
	        char meString[]="=?".toCharArray();
	        String getmes="";
	        for (int i = 5; i <7; i++) {  
	        	
	        	getmes = String.valueOf(meString[i-5]);  
	            sRand += getmes;  
	            // 将认证码显示到图象中  
	            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));  
	            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成  
	            g.drawString(getmes, 13 * i +6, random.nextInt(20)+10);  
	        }  
	      
	  
	        // 图象生效  
	        g.dispose();  
	        // ByteArrayInputStream input = null;  
	        // ByteArrayOutputStream output = new ByteArrayOutputStream();  
	        // try {  
	        // ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);  
	        // ImageIO.write(image, "JPEG", imageOut);  
	        // imageOut.close();  
	        // input = new ByteArrayInputStream(output.toByteArray());  
	        // } catch (Exception e) {  
	        // System.out.println("验证码图片产生出现错误：" + e.toString());  
	        // }  
	        // this.image = input  
	        this.image = image;/* 赋值图像 */  
	    }  
	  
	    /* 
	     * 给定范围获得随机颜色 
	     */  
	    private Color getRandColor(int fc, int bc) {  
	        Random random = new Random();  
	        if (fc > 255)  
	            fc = 255;  
	        if (bc > 255)  
	            bc = 255;  
	        int r = fc + random.nextInt(bc - fc);  
	        int g = fc + random.nextInt(bc - fc);  
	        int b = fc + random.nextInt(bc - fc);  
	        return new Color(r, g, b);  
	    }  
}