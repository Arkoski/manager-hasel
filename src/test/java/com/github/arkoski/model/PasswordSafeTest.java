package com.github.arkoski.model;


import org.junit.Assert;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class PasswordSafeTest {

    private PasswordSafe passwordSafe = new PasswordSafe();
    private PasswordEntry passwordEntry = new PasswordEntry(1l,"wp.pl","looogin","haaaslo".toCharArray());

    @org.junit.Test
    public void add() {
        passwordSafe.add(passwordEntry);
        Assert.assertThat(passwordSafe.getData().get(1l).getServiceName(),is("wp.pl"));
    }

    @org.junit.Test
    public void remove() {
        passwordSafe.add(passwordEntry);
        passwordSafe.remove(1l);
        boolean test = passwordSafe.getData().isEmpty();
        Assert.assertTrue("nie skasowano", test);
    }
}
