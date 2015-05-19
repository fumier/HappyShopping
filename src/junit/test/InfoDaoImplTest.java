package junit.test;

import java.util.List;

import org.junit.Test;

import cn.sict.dao.InfoDao;
import cn.sict.dao.impl.InfoDaoImpl;
import cn.sict.domain.Info;

public class InfoDaoImplTest
{
	public static void main(String[] args)
	{

	}
    @Test
	public void testFindInfoByUserID() throws Exception
	{
      InfoDao infoDao=new InfoDaoImpl("314325423");
      List<Info> infoList=infoDao.findInfoByUserID();
      if(infoList==null)return;
      for(Info info:infoList)
      {
    	  System.out.println(info.getPersonName());
    	  System.out.println(info.getInfoID());
      }
      
	}
}
