package test;

import static org.junit.Assert.fail;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.ExperimentalTestMapper;
import org.gzhmc.report4gzhmc.model.ExperimentalTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExperimentalTestMapperTest {

	
	static ExperimentalTestMapper experimentalTestMapper;;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		experimentalTestMapper = app.getBean(ExperimentalTestMapper.class);
	}
	@Test
	public void testAdd() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateSelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		List<ExperimentalTest> experimentalTests=experimentalTestMapper.getAll();
		System.out.println(experimentalTests.size());
	}

}
