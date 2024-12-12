package com.biOneMany.dao;


import com.biOneMany.entity.Sim;
import com.biOneMany.repository.SimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SimDao {

    @Autowired
    private SimRepository simRepository;

    public Sim save (Sim sim){
        return simRepository.save(sim);
    }

    public Sim findByName(String name){
        return simRepository.findByName(name);
    }

    public Sim findById(Long id){
        Optional<Sim> optional=simRepository.findById(id);
        return optional.orElse(null);
    }


}
