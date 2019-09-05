package com.ford.cpp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChargingStatusDomain {

	private String name;	
	private double latitude;
	private double longitude;
	private int usageCounter;
	private String city;
	private String vin;
	private Long chargerId;
	private double chargePct;
	private boolean status;
	
}
