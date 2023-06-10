package com.precize.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.precize.entity.User;
import com.precize.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userDetailsService;
	
	@PutMapping("/addUser")
	public ResponseEntity<String> addUser(@Valid @RequestBody User userDetails){
		String newUser = userDetailsService.newUser(userDetails);	
		return new ResponseEntity<>(newUser,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getUserAllUser(){
		List<User> userList = userDetailsService.getAllUserData();
		return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{name}")
	public ResponseEntity<String> deleteUser(@PathVariable String name){
		userDetailsService.deleteUser(name);
		return new ResponseEntity<String>("Successfully Deleted",HttpStatus.OK);
	}
	
	@PatchMapping("/updateUser/{name}/{satScore}")
	public ResponseEntity<String> updateUser(@PathVariable String name,
											@PathVariable Integer satScore){
		userDetailsService.updateUser(name,satScore);
		return new ResponseEntity<String>("Score Successfully Updated",HttpStatus.OK);
	}
	
	@GetMapping("/getRank/{name}")
	public ResponseEntity<Integer> getRank(@PathVariable String name){
		Integer rank = userDetailsService.getRank(name);
		return new ResponseEntity<Integer>(rank,HttpStatus.OK);
	}
	
	
}
