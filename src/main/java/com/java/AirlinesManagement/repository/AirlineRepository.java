package com.java.AirlinesManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.AirlinesManagement.entity.AirlineEntity;

public interface AirlineRepository extends JpaRepository<AirlineEntity,Integer> {

}
