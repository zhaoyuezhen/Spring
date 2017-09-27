package com.sts.spring.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DataTables {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String customer_sn;
	private String city;
	private String address;
	private String contact;
	private String contact_phone;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCustomer_sn() {
		return customer_sn;
	}
	public void setCustomer_sn(String customer_sn) {
		this.customer_sn = customer_sn;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	
	@Override
	public String toString() {
		return "DataTables [id=" + id + ", name=" + name + ", customer_sn=" + customer_sn + ", city=" + city
				+ ", address=" + address + ", contact=" + contact + ", contact_phone=" + contact_phone + "]";
	}
	
	
}
