package com.crossover.ecommerce.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer_order", catalog = "affablebean")
public class CustomerOrder implements java.io.Serializable {

	private static final long serialVersionUID = 4881446981614758155L;

	private Integer id;
	private Customer customer;
	private BigDecimal amount;
	private Date dateCreated;
	private int confirmationNumber;
	private Set<OrderedProduct> orderedProducts = new HashSet<OrderedProduct>(0);

	public CustomerOrder() {
	}

	public CustomerOrder(Customer customer, BigDecimal amount, Date dateCreated, int confirmationNumber) {
		this.customer = customer;
		this.amount = amount;
		this.dateCreated = dateCreated;
		this.confirmationNumber = confirmationNumber;
	}

	public CustomerOrder(Customer customer, BigDecimal amount, Date dateCreated, int confirmationNumber, Set<OrderedProduct> orderedProducts) {
		this.customer = customer;
		this.amount = amount;
		this.dateCreated = dateCreated;
		this.confirmationNumber = confirmationNumber;
		this.orderedProducts = orderedProducts;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "amount", nullable = false, precision = 6)
	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_created", nullable = false, length = 19)
	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Column(name = "confirmation_number", nullable = false)
	public int getConfirmationNumber() {
		return this.confirmationNumber;
	}

	public void setConfirmationNumber(int confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerOrder")
	public Set<OrderedProduct> getOrderedProducts() {
		return this.orderedProducts;
	}

	public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

}
