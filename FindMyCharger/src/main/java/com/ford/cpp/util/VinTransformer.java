package com.ford.cpp.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ford.cpp.contract.ChargingStationContract;
import com.ford.cpp.contract.VinChargeStatusContract;
import com.ford.cpp.contract.VinOwnerContract;
import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.entities.VinChargeStatus;
import com.ford.cpp.entities.VinOwner;

@Component
public class VinTransformer {
	
//	public List<ChargingStationContract> transformResponse(List<ChargingStation> stationList)
//	{
//		
//		
//		List<ChargingStationContract> responseList = new ArrayList<ChargingStationContract>();
//		for(ChargingStation station: stationList)
//		{
//			ChargingStationContract response = new ChargingStationContract();
//			response.setId(station.getId());
//			response.setName(station.getName());
//			response.setLatitude(station.getLatitude());
//			response.setLongitude(station.getLongitude());
//			response.setStatus(station.isStatus());
//			response.setUsageCounter(station.getUsageCounter());
//			response.setCity(station.getCity());
//			responseList.add(response);
//		}
//		 return responseList;
//	}
//	
//	public List<ChargingStation> transformRequest(List<ChargingStationContract> stationContractList)
//	{
//		List<ChargingStation> entityList = new ArrayList<ChargingStation>();
//		
//		for(ChargingStationContract station: stationContractList)
//		{
//			entityList.add(transformRequest(station));
//		}
//		 return entityList;
//	}
	
	public VinOwner transformRequest(VinOwnerContract contract)
	{
			VinOwner entity = new VinOwner();
			entity.setOwner(contract.getOwner());
			entity.setVin(contract.getVin());
			return entity;
	}
	
	public VinChargeStatus transformRequest(VinChargeStatusContract contract)
	{
		VinChargeStatus entity = new VinChargeStatus();
			entity.setChargePct(contract.getChargePct());
			entity.setVin(contract.getVin());
			entity.setChargerId(contract.getChargerId());
			entity.setStatus(contract.isStatus());
			return entity;
	}
}
