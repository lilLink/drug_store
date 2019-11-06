package com.univer.lab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@XmlRootElement(name = "Storage")
@XmlAccessorType(XmlAccessType.FIELD)
public class Storage {

    @XmlElement
    @NotNull
    private Long storageId;

    /*@XmlElement
    @XmlElementWrapper
    private List<CountDrug> countDrugs;*/

    @XmlElement
    @XmlElementWrapper
    @NotNull(message = "Provider must be not null")
    private List<Provider> providers;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(storageId, storage.storageId) &&
                Objects.equals(countDrugs, storage.countDrugs) &&
                Objects.equals(providers, storage.providers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storageId, countDrugs, providers);
    }
*/
    @Override
    public String toString() {
        return "Storage{" +
                "storageId=" + storageId +
               //", countDrugs=" + countDrugs +
                ", providers=" + providers +
                '}';
    }
}
