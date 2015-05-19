package cn.sict.service;

import cn.sict.domain.User;
import cn.sict.exception.UserExistException;

public interface UserService
{

	//��web���ṩע�����
	void register(User user) throws UserExistException, Exception;

	//��web���ṩ��½����
	User login(String username, String password);
	Boolean UserExist(String username);

}