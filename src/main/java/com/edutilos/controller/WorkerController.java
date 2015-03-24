package com.edutilos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edutilos.domain.Worker;
import com.edutilos.service.WorkerService;
import com.edutilos.validator.WorkerValidator;

@Controller
@RequestMapping("/worker")
public class WorkerController {
  private final Logger logger = LoggerFactory.getLogger(WorkerController.class); 
  
  @Autowired
  private WorkerService workerService; 
  
  @Autowired
  private WorkerValidator workerValidator; 
  
  
  private static final String DEFAULT_WORKER_FNAME="default fname"; 
  private static final String DEFAULT_WORKER_LNAME= "default lname"; 
  private static final String DEFAULT_WORKER_EMAIL="default@gmail.com"; 
  private static final Long DEFAULT_WORKER_ID=1L; 
  
  @RequestMapping("/")
  public String workerForm(ModelMap model) {
	  logger.info("adding empty worker to modelMap"); 
	  model.addAttribute("worker", new Worker()); 
	  logger.info("adding all workers from db to modelMap");
	  model.addAttribute("listWorkers", workerService.findAll()); 
	  return "workerForm"; 
  }
	
  
  @RequestMapping(value="/add", method=RequestMethod.POST)
  public String workerAdd(@ModelAttribute("worker")@Validated Worker worker, 
		  BindingResult result, 
		  ModelMap model) {
	  logger.info("validating worker"); 
	  
	  workerValidator.validate(worker, result);
	  
	  if(result.hasErrors()|| result.hasFieldErrors()) {
		  logger.error("validation failed");
		  model.addAttribute("listWorkers", workerService.findAll()); 
		  return "workerForm"; 
	  }
	 
	  logger.info("validation succeeded, saving new worker into db"); 
	  workerService.saveWorker(worker);
	  return "redirect:/worker/"; 
  }
  
  
  @RequestMapping(value="/delete", method=RequestMethod.POST )
  public String workerDelete(@ModelAttribute("worker")@Validated Worker worker, 
		  BindingResult result, 
		  ModelMap model) {
	   logger.info("validating worker");
	   logger.info("firstly setting default values");
/*	   worker.setFname(DEFAULT_WORKER_FNAME);
	   worker.setLname(DEFAULT_WORKER_LNAME);
	   worker.setEmail(DEFAULT_WORKER_EMAIL);*/
	   
	    Long id = worker.getId(); 
	    if(id==null || id<1) {
	    	  result.rejectValue("id", "worker.id.invalid", "worker id is invalid");
	    	 logger.error("validation failed"); 
	    	 model.addAttribute("listWorkers", workerService.findAll()); 
	    	 return "workerForm"; 
	    }
	   
	    logger.info("validation succeeded");
	    logger.info("deleting worker with id "+ id);
	     workerService.deleteWorker(id);
	    return "redirect:/worker/"; 
  }
  
  @RequestMapping(value="/list", method =RequestMethod.POST)
  public String listWorkers(ModelMap model) {
	   logger.info("finding all workers and adding them to model");
	   model.addAttribute("listWorkers", workerService.findAll()); 
	   return "listWorkers"; 
  }
  
}
