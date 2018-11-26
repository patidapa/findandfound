package com.findfound.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findfound.demo.model.NGO;
import com.findfound.demo.repository.NgoRepository;
@Service
public class NgoService
{
	@Autowired
	private NgoRepository ngoRepo;
	public void saveNgo(NGO ngo)
	{
		ngoRepo.save(ngo);
	}
	public Collection<NGO> getNgo()
	{
		return ngoRepo.findAll();
	}
	public void updateNgo(int id, NGO ngo) {
		NGO ngo1=ngoRepo.getOne(id);
		ngoRepo.delete(ngo1);
		ngo.setId(id);
		ngoRepo.save(ngo);	
	}
	public void deleteNgo(int id)
	{
		NGO ngo=ngoRepo.getOne(id);
		ngoRepo.delete(ngo);
	}
}