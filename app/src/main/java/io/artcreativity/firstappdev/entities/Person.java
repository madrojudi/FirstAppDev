package io.artcreativity.firstappdev.entities;

import java.io.Serializable;

public class Person implements Serializable {

    public long id;
    public String firstName;
    public String lastName;
    public String email;
    public String avatar;
    public String phone;
    public String facebook;
    public String whatsapp;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String phone, String facebook, String whatsapp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.facebook = facebook;
        this.whatsapp = whatsapp;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", facebook='" + facebook + '\'' +
                ", whatsapp='" + whatsapp + '\'' +
                '}';
    }
}
