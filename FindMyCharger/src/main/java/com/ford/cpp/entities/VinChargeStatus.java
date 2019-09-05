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
	private boolean status;
	
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Long getChargerId() {
		return chargerId;
	}
	public void setChargerId(Long chargerId) {
		this.chargerId = chargerId;
	}
	public double getChargePct() {
		return chargePct;
	}
	public void setChargePct(double chargePct) {
		this.chargePct = chargePct;
	}
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
