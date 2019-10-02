package com.univer.lab.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Provider {

    private Long providerId;

    private String providerName;

    private List<Storage> storages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(providerId, provider.providerId) &&
                Objects.equals(providerName, provider.providerName) &&
                Objects.equals(storages, provider.storages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(providerId, providerName, storages);
    }
}
