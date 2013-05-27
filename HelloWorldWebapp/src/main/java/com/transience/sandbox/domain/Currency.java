package com.transience.sandbox.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the CURRENCIES database table.
 * 
 */
@Entity
@Table(name="CURRENCIES")
@NamedQueries
({
@NamedQuery(name="Currency.findByCurrencyname", query="SELECT c FROM Currency c WHERE c.currencyName = :currencyName")	
})

public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CURRENCIES_ID_GENERATOR", sequenceName="PERSONAL.GLOBALSEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CURRENCIES_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	private long id;

	@Column(name="CURRENCY_NAME", length=50)
	private String currencyName;

	//bi-directional many-to-one association to Expense
	@OneToMany(mappedBy="currency")
	private List<Expense> expenses;

	public Currency() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public List<Expense> getExpenses() {
		return this.expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

}