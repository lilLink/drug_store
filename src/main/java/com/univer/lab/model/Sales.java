package com.univer.lab.model;

import com.univer.lab.adapter.LocalDateAdapterXml;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "Sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sales {

    @XmlElement
    @NotNull
    private Long saleId;

    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapterXml.class)
    private LocalDate realizationDate;

    @XmlElement
    @NotNull(message = "Summation price must be not null")
    private Long realization;

    @XmlElement
    @NotNull(message = "Drug must be not null")
    private Drug drug;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return Objects.equals(saleId, sales.saleId) &&
                Objects.equals(realizationDate, sales.realizationDate) &&
                Objects.equals(realization, sales.realization) &&
                Objects.equals(drug, sales.drug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleId, realizationDate, realization, drug);
    }

    @Override
    public String toString() {
        return "Sales{" +
                "saleId=" + saleId +
                ", realizationDate=" + realizationDate +
                ", realization=" + realization +
                ", drug=" + drug +
                '}';
    }
}
