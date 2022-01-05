package net.javaguides.springmvc.service;

import net.javaguides.springmvc.entity.Customer;
import net.javaguides.springmvc.entity.Phone;

import java.util.List;

public interface PhoneService {

    public List<Phone> getPhones();

    public void savePhone(Phone phone);

    public Phone getPhone(int theId);

    public void deletePhone(int theId);
}
