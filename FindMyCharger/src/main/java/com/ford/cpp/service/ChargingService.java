package com.ford.cpp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ford.cpp.ChargingStatusDomain;
import com.ford.cpp.client.SlackClient;
import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.entities.TimeToChargeStaticMap;
import com.ford.cpp.entities.VinChargeStatus;
import com.ford.cpp.repository.ChargingStationRepository;
import com.ford.cpp.repository.VinChargerRepository;

@Component
public class ChargingService {
	
	@Autowired
	private ChargingStationRepository repo;
	
	@Autowired
	private VinChargerRepository vinStatusRepo;
	
	@Autowired
	private SlackClient slackClient;
	

	public List<ChargingStation> getStations(String city)
	{
		List<ChargingStation> stationList = new ArrayList<ChargingStation>();
		
		if(city.equalsIgnoreCase("all"))
			stationList=  repo.findAll();
		else
			stationList = repo.findAllByCity(city).orElse(Collections.emptyList());
		
		return stationList;
	}
	
	public List<ChargingStatusDomain> getStationsWithChargeStatus(String city)
	{
		List<ChargingStation> stationList = getStations(city);
		List<Long> chargingIdList = new ArrayList<Long>();
		for(ChargingStation station: stationList) {
			chargingIdList.add(station.getId());
		}
		
	//	System.out.println(" --- >>>> chargingIdList: "+chargingIdList);
	
		List<VinChargeStatus> chargingStationStatusList = vinStatusRepo.findAllByChargerIdIn(chargingIdList).orElse(Collections.emptyList());
		
		return aggregateChargerStatus(stationList, chargingStationStatusList);
	}
	
	private List<ChargingStatusDomain> aggregateChargerStatus(List<ChargingStation> stationList,
			List<VinChargeStatus> chargingStationStatusList) {
		
	//	System.out.println(" --- >>>> stationList: "+stationList+" --- Charging Station List: "+chargingStationStatusList);
		List<ChargingStatusDomain> domainList = new ArrayList<ChargingStatusDomain>();
		VinChargeStatus tempStatus= null;
		ChargingStation tempStation = null;
		
		for(int counter=0;counter<stationList.size();counter++)
		{
			tempStatus = chargingStationStatusList.get(counter);
			tempStation = stationList.get(counter);
			
			ChargingStatusDomain domain = new ChargingStatusDomain();
			domain.setChargePct(tempStatus.getChargePct());
			domain.setChargerId(tempStation.getId());
			domain.setCity(tempStation.getCity());
			domain.setLatitude(tempStation.getLatitude());
			domain.setLongitude(tempStation.getLongitude());
			domain.setName(tempStation.getName());
			domain.setStatus(tempStatus.isStatus());
			domain.setUsageCounter(tempStation.getUsageCounter());
			domain.setVin(tempStatus.getVin());
			domain.setTimeToFullyCharge((Double)TimeToChargeStaticMap.TIME_TO_CHARGE_MAP.get(tempStatus.getChargePct()));
			domainList.add(domain);
		}
		return domainList;
	}

	public void createStations(List<ChargingStation> list)
	{
		repo.saveAll(list);
	}
	
	public void updateStatus(ChargingStation station)
	{
		ChargingStation stn= repo.findById(station.getId()).orElse(new ChargingStation());
		station.setUsageCounter(stn.getUsageCounter()+1);
		repo.save(station);
		if(station.isStatus())
		{
			slackClient.postMessage("Charger "+station.getName()+" is now available.");
		}
		else
		{
			slackClient.postMessage("Charger "+station.getName()+" is now in use.");			
		}
		
	}
	

}
