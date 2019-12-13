package com.univer.lab;

import com.univer.lab.model.*;
import com.univer.lab.service.Parser;
import com.univer.lab.service.Writer;
import com.univer.lab.service.parser.XmlParser;
import com.univer.lab.service.writer.XmlWriter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmlParserTest {

    @Test
    public void serializeTest(){
        String path = "drugstore.xml";
        Writer<Sales> writer = new XmlWriter(path);

        Sales sales = Sales.builder()
                .saleId(1L)
                .realization(2000L)
                .realizationDate(LocalDate.parse("2019-04-10"))
                .build();

        Drug drug = new Drug();
                drug.setDrugId(1L);
                drug.setName("Pantenol");
                drug.setCount(42L);
                drug.setPrice(120L);
                drug.setCountry("Germany");

        Storage storage = Storage.builder()
                .storageId(1L)
                .build();

        List<Provider> providers = new ArrayList<>();
        Provider provider = new Provider();
                provider.setProviderId(1L);
                provider.setProviderName("C&C");

        providers.add(provider);
        storage.setProviders(providers);

        drug.setStorage(storage);

        sales.setDrug(drug);

        writer.write(sales);

        Parser<Sales> parser = new XmlParser(path);
        Sales sales1 = parser.parse().orElseThrow(RuntimeException::new);

        assertEquals(sales.toString().trim(), sales1.toString().trim());
    }
}
