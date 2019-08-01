package com.ford.cpp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
