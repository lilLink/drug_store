package com.univer.lab.model;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "CountDrug")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountDrug {

    @XmlElement
    private Drug drug;

    @XmlElement
    private Long count;

    @Override
    public String toString() {
        return "CountDrug{" +
                "drug=" + drug.getName() +
                ", count=" + count +
                '}';
    }
}
