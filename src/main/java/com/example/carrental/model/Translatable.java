package com.example.carrental.model;

public interface Translatable {
    default String getMessageCode() {
        return getClass().getSimpleName() + "." + this.toString();
    }
}
