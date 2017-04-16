package test;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.StudentMapper;
import org.gzhmc.report4gzhmc.model.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Student单元测试用例
 * 
 * @author Administrator
 *
 */

public class StudentMapperTest {

	static StudentMapper studentMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		studentMapper = app.getBean(StudentMapper.class);
	}

	@Test
	public void testAdd() {
		Student student = new Student();
		student.setcUserId(3);
		student.setcName("张三");
		student.setcStudentNumber("1234567890");
		student.setcGradeId(2);
		student.setcPicturePath("c:/text/jpl.word");
		student.setcIDNumber("3");

		int result = studentMapper.add(student);
		System.out.println("add result :" + result);
	}

	@Test
	public void testDelete() {
		int result = studentMapper.delete(1);
		System.out.println("delete result :" + result);
	}

	@Test
	public void testUpdate() {
		Student student = studentMapper.getById(1);
		student.setcName("李四");
		int result = studentMapper.update(student);
		System.out.println("update result :" + result);
	}

	@Test
	public void testupdateSelective() {
		Student student = studentMapper.getById(1);
		student.setcName("李琦");
		int result = studentMapper.update(student);
		System.out.println("update result :" + result);
	}

	@Test
	public void testGetAll() {
		List<Student> list = studentMapper.getAll();
		System.out.println("list size :" + list.size() + ":" + list.get(0).getcName());
	}

}
