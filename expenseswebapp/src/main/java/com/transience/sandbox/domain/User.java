package com.transience.sandbox.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQueries
({
@NamedQuery(name="User.findByUserNameAndPassword", query="SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),	
@NamedQuery(name="User.findAllUsers", query="SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
})
 	
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_ID_GENERATOR", sequenceName="PERSONAL.GLOBALSEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_ID_GENERATOR")
	private long id;

	private String notes;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="USERNAME")
	private String username;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="USERS_X_ROLES"
		, joinColumns={
			@JoinColumn(name="USER_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		)
	private List<Role> roles;

	public User() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}