package com.univer.lab.model;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drug {

    private Long drugId;

    private String name;

    private Long price;

    private String country;

    private Storage storage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return Objects.equals(drugId, drug.drugId) &&
                Objects.equals(name, drug.name) &&
                Objects.equals(price, drug.price) &&
                Objects.equals(country, drug.country) &&
                Objects.equals(storage, drug.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drugId, name, price, country, storage);
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugId=" + drugId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", country='" + country + '\'' +
                ", storage=" + storage +
                '}';
    }
}
