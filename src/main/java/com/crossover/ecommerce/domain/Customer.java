package com.crossover.ecommerce.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer", catalog = "affablebean")
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = -193400405034118558L;

	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String cityRegion;
	private String ccNumber;
	private Set<CustomerOrder> customerOrders = new HashSet<CustomerOrder>(0);

	public Customer() {
	}

	public Customer(String name, String email, String phone, String address, String cityRegion, String ccNumber) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.cityRegion = cityRegion;
		this.ccNumber = ccNumber;
	}

	public Customer(String name, String email, String phone, String address, String cityRegion, String ccNumber, Set<CustomerOrder> customerOrders) {
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.cityRegion = cityRegion;
		this.ccNumber = ccNumber;
		this.customerOrders = customerOrders;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email", unique = true, nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", nullable = false, length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address", nullable = false, length = 45)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "city_region", nullable = false, length = 2)
	public String getCityRegion() {
		return this.cityRegion;
	}

	public void setCityRegion(String cityRegion) {
		this.cityRegion = cityRegion;
	}

	@Column(name = "cc_number", nullable = false, length = 19)
	public String getCcNumber() {
		return this.ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<CustomerOrder> getCustomerOrders() {
		return this.customerOrders;
	}

	public void setCustomerOrders(Set<CustomerOrder> customerOrders) {
		this.customerOrders = customerOrders;
	}

}
