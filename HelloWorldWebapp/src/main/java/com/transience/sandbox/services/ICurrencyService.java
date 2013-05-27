package com.transience.sandbox.services;

import com.transience.sandbox.domain.Currency;

public interface ICurrencyService {
	
	public Currency findByCurrencyName(String currencyName);

}
