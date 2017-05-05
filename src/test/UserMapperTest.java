package test;

import java.util.Date;
import java.util.List;

import org.gzhmc.report4gzhmc.mapper.UserMapper;
import org.gzhmc.report4gzhmc.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {
	static UserMapper userMapper;
	static {
		ApplicationContext app = new ClassPathXmlApplicationContext("config/applicationContext.xml");
		userMapper = app.getBean(UserMapper.class);
	}
    @Test
    public void testgetUsername(){
    	User user = userMapper.getByUserName("1988001");
    	if("1988001".equals(user.getcUserName()))
    	System.out.println(user.getcPassword());
    	
    	System.out.println(user.getcPassword());
    	
    }
	
	@Test
	public void testAdd() {
		User user = new User();
		Date date = new Date();
		user.setcCreateTime(date);
		user.setcId(3);
		user.setcPassword("122345");
		user.setcRole(2);
		user.setcUserName("root");
		int result = userMapper.add(user);
		System.out.println("add result :" + result);

	}

	@Test
	public void testDelete() {
		int result = userMapper.delete(1);
		System.out.println("delete result :" + result);
	}

	@Test
	public void testUpdate() {
		User user = userMapper.getById(1);
		user.setcPassword("333333");
		int result = userMapper.update(user);
		System.out.println("update result :" + result);
	}

	@Test
	public void testupdateSelective() {
		User user = userMapper.getById(1);
		user.setcRole(2);
		int result = userMapper.update(user);
		System.out.println("update result :" + result);
	}

	@Test
	public void testGetAll() {
		List<User> list = userMapper.getAll();
		System.out.println("list size :" + list.size() + ":" + list.get(0).getcUserName());
	}

}
