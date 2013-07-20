package com.transience.sandbox.serviceimplementations;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private CurrencyRepository currencyRepository;

	public Currency findByCurrencyName(String currencyName) {
		logger.info("* * * * * * * * currencyName going to be looked up is: " + currencyName); 
		return currencyRepository.findByCurrencyName(currencyName);
		
	}

}
