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

        Drug drug = Drug.builder()
                .drugId(1L)
                .name("Pantenol")
                .price(120L)
                .country("Germany")
                .build();

        Storage storage = Storage.builder()
                .storageId(1L)
                .build();

        List<CountDrug> countDrugs = new ArrayList<>();
        CountDrug countDrug = CountDrug.builder()
                .count(4L)
                .build();


        List<Provider> providers = new ArrayList<>();
        Provider provider = Provider.builder()
                .providerId(1L)
                .providerName("C&C")
                .build();

        countDrug.setDrug(drug);
        countDrugs.add(countDrug);
        //storage.setCountDrugs(countDrugs);
        providers.add(provider);
        storage.setProviders(providers);

        drug.setStorage(storage);
        //drug.setCountDrug(countDrug);

        sales.setDrug(drug);

        writer.write(sales);

        Parser<Sales> parser = new XmlParser(path);
        Sales sales1 = parser.parse().orElseThrow(RuntimeException::new);

        assertEquals(sales.toString().trim(), sales1.toString().trim());
    }
}
