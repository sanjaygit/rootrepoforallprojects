package com.transience.sandbox.services;
import com.transience.sandbox.domain.*;
import java.util.*;

public interface IUserService {
	
	public User authenticate(String userName, String password);
	
	public void assignRolesToUser(Set<Role> roles);

}
