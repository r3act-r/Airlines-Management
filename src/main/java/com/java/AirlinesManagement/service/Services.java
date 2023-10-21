package com.java.AirlinesManagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.AirlinesManagement.dto.AirlineDto;
import com.java.AirlinesManagement.dto.RouteDto;
import com.java.AirlinesManagement.entity.AirlineEntity;
import com.java.AirlinesManagement.entity.RouteEntity;
import com.java.AirlinesManagement.exceptions.AirlineException;
import com.java.AirlinesManagement.exceptions.RouteException;
import com.java.AirlinesManagement.repository.AirlineRepository;
import com.java.AirlinesManagement.repository.RouteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class Services {
	
	@Autowired
	public RouteRepository routeRepo;
	
	@Autowired
	public AirlineRepository airRepo;
	
	public int addRoute(RouteDto dto ) throws RouteException{
		
		Optional<RouteEntity> optional = routeRepo.findBySourceAndDestination(dto.getSource(),dto.getDestination());
		if(optional.isPresent())
		{
			throw new RouteException("Route already exist in this path");
		}
		RouteEntity route = dto.routeDtoToEntity(dto);
		routeRepo.save(route);
		return route.getRouteId();
	}

	public RouteDto getRouteById(int routeId) throws RouteException
	{
		Optional<RouteEntity> r = routeRepo.findById(routeId);
		
		RouteEntity route= r.orElseThrow(()-> new RouteException("Route Not Found"));
		RouteDto rt= route.routeEntityTorouteDto(route);
		return rt;
		
	}
	
	public RouteDto getRouteBySourceAndDestination(String source,String destination) throws RouteException{
		
		Optional<RouteEntity> optional = routeRepo.findBySourceAndDestination(source, destination);
		
		RouteEntity route= optional.orElseThrow(()-> new RouteException("Route Not Found"));
		RouteDto rt= route.routeEntityTorouteDto(route);
		return rt;
		
	}
	
	public int updateSourceAndDestination(int routeId,String source,String destination)throws RouteException{
		
		Optional<RouteEntity> optional = routeRepo.findById(routeId);
		
		RouteEntity route = optional.orElseThrow(()-> new RouteException("Route Not Found"));	
		route.setSource(source);
		route.setDestination(destination);
		routeRepo.saveAndFlush(route);	
		return routeId;
	}
	
	public void updateFlightsInRoute(int routeId, AirlineDto dto)throws RouteException,AirlineException
	{
		Optional<RouteEntity> optional = routeRepo.findById(routeId);
		
		RouteEntity route = optional.orElseThrow(()-> new RouteException("Route Not Found"));
		
		List<AirlineEntity> airline = route.getAirlines();
		boolean done= false;
		
		for(AirlineEntity air :airline) {
			if(air.getFlightId()==dto.getFlightId())
			{
				if(dto.getFlightName()!=null)
				{
					air.setFlightName(dto.getFlightName());
				}
				if(dto.getArrivalTime()!=null)
				{
					air.setArrivalTime(dto.getArrivalTime());
				}
				if(dto.getDepartureTime()!=null)
				{
					air.setDepartureTime(dto.getDepartureTime());
				}
				if(dto.getFare()>0)
				{
					air.setFare(dto.getFare());
				}
				done=true;
			}
		}
		
		if(done)
		{
			routeRepo.save(route);
		}
		else
		{
			throw new AirlineException("AirLine Not Found");
		}	
		
	}
	
	public void deleteAirlinesInRoute(int routeId, int flightId) throws RouteException,AirlineException {
		
		Optional<RouteEntity> optional = routeRepo.findById(routeId);
		RouteEntity route = optional.orElseThrow(()-> new RouteException("Route Not Found") );
		 
		List<AirlineEntity> airlines = route.getAirlines();
		Optional<AirlineEntity>op = Optional.empty();
		for(AirlineEntity air : airlines) {
			
			if(air.getFlightId()==flightId) {
				op= Optional.of(air);
			}
		}
		
		if(op.isPresent())
		{
			airlines.remove(op.get());
			route.setAirlines(airlines);
			routeRepo.saveAndFlush(route);
			airRepo.deleteById(flightId);
			
		}
		else
		{
			throw new AirlineException("Flight Not Found");
		}
		 
	}
	
	public int addFlight(AirlineDto dto) throws AirlineException{
		
		AirlineEntity airline = dto.airlineDtoToEntity(dto);
		airRepo.save(airline);
		return airline.getFlightId();
	}
	
	public void updateFare(int flightId, AirlineDto airlinedto) throws AirlineException{
		
		Optional<AirlineEntity> optional= airRepo.findById(flightId);
		
		AirlineEntity airlines = optional.orElseThrow(()-> new AirlineException("Airline Not Found"));
		
		if(airlinedto.getFare()>0)
		{
			airlines.setFare(airlinedto.getFare());
			airRepo.saveAndFlush(airlines);
		}
		else
		{
			throw new AirlineException("Please provide valid fare");
		}
	}
	
}
