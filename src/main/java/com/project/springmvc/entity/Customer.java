package com.project.springmvc.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;

    private String last_name;

    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Set<Phone> phones;

    public Customer() {
    }

    public Customer(int id, String first_name, String last_name, String email, Set<Phone> phones) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phones = phones;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String firstName) {
        this.first_name = firstName;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String lastName) {
        this.last_name = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", email='" + email +
                ", phones=" + phones +
                '}';
    }
}





