package com.transience.sandbox.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.bytecode.opencsv.CSVReader;

//import com.google.common.base.Splitter;
import com.transience.sandbox.commandobjects.FileUploadCommand;
import com.transience.sandbox.commandobjects.LoginFormCommand;
import com.transience.sandbox.csvimport.BaseCSVParser;
import com.transience.sandbox.domain.Currency;
import com.transience.sandbox.domain.Expense;
import com.transience.sandbox.domain.Tag;
import com.transience.sandbox.dtos.ExpenseDTO;
import com.transience.sandbox.dtos.ExpenseGridJSONMessage;
import com.transience.sandbox.services.*;
import com.transience.sandbox.ui.AutoCompleteModel;


@Controller
@RequestMapping("/expenses")
public class ExpensesController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	IExpenseService expenseService;
	@Autowired
	ICurrencyService currencyService;
	@Autowired
	ITagService tagService;
	
	@RequestMapping(value="/showAllExpensesGrid", method=RequestMethod.GET)
	public ModelAndView showAllExpensesGrid() {
		logger.info("* * *getAllExpensesGrid method called");
		ModelAndView mav = new ModelAndView("all_expenses_grid");
		return mav;    
	}	
	
	@RequestMapping(value="ftl") 
	public String getFTL() {
		return "index";
	}
	
	
	@RequestMapping(value="getAllTagNamesJSON", method=RequestMethod.GET)
	public @ResponseBody List<AutoCompleteModel> getAllTagNamesJSON(@RequestParam("q") String term) {
		Collection<Tag> tags = tagService.findTagsWithNameLike(term);
		List<AutoCompleteModel> tagNames = new ArrayList<AutoCompleteModel>();
		for(Tag tag : tags) {
			AutoCompleteModel tagModel = new AutoCompleteModel();
			tagModel.setId(String.valueOf(tag.getId()));
			tagModel.setName(tag.getTagName());
			tagNames.add(tagModel);
		}
		
		return tagNames;
	}
	
	@RequestMapping(value="getAllExpensesJSON", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ExpenseGridJSONMessage getAllExpensesJSON() {
		logger.info("Received json request...");
		List<ExpenseDTO> allExpensesList = expenseService.getAllExpenses();
		
		ExpenseGridJSONMessage json = new ExpenseGridJSONMessage();
		json.setRows(allExpensesList);
		json.setPage("1");
		json.setRecords(String.valueOf(allExpensesList.size()));
		json.setTotal("10");
		return json;
	}
	
	
	@RequestMapping(value = "testConverters", method = RequestMethod.GET)
	public ModelAndView testConverters(@RequestParam Currency currency, @RequestParam java.util.Date date, @RequestParam List<Tag> tags) {		
		ModelAndView mav = new ModelAndView("success");
		logger.info("Attempting to convert tags, date and currency...");
		logger.info("Date is: [" + date + "]");		
		logger.info("Currency id is: " + currency.getId());
		for(Tag tag : tags) {
			logger.info("Tag obtained is: " + tag.getTagName());
		}
		//return Long.toString(currency.getId());
		return mav;    
	}	
	
	@RequestMapping(value = "showAddExpense", method = RequestMethod.GET)
	public ModelAndView showAddExpense() {
		logger.info("* * *showAddExpense method called");
		ModelAndView mav = new ModelAndView("add_expense");
		mav.addObject("expense", new Expense());
		return mav;    
	}	
	
	@RequestMapping(value = "addExpenses", method = RequestMethod.POST)
	public ModelAndView addExpense(@ModelAttribute Expense expense, HttpServletRequest request, BindingResult bindingResult) {
		logger.info("");
		expenseService.addExpense(expense);
		ModelAndView mav = new ModelAndView("success");
		return mav;
	}
	
	@RequestMapping(value = "addExpensesAjax", method = RequestMethod.POST)
	public @ResponseBody String addExpenseAjax(@ModelAttribute Expense expense, HttpServletRequest request, BindingResult bindingResult) {
		logger.info("Adding expense via ajax call...");
		Expense savedExpense = expenseService.addExpense(expense);
		//ModelAndView mav = new ModelAndView("success");
		return "Added expense, generated expenseID is: " + savedExpense.getId();
	}	
	
	@RequestMapping(value="uploadFile", method=RequestMethod.POST )
	public ModelAndView uploadExpenseFile(HttpServletRequest request, @ModelAttribute("uploadForm") FileUploadCommand uploadForm, Model map)
	{		
		logger.info("Castinig command to FileUploadCommand");
		logger.info("* * * * * * * * currencyService here is: " + currencyService);
		//FileUploadCommand fileUploadCommand = (FileUploadCommand) command;
		//let's see if there's content there
        MultipartFile file = uploadForm.getFile();
        logger.info("fileBean is: " + file);
		 
		if(file != null) {			
			List<Expense> expensesToBeAdded = new ArrayList<Expense>();
			InputStream inputStream = null;
			try {
				inputStream = file.getInputStream();
				if(inputStream == null) {
					System.out.println("*** File input stream is null");					
				} else {
					logger.info("Input stream was not null, attempting CSV parse now...");
					BaseCSVParser parser = new BaseCSVParser();
					expensesToBeAdded = parser.parse(inputStream);
					expenseService.addAllExpenses(expensesToBeAdded);							
				}
			} catch(IOException ioe) {				
				ioe.printStackTrace();
			} catch(ParseException pe) {
				logger.info("Date parsing from csv was not successful");
				pe.printStackTrace();
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
				
		ModelAndView mav = new ModelAndView("expense_upload_success");
		return mav;
	}
	
	
	
}
