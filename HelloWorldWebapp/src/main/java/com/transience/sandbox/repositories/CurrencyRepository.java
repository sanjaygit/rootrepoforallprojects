package com.transience.sandbox.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.transience.sandbox.domain.Currency;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
	
	public Currency findByCurrencyName(String currencyName);

}
