package com.transience.sandbox.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.transience.sandbox.domain.Expense;

public class ExpenseItemWriter implements ItemWriter<Expense> {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static final String EXPENSE_INSERT_QUERY = "insert into expenses(amount, description, currency) values (?, ?, ?)";

	public void write(List<? extends Expense> expenses) throws Exception {
		for(Expense expense : expenses) {
			jdbcTemplate.update(EXPENSE_INSERT_QUERY, expense.getAmount(), expense.getDescription(), expense.getCurrency().getId());
		}
		
	}

}
