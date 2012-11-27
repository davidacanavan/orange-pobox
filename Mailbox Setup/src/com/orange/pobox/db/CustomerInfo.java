package com.orange.pobox.db;

import java.io.Serializable;

public class CustomerInfo implements Serializable
{
	public CustomerInfo(String firstname, String surname, String email, String homePhone, String mobilePhone, String streetAddress, 
			String city, String county, String state, String country, String zipCode)
	{
	 this.firstname = firstname;
	 this.surname = surname;
	 this.email = email;
	 this.homePhone = homePhone;
	 this.mobilePhone = mobilePhone;
	 this.streetAddress = streetAddress;
	 this.city = city;
	 this.county = county;
	 this.state = state;
	 this.country = country;
	 this.zipCode = zipCode;
	}
	
	public String getFirstName()
	{
	 return this.firstname;
	}
	
	public String getSurname()
	{
	 return this.surname;
	}
	
	public String getEmail()
	{
	 return this.email;
	}
	
	public String getHomePhone()
	{
	 return this.homePhone;
	}
	
	public String getMobilePhone()
	{
	 return this.mobilePhone;
	}
	
	public String getStreetAddress()
	{
	 return this.streetAddress;
	}
	
	public String getCity()
	{
	 return this.city;
	}
	
	public String getCounty()
	{
	 return this.county;
	}
	
	public String getState()
	{
	 return this.state;
	}
	
	public String getCountry()
	{
	 return this.country;
	}
	
	public String getZipCode()
	{
	 return this.zipCode;
	}
	
	public String getFullAddress()
	{
	 return getStreetAddress() + ", " + getCity() + ", " + getCounty() + ", " + getState() + ", " + getCountry() + ", " + getZipCode();
	}
	
 private String firstname, surname, email, homePhone, mobilePhone, streetAddress, 
 	city, county, state, country, zipCode;
 private static final long serialVersionUID = 1L;
}
