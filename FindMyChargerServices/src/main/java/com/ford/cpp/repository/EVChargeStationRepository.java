package com.ford.cpp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EVChargeStationRepository extends JpaRepository<EVChargeStation, Long>{

}
