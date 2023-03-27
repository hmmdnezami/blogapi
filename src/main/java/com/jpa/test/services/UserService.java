package com.jpa.test.services;

import java.util.List;

import com.jpa.test.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user) ;
	UserDto updateUser(UserDto user, Integer userid) ;
	UserDto getUserById(Integer userid) ;
	List<UserDto> getAllUsers(); 
	void deleteUser(Integer userId) ;
	
}
