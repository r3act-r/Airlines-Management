package com.java.AirlinesManagement.entity;

import com.java.AirlinesManagement.dto.AirlineDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Airlines")
public class AirlineEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="flightId")
	private Integer flightId;
	
	@Column(name="flightName")
	private String flightName;
	
	@Column(name="arrivalTime")
	private String arrivalTime;
	
	@Column(name="departureTime")
	private String departureTime;
	
	@Column(name="fare")
	private double fare;
	
	
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	
	//Airline Entity to AirlineDto Conversion
	
	public AirlineDto airlineEntityToAirlineDto(AirlineEntity entity)
	{
		AirlineDto airLine = new AirlineDto();
		airLine.setFlightId(entity.getFlightId());
		airLine.setFlightName(entity.getFlightName());
		airLine.setArrivalTime(entity.getArrivalTime());
		airLine.setDepartureTime(entity.getDepartureTime());
		airLine.setFare(entity.getFare());
		
		return airLine;
	}
	@Override
	public String toString() {
		return "AirlineEntity [flightId=" + flightId + ", flightName=" + flightName + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", fare=" + fare + "]";
	}
	
	

}
