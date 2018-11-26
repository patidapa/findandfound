package com.findfound.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findfound.demo.model.Address;

public interface addressRepository extends JpaRepository<Address,Integer> {

}
