package com.java.AirlinesManagement.dto;

import java.util.ArrayList;
import java.util.List;

import com.java.AirlinesManagement.entity.AirlineEntity;
import com.java.AirlinesManagement.entity.RouteEntity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RouteDto {

	private Integer routeId;
	
	@NotNull(message="Route Source Can't be Null")
	@Pattern(regexp="[A-Z]+([a-z])*",message="Route Source is Invalid")
	private String source;
	
	@NotNull(message="Route Destination can't be Null")
	@Pattern(regexp="[A-Z]+([a-z])*",message="Route Destination is Invalid")
	private String destination;
	
	
	@NotEmpty(message="Airlines Details on the route are not present")
	@Valid
	private List<AirlineDto>airlines;
	
	
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	public List<AirlineDto> getAirlines() {
		return airlines;
	}
	public void setAirlines(List<AirlineDto> airlines) {
		this.airlines = airlines;
	}
	
	//Converting RouteDto to RouteEntity
	public RouteEntity routeDtoToEntity(RouteDto dto) {
		
		RouteEntity route = new RouteEntity();
		
		route.setRouteId(dto.getRouteId());
		route.setSource(dto.getSource());
		route.setDestination(dto.getDestination());
		
		List<AirlineEntity>airLines = new ArrayList<>();
		
		List<AirlineDto> airDto = dto.getAirlines();
		
		airDto.forEach((c)->{
			AirlineEntity airEnt = new AirlineEntity();
			airEnt.setFlightId(c.getFlightId());
			airEnt.setArrivalTime(c.getArrivalTime());
			airEnt.setFlightName(c.getFlightName());
			airEnt.setDepartureTime(c.getDepartureTime());
			airEnt.setFare(c.getFare());
			
			airLines.add(airEnt);
		});
		
		route.setAirlines(airLines);
		return route;
	}
	@Override
	public String toString() {
		return "RouteDto [routeId=" + routeId + ", source=" + source + ", destination=" + destination + ", airlines="
				+ airlines + "]";
	}
}
