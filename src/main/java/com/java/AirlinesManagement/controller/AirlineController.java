package com.java.AirlinesManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.AirlinesManagement.dto.AirlineDto;
import com.java.AirlinesManagement.dto.RouteDto;
import com.java.AirlinesManagement.exceptions.AirlineException;
import com.java.AirlinesManagement.exceptions.RouteException;
import com.java.AirlinesManagement.service.Services;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/flights")
public class AirlineController {

	@Autowired
	private Services service;
	
	@PostMapping
	public ResponseEntity<String> addRoutes(@Valid @RequestBody AirlineDto dto) throws AirlineException
	{
		int id = service.addFlight(dto);
		return new ResponseEntity<String>("Flight added with id : "+id,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{flightId}")
	public ResponseEntity<String> updateFare(@PathVariable int flightId,@RequestBody AirlineDto airline) throws AirlineException{
		
		service.updateFare(flightId,airline);
		return new ResponseEntity<String>("Flight Fare Updated successfully",HttpStatus.OK);
	}
}
