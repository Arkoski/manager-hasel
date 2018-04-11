package com.github.arkoski;

import com.github.arkoski.controller.FileSafeController;
import com.github.arkoski.controller.PasswordManagerController;
import com.github.arkoski.model.PasswordEntry;
import com.github.arkoski.model.PasswordSafe;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.io.FileUtils;
import org.jcp.xml.dsig.internal.dom.ApacheData;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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

        while (!loop) {
            System.out.println("p - Koniec programu\n" +
                    "1 - Dodaj nowy wpis\n" +
                    "2 - Usun wpis\n" +
                    "3 - Pobierz haslo\n" +
                    "4 - Wyswietl liste rekordow");
            String value = sc.nextLine();
            if (value.equals("p")) {
                loop = true;
            } else if (value.equals("1")) {
                System.out.println("Podaj nazwe serwisu");
                String serviceName = sc.nextLine();
                System.out.println("Podaj login");
                String login = sc.nextLine();
                System.out.println("Podaj haslo");
                char[] password = sc.nextLine().toCharArray();
                PasswordManagerController pmc = new PasswordManagerController();
                pmc.addNewRecord(ps, serviceName, login, password);
            } else if (value.equals("2")) {
                System.out.println("Podaj nazwe serwisu do skasowania:");
                String serviceName = sc.nextLine();
                PasswordManagerController pmc = new PasswordManagerController();
                pmc.printListPartial(pmc.mapToListPartial(ps, serviceName));
                System.out.println("Podaj numer rekordu do skasowania:");
                Integer tempId = Integer.parseInt(sc.nextLine());
                pmc.deleteRecord(ps, pmc.mapToListPartial(ps, serviceName).get(tempId - 1));
            } else if (value.equals("3")) {
                System.out.println("Podaj nazwe serwisu do pobrania:");
                String serviceName = sc.nextLine();
                PasswordManagerController pmc = new PasswordManagerController();
                pmc.printListPartial(pmc.mapToListPartial(ps, serviceName));
                System.out.println("Podaj numer rekordu do pobrania:");
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
