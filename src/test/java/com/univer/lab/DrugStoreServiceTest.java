package com.univer.lab;

import com.univer.lab.model.Drug;
import com.univer.lab.model.Provider;
import com.univer.lab.model.Sales;
import com.univer.lab.model.Storage;
import com.univer.lab.service.stream.DrugStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DrugStoreServiceTest {

    private DrugStoreService drugStoreService;
    
    private Sales sales = new Sales();

    private List<Provider> providers = new ArrayList<>();
    
    private Provider provider = new Provider();

    @Before
    public void setUp(){
        sales = Sales.builder()
                .saleId(1L)
                .realization(2000L)
                .realizationDate(LocalDate.parse("2019-04-10"))
                .build();

        Drug drug = Drug.builder()
                .drugId(1L)
                .name("Pantenol")
                .count(20L)
                .price(120L)
                .country("Germany")
                .build();

        Storage storage = Storage.builder()
                .storageId(1L)
                .build();



        provider = Provider.builder()
                .providerId(1L)
                .providerName("C&C")
                .build();

        providers.add(provider);
        storage.setProviders(providers);

        drug.setStorage(storage);

        sales.setDrug(drug);
    }

    @Before
    public void createDrugStoreService(){
        drugStoreService = new DrugStoreService(sales);
    }

    @Test
    public void sortWorkersByPassportNumber(){
        List<Provider> sortedProvider = drugStoreService.sortProviderByName();
        List<Provider> expectedSortedProviders = new ArrayList<>();
        expectedSortedProviders.add(provider);

        Assert.assertEquals(expectedSortedProviders, sortedProvider);
    }

    @Test
    public void countWeaponTypeTest() {
        long expectedCountWorker = 1;
        long countProviders = drugStoreService.countProviderInStore();
        Assert.assertEquals(expectedCountWorker, countProviders);
    }
}
