package com.ford.cpp.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = {"/station","/station/{city}"})
	public List<ChargingStationContract> getStations(@PathVariable(required=false) String city)
	{
		System.out.println("Invoked the Get Stations API: "+city);
		List<ChargingStation> list = service.getStations(city);
		
		List<ChargingStationContract> responseList = transformer.transformResponse(list);
		
		return responseList;
	}
	
	@RequestMapping(value = "/station", method=RequestMethod.POST)
	public ResponseEntity createStation(@RequestBody List<ChargingStationContract> stations)
	{
		System.out.println("Invoked the Create Stations API");
		List<ChargingStation> responseList = transformer.transformRequest(stations);
		service.createStations(responseList);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/station", method=RequestMethod.PUT)
	public ResponseEntity updateStation(@RequestBody ChargingStationContract station)
	{
		System.out.println("Invoked the PUT Stations API");
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
