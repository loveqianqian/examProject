package org.marquee.exam.service;

import java.io.Serializable;

//TODO 2-2
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;

	//TODO 2-2.1
	private int id;
	private String name;
	private String password;
	private String certifyName;
	private String phoneNumber;
	private String email;


	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCertifyName() {
		return certifyName;
	}

	public void setCertifyName(String certifyName) {
		this.certifyName = certifyName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	// TODO 2-2.2
	public User(){
		super();
	}
	public User(int id,String name,String password){
		super();
		this.id=id;
		this.name=name;
		this.password=password;
	}

	// TODO 2-2.3
	public int hashCode() {
		return id;
	}

	public boolean equals(Object obj) {
		if (obj==null) 
			return false;
		if (this==obj) 
			return true;
		if (obj instanceof User) {
			User other=(User)obj;
			return this.id==other.id;
		}
		return false;
	}

	public String toString() {
		return name;
	}

}
