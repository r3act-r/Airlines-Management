package com.java.AirlinesManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
=======
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
>>>>>>> 4bd299e (Added JWT)
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.AirlinesManagement.dto.AirlineDto;
<<<<<<< HEAD
import com.java.AirlinesManagement.dto.RouteDto;
import com.java.AirlinesManagement.exceptions.AirlineException;
import com.java.AirlinesManagement.exceptions.RouteException;
=======
import com.java.AirlinesManagement.exceptions.AirlineException;
>>>>>>> 4bd299e (Added JWT)
import com.java.AirlinesManagement.service.Services;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/flights")
public class AirlineController {

	@Autowired
	private Services service;
	
	@PostMapping
<<<<<<< HEAD
=======
	@PreAuthorize("hasRole('ADMIN')")
>>>>>>> 4bd299e (Added JWT)
	public ResponseEntity<String> addRoutes(@Valid @RequestBody AirlineDto dto) throws AirlineException
	{
		int id = service.addFlight(dto);
		return new ResponseEntity<String>("Flight added with id : "+id,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{flightId}")
<<<<<<< HEAD
=======
	@PreAuthorize("hasRole('ADMIN')")
>>>>>>> 4bd299e (Added JWT)
	public ResponseEntity<String> updateFare(@PathVariable int flightId,@RequestBody AirlineDto airline) throws AirlineException{
		
		service.updateFare(flightId,airline);
		return new ResponseEntity<String>("Flight Fare Updated successfully",HttpStatus.OK);
	}
}
