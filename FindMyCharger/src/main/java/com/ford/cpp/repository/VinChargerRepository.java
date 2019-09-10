package com.ford.cpp.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ford.cpp.contract.VinChargeStatusContract;
import com.ford.cpp.entities.VinChargeStatus;

@Repository
public interface VinChargerRepository extends JpaRepository <VinChargeStatus, Long>{
	
	Optional<VinChargeStatus> findByVin(String vin); 
	Optional<VinChargeStatus> findByChargerId(Long id); 
//	Optional<List<VinChargeStatus>> findAllByChargerIdIn(List<Long> idList); 
	Optional<List<VinChargeStatus>> findAllByChargerIdIn(List<Long> idList);
	

}
