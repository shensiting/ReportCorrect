package test;

import org.gzhmc.common.util.StringUtils;
import org.junit.Test;

/**
 * 
 * @author Administrator
 * @date 2016年9月9日
 */
public class StringUtilTest {

	@Test
	public void isIdcardTest(){
		if(StringUtils.isIdcard("1234567X9012345678")){
			System.out.println("成功");}
		else {
			System.out.println("失败");
		}
		
	}
	
}
