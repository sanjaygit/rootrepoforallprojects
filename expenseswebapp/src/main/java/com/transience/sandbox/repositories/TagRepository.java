package com.transience.sandbox.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.transience.sandbox.domain.Currency;
import com.transience.sandbox.domain.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
	public Tag findByTagName(String tagName);
	
}
