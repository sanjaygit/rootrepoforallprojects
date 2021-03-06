package com.transience.sandbox.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.transience.sandbox.domain.Currency;
import com.transience.sandbox.repositories.CurrencyRepository;

public class StringToDateConverter implements Converter<String, Date> {

	protected final Log logger = LogFactory.getLog(getClass());
	
	public Date convert(String dateString) {
		
		logger.info("***** In date converter *****");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date dateConvertedFromString = null;
		try {
			dateConvertedFromString = simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dateConvertedFromString;
		
		
	}

}
