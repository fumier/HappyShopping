package cn.sict.web.formbean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
public class RegisterForm
{
	private String username;
	private String password;
	private String password1;
	private String email;
	private String birthday;
	private String nickname;
	private Map<String, String> errors = new HashMap<String, String>();

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword1()
	{
		return password1;
	}

	public void setPassword1(String password1)
	{
		this.password1 = password1;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public Map<String, String> getErrors()
	{
		return errors;
	}

	public void setErrors(Map<String, String> errors)
	{
		this.errors = errors;
	}

	// �ֶμ�Ȼ�������������У��������������
	// ����Ҫ����У�����
	// �û�������Ϊ�գ�����Ҫ��3-8λ��ĸ
	// ���벻��Ϊ�գ�����Ҫ��3-8λ��ĸ
	// ȷ�����벻��Ϊ�գ���Ҫ������ֵ��ͬ
	// �������䲻��Ϊ�գ�����Ҫ��һ���Ϸ�������
	// ���տ���Ϊ�գ���Ϊ��ʱ��������һ������
	// �ǳƲ�Ϊ�գ�����ҪΪ����
	public boolean validate()
	{
		boolean isOK = true;
		if (this.username == null || this.username.trim().equals(""))
		{
			isOK = false;
			errors.put("username", "�û�������Ϊ��");
		} else
		{
			if (!this.username.matches("[A-Za-z]{3,8}"))
			{
				isOK = false;
				errors.put("username", "�û���������3��8λ");
			}
		}

		if (this.password == null || this.password.trim().equals(""))
		{
			isOK = false;
			errors.put("password", "���벻��Ϊ��");
		} else
		{
			if (!this.password.matches("\\d{3,8}"))
			{// ������3-8λ����
				isOK = false;
				errors.put("password", "���������3-8λ����");
			}
		}
		if (this.password1 ==null || this.password1.trim().equals(""))
		{
			isOK = false;
			errors.put("password1", "ȷ�����벻��Ϊ��");
		} else
		{
			if (!(password.equals(password1)))
			{
				isOK = false;
				errors.put("password1", "ȷ�������������������һ��");
			}
		}

		if (this.email == null || this.email.trim().equals(""))
		{
			isOK = false;
			errors.put("email", "���䲻��Ϊ��");
		} else
		{
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+"))
			{
				isOK = false;
				errors.put("email", "�����ʽ���벻�Ϸ�");
			}
		}
		if(this.birthday!=null && !this.birthday.trim().equals(""))
		{
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			Date d=null;
			try{
				d=df.parse(this.birthday);
			}catch(Exception e)
			{
				isOK=false;
				errors.put("birthday", "���ڸ�ʽ����ȷ");
			}
			String s1=df.format(d);
			if(!this.birthday.equals(s1))
			{
				isOK=false;
				errors.put("birthday", "���ڸ�ʽ����ȷ");
			}
			
		}else{
			isOK=false;
			errors.put("birthday", "���ղ���Ϊ��");
		}
		if (this.nickname == null || this.nickname.trim().equals(""))
		{
			isOK = false;
			errors.put("nickname", "�ǳƲ���Ϊ��");
		}
//		} else
//		{
//			if(!this.nickname.matches("[\\u4e00-\\u9fa5]+"))
//			{
//			isOK=false;
//			errors.put("nickname", "�ǳƱ���Ϊ����");
//			}
//		}
		return isOK;
	}

}
