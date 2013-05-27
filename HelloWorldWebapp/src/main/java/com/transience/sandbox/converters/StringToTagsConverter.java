package com.transience.sandbox.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.google.common.base.Splitter;
import com.transience.sandbox.domain.Tag;
import com.transience.sandbox.services.ITagService;

public class StringToTagsConverter implements Converter<String, List<Tag>>{
	
	@Autowired
	ITagService tagService;

	public List<Tag> convert(String stringOfTags) {
		
		List<Tag> tags = new ArrayList<Tag>();
		Iterable<String> tagsString = Splitter.on(',').split(stringOfTags);
		for(String tagName : tagsString) {
			Tag tag = tagService.findByTagName(tagName);
			tags.add(tag);
		}
		
		return tags;
	}

}
