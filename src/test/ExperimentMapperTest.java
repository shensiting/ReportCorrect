package test;

import static org.junit.Assert.*;

import org.gzhmc.report4gzhmc.mapper.ExperimentMapper;
import org.gzhmc.report4gzhmc.model.Experiment;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author stshen
 *
 * 2017年9月25日
 */
public class ExperimentMapperTest {

	static ExperimentMapper experimentalMapper;;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		experimentalMapper = app.getBean(ExperimentMapper.class);
	}
	@Test
	public void testAdd() {
		Experiment experiment=new Experiment();
		experiment.setcExperimentEnglishName("esdgfs");
		experiment.setcExperimentName("TEST");
		experiment.setcExperimentTime("2015-6");
		experiment.setcClassify(1);
		System.out.println(experimentalMapper.add(experiment));
	}

	@Test
	public void testDelete() {
		System.out.println(experimentalMapper.delete(10));
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
