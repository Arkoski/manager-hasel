package com.github.arkoski;

import com.github.arkoski.controller.FileSafeController;
import com.github.arkoski.controller.PasswordManagerController;
import com.github.arkoski.controller.TestThread;
import com.github.arkoski.model.PasswordEntry;
import com.github.arkoski.model.PasswordSafe;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.io.FileUtils;
import org.jcp.xml.dsig.internal.dom.ApacheData;
import org.junit.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static boolean isLoggedIn = false;

    public static void main(String[] args) throws IOException {
        String haslo = "blabla";



        TestThread testThread = new TestThread();
        Thread thread = new Thread(testThread);
        TestThread.start = System.currentTimeMillis();

        thread.start();




        PasswordSafe ps = new PasswordSafe();
        FileSafeController fsc = new FileSafeController();
        ps = fsc.readFileToPasswordSafe("baza.txt");

        Scanner sc = new Scanner(System.in);

        boolean loop = false;

        while (!loop) {
            TestThread.start = System.currentTimeMillis();

            while (!isLoggedIn) {
                System.out.println("Podaj haslo do aplikacji:");
                String temp = sc.nextLine();
                if (temp.equals(haslo)) {
                    isLoggedIn = true;
                } else {
                    System.out.println("Zle haslo !");
                }
            }


            System.out.println("p - Koniec programu\n" +
                    "1 - Dodaj nowy wpis\n" +
                    "2 - Usun wpis\n" +
                    "3 - Pobierz haslo\n" +
                    "4 - Wyswietl liste rekordow");
            TestThread.start = System.currentTimeMillis();
            String value = sc.nextLine();
            if (value.equals("p")) {
                loop = true;
            } else if (value.equals("1")) {
                System.out.println("Podaj nazwe serwisu");
                TestThread.start = System.currentTimeMillis();
                String serviceName = sc.nextLine();
                System.out.println("Podaj login");
                TestThread.start = System.currentTimeMillis();
                String login = sc.nextLine();
                System.out.println("Podaj haslo");
                TestThread.start = System.currentTimeMillis();
                char[] password = sc.nextLine().toCharArray();
                PasswordManagerController pmc = new PasswordManagerController();
                pmc.addNewRecord(ps, serviceName, login, password);
            } else if (value.equals("2")) {
                System.out.println("Podaj nazwe serwisu do skasowania:");
                TestThread.start = System.currentTimeMillis();
                String serviceName = sc.nextLine();
                PasswordManagerController pmc = new PasswordManagerController();
                pmc.printListPartial(pmc.mapToListPartial(ps, serviceName));
                System.out.println("Podaj numer rekordu do skasowania:");
                TestThread.start = System.currentTimeMillis();
                Integer tempId = Integer.parseInt(sc.nextLine());
                pmc.deleteRecord(ps, pmc.mapToListPartial(ps, serviceName).get(tempId - 1));
            } else if (value.equals("3")) {
                System.out.println("Podaj nazwe serwisu do pobrania:");
                TestThread.start = System.currentTimeMillis();
                String serviceName = sc.nextLine();
                PasswordManagerController pmc = new PasswordManagerController();
                pmc.printListPartial(pmc.mapToListPartial(ps, serviceName));
                System.out.println("Podaj numer rekordu do pobrania:");
                TestThread.start = System.currentTimeMillis();
                Integer tempId = Integer.parseInt(sc.nextLine());

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < pmc.mapToListPartial(ps, serviceName).get(tempId - 1).getPassword().length; i++) {
                    sb.append(pmc.mapToListPartial(ps, serviceName).get(tempId - 1).getPassword()[i]);
                }

                StringSelection selection = new StringSelection(sb.toString());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);

            } else if (value.equals("4")) {
                PasswordManagerController pmc = new PasswordManagerController();
                pmc.printList(pmc.mapToList(ps));
                System.out.println("-----------------------------------------");
            }


        }
    }
}
