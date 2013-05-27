package com.transience.sandbox.serviceimplementations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transience.sandbox.domain.Expense;
import com.transience.sandbox.services.IExpenseService;

@Service("jpaExpenseService")
@Repository
@Transactional
public class ExpenseServiceImplementation implements IExpenseService {

	@PersistenceContext
	private EntityManager em;
	
	public void addExpense(Expense expense) {
		// TODO Auto-generated method stub
		if((Long)expense.getId() == null) {
			em.persist(expense);			
		} else {
			em.merge(expense);
		}

	}

}
