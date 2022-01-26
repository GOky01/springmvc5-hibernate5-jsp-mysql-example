package com.project.springmvc.entity;

import javax.persistence.*;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phone_id;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer;

    public Phone() {
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Phone(int phone_id, String phone) {
        this.phone_id = phone_id;
        this.phone = phone;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phone_id=" + phone_id +
                ", phone='" + phone + '\'' +
                '}';
    }
}

