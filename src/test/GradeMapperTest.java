package test;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.GradeMapper;
import org.gzhmc.report4gzhmc.model.Grade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GradeMapperTest {

	static GradeMapper gradeMapper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		gradeMapper = app.getBean(GradeMapper.class);
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAdd() {
		Grade g = new Grade();
		g.setcClass("互动媒体");
		g.setcCollegeId(1);
		g.setcMajorId(1);
		g.setcYearClass("一年级");
		int result = gradeMapper.add(g);
		System.out.println("add result :" + result);
	}

	@Test
	public void testDelete() {
		int result = gradeMapper.delete(1);
		System.out.println("delete result :" + result);
	}

	@Test
	public void testUpdate() {
		Grade g = gradeMapper.getById(2);
		g.setcClass("影视特速班");
		g.setcCollegeId(1);
		g.setcMajorId(1);
		g.setcYearClass("一年级");
		int result = gradeMapper.update(g);
		System.out.println("update result :" + result);
	}

	@Test
	public void testUpdateSelective() {
		Grade g = gradeMapper.getById(2);
		g.setcClass("影视特速班");

		int result = gradeMapper.update(g);
		System.out.println("update result :" + result);
	}

	@Test
	public void testGetById() {
		Grade g = gradeMapper.getById(2);
		System.out.println("college name:" + g.getcClass());
	}

	@Test
	public void testGetAll() {
		List<Grade> list = gradeMapper.getAll();
		System.out.println("list size :" + list.size());
	}

}
