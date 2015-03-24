package com.edutilos.service;

import java.util.List;

import com.edutilos.domain.Worker;

public interface WorkerService {
  void saveWorker(Worker w); 
  void updateWorker(Worker newWorker, Long id); 
  void deleteWorker(Long id); 
  Worker findById(Long id); 
  List<Worker> findAll(); 
}
