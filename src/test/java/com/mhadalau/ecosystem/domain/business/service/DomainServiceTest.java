package com.mhadalau.ecosystem.domain.business.service;

import com.mhadalau.ecosystem.domain.business.model.DomainModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DomainServiceTest {

    @Autowired
    private DomainService domainService;


    @Before
    public void setUp(){
        domainService.findAllDomains().stream().forEach(doc -> domainService.deleteDomain(doc.getId()));

        DomainModel domain0 = new DomainModel();
        domain0.setDomainName("Test1");
        domain0.setIsActive(Boolean.TRUE);

        DomainModel domain1 = new DomainModel();
        domain1.setDomainName("Test2");
        domain1.setIsActive(Boolean.FALSE);

        DomainModel domain2 = new DomainModel();
        domain2.setDomainName("Test3");
        domain2.setIsActive(Boolean.TRUE);

        domainService.createDomainModel(domain0);
        domainService.createDomainModel(domain1);
        domainService.createDomainModel(domain2);
    }

    @After
    public void tearDawn(){
        domainService.findAllDomains().stream().forEach(doc -> domainService.deleteDomain(doc.getId()));
    }

    @Test
    public void findAllTest(){
        List<DomainModel> allDocs = domainService.findAllDomains();

        Integer expectedSize = 3;

        Assert.assertThat("FindAll works incorrectly",allDocs.size(), is(expectedSize));
    }

    @Test
    public void findActiveTest(){
        List<DomainModel> allDocs = domainService.findActiveDomains();

        Integer expectedSize = 2;

        Assert.assertThat("Find active domains works incorrectly",allDocs.size(), is(expectedSize));
    }

    @Test
    public void createDomainTest(){
        DomainModel domain = new DomainModel();
        domain.setDomainName("Oracle");
        domain.setIsActive(Boolean.TRUE);

        DomainModel savedDomainModel = domainService.createDomainModel(domain);

        String docId = savedDomainModel.getId();
        domain.setId(docId);

        Assert.assertThat("Create domain works incorrectly", domain.equals(savedDomainModel), is(Boolean.TRUE));
    }

    @Test
    public void findOneTest(){
        DomainModel domain = new DomainModel();
        domain.setDomainName("Netflix");
        domain.setIsActive(Boolean.FALSE);

        DomainModel savedModel = domainService.createDomainModel(domain);

        String savedId = savedModel.getId();

        DomainModel domain2 = domainService.findDomainById(savedId);

        Assert.assertThat("FindById works incorrectly", savedModel.equals(domain2), is(Boolean.TRUE));
    }

    @Test
    public void deleteTest(){
        DomainModel domain = new DomainModel();
        domain.setDomainName("Google");
        domain.setIsActive(Boolean.TRUE);

        DomainModel savedModel = domainService.createDomainModel(domain);

        String savedId = savedModel.getId();

        domainService.deleteDomain(savedId);

        DomainModel domain2 = domainService.findDomainById(savedId);
        DomainModel expectedResult = null;

        Assert.assertThat("Delete method works incorrectly", domain2, is(expectedResult));
    }
}
