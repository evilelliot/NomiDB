package com.controllers;

public interface Controller {
    public void create();
    public void read();
    public void update();
    public void delete();

    public default void create(String data){
        System.out.println("Bringle");
    }
}
