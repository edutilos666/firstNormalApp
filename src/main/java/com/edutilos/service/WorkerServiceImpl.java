package com.edutilos.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutilos.domain.Worker;
import com.edutilos.repository.WorkerRepository;

@Transactional
@Service(value="workerService")
public class WorkerServiceImpl implements WorkerService{

	@Autowired
	private WorkerRepository repo; 
	
	private final Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class); 
	
	
	public void saveWorker(Worker w) {
	  logger.info("saving worker");
	   repo.save(w); 
	}

	public void updateWorker(Worker newWorker, Long id) {
		 logger.info("updating worker");
		 
		 if(repo.findOne(id)!=null) {
			 String fname = newWorker.getFname(), 
					 lname = newWorker.getLname(), 
					 email = newWorker.getEmail(); 
			 
			 repo.updateWorker(fname, lname, email, id);
			 logger.info("updated worker successfully");
		 } else {
			 logger.error("couldnot find worker with id " + id);
		 }

	}

	public void deleteWorker(Long id) {
		 logger.info("deleting worker");
		  Worker w = repo.findOne(id); 
		  if(w!=null) {
			repo.delete(id);
			 logger.info("deleted worker successfully");	 
		  } else { 
			  logger.error("could not find worker with id " + id);
		  }
	
	}

	public Worker findById(Long id) {
	   logger.info("finding worker by id " + id);
	   return repo.findOne(id); 
	}

	public List<Worker> findAll() {
	   logger.info("finding all workers...");
	   List<Worker> list = new ArrayList<Worker>(); 
	   for(Worker w: repo.findAll())
		   list.add(w); 
		   return list; 
	}

}
