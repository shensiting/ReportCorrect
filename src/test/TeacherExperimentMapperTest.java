package test;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TeacherExperimentalMapper;
import org.gzhmc.report4gzhmc.model.TeacherExperimental;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TeacherExperimentMapperTest {

	static TeacherExperimentalMapper teacherExperimentalMapper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		teacherExperimentalMapper = app.getBean(TeacherExperimentalMapper.class);
	}

	@Test
	public void testAdd() {
		TeacherExperimental teacherExperimental = new TeacherExperimental();
		teacherExperimental.setcExperimentalTestId(1);
		teacherExperimental.setcId(11);
		teacherExperimental.setcTeacherId(1);
		int result = teacherExperimentalMapper.add(teacherExperimental);
		System.out.println("add :" + result);
	}

	@Test
	public void testDelete() {
		int result = teacherExperimentalMapper.delete(4);
		System.out.println("testDelete:" + result);
	}

	@Test
	public void testUpdate() {
		TeacherExperimental teacherExperimental = teacherExperimentalMapper.getById(1);
		teacherExperimental.setcExperimentalTestId(12);
		int result = teacherExperimentalMapper.update(teacherExperimental);
		System.out.println("testUpdate:" + result);
	}

	@Test
	public void testUpdateSelective() {
		TeacherExperimental teacherExperimental = teacherExperimentalMapper.getById(1);
		teacherExperimental.setcExperimentalTestId(10);
		int result = teacherExperimentalMapper.updateSelective(teacherExperimental);
		System.out.println("testUpdateSelective:" + result);
	}

	@Test
	public void testGetById() {
		TeacherExperimental teacherExperimental = teacherExperimentalMapper.getById(15);
		System.out.println(teacherExperimental.getcExperimentalTestId());
	}
	
	@Test
	public void testGetByTeacherId() {
		List<TeacherExperimental> teacherExperimental = teacherExperimentalMapper.getByTeacherId(15);
		System.out.println(teacherExperimental.size());
	}

	@Test
	public void testGetAll() {
		List<TeacherExperimental> teacherExperimentals = teacherExperimentalMapper.getAll();
		System.out.println(teacherExperimentals.size());
	}

}
