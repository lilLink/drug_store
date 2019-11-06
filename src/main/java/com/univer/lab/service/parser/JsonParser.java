package com.univer.lab.service.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.univer.lab.adapter.LocalDateAdapterJson;
import com.univer.lab.model.Sales;
import com.univer.lab.service.Parser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class JsonParser extends Parser<Sales> {

    public JsonParser(String path){
        super(path);
    }

    @Override
    public Optional<Sales> parse(){
        try {
            return this.deserializationJson(path);
        } catch (IOException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private Optional<Sales> deserializationJson(String path) throws IOException{
        File file = new File(path);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapterJson()).create();
        return Optional.of((gson.fromJson(new FileReader(file),Sales.class)));
    }
}
