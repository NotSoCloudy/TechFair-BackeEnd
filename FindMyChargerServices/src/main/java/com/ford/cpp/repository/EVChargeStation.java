package com.ford.cpp.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class EVChargeStation {
	
	@Id
	Long id;
	double longitude;
	double latitude;
	
}
