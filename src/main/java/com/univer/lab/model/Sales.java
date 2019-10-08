package com.univer.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sales {

    private Long saleId;

    private Date realizationDate;

    private Long realization;

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
