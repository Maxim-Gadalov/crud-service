package com.mhadalau.ecosystem.domain.business.controller;

import com.mhadalau.ecosystem.domain.business.model.DomainModel;
import com.mhadalau.ecosystem.domain.business.service.DomainService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/domain")
@Api(tags = "Domain service", description = "Domain Service Endpoints")
public class DomainController {

    private DomainService domainService;

    @Autowired
    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DomainModel> findDomainById(@PathVariable("id") String id){

        DomainModel domain = domainService.findDomainById(id);

        return new ResponseEntity<>(domain, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DomainModel>> findAllDomains(){
        List<DomainModel> domains = domainService.findAllDomains();

        return new ResponseEntity<>(domains, HttpStatus.OK);
    }

    @GetMapping("/active")
    public  ResponseEntity<List<DomainModel>> findActiveDomains(){
        List<DomainModel> domains = domainService.findActiveDomains();

        return new ResponseEntity<>(domains, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DomainModel> createDomain(@RequestBody DomainModel domain){
        DomainModel savedModel = domainService.createDomainModel(domain);

        return new ResponseEntity<>(savedModel, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDomain(@PathVariable("id") String id){
        domainService.deleteDomain(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
