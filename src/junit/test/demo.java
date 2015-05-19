package junit.test;

import java.util.Locale;

import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class demo
{
	
	public static void main(String[] args)
	{
		DateLocaleConverter dlc=new DateLocaleConverter();
        dlc.convert("2012-02-09", "yyyy-mm-dd");
        
	}

}
