package com.univer.lab.service.parser;

import com.univer.lab.model.Sales;
import com.univer.lab.service.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Optional;

public class XmlParser extends Parser<Sales> {

    public XmlParser(String path){
        super(path);
    }

    @Override
    public Optional<Sales> parse(){
        try {
            return this.deserializeXml(path);
        }catch (JAXBException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<Sales> deserializeXml(String path) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Sales.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Sales sales = (Sales) unmarshaller.unmarshal(new File(path));
        return Optional.of(sales);
    }
}
