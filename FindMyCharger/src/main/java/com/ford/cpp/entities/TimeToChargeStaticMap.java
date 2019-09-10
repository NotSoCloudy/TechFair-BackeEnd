package com.ford.cpp.entities;

import java.util.HashMap;

public class TimeToChargeStaticMap {

	public static final HashMap TIME_TO_CHARGE_MAP= new HashMap();
	
	static
	{
		TIME_TO_CHARGE_MAP.put(new Double(0),new Double(120));
		TIME_TO_CHARGE_MAP.put(new Double(5),new Double(110));
		TIME_TO_CHARGE_MAP.put(new Double(10),new Double(100));
		TIME_TO_CHARGE_MAP.put(new Double(15),new Double(90));
		TIME_TO_CHARGE_MAP.put(new Double(20),new Double(85));
		TIME_TO_CHARGE_MAP.put(new Double(25),new Double(75));
		TIME_TO_CHARGE_MAP.put(new Double(30),new Double(70));
		TIME_TO_CHARGE_MAP.put(new Double(35),new Double(65));
		TIME_TO_CHARGE_MAP.put(new Double(40),new Double(60));
		TIME_TO_CHARGE_MAP.put(new Double(45),new Double(55));
		TIME_TO_CHARGE_MAP.put(new Double(50),new Double(50));
		TIME_TO_CHARGE_MAP.put(new Double(55),new Double(45));
		TIME_TO_CHARGE_MAP.put(new Double(60),new Double(40));
		TIME_TO_CHARGE_MAP.put(new Double(65),new Double(35));
		TIME_TO_CHARGE_MAP.put(new Double(70),new Double(30));
		TIME_TO_CHARGE_MAP.put(new Double(75),new Double(25));
		TIME_TO_CHARGE_MAP.put(new Double(80),new Double(20));
		TIME_TO_CHARGE_MAP.put(new Double(85),new Double(15));
		TIME_TO_CHARGE_MAP.put(new Double(90),new Double(10));
		TIME_TO_CHARGE_MAP.put(new Double(95),new Double(5));
		TIME_TO_CHARGE_MAP.put(new Double(100),new Double(0));		
	}
}
