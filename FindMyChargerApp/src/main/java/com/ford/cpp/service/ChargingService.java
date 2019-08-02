package com.ford.cpp.service;

import com.ford.cpp.client.SlackClient;
import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.repository.ChargingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChargingService {
	
	@Autowired
	private ChargingRepository repo;
	
	@Autowired
	private SlackClient slackClient;
	
	public List<ChargingStation> getStations()
	{
//		ChargingStation station = new ChargingStation();
//		station.setId(new Long(1));
//		station.setName("Station 1");
//		station.setLongitude(2.5);
//		station.setLatitude(3.7);
//		station.setStatus(true);
//		repo.save(station);
		
		return repo.findAll();
		
	}
	
	public void createStations(List<ChargingStation> list)
	{
		repo.saveAll(list);
	}
	
	public void updateStatus(ChargingStation station)
	{
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
