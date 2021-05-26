package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class DB {
    public String databaseURL = "";
    public NavigableMap<Integer, String> tables = new TreeMap<>();
    public DB(String DBURL, boolean create){
        File database = null;
        this.databaseURL = DBURL;
        database = new File(this.databaseURL);
        try {
            if(database.exists()){
                System.out.println("Connected to " + this.databaseURL);
            }else{
                if(create){
                    File createdDatabase = new File(this.databaseURL);
                    if(createdDatabase.createNewFile()){
                        System.out.println("Database created!");
                    }else{
                        System.out.println("Couldn't create database!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void newTable(String tableURL){
        int index = 0;
        File table_file = new File(tableURL);
        NavigableMap<Integer, String> table = new TreeMap<>();
        if(table_file.exists()){
            try {
                if(table_file.length() == 0){
                    System.out.println("Emtpy database!");
                    index = 1;
                    table.put(index, tableURL);
                    ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(tableURL));
                    fileWriter.writeObject(table);
                    fileWriter.close();
                    System.out.println("New table :" + tableURL + " created");
                }else{
                    ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(tableURL));
                    System.out.println(fileReader.available());
                    NavigableMap<Integer, String> tables = (NavigableMap<Integer, String>) fileReader.readObject();
                    if(tables.size() >= 1){
                        Entry<Integer, String> last_entry = tables.lastEntry();
                        int last_entry_id = last_entry.getKey();
                        String last_entry_value = last_entry.getValue();
                        if(last_entry_value.equals(tableURL)){
                            System.out.println("Cannot insert the same table twice!");
                        }else{
                            index = (Integer) last_entry_id + 1;
                            table.put(index, tableURL);
                            // Tables are not the same, proceding to insert a new table
                            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(tableURL));
                            fileWriter.writeObject(table);
                            fileWriter.close();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
    }
}
