package com.github.arkoski.model;


import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class PasswordEntryTest {

    private PasswordEntry passwordEntry = new PasswordEntry(1l,"wp.pl","looogin","haaaslo".toCharArray());

    @Test
    public void getId() {
        Assert.assertThat(passwordEntry.getId(), is(1l));
    }


    @Test
    public void getServiceName() {
        Assert.assertThat(passwordEntry.getServiceName(), is("wp.pl"));
    }

    @Test
    public void getLogin() {
        Assert.assertThat(passwordEntry.getLogin(), is("looogin"));
    }

    @Test
    public void getPassword() {
        Assert.assertThat(passwordEntry.getPassword(), is("haaaslo".toCharArray()));
    }

}
