package com.findfound.demo.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.findfound.demo.dto.UserDTO;
import com.findfound.demo.dto.UserDTO1;
import com.findfound.demo.model.User;
import com.findfound.demo.response.ErrorObject;
import com.findfound.demo.service.UserService;
import com.findfound.demo.util.CommonUtils;
import com.findfound.demo.util.JsonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "Find And Found", description = "Operations pertaining to users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	
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
	@RequestMapping("/user/{id}")
	public ResponseEntity<Object> getUser(@PathVariable int id) {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@ApiOperation(value = "Add a User")
	@PostMapping("/user")
	public String saveUser(@RequestBody @Valid UserDTO userDTO) throws Exception {
		System.out.println("user json-->" + JsonUtil.toJsonString(userDTO));
		userService.saveUser(userDTO);
		com.findfound.demo.response.Response res = com.findfound.demo.util.CommonUtils
				.getResponseObject("User saved Sussessfully");
		res.setData(userDTO);
		return CommonUtils.getJson(res);
	}

	@ApiOperation(value = "Update a User")
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> updateUser(@RequestBody @Valid UserDTO1 userDTO, @PathVariable int id) {
		userService.updateUser(userDTO, id);
		return new ResponseEntity<>("User information updated Successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Update User Password")
	@PutMapping("/updatePassword/{id}")
	public ResponseEntity<Object> updateUserPassword(@RequestBody UserDTO userDTO, @PathVariable int id) {
		userService.updatePassword(userDTO, id);
		return new ResponseEntity<>("User Password updated Successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a User")
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("User information deleted SuccessFully", HttpStatus.OK);
	}

	@ApiOperation(value = "Login to user")
	@PostMapping(value = "/login")
	public String login(@RequestBody UserDTO userDTO, HttpServletResponse response)
			throws IOException, URISyntaxException {

		userDTO = userService.login(userDTO);
		com.findfound.demo.response.Response res = com.findfound.demo.util.CommonUtils
				.getResponseObject("user is authenticated");
		HttpSession session = null;

		if (session == null) {
			if (userDTO == null) {
				ErrorObject err = com.findfound.demo.util.CommonUtils.getErrorResponse("Login failed !",
						"invalid Email or Password");
				res.setErrors(err);
				res.setStatus(com.findfound.demo.constant.StatusCode.ERROR.name());
			} else {
				res.setData(userDTO);
				System.out.println("Welcome: " + CommonUtils.getUserId(userDTO.getEmail()));
				System.out.println("Success");
				return CommonUtils.getJson(res);
			}
		} else {
			ErrorObject err = com.findfound.demo.util.CommonUtils.getErrorResponse("Login failed !",
					"user already login");
			res.setErrors(err);
			res.setStatus(com.findfound.demo.constant.StatusCode.ERROR.name());
		}
		return CommonUtils.getJson(res);
	}

	@ApiOperation(value = "Logout to user")
	@RequestMapping(value = "/logout")
	public String logout() {
		HttpSession session = CommonUtils.geSsession();
		com.findfound.demo.response.Response res = com.findfound.demo.util.CommonUtils
				.getResponseObject("You have been Successfully logout");
		System.out.println(session.getAttribute("name"));
		if (session.getAttribute("name") != null) {
			session.invalidate();
		} else {
			System.out.println("befre login" + session.getAttribute("name"));
			ErrorObject err = com.findfound.demo.util.CommonUtils.getErrorResponse("Logout failed !",
					"You already logout!");
			res.setErrors(err);
			res.setStatus(com.findfound.demo.constant.StatusCode.ERROR.name());
		}
		return CommonUtils.getJson(res);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationException(MethodArgumentNotValidException ex) {

		return ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
	}
}
