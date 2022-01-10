package com.project.springmvc.service;

import com.project.springmvc.entity.Phone;
import com.project.springmvc.repository.PhoneRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    PhoneRepository phoneRepository;
    @Override
    @Transactional
    public List<Phone> getPhones() {
        return (List<Phone>) phoneRepository.findAll();
    }

    @Override
    @Transactional
    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    @Transactional
    public Phone getPhone(int theId) throws NotFoundException {
        return phoneRepository.findById(theId).orElseThrow(()
                -> new NotFoundException("Phone was not fount with id :" + theId));
    }

    @Override
    @Transactional
    public void deletePhone(int theId) {
        phoneRepository.deleteById(theId);
    }
}