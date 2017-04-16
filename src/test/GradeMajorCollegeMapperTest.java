package test;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.GradeMajorCollegeMapper;
import org.gzhmc.report4gzhmc.model.GradeMajorCollege;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GradeMajorCollegeMapperTest {

	static GradeMajorCollegeMapper gradeMajorCollegeMapper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		gradeMajorCollegeMapper = app.getBean(GradeMajorCollegeMapper.class);
	}

	@Test
	public void testGetAll() {

		List<GradeMajorCollege> gradeMajorColleges = gradeMajorCollegeMapper.getAll();
		System.out.println(
				"getAll:" + gradeMajorColleges.size() + ":" + gradeMajorColleges.get(0).getMajor().getcMajorName());

	}

}
