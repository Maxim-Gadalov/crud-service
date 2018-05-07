package com.mhadalau.ecosystem.domain.business.service;

import com.mhadalau.ecosystem.domain.business.dao.DomainRepository;
import com.mhadalau.ecosystem.domain.business.model.DomainModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainService {

    private static final Logger LOG = LoggerFactory.getLogger(DomainService.class);

    private DomainRepository dao;

    @Autowired
    public DomainService(DomainRepository dao) {
        this.dao = dao;
    }

    public DomainModel createDomainModel(DomainModel domain){
        return dao.save(domain);
    }

    public List<DomainModel> findAllDomains(){
        return dao.findAll();
    }

    public List<DomainModel> findActiveDomains(){
        return dao.findByIsActiveTrue();
    }

    public Boolean isDomainExsist(String id){
        return dao.exists(id);
    }

    public DomainModel findDomainById(String id){
        return dao.findOne(id);
    }

    public void deleteDomain(String id){
        dao.delete(id);
    }


}
