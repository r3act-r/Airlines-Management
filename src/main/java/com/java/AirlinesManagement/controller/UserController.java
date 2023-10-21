package com.java.AirlinesManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.AirlinesManagement.dto.AuthRequest;
import com.java.AirlinesManagement.dto.UserDto;
import com.java.AirlinesManagement.filter.JwtService;
import com.java.AirlinesManagement.service.Services;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Services service;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/register")
	public ResponseEntity<String> addUser(@Valid @RequestBody UserDto userDto)throws UsernameNotFoundException{
		
		Integer id= service.registerUser(userDto);
		return new ResponseEntity<String>("User added in database with id "+id,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(authRequest.getUsername());
		}
		else
		{
			throw new UsernameNotFoundException("Invalid user request");
		}
	}
	
}
