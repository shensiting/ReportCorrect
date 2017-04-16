package test;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.CollegeMapper;
import org.gzhmc.report4gzhmc.model.College;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author : gcliang
 * @Date : 2016年6月24日 单元测试示例
 */

public class CollegeMapperTest {

	static CollegeMapper collegeMapper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		collegeMapper = app.getBean(CollegeMapper.class);
	}

	@Test
	public void testAdd() {
		College c = new College();
		c.setcCollegeName("中文系学院");
		int result = collegeMapper.add(c);
		System.out.println("add result :" + result);
	}

	@Test
	public void testDelete() {
		int result = collegeMapper.delete(5);
		System.out.println("delete result :" + result);
	}

	@Test
	public void testUpdate() {
		College c = collegeMapper.getById(1);
		c.setcCollegeName("测试学院");
		int result = collegeMapper.update(c);
		System.out.println("update result :" + result);
	}

	@Test
	public void testUpdateSelective() {
		College c = collegeMapper.getById(1);
		c.setcCollegeName("测试学院Selective");
		int result = collegeMapper.updateSelective(c);
		System.out.println("update result :" + result);
	}

	@Test
	public void testGetById() {
		College c = collegeMapper.getById(1);
		System.out.println("college name:" + c.getcCollegeName());
	}

	@Test
	public void testGetAll() {
		List<College> list = collegeMapper.getAll();
		System.out.println("list size :" + list.size());
	}

}
