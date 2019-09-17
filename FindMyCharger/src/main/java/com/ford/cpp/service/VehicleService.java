package com.ford.cpp.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.jupiter.api.Test;
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
		stn.setStatus(chargeStatus.isStatus());
		
		chargingRepo.save(stn);
		
		VinChargeStatus status = vinChargerRepo.findByChargerId(chargeStatus.getChargerId()).orElse(new VinChargeStatus());
		status.setVin(chargeStatus.getVin());
		status.setChargePct(chargeStatus.getChargePct());
		status.setChargerId(chargeStatus.getChargerId());
		status.setStatus(chargeStatus.isStatus());
		
		vinChargerRepo.save(status);
		
		VinOwner owner = vehicleRepo.findByVin(chargeStatus.getVin()).orElse(new VinOwner());
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		 
		LocalDateTime today = LocalDateTime.now();

		ZoneId id = ZoneId.of("America/Detroit");
		ZonedDateTime zonedDateTime = ZonedDateTime.of(today, id).minusHours(4);      //That's how you add timezone to date
		 
		String formattedDateTime = DateTimeFormatter
		                            .ofPattern("MM-dd-yyyy hh:mm a")
		                            .format(zonedDateTime);         
		

		
	   if(chargeStatus.isStatus())
		{
		   slackClient.postMessage("Charger *"+stn.getName()+"* is now *AVAILABLE*. "+owner.getOwner()+" unplugged at: "+formattedDateTime);
		}
		else
		{
			slackClient.postMessage("Charger *"+stn.getName()+"* is now *IN USE*. "+owner.getOwner()+" plugged in at: "+formattedDateTime);			
		}
		
	}
	
	public void registerVIN(VinOwner owner)
	{
		if(owner!=null)
		vehicleRepo.save(owner);
	}
	
	public void deleteAllStations()
	{
		chargingRepo.deleteAll();
		vinChargerRepo.deleteAll();
		vehicleRepo.deleteAll();
	}
	
}