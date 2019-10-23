package com.univer.lab.model;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Storage {

    private Long storageId;

    private List<Drug> drugs;

    private List<CountDrug> countDrugs;

    private List<Provider> providers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(storageId, storage.storageId) &&
                Objects.equals(drugs, storage.drugs) &&
                Objects.equals(countDrugs, storage.countDrugs) &&
                Objects.equals(providers, storage.providers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storageId, drugs, countDrugs, providers);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "storageId=" + storageId +
                ", drugs=" + drugs +
                ", countDrugs=" + countDrugs +
                ", providers=" + providers +
                '}';
    }
}
