package com.github.arkoski.model;

import java.util.HashMap;
import java.util.Map;

public class PasswordSafe {

    private Map<Long, PasswordEntry> data = new HashMap<>();

    public void add(PasswordEntry passwordEntry) {
        data.put(passwordEntry.getId(), passwordEntry);
    }

    public Map<Long, PasswordEntry> getData() {
        return data;
    }

    public void remove(Long id) {
        data.remove(id);
    }

    public void printData() {
        for (Map.Entry<Long, PasswordEntry> entry : data.entrySet()) {
            System.out.println("ID : " + entry.getKey() + " Value : " + entry.getValue());
        }
    }
}
