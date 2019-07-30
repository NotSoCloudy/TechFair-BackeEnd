package com.ford.cpp.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ford.cpp.contract.EVLocationResponse;

@RestController
public class EVLocationController {

@RequestMapping("/location")
public String getEVLocation() {
	
	System.out.println("I was called");
	return "50.50, 55.50";
}

	
}
