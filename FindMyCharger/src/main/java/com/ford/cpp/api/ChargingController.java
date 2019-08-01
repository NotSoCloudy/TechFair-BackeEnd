package com.ford.cpp.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ford.cpp.contract.ChargingStationContract;
import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.service.ChargingService;
import com.ford.cpp.util.StationTransformer;

@RestController
public class ChargingController 
{
	@Autowired
	private ChargingService service;
	@Autowired
	private StationTransformer transformer;

	@RequestMapping(value = "/station",method=RequestMethod.GET)
	public List<ChargingStationContract> getStations()
	{
		List<ChargingStation> list = service.getStations();
		
		List<ChargingStationContract> responseList = transformer.transformResponse(list);
		
		return responseList;
	}
	
	@RequestMapping(value = "/station", method=RequestMethod.POST)
	public ResponseEntity createStation(@RequestBody List<ChargingStationContract> stations)
	{
		List<ChargingStation> responseList = transformer.transformRequest(stations);
		service.createStations(responseList);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/station", method=RequestMethod.PUT)
	public ResponseEntity updateStation(@RequestBody ChargingStationContract station)
	{
		
		service.updateStatus(transformer.transformRequest(station));
		return new ResponseEntity(HttpStatus.OK);
	}
	
//	@PostMapping(path = "/station", consumes="application/json")
//	public ResponseEntity createStation(@RequestBody ChargingStationContract station)
//	{
//		List<ChargingStationContract> contractList= new ArrayList<ChargingStationContract>();
//		contractList.add(station);
//		List<ChargingStation> responseList = transformer.transformRequest(contractList);
//		service.createStations(responseList);
//		return new ResponseEntity(HttpStatus.OK);
//	}
}
