package com.transience.sandbox.serviceimplementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transience.sandbox.domain.Expense;
import com.transience.sandbox.services.IExpenseService;

@Service("jpaExpenseService")
@Repository
@Transactional
public class ExpenseServiceImplementation implements IExpenseService {
	protected final Log logger = LogFactory.getLog(getClass());

	@PersistenceContext
	private EntityManager em;
	
	public Expense addExpense(Expense expense) {
		
		return em.merge(expense);
		/*
		// TODO Auto-generated method stub
		if((Long)expense.getId() == null) {
			em.persist(expense);			
		} else {
			em.merge(expense);
		}
		*/

	}

	public void addAllExpenses(List<Expense> expenses) {
		// TODO Auto-generated method stub
		int ctr = 0;
		for(Expense expense : expenses) {
			ctr++;
			em.persist(expense);
			if(ctr > 50) {
				em.flush();
				em.clear();
				ctr = 0;
			}
		}
		logger.info("Finished batchupdate");
	}

}
