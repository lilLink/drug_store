package com.univer.lab.service.writer;

import com.univer.lab.model.Sales;
import com.univer.lab.service.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Optional;

public class XmlWriter extends Writer<Sales> {

    public XmlWriter(String path){
        super(path);
    }


    @Override
    public Optional<Sales> write(Sales sales){
        try {
            return this.serializeXml(sales);
        }catch (JAXBException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Sales> serializeXml(Sales sales) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Sales.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(sales, new File(path));
        return Optional.of(sales);
    }
}
