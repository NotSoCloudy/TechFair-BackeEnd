package com.ford.cpp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.cpp.entities.ChargingStation;

@Repository
public interface ChargingRepository extends JpaRepository<ChargingStation,Long>{
	
	Optional<List<ChargingStation>> findAllByCity(String city); 

}
