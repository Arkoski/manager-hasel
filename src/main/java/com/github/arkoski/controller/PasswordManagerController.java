package com.github.arkoski.controller;

import com.github.arkoski.model.PasswordEntry;
import com.github.arkoski.model.PasswordSafe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordManagerController {

    private PasswordSafe passwordSafe;

    public void addNewRecord(PasswordSafe passwordSafe, String serviceName, String login, char[] password) throws IOException {

        long firstFreeId = passwordSafe.getData().keySet().stream().mapToLong(l -> l).max().orElse(0L) + 1;
        PasswordEntry pe = new PasswordEntry(firstFreeId, serviceName, login, password);
        passwordSafe.add(pe);
        FileSafeController fsc = new FileSafeController();
        fsc.writeSingleRecordToFile("baza.txt", pe);
    }

    public void deleteRecord(PasswordSafe passwordSafe, PasswordEntry passwordEntry) throws IOException {
        FileSafeController fsc = new FileSafeController();
        passwordSafe.remove(passwordEntry.getId());

        fsc.writeToFile("baza.txt", passwordSafe.getData());
    }

    public List<PasswordEntry> mapToListPartial(PasswordSafe passwordSafe, String serviceName) {
        List<PasswordEntry> list = new ArrayList<>();

        Map<Long, PasswordEntry> map = passwordSafe.getData();
        for (Map.Entry<Long, PasswordEntry> entry : map.entrySet()) {
            if (entry.getValue().getServiceName().equals(serviceName)) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public void printListPartial(List<PasswordEntry> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i+1+": "+lista.get(i).getServiceName() + " - " + lista.get(i).getLogin());
        }
    }

    public List<PasswordEntry> mapToList(PasswordSafe passwordSafe) {
        List<PasswordEntry> list = new ArrayList<>();

        Map<Long, PasswordEntry> map = passwordSafe.getData();
        for (Map.Entry<Long, PasswordEntry> entry : map.entrySet()) {
                list.add(entry.getValue());
        }
        return list;
    }

    public void printList(List<PasswordEntry> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getId()+": "+lista.get(i).getServiceName() + " - " + lista.get(i).getLogin());
        }
    }


}
