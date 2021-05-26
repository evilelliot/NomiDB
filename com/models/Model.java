package com.models;

public class Model {
    protected String[] modelData;
    public Model(String[] modelData){
        this.modelData = modelData;
    }   
    @Override
    public String toString(){
        String MD = "";
        for(int i = 0; i <= this.modelData.length - 1; i++){
            MD = MD + "|" + this.modelData[i];
        }
        return MD;
    }
}
