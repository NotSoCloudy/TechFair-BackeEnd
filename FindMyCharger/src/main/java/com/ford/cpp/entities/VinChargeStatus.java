package com.ford.cpp.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class VinChargeStatus {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String vin;
	private Long chargerId;
	private double chargePct;
	private boolean status=true;
	private Double timeToFullyCharge;	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

}
