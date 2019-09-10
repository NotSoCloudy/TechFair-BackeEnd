package com.ford.cpp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ford.cpp.entities.VinChargeStatus;
import com.ford.cpp.repository.VinChargerRepository;

@Component
public class UpdateChargeStatusScheduler {
	
	@Autowired
	private VinChargerRepository vinChargerRepo;
	
	@Scheduled(fixedRate=60000)
	public void updateChargeStatus()
	{
		System.out.println("Triggered the Scheduler");
		List<VinChargeStatus> statusList = vinChargerRepo.findAll();
		List<VinChargeStatus> updatedList = new ArrayList<VinChargeStatus>();
		
		for(VinChargeStatus status: statusList)
		{
			Double chargePct = status.getChargePct();			
			if(chargePct<100 && !status.isStatus())
			{
				chargePct+=5;
			}
			else if(chargePct>=100)
			{
				chargePct= Double.valueOf(100);
			}
			else if (status.isStatus())
			{
				chargePct = Double.valueOf(0);
			}
			status.setChargePct(chargePct);
			updatedList.add(status);
		}
		vinChargerRepo.saveAll(updatedList);
	}

}
