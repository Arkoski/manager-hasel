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


    public static void main(String[] args) throws IOException {

//        PasswordEntry pe1 = new PasswordEntry(1l,"wp1", "login1", new char[]{120,121,122});
//        PasswordEntry pe2 = new PasswordEntry(2l,"wp2", "login2", new char[]{122,122,122});
//
//        ps.add(pe1);
//        ps.add(pe2);
//        //ps.printData();
//
//        writeToFile("baza.txt", ps.getData());
        FileSafeController fsc = new FileSafeController();
        fsc.readFileToPasswordSafe("baza.txt").printData();
    }
}
