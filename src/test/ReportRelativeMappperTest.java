package test;

import static org.junit.Assert.fail;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.ReportRelativeMapper;
import org.gzhmc.report4gzhmc.model.ReportRelative;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportRelativeMappperTest {

	static ReportRelativeMapper reportRelativeMappper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		reportRelativeMappper = app.getBean(ReportRelativeMapper.class);
	}
	@Test
	public void testGetById() {
		ReportRelative reportRelative=reportRelativeMappper.getById(19);
		System.out.println(reportRelative.getcProcess());
	
	}

	@Test
	public void testGetByGradeId() {
//		List<ReportRelative> relatives=reportRelativeMappper.getByGradeId(1);
//		System.out.println(relatives.get(0).getGradeExam().getcStatus());
	}

	@Test
	public void testGetByExperimentId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		List<ReportRelative> reportRelatives=reportRelativeMappper.getAll();
        System.out.println(reportRelatives.size());
	}

	@Test
	public void getExitScoreByExperimentId() {
		int[] a=new int[3];
		a[0]=1;a[1]=2;
		List<ReportRelative> relatives=reportRelativeMappper.getExitScoreByExperimentId(a);
		System.out.println(relatives.size());
	}

	
	@Test
	public void testGetExitScoreAllByStudentId() {
		fail("Not yet implemented");
	}

	@Test
	public void testgetExperimentIdandGradeId() {
		List<ReportRelative> reportRelatives=reportRelativeMappper.getExitScoreByExperimentIdandGradeId(7, 1, 1);
		System.out.println(reportRelatives.size());		
	}

	@Test
	public void testGetReportLinkExperimentByStuId() {
		//List<ReportRelative> reportRelatives=reportRelativeMappper.getReportLinkExperimentByStuId(6);
       // System.out.println(reportRelatives.size());
	}

}
