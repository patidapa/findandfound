package com.findfound.demo.controller;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findfound.demo.exception.UserNotFound;
import com.findfound.demo.model.User;
import com.findfound.demo.model.UserDTO;
import com.findfound.demo.service.AddressService;
import com.findfound.demo.service.UserService;
import com.findfound.demo.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "Find And Found", description = "Operations pertaining to users")
//@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
public class UserController {
     @Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserService userService;

	@Autowired
	@ApiOperation(value = "View a list of available users", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping("/users")
	public ResponseEntity<Object> getUsers() {
		System.out.println("get success");
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@ApiOperation(value = " Search a user with Id ")
	@RequestMapping("/users/{id}")
	public ResponseEntity<Object> getUser(@PathVariable int id) {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Add a User")
	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@RequestBody UserDTO u) throws Exception {
		System.out.println("user json-->" + JsonUtil.toJsonString(u));
		String pwd = u.getPassword();
		String encryptPassword = passwordEncoder.encode(pwd);
		u.setPassword(encryptPassword);
		//System.out.println(u.getPassword());
		userService.saveUser(u);
		return new ResponseEntity<>("User saved Sussessfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Update a User")
	@PutMapping("/users/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody UserDTO u, @PathVariable int id) {
		userService.updateUser(u, id);
		return new ResponseEntity<>("User information updated Successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a User")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		 userService.deleteUser(id);
		return new ResponseEntity<>("User information deleted SuccessFully", HttpStatus.OK);
	}
}
