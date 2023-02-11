package com.main.entity;




import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.Id;





@Entity
public class Customer {
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String adharid;
	@Column(nullable = false)
	private String cname;
	@Column(nullable = false)
	private String cadress;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String phNo;
	private int rid;

	

	public Integer getRid() {
		return rid;
	}



	public void setRid(int rid) {
		this.rid = rid;
	}



	public String
	getAdharid() {
		return adharid;
	}
	
	
	
	public void setAdharid(String adharid) {
		this.adharid = adharid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCadress() {
		return cadress;
	}
	public void setCadress(String cadress) {
		this.cadress = cadress;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	@Override
	public String toString() {

		return this.adharid+this.age+this.cadress+this.cname+this.phNo;
	}
	
	
 

}
