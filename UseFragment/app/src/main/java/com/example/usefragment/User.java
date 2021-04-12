package com.example.usefragment;

public class User {
        String Name, Email;
        String Number;
        public User(){}

        public User(String name, String number, String email){
            this.Name = name;
            this.Number = number;
            this.Email = email;
        }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
