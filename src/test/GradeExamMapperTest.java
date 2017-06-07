package test;

import static org.junit.Assert.*;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.GradeExamMapper;
import org.gzhmc.report4gzhmc.mapper.GradeMapper;
import org.gzhmc.report4gzhmc.model.GradeExam;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author stshen
 *
 * 2017年9月29日
 */
public class GradeExamMapperTest {
	static  GradeExamMapper gradeExamMapper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		gradeExamMapper = app.getBean(GradeExamMapper.class);
	}
	
	
	@Test
	public void test() {
		List<GradeExam> gradeExams2=gradeExamMapper.getByTeaGradeId(1);
		System.out.println(gradeExams2.get(0).getcStatus());
	}

}
