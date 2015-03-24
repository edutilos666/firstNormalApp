package com.edutilos.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edutilos.domain.Person;

@Controller
public class PersonController {
  /* private static Map<Integer, String> personDB = new HashMap<>(); */
	private static List<Person> personDB = new ArrayList<Person>(); 
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class); 
    
    
     public PersonController() {
    	 logger.info("adding some persons...");
    	  Person p1, p2, p3; 
    	  p1 = new Person(20, "foo", "bar", "foobar@yahoo.com"); 
    	  p2 = new Person(30, "edu", "tilos", "edutilos@gmail.com"); 
    	  p3 = new Person(40, "pako", "ceko", "pakoceko@inbox.ru"); 
    	  personDB.addAll(Arrays.asList(p1, p2, p3)); 
     }
    
    @RequestMapping("/")
    public String personForm(ModelMap model) {
    	 logger.info("adding empty person to model");
    	 model.addAttribute("person", new Person()); 
    	 logger.info("adding list persons ...");
    	  model.addAttribute("listPersons", personDB); 
    	  return "personForm"; 
    }
    
    @RequestMapping(value="/addPerson", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person")Person person,
    		BindingResult result, 
    		ModelMap model) {
    	logger.info("validating person...");
    	String fname = person.getFname(), 
    			lname = person.getLname(), 
    			email = person.getEmail(); 
    	int age = person.getAge(); 
    	
    	boolean invalid =false; 
    	
    	if(fname==null || fname.trim().length()<2) {
    		result.rejectValue("fname", "person.fname.emptyOrInvalid", "person fname must be at least 2 chars");
    	  invalid =true; 
    	}
    	
    	if(lname==null || lname.trim().length()<2) {
    		result.rejectValue("lname", "person.lname.emptyOrInvalid", "person lname must be at least 2 chars");
    		invalid =true;
    	}
    	
    	if(email==null || !email.trim().contains("@")) {
    		result.rejectValue("email", "person.email.emptyOrInvalid", "person email must be valid");
    		invalid =true;
    	}
    	
    	if((Integer)age==null || age<20) {
    		result.rejectValue("age", "person.age.invalid", "person age must be>=20");
    		invalid =true;
    	}
    	
    	 if(invalid) {
    		 model.addAttribute("listPersons", personDB); 
    		 return "personForm"; 
    	 }
    	
    	logger.info("adding new person");
    	personDB.add(person); 
    	
    	return "redirect:/";  
    }
    
    @RequestMapping("/listPersons")
    public String listPersons(ModelMap model) {
    	 logger.info("adding persons to modelMap"); 
    	 model.addAttribute("listPersons", personDB); 
    	 return "listPersons"; 
    }
}
