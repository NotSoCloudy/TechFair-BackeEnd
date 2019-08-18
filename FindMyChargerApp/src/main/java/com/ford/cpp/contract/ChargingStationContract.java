package com.ford.cpp.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargingStationContract {

		private String name;
		
		private Long id;
		
		private boolean status;
		
		private double latitude;
		
		private double longitude;
		
}
