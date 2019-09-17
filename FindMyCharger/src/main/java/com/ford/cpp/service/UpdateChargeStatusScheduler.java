package com.ford.cpp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ford.cpp.client.SlackClient;
import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.entities.VinChargeStatus;
import com.ford.cpp.repository.ChargingStationRepository;
import com.ford.cpp.repository.VinChargerRepository;

@Component
public class UpdateChargeStatusScheduler {
	
	@Autowired
	private VinChargerRepository vinChargerRepo;
	

	@Autowired
	private ChargingStationRepository chargingRepo;
	
	
	@Autowired
	private SlackClient slackClient;
	
	@Scheduled(fixedRate=60000)
	public void updateChargeStatus()
	{
		System.out.println("Triggered the Scheduler");
		List<VinChargeStatus> statusList = vinChargerRepo.findAll();
		List<VinChargeStatus> updatedList = new ArrayList<VinChargeStatus>();
		
		for(VinChargeStatus status: statusList)
		{
			ChargingStation stn= chargingRepo.findById(status.getChargerId()).orElse(new ChargingStation());
			Double chargePct = status.getChargePct();	
			System.out.println("Charge name:"+stn.getName()+" %: "+chargePct);
			if(chargePct<100 && !status.isStatus())
			{
				chargePct+=5;
				System.out.println("Inside <100 :"+stn.getName()+" %: "+chargePct);

			}
			else if(chargePct>=100)
			{
				System.out.println("Inside >=100 :"+stn.getName()+" %: "+chargePct);
				chargePct= Double.valueOf(100);
				slackClient.postMessage("Charger *"+stn.getName()+"* is now *IN USE BUT NOT CHARGING*");
			}
			else if (status.isStatus())
			{
				System.out.println("Inside isStatus :"+stn.getName()+" %: "+chargePct);
				chargePct = Double.valueOf(0);
			}
			status.setChargePct(chargePct);
			updatedList.add(status);
		}
		vinChargerRepo.saveAll(updatedList);
	}

}
