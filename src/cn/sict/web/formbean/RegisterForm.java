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

	// 字段既然属于这个表单，那校验就属于这个方法
	// 首先要定义校验规则
	// 用户名不能为空，并且要有3-8位字母
	// 密码不能为空，并且要有3-8位字母
	// 确认密码不能为空，且要和密码值相同
	// 电子邮箱不能为空，并且要是一个合法的邮箱
	// 生日可以为空，不为空时，必须是一个日期
	// 昵称不为空，并且要为汉字
	public boolean validate()
	{
		boolean isOK = true;
		if (this.username == null || this.username.trim().equals(""))
		{
			isOK = false;
			errors.put("username", "用户名不能为空");
		} else
		{
			if (!this.username.matches("[A-Za-z]{3,8}"))
			{
				isOK = false;
				errors.put("username", "用户名必须是3到8位");
			}
		}

		if (this.password == null || this.password.trim().equals(""))
		{
			isOK = false;
			errors.put("password", "密码不能为空");
		} else
		{
			if (!this.password.matches("\\d{3,8}"))
			{// 密码是3-8位数字
				isOK = false;
				errors.put("password", "密码必须是3-8位数字");
			}
		}
		if (this.password1 ==null || this.password1.trim().equals(""))
		{
			isOK = false;
			errors.put("password1", "确认密码不能为空");
		} else
		{
			if (!(password.equals(password1)))
			{
				isOK = false;
				errors.put("password1", "确认密码必须与密码输入一致");
			}
		}

		if (this.email == null || this.email.trim().equals(""))
		{
			isOK = false;
			errors.put("email", "邮箱不能为空");
		} else
		{
			if (!this.email.matches("\\w+@\\w+(\\.\\w+)+"))
			{
				isOK = false;
				errors.put("email", "邮箱格式输入不合法");
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
				errors.put("birthday", "日期格式不正确");
			}
			String s1=df.format(d);
			if(!this.birthday.equals(s1))
			{
				isOK=false;
				errors.put("birthday", "日期格式不正确");
			}
			
		}else{
			isOK=false;
			errors.put("birthday", "生日不能为空");
		}
		if (this.nickname == null || this.nickname.trim().equals(""))
		{
			isOK = false;
			errors.put("nickname", "昵称不能为空");
		}
//		} else
//		{
//			if(!this.nickname.matches("[\\u4e00-\\u9fa5]+"))
//			{
//			isOK=false;
//			errors.put("nickname", "昵称必须为汉字");
//			}
//		}
		return isOK;
	}

}
