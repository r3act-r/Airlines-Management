package com.java.AirlinesManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.AirlinesManagement.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	@Query("select u from UserEntity u where u.email =:email")
	public UserEntity getUserByUserName(@Param("email") String email);
	
	Optional<UserEntity> findByEmail(String email);

}
