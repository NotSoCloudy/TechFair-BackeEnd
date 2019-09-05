package com.ford.cpp.repository;

import org.springframework.stereotype.Repository;

import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.entities.VinOwner;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VinOwnerRepository extends JpaRepository<VinOwner,Long> {

	Optional<List<VinOwner>> findAllByOwner(String owner); 
	Optional<VinOwner> findByVin(String vin); 


}
