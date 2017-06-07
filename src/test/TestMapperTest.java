package test;

import static org.junit.Assert.*;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TeacherMapper;
import org.gzhmc.report4gzhmc.mapper.TestMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author stshen
 *
 * 2017年9月22日
 */
public class TestMapperTest {
	static TestMapper testMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		testMapper = app.getBean(TestMapper.class);
	}
	@Test
	public void testUpdateByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		org.gzhmc.report4gzhmc.model.Test test=new org.gzhmc.report4gzhmc.model.Test();
		test.setcTestName("机能实验3");
		int result=testMapper.add(test);
		System.out.println(result);
	}

	@Test
	public void testDelete() {
		int result = testMapper.delete(3);
		System.out.println("delete result :" + result);
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
		org.gzhmc.report4gzhmc.model.Test test=testMapper.getById(1);
		System.out.println(test.getcTestName());
	}

	@Test
	public void testGetAll() {
	List<org.gzhmc.report4gzhmc.model.Test> tests=testMapper.getAll();
	System.out.println(tests.get(0).getcTestName()+"\n"+tests.get(1).getcTestName());
	}

}
