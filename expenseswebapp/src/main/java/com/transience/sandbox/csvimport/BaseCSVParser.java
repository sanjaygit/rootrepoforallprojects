package com.transience.sandbox.csvimport;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import au.com.bytecode.opencsv.CSVReader;

import com.transience.sandbox.domain.Currency;
import com.transience.sandbox.domain.Expense;
import com.transience.sandbox.domain.Tag;
import com.transience.sandbox.services.ICurrencyService;
import com.transience.sandbox.services.IExpenseService;
import com.transience.sandbox.services.ITagService;

public class BaseCSVParser {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private Map<String, Integer> fieldMapper = new TreeMap<String, Integer>();
	
	@Autowired
	IExpenseService expenseService;
	
	ICurrencyService currencyServices;
	
	@Autowired
	ITagService tagService;
	
	public void setCurrencyServices(ICurrencyService currencyService) {
		this.currencyServices = currencyService; 
	}

	public List<Expense> parse(InputStream inputStream) throws IOException, ParseException {
		
		List<Expense> mappedExpenses = null;
		

		CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream), ',', '"', 1);
		String[] nextLine = null;
		
		// Read the header row and create the mapper
		String[] headerRow = csvReader.readNext();
		for(String cell : headerRow) {
			logger.info("* * * * * * * * Header row cell is: " + cell);
		}
		fieldMapper = createMapper(headerRow);			
		logger.info("* * * * * * * * Mapper created");
		
		// Now continue to read the file 
		while((nextLine = csvReader.readNext()) != null) {
			Expense expense = new Expense();
			logger.info("* * * * * * * * Amount: " + nextLine[fieldMapper.get("amount")]);
			expense.setAmount(new BigDecimal(nextLine[fieldMapper.get("amount")]));
			
			logger.info("* * * * * * * * description: " + nextLine[fieldMapper.get("description")]);
			expense.setDescription(nextLine[fieldMapper.get("description")]);						
			
			logger.info("* * * * * * * * currency: [" + nextLine[fieldMapper.get("currency")] + "]"  + "currencyService is: " + currencyServices);
			Currency currency = currencyServices.findByCurrencyName(nextLine[fieldMapper.get("currency")]);
			// TODO Handle error, in case currency not found or not provided in batch file. 
			logger.info("Currency identified by ID in csv file is: " + currency.getCurrencyName());
			expense.setCurrency(currency);
			
			logger.info("* * * * * * * * Amount: " + nextLine[fieldMapper.get("tags")]);
			String[] tagsString = nextLine[fieldMapper.get("tags")].split(";");
			List<Tag> tags = getTagsFromTagNames(tagsString);
			expense.setTags(tags );
			
			expense.setExpenseDate(new SimpleDateFormat("dd/MM/yyyy").parse(nextLine[fieldMapper.get("description")]));			
			
			if(!mappedExpenses.contains(expense)) { // To avoid adding duplicates when taking an export file next time
				mappedExpenses.add(expense);
			} else {
				logger.info("The expense object: " + expense.getId() + " already exists, not going to add it again");
			}
		}
			
		
		return mappedExpenses;
	}
	
	/**
	 * 
	 * @param memberNames An array containing a list of the expense member variables
	 * @return
	 */
	private Map<String, Integer> createMapper(String[] memberNames) {
		Map<String, Integer> rowMapper = new TreeMap<String, Integer>();
		int idx = 0;
		for(String memberName : memberNames) {
			logger.info("* * * * * * * *Adding: " + memberName + ":" + Integer.valueOf(idx));
			rowMapper.put(memberName, Integer.valueOf(idx));
			idx++;
		}
		return rowMapper;
	}
	
	/**
	 * 
	 * @param tagNames
	 * @return
	 */
	private List<Tag> getTagsFromTagNames(String[] tagNames) {
		List<Tag> tags = new ArrayList<Tag>();
		for(String tagName : tagNames) {
			logger.info("Searching for Tag with name: >" + tagName + "<");
			if(tagName != null && tagName.equals("")) {								
				tagName = "Default";
				logger.info("setting tag name to: " + tagName);
			}
			Tag tag = tagService.findByTagName(tagName);
			if(tag == null) {
				tag = new Tag();
				tag.setTagName(tagName);								
				tagService.createTag(tag);
			}
			tags.add(tag);
		}	
		return tags;
	}

} // End class
