package test;
/**
 * 
 * @author stshen
 * @date 2017年2月14日
 */

import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4jTest {
	private static Logger logger = Logger.getLogger(Log4jTest.class.getName());
	@Test
    public void MethodXXX(){
        logger.error("错误测试测试测试");;
        logger.warn("警告警告");
      
    }
}
