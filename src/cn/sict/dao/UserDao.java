package cn.sict.dao;

import cn.sict.domain.User;

public interface UserDao
{

	public abstract void add(User user);

	public abstract User find(String username, String password);

	//����ע����û��Ƿ������ݿ��д���
	public abstract boolean find(String username);

}