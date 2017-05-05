package test;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.gzhmc.report4gzhmc.mapper.ReportMapper;
import org.gzhmc.report4gzhmc.mapper.TeacherExperimentalMapper;
import org.gzhmc.report4gzhmc.model.Report;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportMapperTest {

	static ReportMapper reportMapper;
    static TeacherExperimentalMapper teacherExperimentalMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		reportMapper = app.getBean(ReportMapper.class);
	}

	@Test
	public void testAdd() {
		Report report = new Report();
		report.setcCreateTime(new Date());
		
		report.setcPath("c:t/er/j.jpg");
		report.setcScoreId(1);
		report.setcStatus(2);
		report.setcStudentId(3);
		report.setcPdfPath("c://pdf");
		report.setcProcess("PCR实验技术与应用");
		report.setcQRcode("c://QRcode");
		report.setcReportNum("测试.word");
		int result = reportMapper.add(report);
		System.out.println("add result :" + result);

	}

	@Test
	public void testDelete() {
		int result = reportMapper.delete(1);
		System.out.println("delete result :" + result);

	}

	@Test
	public void testUpdate() {

		Report report = reportMapper.getById(1);

		report.setcReportNum("测试名字");

		int result = reportMapper.update(report);
		System.out.println("update result :" + result);
	}

	@Test
	public void testUpdateSelective() {
		Report report = reportMapper.getById(1);

		report.setcReportNum("测试名字2");
		report.setcCreateTime(new Date());
		int result = reportMapper.updateSelective(report);
		System.out.println("UpdateSelective result :" + result);
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		List<Report> list = reportMapper.getAll();
		System.out.println("list size :" + list.size() + ":" + list.get(0).getcPath());

	}

	@Test
	public void testgetByexperimrntAndstudent() {
//		Report report1=new Report();
//		report1.setcExperimentTextId(1);
//		report1.setcStudentId(1);
//		int result=reportMapper.getCountByexperimrntAndstudent(report1);
//		System.out.println(result);
//		if(result!=0)
//		{
//		Report report2=reportMapper.getByexperimrntAndstudentId(report1);
//		System.out.println(report2.getcExperimentTextId());
//		}
		
	}
	@Test
	public void testgetByExperimentalId() {
		Report report1=new Report();
		Report report2=new Report();
		report1.setcExperimentTextId(1);
//		report2.set
//        List<Report> report2=reportMapper.getByExperimentalId(teacherExperimental);		
//		System.out.println(report2.size());
//		
		
	}
	
}
