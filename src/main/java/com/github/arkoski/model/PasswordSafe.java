package com.github.arkoski.model;

import java.util.HashMap;
import java.util.Map;

public class PasswordSafe {

    private Map<Integer, PasswordEntry> data = new HashMap<Integer, PasswordEntry>();              //String serviceName

    public void add(Integer id, PasswordEntry passwordEntry){
        data.put(id,passwordEntry);
    }

    public Map<Integer, PasswordEntry> getData() {
        return data;
    }

    public void remove(Integer id){
        data.remove(id);
    }

    public void printData(){
        for (Map.Entry<Integer, PasswordEntry> entry : data.entrySet()) {
            System.out.println("ID : " + entry.getKey() + " Value : " + entry.getValue());
        }
    }
}
