package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.gzhmc.report4gzhmc.mapper.TestMapper;
import org.gzhmc.report4gzhmc.mapper.TopicThemeMapper;
import org.gzhmc.report4gzhmc.model.TopicTheme;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author stshen
 *
 * 2017年10月24日
 */
public class TopicThemeMapperTest {

	static TopicThemeMapper topicThemeMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		topicThemeMapper = app.getBean(TopicThemeMapper.class);
	}
	@Test
	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		TopicTheme topicTheme=new TopicTheme();
		topicTheme.setcLaunchId(18);
		topicTheme.setcContent("测试专用马甲");
		topicTheme.setcExperimentId(5);
		topicTheme.setcCreateTime(new Date());
		topicTheme.setcTitle("测试测试");
		System.out.println(topicThemeMapper.insert(topicTheme));
		
	}

	@Test
	public void testGetAll() {
        int themes=topicThemeMapper.getCountMes(18);
        System.out.println(themes);
	}


	@Test
	public void testSelectByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByPrimaryKey() {
		List<TopicTheme> aList=topicThemeMapper.getPageAllByMes("第三",0,20);
				System.out.println(aList.size());
	}

}
