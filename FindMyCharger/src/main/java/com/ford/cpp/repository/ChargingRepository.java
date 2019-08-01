package com.ford.cpp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.cpp.entities.ChargingStation;

@Repository
public interface ChargingRepository extends JpaRepository<ChargingStation,Long>{
	
	

}
