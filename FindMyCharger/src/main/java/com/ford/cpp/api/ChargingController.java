package com.ford.cpp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ford.cpp.ChargingStatusDomain;
import com.ford.cpp.contract.ChargingStationContract;
import com.ford.cpp.contract.VinChargeStatusContract;
import com.ford.cpp.contract.VinOwnerContract;
import com.ford.cpp.entities.ChargingStation;
import com.ford.cpp.service.ChargingService;
import com.ford.cpp.service.VehicleService;
import com.ford.cpp.util.StationTransformer;
import com.ford.cpp.util.VinTransformer;

@RestController
public class ChargingController 
{
	@Autowired
	private ChargingService chargingService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private StationTransformer transformer;
	@Autowired
	private VinTransformer vinTransformer;

	@GetMapping(value = {"/station","/station/{city}"})
	public List<ChargingStatusDomain> getStations(@PathVariable(required=false) String city)
	{
		System.out.println("Invoked the Get Stations API: "+city);
		List<ChargingStatusDomain> list = chargingService.getStationsWithChargeStatus(city);
		
		//List<ChargingStationContract> responseList = transformer.transformResponse(list);
		
		return list;
	}
	
	@RequestMapping(value = "/station", method=RequestMethod.POST)
	public ResponseEntity createStation(@RequestBody List<ChargingStationContract> stations)
	{
		System.out.println("Invoked the Create Stations API");
		List<ChargingStation> responseList = transformer.transformRequest(stations);
		chargingService.createStations(responseList);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/station", method=RequestMethod.PUT)
	public ResponseEntity updateStation(@RequestBody VinChargeStatusContract chargeStatus)
	{
		System.out.println("Invoked the PUT Stations API");
		vehicleService.updateStatus(vinTransformer.transformRequest(chargeStatus));
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/station", method=RequestMethod.DELETE)
	public ResponseEntity deleteStation()
	{
		System.out.println("Invoked the DELETE ALL Stations API");
		vehicleService.deleteAllStations();
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/vinowner", method=RequestMethod.POST)
	public ResponseEntity registerVIN(@RequestBody VinOwnerContract vinOwner)
	{
		System.out.println("Invoked the Register CAR API");
		vehicleService.registerVIN(vinTransformer.transformRequest(vinOwner));
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
