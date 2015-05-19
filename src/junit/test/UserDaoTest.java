package junit.test;

import java.util.Date;

import org.junit.Test;

import cn.sict.dao.UserDao;
import cn.sict.dao.impl.UserDaoImpl;
import cn.sict.domain.User;

public class UserDaoTest
{
	@Test
	public void testAdd()
	{
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("ddd@163.com");
		user.setId("123242");
		user.setNickname("cheng");
		user.setPassword("123");
		user.setUsername("ccc");

		UserDao dao = new UserDaoImpl();
		dao.add(user);

	}

	@Test
	public void testFind()
	{
		UserDao dao = new UserDaoImpl();
		User user = dao.find("fannie", "123");
		System.out.println(user.getUsername());
	}
	
}
