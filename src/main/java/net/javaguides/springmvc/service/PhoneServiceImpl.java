package net.javaguides.springmvc.service;

import net.javaguides.springmvc.dao.PhoneDAO;
import net.javaguides.springmvc.entity.Phone;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneDAO phoneDAO;

    public PhoneServiceImpl(PhoneDAO phoneDAO) {
        this.phoneDAO = phoneDAO;
    }

    @Override
    @Transactional
    public List<Phone> getPhones() {
        return phoneDAO.getPhones();
    }

    @Override
    @Transactional
    public void savePhone(Phone phone) {
        phoneDAO.savePhone(phone);
    }

    @Override
    @Transactional
    public Phone getPhone(int theId) {
        return phoneDAO.getPhone(theId);
    }

    @Override
    @Transactional
    public void deletePhone(int theId) {
        phoneDAO.deletePhone(theId);
    }
}

