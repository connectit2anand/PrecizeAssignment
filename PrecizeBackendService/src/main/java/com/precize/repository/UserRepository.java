package com.precize.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.precize.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	
	@Query("Select u from User u")
	List<User> getAllUser();
	
	@Query("Select u from User u order by u.satScore desc")
	List<User> getRankList();

}
