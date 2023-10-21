package com.java.AirlinesManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

import com.java.AirlinesManagement.entity.AirlineEntity;

=======
import org.springframework.stereotype.Repository;

import com.java.AirlinesManagement.entity.AirlineEntity;

@Repository
>>>>>>> 4bd299e (Added JWT)
public interface AirlineRepository extends JpaRepository<AirlineEntity,Integer> {

}
