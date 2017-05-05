package test;

import java.io.FileNotFoundException;

import org.gzhmc.report4gzhmc.mapper.ReportRelativeMapper;
import org.gzhmc.report4gzhmc.model.ReportRelative;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PdfMakeTest {

	static ReportRelativeMapper reportRelativeMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		reportRelativeMapper = app.getBean(ReportRelativeMapper.class);
	}
	@Test
	public void testpdfcertificate() throws FileNotFoundException {
		ReportRelative reportRelative=reportRelativeMapper.getById(19);
		System.out.println(reportRelative.getcProcess());		
		//SetpdfCertificate.setCertificate(reportRelative);
		
	}
}

