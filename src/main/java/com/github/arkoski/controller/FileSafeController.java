package com.github.arkoski.controller;

import com.github.arkoski.model.PasswordEntry;
import com.github.arkoski.model.PasswordSafe;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FileSafeController {

    public static void writeToFile(String plik, Map<Integer, PasswordEntry> map) throws IOException {
        File path = new File(plik);
        Gson gson = new Gson();

        for (Map.Entry<Integer, PasswordEntry> entry : map.entrySet()) {

            String zapis = gson.toJson(entry.getValue());

            FileUtils.write(path, zapis+"\n", true);
            //        System.out.println("ID : " + entry.getKey() + " Value : " + entry.getValue());
        }
    }


    public static void main(String[] args) throws IOException {

        PasswordEntry pe1 = new PasswordEntry("wp1", "login1", "haslo1");
        PasswordEntry pe2 = new PasswordEntry("wp2", "login2", "haslo2");
        PasswordSafe ps = new PasswordSafe();

        ps.add(1, pe1);
        ps.add(2, pe2);

        writeToFile("baza.txt", ps.getData());
        //ps.printData();

//        File path = new File("baza.txt");
//        Gson gson = new Gson();
//
//        String zapisz = gson.toJson("1" + ps.getData().get(1));
//        System.out.println("zapisz: " + zapisz);
//
//        try {
//            FileUtils.write(path, zapisz);
//        } catch (IOException e) {
//            System.out.println("Problem occurs when writing file " + path);
//            e.printStackTrace();
//        }
//
//        System.out.println("-----------ODCZYT------------");
//
//        PasswordEntry passwordEntry = gson.fromJson(zapisz, PasswordEntry.class);
//
//        try {
//            System.out.println(FileUtils.readFileToString(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
