package com.findfound.demo.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findfound.demo.model.Address;
import com.findfound.demo.repository.addressRepository;

@Service
@Transactional
public class AddressService {
@Autowired	
private	addressRepository addressRepo;


public void saveAddress(Address address)
{
	addressRepo.save(address);
}
}
