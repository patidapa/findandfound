package com.findfound.demo.testcontroller;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.findfound.demo.FindandFound2ApplicationTests;
import com.findfound.demo.dto.AddressDTO;
import com.findfound.demo.dto.UserDTO;
import com.findfound.demo.model.Address;
import com.findfound.demo.model.User;
/*
public class UserServiceControllerTest extends FindandFound2ApplicationTests{

	
	@Before
	 public void setUp() {
	      super.setUp();
	   }
	   @Test
	   public void getUsers() throws Exception {
	      String uri = "/api/users";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      User [] userlist = super.mapFromJson(content, User[].class);
	      assertTrue(userlist.length > 0);
	   }
	   @Test
	   public void deleteUser() throws Exception {

		   String uri = "/api/user/45";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "User information deleted Successsfully");
	   }
	   @Test
	   public void getUser() throws Exception {
	      String uri = "/api/user/15";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
	      int status = mvcResult.getResponse().getStatus();
	     
	      String content = mvcResult.getResponse().getContentAsString();
	      User [] userlist = super.mapFromJson(content, User[].class);
	      assertEquals(200, status);
	   }
	   
	  @Test
	  public void createUser() throws Exception {
	      String uri = "/api/user";
	      UserDTO userDTO=new UserDTO();
	    User user = new User();
	    userDTO.setFullName("ramkumar");
	    user.setFullName(userDTO.getFullName());
	    userDTO.setEmail("ram123@gmail.com");
	    user.setEmail(userDTO.getEmail());
	    userDTO.setGender("male");
	    user.setGender(userDTO.getGender());
	    userDTO.setPassword("ramkumar123");
	     user.setPassword(userDTO.getPassword());
	     userDTO.setPhoneNo(889632145);
	     user.setPhoneNo(userDTO.getPhoneNo());
	  AddressDTO addressDTO=new AddressDTO();
	  Address address=new Address();
	         addressDTO.setHouseAddress("123,taverekere");
	         address.setHouseAddress(addressDTO.getHouseAddress());
	         addressDTO.setCity("bangalore");
	         address.setCity(addressDTO.getCity());
	         addressDTO.setState("karnataka");
	         address.setState(addressDTO.getState());
	         addressDTO.setPinCode(789654);
	         address.setPinCode(addressDTO.getPinCode());
	         BeanUtils.copyProperties(userDTO, user);
	         BeanUtils.copyProperties(addressDTO, address);
	      user.setAddress(address);
	      address.setUser(user);
	      String inputJson = super.mapToJson(user);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "User information saved Successfully");
	   }
	   @Test
	   public void updateUser() throws Exception {
	      String uri = "/api/user/35";
	      UserDTO userDTO=new UserDTO();
	    User user = new User();
	    userDTO.setFullName("ramkumar");
	    user.setFullName(userDTO.getFullName());
	    userDTO.setEmail("ram123@gmail.com");
	    user.setEmail(userDTO.getEmail());
	    userDTO.setGender("male");
	    user.setGender(userDTO.getGender());
	    userDTO.setPassword("ramkumar123");
	     user.setPassword(userDTO.getPassword());
	     userDTO.setPhoneNo(889632145);
	     user.setPhoneNo(userDTO.getPhoneNo());
	  AddressDTO addressDTO=new AddressDTO();
	  Address address=new Address();
	         addressDTO.setHouseAddress("123,taverekere");
	         address.setHouseAddress(addressDTO.getHouseAddress());
	         addressDTO.setCity("bangalore");
	         address.setCity(addressDTO.getCity());
	         addressDTO.setState("karnataka");
	         address.setState(addressDTO.getState());
	         addressDTO.setPinCode(789654);
	         address.setPinCode(addressDTO.getPinCode());
	         BeanUtils.copyProperties(userDTO, user);
	         BeanUtils.copyProperties(addressDTO, address);
	      user.setAddress(address);
	      address.setUser(user);
	      String inputJson = super.mapToJson(user);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "User information updated Successfully");
	   }
}
*/
