package com.lysenko.university.model;

public enum Degree {

    ASSISTANT("assistans"),
    ASSOCIATE_PROFESSOR("associate professors"),
    PROFESSOR("professors");

    private String name;

    Degree(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
