package com.ford.cpp.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ford.cpp.client.SlackClient;
import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.repository.ChargingRepository;

@Component
public class ChargingService {
	
	@Autowired
	private ChargingRepository repo;
	
	@Autowired
	private SlackClient slackClient;
	
	public List<ChargingStation> getStations(String city)
	{
//		ChargingStation station = new ChargingStation();
//		station.setId(new Long(1));
//		station.setName("Station 1");
//		station.setLongitude(2.5);
//		station.setLatitude(3.7);
//		station.setStatus(true);
//		repo.save(station);
		if(city.equalsIgnoreCase("all"))
		return repo.findAll();
		else
		{
			List<ChargingStation> stationList = repo.findAllByCity(city).orElse(Collections.EMPTY_LIST);
			return stationList;
		}
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
