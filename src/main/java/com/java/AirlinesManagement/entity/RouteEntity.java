package com.java.AirlinesManagement.entity;

import java.util.ArrayList;
import java.util.List;

import com.java.AirlinesManagement.dto.AirlineDto;
import com.java.AirlinesManagement.dto.RouteDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="Route")
public class RouteEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="three_digit")
	@SequenceGenerator(name="three_digit",sequenceName="three",initialValue=100,allocationSize=1)
	@Column(name="routeId")
	private Integer routeId;
	
	@Column(name="source")
	private String source;
	
	@Column(name="destination")
	private String destination;
	
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="routeId")
	private List<AirlineEntity>airlines;

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

	
	
	
	public List<AirlineEntity> getAirlines() {
		return airlines;
	}

	public void setAirlines(List<AirlineEntity> airlines) {
		this.airlines = airlines;
	}

	//Converting RouteEntity to RouteDto Conversion
	public RouteDto routeEntityTorouteDto(RouteEntity entity)
	{
		RouteDto route = new RouteDto();
		route.setRouteId(entity.getRouteId());
		route.setSource(entity.getSource());
		route.setDestination(entity.getDestination());
		
		List<AirlineDto> airLines= new ArrayList<>();
		List<AirlineEntity> airEnt = entity.getAirlines();
		
		if(airEnt.isEmpty()==false) {
		airEnt.forEach((c)->{
			AirlineDto airDto = new AirlineDto();
			
			airDto.setFlightId(c.getFlightId());
			airDto.setFlightName(c.getFlightName());
			airDto.setArrivalTime(c.getArrivalTime());
			airDto.setDepartureTime(c.getDepartureTime());
			airDto.setFare(c.getFare());
			
			airLines.add(airDto);
		});}
		
		route.setAirlines(airLines);
		
		return route;
	}

	@Override
	public String toString() {
		return "RouteEntity [routeId=" + routeId + ", source=" + source + ", destination=" + destination + ", airlines="
				+ airlines + "]";
	}
	
	
}
