package test;

import org.gzhmc.common.util.JacobUtil;
import org.junit.Test;

public class JacobUtilTest {

	    @Test
	    public void testwordToHtml() {	      
	       String docfile = "C:\\Users\\lenovo\\Desktop\\人力资源课后习题(1).docx";
	       String htmlfile = "C:\\Users\\lenovo\\Desktop\\人力资源课后习题(1).html";
	       JacobUtil.wordToHtml(docfile, htmlfile);
	       
	    }
	    @Test
	    public void testhtmotoutf() {
			String htmlpath="C:\\Users\\lenovo\\Desktop\\报告查看\\报告查看.html";
			String docfile="C:\\Users\\lenovo\\Desktop\\报告查看\\报告查看.doc";
			JacobUtil.htmlToWord(htmlpath, docfile);
			System.out.println("成功");
		}
	    @Test
	    public void name() {
			
		}

}
