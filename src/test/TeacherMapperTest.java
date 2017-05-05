package test;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TeacherMapper;
import org.gzhmc.report4gzhmc.model.Teacher;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TeacherMapperTest {

	static TeacherMapper teacherMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		teacherMapper = app.getBean(TeacherMapper.class);
	}

	@Test
	public void testAdd() {
		Teacher teacher = new Teacher();
		teacher.setcUserId(1);
		teacher.setcName("赵六");
		teacher.setcCollegeId(1);
		teacher.setcTeacherId("19880102");
		int result = teacherMapper.add(teacher);
		System.out.println("add result :" + result);
	}

	@Test
	public void testDelete() {
		int result = teacherMapper.delete(1);
		System.out.println("delete result :" + result);
	}

	@Test
	public void testUpdate() {
		Teacher teacher = teacherMapper.getById(1);
		teacher.setcName("李四");
		int result = teacherMapper.update(teacher);
		System.out.println("update result :" + result);
	}

	@Test
	public void testupdateSelective() {
		Teacher teacher = teacherMapper.getById(1);
		teacher.setcName("王五");
		int result = teacherMapper.update(teacher);
		System.out.println("update result :" + result);
	}

	@Test
	public void testGetAll() {
		List<Teacher> list = teacherMapper.getAll();
		System.out.println("list size :" + list.size() + ":" + list.get(0).getcName());
	}

}
