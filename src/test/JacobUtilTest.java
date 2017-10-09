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
			String htmlpath="C:\\Users\\Administrator\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Report4gzhmc\\gzhmc\\report\\基础学院\\2013级\\生物技术\\3班\\生物技术生化实验\\html\\2_2014153004.html";
			JacobUtil.html2utf(htmlpath);
			System.out.println("成功");
		}

}
