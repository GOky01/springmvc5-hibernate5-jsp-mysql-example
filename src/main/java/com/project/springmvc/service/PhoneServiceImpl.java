package com.project.springmvc.service;

import com.project.springmvc.entity.Phone;
import com.project.springmvc.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional
    public List<Phone> getPhones() {
        return phoneRepository.findAll();
    }

    @Override
    @Transactional
    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    @Transactional
    public Phone getPhone(int id) {
        return phoneRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deletePhone(int id) {
        phoneRepository.deleteById(id);
    }
}