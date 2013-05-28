package com.transience.sandbox.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import com.transience.sandbox.domain.Currency;
import com.transience.sandbox.domain.Expense;
import com.transience.sandbox.domain.Tag;
import com.transience.sandbox.services.*;


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
	

	@RequestMapping(value="getAllTagNamesJSON", method=RequestMethod.GET)
	public @ResponseBody List<String> getAllTagNamesJSON(@RequestParam("term") String term) {
		Collection<Tag> tags = tagService.findTagsWithNameLike(term);
		List<String> tagNames = new ArrayList<String>();
		for(Tag tag : tags) {
			tagNames.add(tag.getTagName());
		}
		return tagNames;
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
	
	@RequestMapping(value="uploadFile", method=RequestMethod.POST )
	public ModelAndView uploadExpenseFile(HttpServletRequest request, @ModelAttribute("uploadForm") FileUploadCommand uploadForm, Model map)
	{		
		logger.info("Castinig command to FileUploadCommand");
		//FileUploadCommand fileUploadCommand = (FileUploadCommand) command;
		//let's see if there's content there
        MultipartFile file = uploadForm.getFile();
        logger.info("fileBean is: " + file);
        //if (file == null) {
             // hmm, that's strange, the user did not upload anything
        //}
		
//		logger.info("Trying to parse from request...");
//		System.out.println("Trying to parse from request...");
//		Collection<Part> parts = null;
//		try {
//			parts = request.getParts();
//			for (Part part : parts) {
//				logger.info("Name:");
//				logger.info(part.getName());
//				logger.info("Header: ");
//				for (String headerName : part.getHeaderNames()) {
//					logger.info(headerName);
//					logger.info(part.getHeader(headerName));
//				}
//				logger.info("Size: ");
//				logger.info(part.getSize());
//				part.write(part.getName() + "-down");
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		 
		if(file != null) {
			byte[] fileContent = null;
			try {
				InputStream inputStream = file.getInputStream();
				if(inputStream == null) {
					System.out.println("*** File input stream is null");					
				} else {
					logger.info("Input stream was not null, attempting CSV parse now...");
					// Parsing using csvReader now...
					CSVReader reader = new CSVReader(new InputStreamReader(inputStream), ',', '"', 1);
					String[] nextLine;
					
					while((nextLine = reader.readNext()) != null) {		
						Expense expense = new Expense();
						expense.setAmount(new BigDecimal(nextLine[0]));
						expense.setDescription(nextLine[1]);						
						Currency currency = currencyService.findByCurrencyName(nextLine[2]);
						logger.info("Currency identified by ID in csv file is: " + currency.getCurrencyName());
						expense.setCurrency(currency);
						expense.setTagsString(nextLine[3]);
						//Now take the tagString, tokenize it and the create list of tags
						// @TODO refactor into criteria queries
						List<Tag> tags = new ArrayList<Tag>();
						logger.info("About to split the string: " + nextLine[3]);
						//Iterable<String> tagsString = Splitter.on(';').split(nextLine[3]);
						String[] tagsString = nextLine[3].split(";");
						logger.info("String splitting has not failed...");
						for(String tagName : tagsString) {
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
						expense.setTags(tags);			
						expense.setExpenseDate(new SimpleDateFormat("dd/MM/yyyy").parse(nextLine[4]));
						expenseService.addExpense(expense);
						logger.info("Expense item created with id: " + expense.getId());
					}
					//fileContent = IOUtils.toByteArray(inputStream);
				}
			} catch(IOException ioe) {
				ioe.printStackTrace();
			} catch(ParseException pe) {
				logger.info("Date parsing from csv was not successful");
				pe.printStackTrace();
			} finally {
				
			}
		}
				
		ModelAndView mav = new ModelAndView("expense_upload_success");
		return mav;
	}
	
	
	
}
