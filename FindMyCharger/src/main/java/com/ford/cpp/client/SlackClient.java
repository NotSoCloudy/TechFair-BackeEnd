package com.ford.cpp.client;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackClient {
	
	public static final String url = "https://hooks.slack.com/services/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";
	public static final String token = "xoxp-701941766275-706995135489-712871617140-93c6fe935a3ea40177d6d687bcc1a0c4";
	
	
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
