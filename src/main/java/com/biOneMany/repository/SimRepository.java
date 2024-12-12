package com.biOneMany.repository;

import com.biOneMany.entity.Sim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimRepository extends JpaRepository<Sim, Long> {

    public Sim findByName(String name);


}