package com.transience.sandbox.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.transience.sandbox.domain.Currency;
import com.transience.sandbox.repositories.CurrencyRepository;

public class StringToCurrencyConverter implements Converter<String, Currency> {

	@Autowired CurrencyRepository currencyRepository;
	
	public Currency convert(String currencyName) {
		
		Currency currency = currencyRepository.findByCurrencyName(currencyName);
		System.out.println("Currency id is: " + currency.getId());
		return currency;
		
		
	}

}
