package com.ford.cpp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ford.cpp.contract.ChargingStationContract;
import com.ford.cpp.entities.ChargingStation;

@Component
public class StationTransformer {
	
	public List<ChargingStationContract> transformResponse(List<ChargingStation> stationList)
	{
		
		
		List<ChargingStationContract> responseList = new ArrayList<ChargingStationContract>();
		for(ChargingStation station: stationList)
		{
			ChargingStationContract response = new ChargingStationContract();
			response.setId(station.getId());
			response.setName(station.getName());
			response.setLatitude(station.getLatitude());
			response.setLongitude(station.getLongitude());
			response.setStatus(station.isStatus());
			response.setUsageCounter(station.getUsageCounter());
			response.setCity(station.getCity());
			responseList.add(response);
		}
		 return responseList;
	}
	
	
	public List<ChargingStation> transformRequest(List<ChargingStationContract> stationContractList)
	{
		List<ChargingStation> entityList = new ArrayList<ChargingStation>();
		
		for(ChargingStationContract station: stationContractList)
		{
			entityList.add(transformRequest(station));
		}
		 return entityList;
	}
	
	public ChargingStation transformRequest(ChargingStationContract station)
	{
			ChargingStation entity = new ChargingStation();
			entity.setId(station.getId());
			entity.setName(station.getName());
			entity.setLatitude(station.getLatitude());
			entity.setLongitude(station.getLongitude());
			entity.setStatus(station.isStatus());
			entity.setCity(station.getCity());
			return entity;
	}
}
