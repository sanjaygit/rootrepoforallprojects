package com.transience.sandbox.dtos;

import java.util.List;

import org.springframework.stereotype.Component;

import com.transience.sandbox.domain.Expense;


public class ExpenseGridJSONMessage {

	private String page;
	private String total;
	private String records;
	
	private List<ExpenseDTO> rows;
	
	public ExpenseGridJSONMessage() {
		
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the records
	 */
	public String getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(String records) {
		this.records = records;
	}

	/**
	 * @return the rows
	 */
	public List<ExpenseDTO> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<ExpenseDTO> rows) {
		this.rows = rows;
	}
	

}
