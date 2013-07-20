package com.transience.sandbox.domain;

import java.io.Serializable;
import javax.persistence.*;

//import com.google.common.base.Splitter;

import java.math.BigDecimal;
import java.util.Collection;
//import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the EXPENSES database table.
 * 
 */
@Entity
@Table(name="EXPENSES")
public class Expense implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="EXPENSES_ID_GENERATOR", sequenceName="PERSONAL.GLOBALSEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EXPENSES_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	private long id;

	@Column(precision=22)
	private BigDecimal amount;

	@Column(length=100)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="EXPENSE_DATE")
	private Date expenseDate;
	
	@Transient
	private String tagsString;

	//bi-directional many-to-one association to Currency
	@ManyToOne
	@JoinColumn(name="CURRENCY_ID")
	private Currency currency;

	//bi-directional many-to-one association to Vendor
	@ManyToOne
	@JoinColumn(name="VENDOR_ID")
	private Vendor vendor;

	//bi-directional many-to-many association to Tag
	@ManyToMany()
	@JoinTable(name="EXPENSES_X_TAGS",
		joinColumns= @JoinColumn(name="EXPENSE_ID", referencedColumnName="ID"), 
		inverseJoinColumns= @JoinColumn(name="TAG_ID", referencedColumnName="ID"))
	private List<Tag> tags;

	public Expense() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpenseDate() {
		return this.expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Vendor getVendor() {
		return this.vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public List<Tag> getTags() {
		return this.tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getTagsString() {
		return tagsString;
	}

	public void setTagsString(String tagsString) {
		this.tagsString = tagsString;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((expenseDate == null) ? 0 : expenseDate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Expense)) {
			return false;
		}
		Expense other = (Expense) obj;
		if (amount == null) {
			if (other.amount != null) {
				return false;
			}
		} else if (!amount.equals(other.amount)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (expenseDate == null) {
			if (other.expenseDate != null) {
				return false;
			}
		} else if (!expenseDate.equals(other.expenseDate)) {
			return false;
		}
		return true;
	}
	
}