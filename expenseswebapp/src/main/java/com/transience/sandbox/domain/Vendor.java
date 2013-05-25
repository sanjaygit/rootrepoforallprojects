package com.transience.sandbox.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the VENDORS database table.
 * 
 */
@Entity
@Table(name="VENDORS")
public class Vendor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="VENDORS_ID_GENERATOR", sequenceName="PERSONAL.GLOBALSEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VENDORS_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	private long id;

	@Column(name="VENDOR_CITY", length=25)
	private String vendorCity;

	@Column(name="VENDOR_NAME", length=25)
	private String vendorName;

	//bi-directional many-to-one association to Expense
	@OneToMany(mappedBy="vendor")
	private List<Expense> expenses;

	public Vendor() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVendorCity() {
		return this.vendorCity;
	}

	public void setVendorCity(String vendorCity) {
		this.vendorCity = vendorCity;
	}

	public String getVendorName() {
		return this.vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public List<Expense> getExpenses() {
		return this.expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

}