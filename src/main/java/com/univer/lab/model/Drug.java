package com.univer.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

    private Long drugId;

    private String name;

    private Long price;

    private String productCountry;

    private Storage storage;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Objects.equals(drugId, drug.drugId) &&
                Objects.equals(name, drug.name) &&
                Objects.equals(price, drug.price) &&
                Objects.equals(productCountry, drug.productCountry) &&
                Objects.equals(storage, drug.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drugId, name, price, productCountry, storage);
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugId=" + drugId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", productCountry='" + productCountry + '\'' +
                ", storage=" + storage +
                '}';
    }
}
