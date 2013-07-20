package com.transience.sandbox.temp;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.type.OrderedMapType;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;

import com.transience.sandbox.domain.Expense;

public class ReflectionExperiment {
	
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * @param args
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, IOException {
		// TODO Auto-generated method stub
		
		Expense expense = new Expense();
		ReflectionExperiment exp = new ReflectionExperiment();
		try {
			exp.printAllFields(expense);
			//Assert.assertEquals(expense.getDescription(), "Test description");
			System.out.println(expense.getDescription().equals("Test description1"));
			
			
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private Map<String, Integer> createMapper(String[] memberNames) {
		Map<String, Long> rowMapper = new TreeMap<String, Long>();
		int idx = 0;
		for(String memberName : memberNames) {
			rowMapper.put(memberName, Long.valueOf(idx++));
		}
		return null;
	}
	
	private String[] getHeaderLine(String fileName) throws Exception {
		InputStream inputStream = new FileInputStream(new File("D:/CBA.csv"));			 
		CSVReader reader = new CSVReader(new InputStreamReader(inputStream), ',', '"', 1);
		String[] firstLine = reader.readNext();
		reader.close();
		inputStream.close();
		return firstLine;		
	}
	
	private Collection<?> getMappedEntities(String fileName, Map rowMap) throws Exception {
		InputStream inputStream = new FileInputStream(new File("D:/CBA.csv"));			 
		CSVReader reader = new CSVReader(new InputStreamReader(inputStream), ',', '"', 1);
		String[] firstLine = reader.readNext(); // First line read
		String[] nextLine = null;
		while((nextLine = reader.readNext()) != null) {
			Object obj = new Object();
			for(PropertyDescriptor pd : (Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors())) {
				System.out.println("PD: " + pd.getDisplayName());
				pd.getWriteMethod().invoke(obj, rowMap.get(pd.getDisplayName()));
			}
			
		}
		
		return null;
	}
	
	
	public void printAllFields(Object obj) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for(Field field : obj.getClass().getDeclaredFields()) {
			logger.info("Member: " + field.getType().getCanonicalName());
			System.out.println("Member: " + field.getName() + " Type: " + field.getType().getCanonicalName());			
		}
		
		for(PropertyDescriptor pd : (Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors())) {
			System.out.println("PD: " + pd.getDisplayName());
			if(pd.getDisplayName() != null && pd.getDisplayName().equals("description")) {
				System.out.println("Setting description");
				pd.getWriteMethod().invoke(obj, "Test description");
			}			
		}
	}
	
	private Collection<String> getFieldNames(Object obj) throws IllegalArgumentException, IntrospectionException, IllegalAccessException, InvocationTargetException {
		Collection<String> memberNames = new ArrayList<String>();
		for(PropertyDescriptor pd : (Introspector.getBeanInfo(obj.getClass()).getPropertyDescriptors())) {
			System.out.println("PD: " + pd.getDisplayName());
			memberNames.add(pd.getDisplayName());
		}		
		return memberNames;		
	}

}
