package com.project.springmvc.entity;

import javax.persistence.*;

@Entity
@Table(name="phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private int phone_id;

    @Column(name = "phone")
    private String phone;

//    @ManyToOne
//    @JoinColumn(name = "customer_id")
//    private Customer customer;

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

//    public Customer getCustomer() {
//        return customer;
//    }

//    public void setCustomer(Customer customer_id) {
//        this.customer = customer_id;
//    }

    public Phone(int phone_id, String phone, Customer customer) {
        this.phone_id = phone_id;
        this.phone = phone;
//        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phone_id=" + phone_id +
                ", phone='" + phone + '\'' +
//                ", customer_id=" + customer.getId() +
                '}';
    }
}
