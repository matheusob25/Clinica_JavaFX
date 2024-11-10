package com.example.clinica.model.services;

import com.example.clinica.model.dao.AddressDao;
import com.example.clinica.model.dao.DaoFactory;
import com.example.clinica.model.entities.Address;

public class AddressService {
    private AddressDao addressDao;

    public AddressService() {
        this.addressDao = DaoFactory.createAddressDao();
    }
    public void insert(Address address) {
        addressDao.insert(address);
    }
    public void update(Address address) {
        addressDao.update(address);
    }
    public void insertOrUpdate(Address address) {
        if (address.getId() == null) {
            insert(address);
        }else{
            update(address);
        }
    }
}
