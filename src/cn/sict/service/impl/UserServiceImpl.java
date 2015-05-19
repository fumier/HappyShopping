package cn.sict.service.impl;

import cn.sict.dao.CartDao;
import cn.sict.dao.TradeDao;
import cn.sict.dao.UserDao;
import cn.sict.dao.impl.CartDaoImpl;
import cn.sict.dao.impl.TradeDaoImpl;
import cn.sict.dao.impl.UserDaoImpl;
import cn.sict.domain.User;
import cn.sict.exception.UserExistException;
import cn.sict.service.UserService;
import cn.sict.utils.ServiceUtils;

//对web层提供所有的业务服务
public class UserServiceImpl implements UserService
{
	private UserDao userdao=new UserDaoImpl();
	private CartDao  cartDao;
	private TradeDao tradeDao;
	//对web层提供注册服务
       /* (non-Javadoc)
	 * @see cn.sict.service.impl.UserService#register(cn.sict.domain.User)
	 */
    @Override
	public void register(User user) throws Exception
       {
    	   //先判断当前要注册的用户是否存在
    	   boolean b=userdao.find(user.getUsername());
    	   if(b)
    	   {
    		   //发现要注册的用户已存在，则给web层抛出一个编译时异常，提醒web层处理这个异常，给用户一个友好提示
    		 throw new UserExistException();  
    	   }else{
    		   user.setPassword(ServiceUtils.md5(user.getPassword()));
    		   userdao.add(user);
    		   cartDao=new CartDaoImpl(user.getId());
    		   cartDao.createCartByUserID();
    		   tradeDao=new TradeDaoImpl(user.getId());
    		   tradeDao.createTradeByUserID();
    	   }
       }
       
       //对web层提供登陆服务
       /* (non-Javadoc)
	 * @see cn.sict.service.impl.UserService#login(java.lang.String, java.lang.String)
	 */
    @Override
	public User login(String username,String password)
       {
    	   //先密码转换为正常模式
    	   password=ServiceUtils.md5(password);
    	   return userdao.find(username, password);
       }

	@Override
	public Boolean UserExist(String username)
	{
		// TODO Auto-generated method stub
		boolean userExist=userdao.find(username);
		return userExist;
	}
}
