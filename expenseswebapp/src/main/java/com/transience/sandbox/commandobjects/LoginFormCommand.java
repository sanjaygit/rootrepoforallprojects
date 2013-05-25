package com.transience.sandbox.commandobjects;

//import javax.validation.constraints.NotNull;

//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotBlank;

public class LoginFormCommand {
	
	//@NotNull(message = "Username cannot be left blank")	 
	//@NotBlank(message = "Username cannot be left blank")
	//@Email(message = "Username has to take the format of an email")
	private String j_username;
	
	//@NotNull(message = "Password cannot be left blank")
	//@NotBlank(message = "Password cannot be left blank")
	private String j_password;

	public String getJ_username() {
		return j_username;
	}

	public void setJ_username(String j_username) {
		this.j_username = j_username;
	}

	public String getJ_password() {
		return j_password;
	}

	public void setJ_password(String j_password) {
		this.j_password = j_password;
	}
	
	public LoginFormCommand(String j_username, String j_password) {
		super();
		this.j_username = j_username;
		this.j_password = j_password;
	}

	public LoginFormCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
