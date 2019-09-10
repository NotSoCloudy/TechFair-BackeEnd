package com.ford.cpp.contract;

import com.ford.cpp.contract.ChargingStationContract.ChargingStationContractBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VinOwnerContract {

	private String owner;
	private String vin;
}
