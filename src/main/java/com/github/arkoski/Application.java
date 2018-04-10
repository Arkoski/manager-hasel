package com.github.arkoski;

import com.github.arkoski.controller.FileSafeController;
import com.github.arkoski.controller.PasswordManagerController;
import com.github.arkoski.model.PasswordEntry;
import com.github.arkoski.model.PasswordSafe;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.io.FileUtils;
import org.jcp.xml.dsig.internal.dom.ApacheData;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException {
        PasswordSafe ps = new PasswordSafe();
        FileSafeController fsc = new FileSafeController();
        ps = fsc.readFileToPasswordSafe("baza.txt");

        Scanner sc = new Scanner(System.in);

        boolean loop = false;

        while(!loop){
            System.out.println("1 - Dodaj nowy wpis\n2 - Usun wpis\ninne - Koniec programu");
            String value = sc.nextLine();
            if(value.equals("1")){
                System.out.println("Podaj nazwe serwisu");
                String serviceName = sc.nextLine();
                System.out.println("Podaj login");
                String login = sc.nextLine();
                System.out.println("Podaj haslo");
                char[] password = sc.nextLine().toCharArray();
                PasswordManagerController pmc = new PasswordManagerController();
                pmc.addNewRecord(ps,serviceName,login,password);
            }
            else if(value.equals("2")){
                System.out.println("W budowie...");
            }
            else {
                loop=true;
            }
        }


    }

}
