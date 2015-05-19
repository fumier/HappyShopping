package cn.sict.domain;

public class Info
{
	private String infoID;
	private String personName;
	private Address address;
	private String telephone;
	private boolean defaultAddress;
	public boolean isDefaultAddress()
	{
		return defaultAddress;
	}
	public void setDefaultAddress(boolean defaultAddress)
	{
		this.defaultAddress = defaultAddress;
	}

	public String getInfoID()
	{
		return infoID;
	}

	public void setInfoID(String infoID)
	{
		this.infoID = infoID;
	}
	public String getPersonName()
	{
		return personName;
	}

	public void setPersonName(String personName)
	{
		this.personName = personName;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	public String getTelephone()
	{
		return telephone;
	}

	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

}
