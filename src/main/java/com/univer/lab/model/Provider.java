package com.univer.lab.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "Provider")
@XmlAccessorType(XmlAccessType.FIELD)
public class Provider {

    @XmlElement
    @NotNull
    private Long providerId;

    @XmlElement
    @NotNull(message = "Name must be not null")
    @Size(min = 3, max = 24, message = "{Size.providerName}")
    private String providerName;

    @XmlElement
    @NotNull
    private Storage storage;

    @Override
    public String toString() {
        return "Provider{" +
                "providerId=" + providerId +
                ", providerName='" + providerName + '\'' +
                ", storage=" + storage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(providerId, provider.providerId) &&
                Objects.equals(providerName, provider.providerName) &&
                Objects.equals(storage, provider.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(providerId, providerName, storage);
    }

    public static class Builder{
        Provider provider;

        public Builder(){
            provider = new Provider();
        }

        public Builder setId(Long id){
            provider.providerId = id;
            return this;
        }

        public Builder setNameProvider(String name){
            provider.providerName = name;
            return this;
        }

        public Builder setStorageProvider(Storage storage){
            provider.storage = storage;
            return this;
        }

        public Provider build(){
            return provider;
        }
    }
}
