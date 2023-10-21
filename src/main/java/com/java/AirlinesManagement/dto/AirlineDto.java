package com.java.AirlinesManagement.dto;

import com.java.AirlinesManagement.entity.AirlineEntity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AirlineDto {
	


	private Integer flightId;
	
	@NotNull(message="Flight Name can't be Null")
	@Pattern(regexp="[A-Z]+([a-z])*",message="Flight Name is Invalid")
	private String flightName;
	
	@NotNull(message="Arrival Time can't be NULL")
	@Pattern(regexp="([01]?[0-9]|[2][0-3]):[0-5][0-9]",message="Arrival Time is Invalid")
	private String arrivalTime;
	
	@NotNull(message="Departure Time can't be NULL")
	@Pattern(regexp="([01]?[0-9]|[2][0-3]):[0-5][0-9]",message="Departure Time is Invalid")
	private String departureTime;
	
	@NotNull(message="Fare can't be NULL")
	@Min(value=1500,message="Fare value is Invalid")
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
	
	
	//AirlineDto to Airline Entity Conversion
	public AirlineEntity airlineDtoToEntity(AirlineDto dto)
	{
		AirlineEntity airLines = new AirlineEntity();
		airLines.setFlightId(dto.getFlightId());
		airLines.setFlightName(dto.getFlightName());
		airLines.setArrivalTime(dto.getArrivalTime());
		airLines.setDepartureTime(dto.getDepartureTime());
		airLines.setFare(dto.getFare());
		
		return airLines;
	}
	
	@Override
	public String toString() {
		return "AirlineDto [flightId=" + flightId + ", flightName=" + flightName + ", arrivalTime=" + arrivalTime
				+ ", departureTime=" + departureTime + ", fare=" + fare + "]";
	}
}
