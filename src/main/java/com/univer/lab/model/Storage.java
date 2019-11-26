package com.univer.lab.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.List;

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

    @XmlElement
    @XmlElementWrapper
    @NotNull(message = "Provider must be not null")
    private List<Provider> providers;

    @Override
    public String toString() {
        return "Storage{" +
                "storageId=" + storageId +
                ", providers=" + providers +
                '}';
    }
}
