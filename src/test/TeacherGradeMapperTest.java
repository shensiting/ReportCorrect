package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.gzhmc.common.util.StringUtils;
import org.gzhmc.report4gzhmc.mapper.TeacherGradeMapper;
import org.gzhmc.report4gzhmc.model.TeacherGrade;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author stshen
 *
 * 2017年9月25日
 */
public class TeacherGradeMapperTest {
	static TeacherGradeMapper teacherGradeMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		teacherGradeMapper = app.getBean(TeacherGradeMapper.class);
	}
	@Test
	public void testAdd() {
		TeacherGrade teacherGrade=new TeacherGrade();
		teacherGrade.setcCreateTime(new Date());
		teacherGrade.setcGradeId(2);
		teacherGrade.setcStatus(0);
		teacherGrade.setcTeacherId(13218);
	    
	    System.out.println(teacherGradeMapper.add(teacherGrade));
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
		List<TeacherGrade> teacherGrades=teacherGradeMapper.getAll();
		System.out.println(teacherGrades.get(0).getcTeacherId());
	}

}
