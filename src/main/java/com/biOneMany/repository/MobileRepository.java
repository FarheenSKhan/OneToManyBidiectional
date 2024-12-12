package com.biOneMany.repository;

import com.biOneMany.entity.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobileRepository extends JpaRepository<Mobile, Long> {
    public Mobile findByName(String name);
}