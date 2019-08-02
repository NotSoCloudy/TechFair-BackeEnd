package com.ford.cpp.client;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackClient {
	
	public static final String url = "https://hooks.slack.com/services/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";
	public static final String token = "xoxp-701810534883-701810536403-713116196900-e5f21b76ae14891240c41df590527649";
	
	
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
