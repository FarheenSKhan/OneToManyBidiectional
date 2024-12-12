package com.biOneMany.dao;

import com.biOneMany.entity.Mobile;
import com.biOneMany.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MobileDao {

    @Autowired
    private MobileRepository mobileRepository;


    public Mobile saveMobile(Mobile mobile){
        return mobileRepository.save(mobile);
    }

    public void deleteMobile(Mobile mobile){
        mobileRepository.delete(mobile);
    }

    public Mobile findById(long id){
        Optional<Mobile> optional=mobileRepository.findById(id);
        return optional.orElse(null);
    }
    public List<Mobile> findAll(){
        return mobileRepository.findAll();
    }

    public Mobile updateMobile(Mobile mobile){
        return mobileRepository.save(mobile);
    }

    public Mobile findByName(String name){
        return mobileRepository.findByName(name);
    }


}
