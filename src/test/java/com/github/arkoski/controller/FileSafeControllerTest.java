package com.github.arkoski.controller;

import com.github.arkoski.model.PasswordEntry;
import com.github.arkoski.model.PasswordSafe;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileSafeControllerTest {

    private PasswordSafe passwordSafe = new PasswordSafe();
    private PasswordEntry passwordEntry = new PasswordEntry(1l,"wp.pl","looogin","haaaslo".toCharArray());
    private FileSafeController fileSafeController = new FileSafeController();


    @Test
    public void writeToFile() throws IOException {
        passwordSafe.add(passwordEntry);
        fileSafeController.writeToFile("test.txt",passwordSafe.getData());
        PasswordSafe passwordSafe2 = fileSafeController.readFileToPasswordSafe("test.txt");
        Assert.assertThat(passwordSafe2.getData().get(1l).getServiceName(), is("wp.pl"));
    }

    @Test
    public void writeSingleRecordToFile() throws IOException {

        PasswordEntry passwordEntry2 = new PasswordEntry(2l,"wp2","aaaa","2222".toCharArray());
        fileSafeController.writeSingleRecordToFile("test.txt",passwordEntry2);
        PasswordSafe passwordSafe2 = fileSafeController.readFileToPasswordSafe("test.txt");
        Assert.assertThat(passwordSafe2.getData().get(2l).getServiceName(), is("wp2"));
    }

    @Test
    public void readFileToPasswordSafe() throws IOException {
        PasswordSafe passwordSafe2 = fileSafeController.readFileToPasswordSafe("test.txt");
        try{
            Assert.assertThat(passwordSafe2.getData().get(2l).getServiceName(), is("wp2"));
        }catch(NullPointerException e){
            System.out.println("brak rekordow");
        }
    }
}
