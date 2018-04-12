package com.github.arkoski.model;

import java.util.Arrays;

public class PasswordEntry {

    private Long id;
    private String serviceName = "";
    private String login = "";
    private char[] password;

    public PasswordEntry(Long id, String serviceName, String login, char[] password) {
        this.id = id;
        this.serviceName = serviceName;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getLogin() {
        return login;
    }

    public char[] getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PasswordEntry{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                '}';
    }

    public static class Builder{
        private Long id;
        private String serviceName;
        private String login;
        private char[] password;

        private Builder(){}

        public static Builder create(){
            return new Builder();
        }

        public Builder withId(Long id){
            this.id = id;
            return this;
        }

        public Builder withServiceName(String serviceName){
            this.serviceName = serviceName;
            return this;
        }

        public Builder withLogin(String login){
            this.login = login;
            return this;
        }

        public Builder withPassword(char[] password){
            this.password = password;
            return this;
        }

        public PasswordEntry build(){
            return new PasswordEntry(id,serviceName,login,password);
        }
    }



}
