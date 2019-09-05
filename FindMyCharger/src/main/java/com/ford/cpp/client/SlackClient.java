package com.ford.cpp.client;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackClient {
	
	public static final String url = "https://hooks.slack.com/services/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";
	public static final String token = "xoxp-701941766275-706995135489-739682545072-23599d7bfa6df199918fab2d974d3b35";
	
	
	public void postMessage(String msg)
	{
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",token);
		RestTemplate rest = new RestTemplate();
		HttpEntity request = new HttpEntity(new SlackMessage(msg), headers);
		ResponseEntity response = rest.postForEntity(url,request,String.class);
		
		System.out.println("RESPONSE FROM SLACK: "+response);
	}
	
}
