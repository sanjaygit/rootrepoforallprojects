package com.transience.sandbox.batch;

import java.math.BigDecimal;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.transience.sandbox.domain.Expense;

public class ExpenseFieldSetMapper implements FieldSetMapper<Expense> {

	public Expense mapFieldSet(FieldSet fieldSet) {
		if(fieldSet == null) {
			return null;
		}
		
		Expense expense = new Expense();
		expense.setAmount(new BigDecimal(fieldSet.readString("amount")));
		expense.setDescription(fieldSet.readString("description"));
		return expense;
		//expense.setCurrency(currency)
	}

}
