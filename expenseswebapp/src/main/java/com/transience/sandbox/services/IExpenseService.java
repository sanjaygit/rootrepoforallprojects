package com.transience.sandbox.services;
import com.transience.sandbox.domain.*;
import com.transience.sandbox.dtos.ExpenseDTO;

import java.util.*;

public interface IExpenseService {
	
	public Expense addExpense(Expense expense);
	public void addAllExpenses(List<Expense> expenses);
	public List<ExpenseDTO> getAllExpenses();

}
