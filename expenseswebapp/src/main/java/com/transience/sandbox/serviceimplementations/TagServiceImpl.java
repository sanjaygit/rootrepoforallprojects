package com.transience.sandbox.serviceimplementations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transience.sandbox.domain.Tag;
import com.transience.sandbox.domain.User;
import com.transience.sandbox.repositories.TagRepository;
import com.transience.sandbox.services.ITagService;

@Service("tagService")
@Transactional
@Repository
public class TagServiceImpl implements ITagService {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private static final String QUERY_FINDTAGBYTAGNAME = "select t from Tag t where t.tagName = :tagName";
	
	//@Autowired
	//private TagRepository tagRepository;
	
	@PersistenceContext
	private EntityManager em;
	

	public Tag findByTagName(String tagName) {
		//return tagRepository.findByTagName(tagName);
		Tag tag = null;
		try {
			tag = em.createQuery(QUERY_FINDTAGBYTAGNAME, Tag.class) .setParameter("tagName", tagName).getSingleResult();
			em.flush();
			logger.info("The EXCEPTION SHOULD THROWN NOW");
		} catch(Exception e) {
			logger.info("Exception thrown");
			e.printStackTrace();
		}
		return tag;
	}
	
	public Tag createTag(Tag tag) {
		em.persist(tag);			
		return tag;
	}
	
	

}
