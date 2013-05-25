package com.transience.sandbox.serviceimplementations;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transience.sandbox.domain.Role;
import com.transience.sandbox.domain.User;
import com.transience.sandbox.services.IUserService;

@Service("userService")
@Transactional
@Repository
public class UserServiceImpl implements IUserService {
	
	private static final String QUERY_FINDBYUSERNAMEANDPASSWORD = "select u from User u where u.username = :username and u.password = :password";
	
	//private Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	public UserServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Transactional(readOnly=true)
	public User authenticate(String userName, String password) {
		List<User> usersList = em.createNamedQuery("User.findAllUsers", User.class)
									.setParameter("username", userName)
									.setParameter("password", password)
									.getResultList();
		User firstUserFromList = usersList.get(0);
		return firstUserFromList;
	}

	public void assignRolesToUser(Set<Role> roles) {
		// TODO Auto-generated method stub

	}

}
