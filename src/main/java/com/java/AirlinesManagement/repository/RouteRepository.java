package com.java.AirlinesManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.AirlinesManagement.entity.RouteEntity;

public interface RouteRepository extends JpaRepository<RouteEntity,Integer> {

	Optional<RouteEntity> findBySourceAndDestination(String source,String destination);
}
