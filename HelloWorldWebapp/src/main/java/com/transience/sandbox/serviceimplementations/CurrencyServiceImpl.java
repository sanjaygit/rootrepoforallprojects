package com.transience.sandbox.serviceimplementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transience.sandbox.domain.Currency;
import com.transience.sandbox.repositories.CurrencyRepository;
import com.transience.sandbox.services.ICurrencyService;

@Service("currencyService")
@Transactional
@Repository
public class CurrencyServiceImpl implements ICurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;

	public Currency findByCurrencyName(String currencyName) {
		return currencyRepository.findByCurrencyName(currencyName);
		
	}

}
