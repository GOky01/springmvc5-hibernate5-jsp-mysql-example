package com.project.springmvc.service;

import com.project.springmvc.entity.Phone;
import javassist.NotFoundException;

import java.util.List;

public interface PhoneService {

    List<Phone> getPhones();

    void savePhone(Phone phone);

    Phone getPhone(int theId) throws NotFoundException;

    void deletePhone(int theId);
}
