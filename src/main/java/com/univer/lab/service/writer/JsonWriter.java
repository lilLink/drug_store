package com.univer.lab.service.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.univer.lab.model.Sales;
import com.univer.lab.service.Writer;
import com.univer.lab.adapter.LocalDateAdapterJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class JsonWriter extends Writer<Sales> {

    public JsonWriter(String path){
        super(path);
    }

    @Override
    public Optional<Sales> write(Sales sales){
        try {
            return this.serializeJson(sales);
        } catch (IOException e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Sales> serializeJson(Sales sales) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapterJson()).create();

        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(gson.toJson(sales));
        fileWriter.close();
        return Optional.of(sales);
    }
}
