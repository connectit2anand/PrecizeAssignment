package com.precize.service;

import java.util.List;

import com.precize.entity.User;

public interface UserService {

	public String newUser(User userDetails);

	public List<User> getAllUserData();

	public void deleteUser(String name);

	public void updateUser(String name, Integer satScore);

	public Integer getRank(String name);



}
