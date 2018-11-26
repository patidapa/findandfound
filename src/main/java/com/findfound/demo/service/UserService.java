package com.findfound.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findfound.demo.exception.UserNotFound;
import com.findfound.demo.model.Address;
import com.findfound.demo.model.User;
import com.findfound.demo.model.UserDTO;
import com.findfound.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {
	org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userRepo;

	public void saveUser(UserDTO u) {
		try {
			User user = new User();
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(u.getEmail());
			user.setPassword(u.getPassword());
			user.setConfirm_password(u.getConfirm_password());
			user.setPhone_no(u.getPhone_no());

			Address address = new Address();
			address.setHouse_no(u.getAddress().getHouse_no());
			address.setStreet(u.getAddress().getStreet());

			user.setAddress(address);

			address.setUser(user);

			userRepo.save(user);
		} catch (Exception ex) {
			log.error("User saving exception :" + ex.getMessage());
			throw new UserNotFound("User values can't be null");
		}
	}

	public List<User> getUsers() {
		try {
			return userRepo.findAll();
		} catch (Exception ex) {
			log.error("Exception in getting users Service :" + ex.getMessage());
		}
		return null;
	}

	public Optional<User> getUser(int id) {
		try {
			return userRepo.findById(id);
		} catch (Exception ex) {
			log.error("Exception to getUser Service: " + ex.getMessage());
			throw new UserNotFound("User is not availble of this Id");
		}
	}

	public void updateUser(UserDTO u, int id) {
		try {
			User user = new User();
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(u.getEmail());
			user.setPassword(u.getPassword());
			user.setConfirm_password(u.getConfirm_password());
			user.setPhone_no(u.getPhone_no());

			Address address = new Address();
			address.setHouse_no(u.getAddress().getHouse_no());
			address.setStreet(u.getAddress().getStreet());

			user.setAddress(address);

			address.setUser(user);
			User u1 = userRepo.getOne(id);
			userRepo.delete(u1);
			userRepo.save(user);
		} catch (Exception ex) {
			log.error("Exception in update User Service : " + ex.getMessage());
			throw new UserNotFound("user is not availbale with Id :" + id);
		}
	}

	public void deleteUser(int id) {
		try {
			User u = userRepo.getOne(id);
			userRepo.delete(u);
		} catch (Exception ex) {
			log.error("Exception in delete user Service: " + ex.getMessage());
			throw new UserNotFound("User already deleted with Id :" + id);
		}
	}
}
