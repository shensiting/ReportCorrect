package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.gzhmc.report4gzhmc.mapper.CollegeMapper;
import org.gzhmc.report4gzhmc.mapper.TopicResponseLinkMapper;
import org.gzhmc.report4gzhmc.model.TopicResponseLink;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author stshen
 *
 * 2017年10月30日
 */
public class TopicResponseLinkMapperTest {
	static TopicResponseLinkMapper topicResponseLinkMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		topicResponseLinkMapper = app.getBean(TopicResponseLinkMapper.class);
	}
	@Test
	public void testGetByUserIdAndExamIdAndDate() {
		List<TopicResponseLink> topicResponseLinks=topicResponseLinkMapper.getByUserIdAndExamIdAndDate(5, "2014153003", null, new Date());
		System.out.println(topicResponseLinks.size());
	}

}
