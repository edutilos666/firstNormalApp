package com.edutilos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edutilos.domain.Worker;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    
	@Query("update Worker w set w.fname =?1, w.lname =?2, w.email=?3 where w.id=?4")
	void updateWorker(String fname, String lname, String email, Long id); 
}
