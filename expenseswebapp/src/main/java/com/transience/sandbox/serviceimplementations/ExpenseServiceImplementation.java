package com.transience.sandbox.serviceimplementations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transience.sandbox.domain.Expense;
import com.transience.sandbox.domain.Tag;
import com.transience.sandbox.dtos.ExpenseDTO;
import com.transience.sandbox.services.IExpenseService;

@Service("jpaExpenseService")
@Repository
@Transactional
public class ExpenseServiceImplementation implements IExpenseService {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String QUERY_FINDALLEXPENSES = "select e from Expense e";

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

	public List<ExpenseDTO> getAllExpenses() {
		List<ExpenseDTO> dtoList = new ArrayList<ExpenseDTO>();
		for(Expense expense : (em.createQuery(QUERY_FINDALLEXPENSES, Expense.class).getResultList())) {
			ExpenseDTO dto = new ExpenseDTO();
			dto.setId(expense.getId());
			dto.setAmount(expense.getAmount());
			dto.setDescription(expense.getDescription());
			dto.setExpenseDate(expense.getExpenseDate());
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	


}
