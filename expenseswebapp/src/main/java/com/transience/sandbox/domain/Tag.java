package com.transience.sandbox.domain;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the TAGS database table.
 * 
 */
@Entity
@Table(name="TAGS")
@NamedQueries
({
//@NamedQuery(name="Tag.findByTagName", query="SELECT distinct t FROM Tag t WHERE t.tagName = :tagName")	
})
public class Tag implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TAGS_ID_GENERATOR", sequenceName="PERSONAL.GLOBALSEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAGS_ID_GENERATOR")
	@Column(unique=true, nullable=false, precision=22)
	private long id;

	@Column(name="TAG_NAME", length=25)
	private String tagName;

	//bi-directional many-to-many association to Expense
	@ManyToMany(mappedBy="tags")
	private List<Expense> expenses;

	public Tag() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public List<Expense> getExpenses() {
		return this.expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

}