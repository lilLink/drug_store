package com.univer.lab;

import com.univer.lab.dao.DrugDao;
import com.univer.lab.model.Drug;
import com.univer.lab.model.Provider;
import com.univer.lab.model.Storage;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DrugDaoTest {

    private DrugDao drugDao;

    private Drug drug;

    private Drug drugForUpdate;

    private Storage storage;

    private List<Provider> providers;

    private Provider provider;

    @Before
    public void before(){
        drugDao = new DrugDao();

        storage = new Storage();
        storage.setStorageId(1L);

        provider = new Provider();
        provider.setProviderId(1L);
        provider.setProviderName("TestName");
        provider.setStorage(storage);

        providers = new ArrayList<>();
        providers.add(provider);


        drug = new Drug();
        drug.setCount(20L);
        drug.setPrice(40L);
        drug.setCountry("Germany");
        drug.setName("Test");
        drug.setStorage(storage);

        drugForUpdate = new Drug();
        drugForUpdate.setDrugId(6L);
        drugForUpdate.setCount(20L);
        drugForUpdate.setPrice(60L);
        drugForUpdate.setCountry("Germany");
        drugForUpdate.setName("TestName");
        drugForUpdate.setStorage(storage);

    }

    @Test
    public void insertTest(){
        drugDao.save(drug);
    }

    @Test
    public void updateTest(){
        drugDao.save(drugForUpdate);
    }

    @Test
    public void findByIdTest(){
        drugDao.findById(6L);
    }

    @Test
    public void findAllTest(){
        drugDao.findAll();
    }

    @Test
    public void deleteTest(){
        drugDao.deleteById(5L);
    }
}
