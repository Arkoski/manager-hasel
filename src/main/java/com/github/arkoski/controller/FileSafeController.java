package com.github.arkoski.controller;

import com.github.arkoski.model.PasswordEntry;
import com.github.arkoski.model.PasswordSafe;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileSafeController {

    public void writeToFile(String plik, Map<Long, PasswordEntry> map) throws IOException {
        File path = new File(plik);
        Gson gson = new Gson();
        FileUtils.write(path, "");
        for (Map.Entry<Long, PasswordEntry> entry : map.entrySet()) {
            String zapis = gson.toJson(entry.getValue());

            FileUtils.write(path, zapis + "\n", true);
        }
    }

    public void writeSingleRecordToFile(String plik, PasswordEntry passwordEntry) throws IOException {
        File path = new File(plik);
        Gson gson = new Gson();
        String zapis = gson.toJson(passwordEntry);
        FileUtils.write(path, zapis + "\n", true);
    }


    public PasswordSafe readFileToPasswordSafe(String plik) throws IOException {
        File path = new File(plik);
        String odczyt = FileUtils.readFileToString(path);
        //System.out.println(odczyt);
        String[] array = odczyt.split("\n");
        //System.out.println(Arrays.toString(array));
        return arrayToPasswordSafe(array);
    }

    private PasswordSafe arrayToPasswordSafe(String[] array) {
        PasswordSafe ps = new PasswordSafe();
        Gson gson = new Gson();
        for (int i = 0; i < array.length; i++) {
            PasswordEntry passwordEntry = gson.fromJson(array[i], PasswordEntry.class);
            ps.add(passwordEntry);
        }
        return ps;
    }

}
