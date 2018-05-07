package com.mhadalau.ecosystem.domain.business.dao;

import com.mhadalau.ecosystem.domain.business.model.DomainModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomainRepository extends MongoRepository<DomainModel, String> {

    @Override
    DomainModel findOne(String id);

    @Override
    List<DomainModel> findAll();

    @Override
    DomainModel save(DomainModel domainModel);

    @Override
    boolean exists(String id);

    @Override
    void delete(String id);

    List<DomainModel> findByIsActiveTrue();
}
