package com.ford.cpp.contract;


public class VinChargeStatusContract {
	
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
	private String vin;
	private Long chargerId;
	private double chargePct;
	private boolean status;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
