package junit.test;

import java.util.Date;

import org.junit.Test;

import cn.sict.domain.User;
import cn.sict.exception.UserExistException;
import cn.sict.service.UserService;
import cn.sict.service.impl.UserServiceImpl;

public class ServiceTest
{
	@Test
	public void testRegister() throws Exception
	{
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("ddd@163.com");
		user.setId("123242");
		user.setNickname("cheng");
		user.setPassword("123");
		user.setUsername("ccc");
		UserService service = new UserServiceImpl();
		try
		{
			service.register(user);
			System.out.println("注册成功");
		} catch (UserExistException e)
		{
			System.out.println("用户已存在");
		}
	}

	@Test
	public void testLogin()
	{
		UserService service = new UserServiceImpl();
		User user = service.login("aaa", "123");
		System.out.println(user);
	}
}
