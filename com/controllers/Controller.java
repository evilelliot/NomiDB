package com.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

public interface Controller {
    public void create();
    public void read();
    public void update();
    public void delete();

    public default void create(String tableURL, String dataInsert[]){
        try {
            int index = 0;
            File table = new File(tableURL);
            NavigableMap<Integer, String[]> data = new TreeMap<>();
            if(table.exists()){
                if(table.length() == 0){
                    index = 1;
                    // Open file writer
                    data.put(index, dataInsert);
                    ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(tableURL));
                    fileWriter.writeObject(table);
                    fileWriter.close();
                    System.out.println("Data inserted at:" + index);
                }else{
                    ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(tableURL));
                    System.out.println(fileReader.available());
                    NavigableMap<Integer, String[]> dataEntries = (NavigableMap<Integer, String[]>) fileReader.readObject();
                    if(dataEntries.size() >= 1){
                        var last_entry = dataEntries.lastEntry();
                        int last_entry_id = last_entry.getKey();
                        
                        
                        index = (Integer) last_entry_id + 1;
                        dataEntries.put(index, dataInsert);
                        // Tables are not the same, proceding to insert a new table
                        ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(tableURL));
                        fileWriter.writeObject(dataEntries);
                        fileWriter.close();
                    }
                }
            }else{
                System.out.println("Table file doesn't exist at " + tableURL);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    public default void read(String tableURL){
        try {
            File data = new File(tableURL);
            NavigableMap<Integer, String[]> d = new TreeMap<>();
            if(data.exists()){
                if(data.length() == 0){
                   System.out.println("Table is empty!"); 
                }else{
                    ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(tableURL));
                    NavigableMap<Integer, String[]> dataEntries = (NavigableMap<Integer, String[]>) fileReader.readObject();
                    // System.out.println(dataEntries);
                    if(dataEntries.size() >= 1){
                        Set keys = dataEntries.keySet();
                        for(Iterator i = keys.iterator(); i.hasNext();){
                            Integer key = (Integer) i.next();
                            try {
                                String[] value = (String[]) dataEntries.get(key);    
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            
                            

                        }
                    }
                }
            }else{
                System.out.println("Table file doesn't exist at " + tableURL);
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
