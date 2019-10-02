package com.univer.lab.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Storage {

    private Long storageId;

    private List<String> productName;

    private Long amount;

    private List<Long> productPrice;

    private List<Provider> providers;

    public Storage(Long storageId, List<String> productName, Long amount, List<Long> productPrice, List<Provider> providers) {
        this.storageId = storageId;
        this.productName = productName;
        this.amount = amount;
        this.productPrice = productPrice;
        this.providers = providers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(storageId, storage.storageId) &&
                Objects.equals(productName, storage.productName) &&
                Objects.equals(amount, storage.amount) &&
                Objects.equals(productPrice, storage.productPrice) &&
                Objects.equals(providers, storage.providers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storageId, productName, amount, productPrice, providers);
    }
}
