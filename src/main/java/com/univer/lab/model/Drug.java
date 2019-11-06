package com.univer.lab.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "Drug")
@XmlAccessorType(XmlAccessType.FIELD)
public class Drug {

    @XmlElement
    @NotNull
    private Long drugId;

    @XmlElement
    @NotNull(message = "Name drug must be not null")
    @Size(min = 3, max = 32, message ="{Size.name}" )
    private String name;

    @XmlElement
    @NotNull(message = "Price must be not null")
    private Long price;

    @XmlElement
    @NotNull(message = "Country must be not null")
    @Size(min = 4, max = 24, message = "{Size.country}")
    private String country;

    @XmlElement
    @NotNull(message = "Storage must be not null")
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
