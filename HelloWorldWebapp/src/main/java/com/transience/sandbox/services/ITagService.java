package com.transience.sandbox.services;

import com.transience.sandbox.domain.Tag;

public interface ITagService {

	public Tag findByTagName(String tagName);
	
	public Tag createTag(Tag tag);
}
