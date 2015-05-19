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

//��web���ṩ���е�ҵ�����
public class UserServiceImpl implements UserService
{
	private UserDao userdao=new UserDaoImpl();
	private CartDao  cartDao;
	private TradeDao tradeDao;
	//��web���ṩע�����
       /* (non-Javadoc)
	 * @see cn.sict.service.impl.UserService#register(cn.sict.domain.User)
	 */
    @Override
	public void register(User user) throws Exception
       {
    	   //���жϵ�ǰҪע����û��Ƿ����
    	   boolean b=userdao.find(user.getUsername());
    	   if(b)
    	   {
    		   //����Ҫע����û��Ѵ��ڣ����web���׳�һ������ʱ�쳣������web�㴦������쳣�����û�һ���Ѻ���ʾ
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
       
       //��web���ṩ��½����
       /* (non-Javadoc)
	 * @see cn.sict.service.impl.UserService#login(java.lang.String, java.lang.String)
	 */
    @Override
	public User login(String username,String password)
       {
    	   //������ת��Ϊ����ģʽ
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
