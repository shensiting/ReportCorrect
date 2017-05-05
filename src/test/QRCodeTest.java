package test;
import org.gzhmc.common.util.QRCodeUtil;;
/**
 * 二维码工具测试类
 * @author Administrator
 *
 */
public class QRCodeTest {
    
    //不包含图片的二维码生成路径  
    private static String qr1 = "E:/report4gzhmc/images/qr_002.jpg";        
    //包含图片的二维码生成路径  
    private static String qr2 = "d:/qrImgs/qr_002.jpg";        
    /** 二维码携带内容 */  
    private static String qrContent = "http://baidu.com";  
  
    public static void main(String[] args) throws Exception {  
          
        //1. 测试生成不包含图片的二维码  
        createQRWithOutLogo();  
          
        //2. 测试生成包含图片的二维码   
       // createQRIncludeLogo();  
          
        //3. 测试解析包含图片的二维码  
        //parseQRIncludeLogo();  
          
        //4. 测试解析不包含图片的二维码  
        parseQRWithOutLogo();  
  
    }  
  
    //测试生成不包含图片的二维码  
    private static void createQRWithOutLogo() throws Exception{  
        QRCodeUtil.createQRCode(qrContent, 200, qr1);  
        System.out.println("不包含图片的二维码已经生成...\n**************************\n");  
    }  
      
    //测试包含图片的二维码  
    private static void createQRIncludeLogo() throws Exception{  
        QRCodeUtil.createQRCode(qrContent, 200, "logo.png", 50, qr2);  
        System.out.println("包含图片的二维码已经生成...\n**************************\n");  
    }  
      
    private static void parseQRWithOutLogo() throws Exception{  
        String result = QRCodeUtil.parseQRCode(qr1);  
        System.out.println("不包含图片的二维码解析结果:" + result + "\n**************************\n");  
    }  
      
    private static void parseQRIncludeLogo() throws Exception{  
        String result = QRCodeUtil.parseQRCode(qr2);  
        System.out.println("包含图片的二维码解析结果:" + result + "\n**************************\n");  
    }  
}
