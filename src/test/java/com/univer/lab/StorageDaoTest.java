package com.univer.lab;

import com.univer.lab.dao.StorageDao;
import com.univer.lab.model.Provider;
import com.univer.lab.model.Storage;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StorageDaoTest {

    private StorageDao storageDao;

    private Storage storage;

    private List<Provider> providers;

    private Provider provider;

    @Before
    public void before(){
        storageDao = new StorageDao();

        provider = new Provider();
        provider.setProviderId(1L);
        provider.setProviderName("TestName");

        providers = new ArrayList<>();
        providers.add(provider);

        storage = new Storage();
        storage.setStorageNumber(24L);

        provider.setStorage(storage);
    }

    @Test
    public void insertTest(){
        storageDao.save(storage);
    }
}
