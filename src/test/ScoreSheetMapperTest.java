package test;

import static org.junit.Assert.fail;

import java.util.Date;

import org.gzhmc.report4gzhmc.mapper.ScoreSheetMapper;
import org.gzhmc.report4gzhmc.model.ScoreSheet;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ScoreSheetMapperTest {
	static ScoreSheetMapper scoreSheetMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		scoreSheetMapper = app.getBean(ScoreSheetMapper.class);
	}
	@Test
	public void testAdd() {
		ScoreSheet scoreSheet=new ScoreSheet();
		scoreSheet.setcComment("很好，表现良好");
		scoreSheet.setcConclution(2.3);
		scoreSheet.setcCreateTime(new Date());
		scoreSheet.setcExperiment(2.4);
		scoreSheet.setcId(423);
       int r=scoreSheetMapper.add(scoreSheet);
       System.out.println(r);
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
		fail("Not yet implemented");
	}

}
