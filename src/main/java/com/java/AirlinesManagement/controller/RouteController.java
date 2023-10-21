package com.java.AirlinesManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.AirlinesManagement.dto.AirlineDto;
import com.java.AirlinesManagement.dto.RouteDto;
import com.java.AirlinesManagement.exceptions.AirlineException;
import com.java.AirlinesManagement.exceptions.RouteException;
import com.java.AirlinesManagement.service.Services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/routes")
@Validated
public class RouteController {
	
	@Autowired
	private Services service;
	
	@PostMapping
	public ResponseEntity<String> addRoutes(@Valid @RequestBody RouteDto dto) throws RouteException
	{
		System.out.println(dto);
		int id = service.addRoute(dto);
		return new ResponseEntity<String>("Route added with id : "+id,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{routeId}")
	public ResponseEntity<RouteDto> getRouteById(@PathVariable @Max(value= 999,message="Maximum Value can be 999") @Min(value=100,message="Minimum value can be 100") int routeId) throws RouteException{
		
		RouteDto route = service.getRouteById(routeId);
		return new ResponseEntity<RouteDto>(route,HttpStatus.FOUND);
	}
	
	@GetMapping("/flights")
	
	public ResponseEntity<RouteDto> getRouteBySourceAndDestination(@RequestParam @NotNull(message="Source can't be NULL")String source,@RequestParam @NotNull(message="Destination can't be NULL")String destination)throws RouteException{
		
		RouteDto r= service.getRouteBySourceAndDestination(source,destination);
		return new ResponseEntity<RouteDto>(r,HttpStatus.FOUND);
	}
	
	@PutMapping ("/{routeId}")
	public ResponseEntity<String> updateSourceAndDestination(@PathVariable Integer routeId, @MatrixVariable String source,@MatrixVariable String destination) throws RouteException{

			int id = service.updateSourceAndDestination(routeId,source,destination);
			return new ResponseEntity<String>("Route with Id :"+ id+" Was Updated Successfully",HttpStatus.OK);
	}
	
	@PutMapping("/{routeId}/flights")
	public ResponseEntity<String> updateFlightsInRoute(@PathVariable int routeId, @RequestBody AirlineDto airlines)throws RouteException,AirlineException{
		
		service.updateFlightsInRoute(routeId,airlines);
		
		return new ResponseEntity<String>("Airline was updated successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/{routeId}/{flightId}")
	public ResponseEntity<String>deleteAirlinesInRoute(@PathVariable int routeId,@PathVariable int flightId) throws RouteException,AirlineException{
		
		service.deleteAirlinesInRoute(routeId,flightId);
		return new ResponseEntity<String>("Flight was deleted in the route",HttpStatus.OK);
	}
	
	
}
