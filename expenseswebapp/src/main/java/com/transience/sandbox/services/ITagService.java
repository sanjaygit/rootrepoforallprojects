package com.transience.sandbox.services;

import java.util.Collection;
import java.util.List;

import com.transience.sandbox.domain.Tag;

public interface ITagService {

	public Tag findByTagName(String tagName);
	
	public Tag findById(long id);
	
	public Collection<Tag> findTagsWithNameLike(String namePattern);
	
	public Tag createTag(Tag tag);
	
	public List<Tag> findAll();
}
