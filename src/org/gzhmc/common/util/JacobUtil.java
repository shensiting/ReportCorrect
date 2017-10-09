package org.gzhmc.common.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * word转html
 * @author stShen
 */
public class JacobUtil {
	public static final int WORD_HTML = 8;

	public static final int WORD_TXT = 7;

	public static final int EXCEL_HTML = 44;

	/**
	 * WORD转HTML
	 * 
	 * @param docfile
	 *            WORD文件全路径
	 * @param htmlfile转换后HTML存放路径
	 */

	public static void wordToHtml(String docfile, String htmlfile) {
        //System.out.println("启动word");
		ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word
		//System.out.println("启动成功");
		try {

			// 设置word不可见
			app.setProperty("Visible", new Variant(false));
			// 获得documents对象
			Dispatch docs = (Dispatch) app.getProperty("Documents").toDispatch();
			// 打开文件
			Dispatch doc = Dispatch.invoke(docs, "Open", Dispatch.Method,
					new Object[] { docfile, new Variant(false), new Variant(true) }, new int[1]).toDispatch();
			  /* 
		     * new Variant(10)筛选过的网页 
		     * new Variant(9) 单个文件网页 
		     * new Variant(8) 另存为网页 
		     * new Variant(7) 另存为txt格式 
		     * new Variant(6) 另存为rtf格式 
		     * new Variant(0) 另存为doc格式 
		     */  			
			// 保存新的文件
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] { htmlfile, new Variant(10) },
					new int[1]);
			Variant f = new Variant(false);
			Dispatch.call(doc, "Close", f);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			app.invoke("Quit", new Variant[] {});
		}
	}

	public static void html2utf(String filePath) {
		try {

			String content = "charset=utf-8";
			String templateContent = "";
			FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
			// 下面四行：获得输入流的长度，然后建一个该长度的数组，然后把输入流中的数据以字节的形式读入到数组中，然后关闭流
			int lenght = fileinputstream.available();
			byte bytes[] = new byte[lenght];
			fileinputstream.read(bytes);
			fileinputstream.close();
			// 通过使用默认字符集解码指定的 byte 数组，构造一个新的			
			//因为原来的html文件是gb2312格式，所以转为string时也要以GBK格式读取，不然依旧是乱码
			templateContent = new String(bytes, "GBK");
			templateContent = templateContent.replaceFirst("charset=gb2312", content);
			// 因为已经替换字符串了，所以使用UTF-8字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
			byte tag_bytes[] = templateContent.getBytes("UTF-8");
			FileOutputStream fileoutputstream = new FileOutputStream(filePath);// 建立文件输出流
			fileoutputstream.write(tag_bytes);
			fileoutputstream.close();

		} catch (Exception e) {
			System.out.print(e.toString());
		}

	}
	
	 /**  
	  
     * JACOB方式  
  
     * notes:需要将jacob.dll拷贝到windows/system32或者项目所在jre\bin目录下面(比如我的Eclipse正在用的Jre路径是D:\Java\jdk1.7.0_17\jre\bin)。  
  
     * @param html html静态页面路径  
  
     * @param wordFile 要生成的word文档路径  
  
     */  
  
    public static void htmlToWord(String htmlFile, String wordFile) {      
          
            ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word  
            //System.out.println("*****正在转换...*****");  
            try {  
  
                app.setProperty("Visible", new Variant(false));  
  
                Dispatch wordDoc = app.getProperty("Documents").toDispatch();  
  
                wordDoc = Dispatch.invoke(wordDoc, "Add", Dispatch.Method, new Object[0], new int[1]).toDispatch();  
  
                Dispatch.invoke(app.getProperty("Selection").toDispatch(), "InsertFile", Dispatch.Method, new Object[] { htmlFile, "", new Variant(false), new Variant(false), new Variant(false) }, new int[3]);  
  
                Dispatch.invoke(wordDoc, "SaveAs", Dispatch.Method, new Object[] {wordFile, new Variant(1)}, new int[1]);  
  
                Dispatch.call(wordDoc, "Close", new Variant(false));  
  
            } catch (Exception e) {  
  
                e.printStackTrace();  
  
            } finally {  
  
                app.invoke("Quit", new Variant[] {});  
  
            }  
           // System.out.println("*****转换完毕********");  
    }  

}
