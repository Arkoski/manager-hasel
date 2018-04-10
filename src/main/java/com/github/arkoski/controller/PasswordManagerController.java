package com.github.arkoski.controller;

import com.github.arkoski.model.PasswordEntry;
import com.github.arkoski.model.PasswordSafe;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PasswordManagerController {

    public void addNewRecord(PasswordSafe passwordSafe, String serviceName, String login, char[] password) throws IOException {

        long firstFreeId = passwordSafe.getData().keySet().stream().mapToLong(l -> l).max().orElse(0L) + 1;
        PasswordEntry pe = new PasswordEntry(firstFreeId, serviceName, login, password);
        passwordSafe.add(pe);
        FileSafeController fsc = new FileSafeController();
        fsc.writeSingleRecordToFile("baza.txt",pe);
    }

}
