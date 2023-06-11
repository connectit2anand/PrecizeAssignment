package com.precize.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.precize.entity.User;
import com.precize.exceptions.InvalidRequestException;
import com.precize.exceptions.UserAlreadyExistsException;
import com.precize.exceptions.UserNotExistException;
import com.precize.repository.UserRepository;
import com.precize.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public String newUser(User userDetails) {
		
		Optional<User> opt = userRepository.findById(userDetails.getName());
		if(opt.isPresent()) {
			throw new UserAlreadyExistsException("User " +  userDetails.getName() + " Already Exists. Name should be unique");
		}
		int marks = userDetails.getSatScore();
		if(marks > 30)
			userDetails.setResult("Pass");
		else
			userDetails.setResult("Fail");
		userRepository.save(userDetails);
		
		return "User " + userDetails.getName() + " saved successfully";
	}

	@Override
	public List<User> getAllUserData() {
		
		List<User> usersList = userRepository.getAllUser();
		return usersList;
	}

	@Override
	public void deleteUser(String name) {
		
		Optional<User> user = userRepository.findById(name);
		if(!user.isPresent()) {
			throw new UserNotExistException("Username " + name + " does not exist");
		}

		User userToBeDeleted = user.get();
		userRepository.delete(userToBeDeleted);
		
	}
	

	@Override
	public void updateUser(String name, Integer satScore) {
		
		Optional<User> user = userRepository.findById(name);
		
		if(!user.isPresent()) {
			throw new UserNotExistException("Username " + name + " does not exist");
		}
		if(satScore < 0 || satScore > 100) {
			throw new InvalidRequestException("satScore should be from 0 to 100");
		}
		
		User userToUpdate = user.get();
		userToUpdate.setSatScore(satScore);
		
		if(satScore > 30) {
			userToUpdate.setResult("Pass");
		} else {
			userToUpdate.setResult("Fail");
		}
		userRepository.save(userToUpdate);
	}

	@Override
	public Integer getRank(String name) {
		
		Optional<User> user = userRepository.findById(name);
		
		if(!user.isPresent()) {
			throw new UserNotExistException("Username " + name + " does not exist");
		}
		
		List<User> usersList = userRepository.getRankList();
		
		int rank = 1;
		int i = 0;
		
		while(i < usersList.size()) {
			User currentUser = usersList.get(i);
			int currentSatScore = currentUser.getSatScore();
			int j = i;
			/*
				Handling the case for same SAT Score.
				Example: If two users are having same SAT Score then their Rank will be same
			*/
			while(j < usersList.size() && usersList.get(j).getSatScore() == currentSatScore) {
				if(usersList.get(j).getName().equalsIgnoreCase(name)) {
					return rank;
				}
				++ j;
			}
			++ rank;
			i = j;
		}
		return rank;
	}
	
}
