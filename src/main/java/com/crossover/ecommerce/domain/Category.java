package com.crossover.ecommerce.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "category", catalog = "affablebean")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1374174219635966716L;

	private Byte id;
	private String name;
	private Set<Product> products = new HashSet<Product>(0);

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public Category(String name, Set<Product> products) {
		this.name = name;
		this.products = products;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Byte getId() {
		return this.id;
	}

	public void setId(Byte id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Product> getProducts() {
		return this.products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
