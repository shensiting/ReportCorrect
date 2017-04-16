package test;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TeacherlinkExperimentMapper;
import org.gzhmc.report4gzhmc.model.TeacherlinkExperiment;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TeacherlinkExperimentMapperTest {

	static TeacherlinkExperimentMapper teacherlinkExperimentMapper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		teacherlinkExperimentMapper = app.getBean(TeacherlinkExperimentMapper.class);
	}

	@Test
	public void testgetAll() {
		List<TeacherlinkExperiment> teacherlinkCourses = teacherlinkExperimentMapper.getAll();
		System.out.println(teacherlinkCourses.size());
	}

	@Test
	public void testgetById() {
		TeacherlinkExperiment teacherlinkExperiment = teacherlinkExperimentMapper.getById(11);
		System.out.println(teacherlinkExperiment.getExperimentalTest().getcExperimentName());
	}

}
