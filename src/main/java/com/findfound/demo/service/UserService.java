package com.findfound.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.findfound.demo.dto.UserDTO;
import com.findfound.demo.dto.UserDTO1;
import com.findfound.demo.exception.UserNotFound;
import com.findfound.demo.model.Address;
import com.findfound.demo.model.User;
import com.findfound.demo.repository.UserRepository;
import com.findfound.demo.repository.addressRepository;
import com.findfound.demo.util.CommonUtils;

@Service
@Transactional
public class UserService {
	org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userRepo;
    @Autowired
    private addressRepository AddressRepo;
	public void saveUser(UserDTO userDTO) {
		User user = new User();
		Address address = new Address();
		BeanUtils.copyProperties(userDTO, user);
		BeanUtils.copyProperties(userDTO.getAddress(), address);
		user.setAddress(address);
		user.getAddress().setUser(user);
		User user1 = userRepo.findByEmail(userDTO.getEmail());
		if (user1 != null) {
			throw new UserNotFound("User already registered with this Email Id");
		}
		userRepo.save(user);
		log.info("User Details is successfully added");
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

	public void updateUser(UserDTO1 u, int id) {
		try {
			User user = userRepo.getOne(id);
			user.setFullName(u.getFullName());
			user.setEmail(u.getEmail());
			user.setGender(u.getGender());
			user.setPhoneNo(u.getPhoneNo());
			Address address = new Address();
		    address=AddressRepo.getOne(user.getAddress().getId());
			address.setHouseAddress(u.getAddress().getHouseAddress());
			address.setCity(u.getAddress().getCity());
			address.setState(u.getAddress().getState());
			address.setPinCode(u.getAddress().getPinCode());
			user.setAddress(address);
			address.setUser(user);
			userRepo.save(user);

			log.info("User Details is successfully updated");
 		} catch (Exception ex) {
			log.error("Exception in update User Service : " + ex.getMessage());
			throw new UserNotFound("user is not availbale with Id :" + id);
		}
	}

	public void deleteUser(int id) {
		try {
			User user = userRepo.getOne(id);
			userRepo.delete(user);
		} catch (Exception ex) {
			log.error("Exception in delete user Service: " + ex.getMessage());
			throw new UserNotFound("User already deleted with Id :" + id);
		}
	}

	public UserDTO login(UserDTO userDTO) {
		User user = new User();
		try {

			BeanUtils.copyProperties(userDTO, user);
			user = userRepo.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
			if (user == null) {
				System.out.println("invalid");
				return null;
			} else {
				System.out.println(CommonUtils.getUserId(user.getEmail()));
				System.out.println("valid:");
			}
		} catch (Exception ex) {
			log.error("Exception in login: " + ex.getMessage());
			throw new UserNotFound("Invalid email or password");
		}
		return userDTO;
	}

	public void updatePassword(UserDTO userDTO, int id) {
		User user = userRepo.getOne(id);
		if (user == null) {
			log.error("Exception in update password");
			throw new EntityNotFoundException("user is not available with Id: " + id);
		}
		if (userDTO.getPassword() == null || userDTO.getPassword().length() < 5)
			throw new UserNotFound("Password field should not be blank" + " and should be 5 character long");
		user.setPassword(userDTO.getPassword());
		userRepo.save(user);
	}
}
