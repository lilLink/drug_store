package com.univer.lab.service;

import java.util.Optional;

public abstract class Writer<T> {

    protected final String path;

    protected Writer(String path){
        this.path = path;
    }

    public abstract Optional<T> write(T instance);
}
