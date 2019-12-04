package com.univer.lab.service.stream;

import com.univer.lab.model.Provider;
import com.univer.lab.model.Sales;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DrugStoreService {

    private Sales sales;

    public DrugStoreService(Sales sales){
        this.sales = sales;
    }

    public long countProviderInStore(){
        return sales.getDrug().getStorage().getProviders().stream().count();
    }

    public List<Provider> sortProviderByName(){
        List<Provider> sortProviders = new ArrayList<>();
        sortProviders.stream()
                .sorted(Comparator.comparing(Provider::getProviderName, Comparator.nullsLast(Comparator.reverseOrder())));
        sortProviders.addAll(sales.getDrug().getStorage().getProviders());
        return sortProviders;
    }
}
