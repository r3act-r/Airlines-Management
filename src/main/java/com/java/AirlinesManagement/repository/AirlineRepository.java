package com.java.AirlinesManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.AirlinesManagement.entity.AirlineEntity;

@Repository
public interface AirlineRepository extends JpaRepository<AirlineEntity,Integer> {

}
