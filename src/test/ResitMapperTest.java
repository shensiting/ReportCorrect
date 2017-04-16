package test;

import static org.junit.Assert.*;

import java.util.List;

import org.gzhmc.report4gzhmc.mapper.GradeMapper;
import org.gzhmc.report4gzhmc.mapper.ResitMapper;
import org.gzhmc.report4gzhmc.model.Resit;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Administrator
 * @date 2017年2月4日
 */
public class ResitMapperTest {

	static ResitMapper resitMapper;

	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		resitMapper = app.getBean(ResitMapper.class);
	}
	@Test
	public void testDeleteByPrimaryKey() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		Resit resit=new Resit();
		resit.setcExperiment(2);
		resit.setcStudentId(13);
		resit.setcReportId(3);
		System.out.println(resitMapper.insert(resit));
	}

	@Test
	public void testInsertSelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectByPrimaryKey() {
	     List<Resit> resits=resitMapper.getAll();
	     for(Resit i:resits){
	    	 System.out.println(i.getcId()+" "+i.getcStudentId()+" "+i.getcReportId()+" "+i.getcStudentId());
	     }
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateByTwoId() {
		Resit record=new Resit();
		record.setcExperiment(3);
		record.setcStudentId(11);
		record.setcReportId(33);
		int r=resitMapper.updateByStudentIdAndExperimentId(record);
		System.out.println(r);
		
	}

}
