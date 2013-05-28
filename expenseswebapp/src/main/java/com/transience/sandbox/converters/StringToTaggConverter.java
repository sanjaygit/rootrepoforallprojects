package com.transience.sandbox.converters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.transience.sandbox.domain.Tag;
import com.transience.sandbox.services.ITagService;

public class StringToTaggConverter implements Converter<String, Tag>{
	
	@Autowired
	ITagService tagService;
	
	protected final Log logger = LogFactory.getLog(getClass());

	public Tag convert(String stringOfTagNames) {
		
		//List<Tag> tags = null;
		
		//String[] tagNames = stringOfTagNames.split(",");
		//logger.info("Looping through tagNames now...");
		//for(String tagName : tagNames) {
			//logger.info("tag name: " + tagName);
			Tag tag = tagService.findByTagName(stringOfTagNames);
			//tags.add(tag);
		//}
		
		return tag;
	}

}
