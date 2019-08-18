package com.ford.cpp.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ChargingStation 
{
	@Id
	private Long id;
	
	private String name;
	
	private boolean status;
	
	private double latitude;
	
	private double longitude;
}
