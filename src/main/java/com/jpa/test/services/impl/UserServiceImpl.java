package com.jpa.test.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.jpa.test.entities.User;
import com.jpa.test.payloads.UserDto;
import com.jpa.test.services.UserService;
import com.jpa.test.repositories.*;
import com.jpa.test.exceptions.ResourceNotFoundException ;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo ;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto) ;
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userid) {
		User user = this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","id ",userId));
		
		user.setName(userDto.getName()); 
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser = this.userRepo.save(user);
		return this.userToDto(updatedUser) ;
	}

	@Override
	public UserDto getUserById(Integer userid) {
		
		User user = this.userRepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User","id ",userId));
		
		return this.userToDto(user) ;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll() ;
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user).collect(Collectors.toList()))
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub

	}
	
	private User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId()) ;
		user.setName(userDto.getName()); 
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user ; 
	}
	
	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId()) ;
		userDto.setName(user.getName()); 
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto ; 
	}

}
