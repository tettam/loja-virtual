package com.dev.backend.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.entities.State;
import com.dev.backend.repository.StateRepository;

@Service
public class StateService {
  
  @Autowired
  private StateRepository repository;

  public List<State> findAll(){
    return repository.findAll();
  }

  public State insert(State state){
    state.setCreatDate(Instant.now());
    return repository.saveAndFlush(state);
  }

  public State update(State state){
    state.setUpdateDate(Instant.now());
    return repository.saveAndFlush(state);
  }

  public void delete(Long id){
    repository.deleteById(id);
  }


}
