package com.findfound.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findfound.demo.exception.NgoNotFound;
import com.findfound.demo.model.NGO;
import com.findfound.demo.service.NgoService;

@RestController
public class NgoController {
	@Autowired
private NgoService ngoService;
	
	@PostMapping("/save")
	  public ResponseEntity<Object> saveNgo(@RequestBody NGO ngo)
	  {
	   ngoService.saveNgo(ngo);
	   return new ResponseEntity<>("Account information saved",HttpStatus.OK);
	  }
	@RequestMapping("/get")
	public ResponseEntity<Object> getNgo()
	{
		return new ResponseEntity<>(ngoService.getNgo(),HttpStatus.OK);
	}
	@PutMapping("/put/{id}")
	public ResponseEntity<Object> updateNgo(@PathVariable int id,@RequestBody NGO ngo )
	{
		if(ngo.getId()!=id) throw new NgoNotFound();
		ngoService.updateNgo(id,ngo);
		return new ResponseEntity<>("Account information updated",HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteNgo(@PathVariable int id)
	{
		ngoService.deleteNgo(id);
		return new ResponseEntity<>("Account deleted",HttpStatus.OK);
	}
}
