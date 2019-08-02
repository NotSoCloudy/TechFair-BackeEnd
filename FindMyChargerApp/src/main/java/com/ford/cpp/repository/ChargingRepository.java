package com.ford.cpp.repository;

import com.ford.cpp.entities.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingRepository extends JpaRepository<ChargingStation,Long>{
	
	

}
