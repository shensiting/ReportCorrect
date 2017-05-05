package test;

import org.gzhmc.report4gzhmc.mapper.MajorMapper;
import org.gzhmc.report4gzhmc.model.Major;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MajorMapperTest {

	static MajorMapper majorMapper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		majorMapper = app.getBean(MajorMapper.class);
	}

	@Test
	public void testUpdateSelective() {
		Major c = majorMapper.getById(1);
		c.setcMajorName("测试学院Selective");
		int result = majorMapper.updateSelective(c);
		System.out.println("update result :" + result);
	}

	@Test
	public void testUpdate() {
		Major c = majorMapper.getById(1);
		c.setcMajorName("测试学院Selective");
		int result = majorMapper.update(c);
		System.out.println("update result :" + result);
	}

	@Test
	public void testDelete() {
		int result = majorMapper.delete(5);
		System.out.println("delete result :" + result);
	}

}
