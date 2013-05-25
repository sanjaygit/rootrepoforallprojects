package com.transience.sandbox.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("expensesUpload")
public class ExpensesBatchUploadController {
	
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("expenseImportJob")
	private Job expenseImportJob;
	
	@RequestMapping(value = "/expenseImport")
	public @ResponseBody String expenseImport() {
		try {
			Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
			parameters.put("date", new JobParameter(new Date()));
			jobLauncher.run(expenseImportJob, new JobParameters(parameters));
			return "true";
		} catch (JobInstanceAlreadyCompleteException ex) {
			return "Job completed already";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
