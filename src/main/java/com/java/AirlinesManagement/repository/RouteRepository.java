package com.java.AirlinesManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

import com.java.AirlinesManagement.entity.RouteEntity;

=======
import org.springframework.stereotype.Repository;

import com.java.AirlinesManagement.entity.RouteEntity;

@Repository
>>>>>>> 4bd299e (Added JWT)
public interface RouteRepository extends JpaRepository<RouteEntity,Integer> {

	Optional<RouteEntity> findBySourceAndDestination(String source,String destination);
}
