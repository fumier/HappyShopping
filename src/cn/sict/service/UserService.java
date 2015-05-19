package cn.sict.service;

import cn.sict.domain.User;
import cn.sict.exception.UserExistException;

public interface UserService
{

	//对web层提供注册服务
	void register(User user) throws UserExistException, Exception;

	//对web层提供登陆服务
	User login(String username, String password);
	Boolean UserExist(String username);

}