package com.biOneMany.controller;

import com.biOneMany.entity.Mobile;
import com.biOneMany.entity.ResponseStructure;
import com.biOneMany.repository.MobileRepository;
import com.biOneMany.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobile")
public class MobileController {

    @Autowired
    private MobileService mobileService;
    @Autowired
    private MobileRepository mobileRepository;

    @PostMapping("/saveMobile")
    public ResponseEntity<ResponseStructure<Mobile>> saveMobile( @RequestBody  Mobile mobile){
        return mobileService.saveMobile(mobile);
    }

    @DeleteMapping("/deleteMobile")
    public ResponseEntity<ResponseStructure<Mobile>> deleteMobile(@RequestParam Long id){
        return mobileService.deleteMobile(id);
    }

    @PutMapping("/updateMobile")
    public ResponseEntity<ResponseStructure<Mobile>> updateMobile(@RequestBody Mobile mobile){
        return mobileService.updateMobile(mobile);
    }

    @GetMapping("/findAll")
    public ResponseEntity<ResponseStructure<List<Mobile>>> findAll(){
        return  mobileService.findAll();
    }

    @GetMapping("/findById")
    public ResponseEntity<ResponseStructure<Mobile>> findById(@RequestParam Long id){
        return mobileService.findById(id);
    }
}
