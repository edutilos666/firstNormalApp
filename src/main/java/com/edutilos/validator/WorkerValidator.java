package com.edutilos.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.edutilos.domain.Worker;

public class WorkerValidator implements Validator{

	private final Logger logger = LoggerFactory.getLogger(WorkerValidator.class); 
	
	
	public boolean supports(Class<?> arg0) {
	   return Worker.class.equals(arg0); 
	}

	public void validate(Object obj, Errors errors) {
		  Worker worker = (Worker)obj; 
		  
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fname", "worker.fname.invalid", "worker fname is invalid");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lname", "worker.lname.invalid", "worker lname is invalid");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "worker.email.invalid", "worker email is invalid");
	
		  String email = worker.getEmail(); 
		  if(!email.contains("@"))
			  errors.rejectValue("email", "worker.email.invalid2", "worker email is invalid2");
	}

}
