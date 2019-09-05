package com.ford.cpp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ford.cpp.client.SlackClient;
import com.ford.cpp.contract.VinChargeStatusContract;
import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.entities.VinChargeStatus;
import com.ford.cpp.entities.VinOwner;
import com.ford.cpp.repository.ChargingStationRepository;
import com.ford.cpp.repository.VinChargerRepository;
import com.ford.cpp.repository.VinOwnerRepository;


@Component
public class VehicleService {
	@Autowired
	private ChargingStationRepository chargingRepo;
	@Autowired
	private VinOwnerRepository vehicleRepo;
	@Autowired
	private VinChargerRepository vinChargerRepo;
	@Autowired
	private SlackClient slackClient;

	
	public void updateStatus(VinChargeStatus chargeStatus)
	{
		ChargingStation stn= chargingRepo.findById(chargeStatus.getChargerId()).orElse(new ChargingStation());
		stn.setUsageCounter(stn.getUsageCounter()+1);
		
		chargingRepo.save(stn);
		
		VinChargeStatus status = vinChargerRepo.findByChargerId(chargeStatus.getChargerId()).orElse(new VinChargeStatus());
		status.setVin(chargeStatus.getVin());
		status.setChargePct(chargeStatus.getChargePct());
		status.setChargerId(chargeStatus.getChargerId());
		
		vinChargerRepo.save(status);
		
		VinOwner owner = vehicleRepo.findByVin(chargeStatus.getVin()).orElse(new VinOwner());
		
	   if(chargeStatus.isStatus())
		{
		   slackClient.postMessage("Charger "+stn.getName()+" is now available."+owner.getOwner()+" unplugged just now");
		}
		else
		{
			slackClient.postMessage("Charger "+stn.getName()+" is now in use."+owner.getOwner()+" plugged in just now");			
		}
		
	}
	
	public void registerVIN(VinOwner owner)
	{
		if(owner!=null)
		vehicleRepo.save(owner);
	}
}