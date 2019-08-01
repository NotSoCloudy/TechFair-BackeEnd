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
			ChargingStationContract responseL = new ChargingStationContract();
			responseL.setId(station.getId());
			responseL.setName(station.getName());
			responseL.setLatitude(station.getLatitude());
			responseL.setLongitude(station.getLongitude());
			responseL.setStatus(station.isStatus());
			responseList.add(responseL);
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
			return entity;
	}
}
