package com.transience.sandbox.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ROLES" database table.
 * 
 */
@Entity
@Table(name="\"ROLES\"")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLES_ID_GENERATOR", sequenceName="PERSONAL.GLOBALSEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLES_ID_GENERATOR")
	private long id;

	@Column(name="ROLE_NAME")
	private String roleName;

	@Column(name="ROLE_NOTES")
	private String roleNotes;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles")
	private List<User> users;

	public Role() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNotes() {
		return this.roleNotes;
	}

	public void setRoleNotes(String roleNotes) {
		this.roleNotes = roleNotes;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}