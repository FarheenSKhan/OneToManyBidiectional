package com.biOneMany.service;

import com.biOneMany.dao.MobileDao;
import com.biOneMany.dao.SimDao;
import com.biOneMany.entity.Mobile;
import com.biOneMany.entity.ResponseStructure;
import com.biOneMany.entity.Sim;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileService {

    @Autowired
    private MobileDao mobileDao;

    @Autowired
    private SimDao simDao;

    public ResponseEntity<ResponseStructure<Mobile>> saveMobile(Mobile mobile) {
        ResponseStructure<Mobile> responseStructure = new ResponseStructure<>();
        Mobile mobile1 = mobileDao.findByName(mobile.getName());
        if (mobile1 != null) {
            responseStructure.setStatus(HttpStatus.CONFLICT.value());
            responseStructure.setMessage("Mobile already exists");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.CONFLICT);
        } else {
            if (mobile.getSims() != null && !mobile.getSims().isEmpty()) {
                List<Sim> sims = new ArrayList<>();
                for (Sim sim : mobile.getSims()) {
                    if (sim.getId() != null) {
                        Sim sim1 = simDao.findById(sim.getId());
                        if (sim1 != null) {
                            sims.add(sim1);
                        }
                    } else {
                        sim.setMobile(mobile);
                        sims.add(sim);
                    }
                }
                mobile.setSims(sims);
            }
            mobileDao.saveMobile(mobile);
            responseStructure.setStatus(HttpStatus.CREATED.value());
            responseStructure.setMessage("Mobile and Sims saved successfully");
            responseStructure.setData(mobile);
            return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);

        }
    }

    public ResponseEntity<ResponseStructure<Mobile>> deleteMobile(Long id) {
        ResponseStructure<Mobile> responseStructure = new ResponseStructure<>();
        Mobile mobile = mobileDao.findById(id);
        if (mobile == null) {
            responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
            responseStructure.setMessage("Mobile not found");
            responseStructure.setData(null);
            return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
        } else {
            mobileDao.deleteMobile(mobile);
            responseStructure.setStatus(HttpStatus.OK.value());
            responseStructure.setMessage("Mobile with Sim deletes successfully");
            responseStructure.setData(mobile);
            return new ResponseEntity<>(responseStructure, HttpStatus.OK);

        }
    }


    public ResponseEntity<ResponseStructure<Mobile>> updateMobile(Mobile mobile) {
        ResponseStructure<Mobile> responseStructure = new ResponseStructure<>();
        Mobile mobile1 = mobileDao.findById(mobile.getId());
        if (mobile1 != null) {
            mobile1.setName(mobile.getName());
            mobile1.setBrand(mobile.getBrand());
            mobile1.setPrice(mobile.getPrice());

            List<Sim> updateSims = new ArrayList<>();
            for (Sim sim : mobile.getSims()) {
                if (sim.getId() == null) {
                    sim.setMobile(mobile1);
                    sim = simDao.save(sim);
                } else {

                    Sim existingSim = simDao.findById(sim.getId());
                    if (existingSim != null) {
                        existingSim.setName(sim.getName());
                        existingSim.setMobile(mobile1);
                        simDao.save(existingSim);
                        updateSims.add(existingSim);
                    }
                }
            }
                mobile1.setSims(updateSims);
                mobile1 = mobileDao.saveMobile(mobile1);

                responseStructure.setStatus(HttpStatus.OK.value());
                responseStructure.setMessage("Mobile updated successfully");
                responseStructure.setData(mobile1);
                return new ResponseEntity<>(responseStructure, HttpStatus.OK);
            } else{
                responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                responseStructure.setMessage("Mobile not found");
                responseStructure.setData(null);
                return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
            }
        }

        public ResponseEntity<ResponseStructure<List<Mobile>>> findAll () {
            ResponseStructure<List<Mobile>> responseStructure = new ResponseStructure<>();
            List<Mobile> mobile = mobileDao.findAll();
            if (mobile == null) {
                responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                responseStructure.setMessage("Mobile not found");
                responseStructure.setData(null);
                return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
            } else {
                responseStructure.setStatus(HttpStatus.OK.value());
                responseStructure.setMessage("All Mobiles found");
                responseStructure.setData(mobile);
                return new ResponseEntity<>(responseStructure, HttpStatus.OK);
            }
        }

        public ResponseEntity<ResponseStructure<Mobile>> findById (Long id){
            ResponseStructure<Mobile> responseStructure = new ResponseStructure<>();
            Mobile mobile = mobileDao.findById(id);
            if (mobile == null) {
                responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
                responseStructure.setMessage("Mobile not found");
                responseStructure.setData(null);
                return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
            } else {
                responseStructure.setStatus(HttpStatus.OK.value());
                responseStructure.setMessage("Found mobile by id");
                responseStructure.setData(mobile);
                return new ResponseEntity<>(responseStructure, HttpStatus.OK);
            }
        }
    }
