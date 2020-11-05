package com.example.loadbance.ribbon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.jni.Time;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RibbonClient(name = "load")
public class Empcontroller {
	/*
	 * @Autowired private IPAdressTrack ipadresstrack;
	 */
	@Autowired
	  RestTemplate restTemplate;

	@GetMapping("/Emp/{id}")
	public List<String> Employer(@PathVariable("id") int id) {
		getEmployees(id);
		return getEmployees(id);

	}

	public List<String> getEmployees(int id) {
		List<String> li = new ArrayList<String>();
		String uri = "http://load/talcott/stfindid/" + id;

		RestTemplate restTemplate = new RestTemplate();
		String str = restTemplate.getForObject(uri, String.class);
		JSONObject bookList = new JSONObject(str);

		System.out.println(bookList);
		li.add(bookList.get("mailid").toString());
		li.add(bookList.get("firstname").toString());
		System.out.println();
		return li;
	}

	@GetMapping("/test")
	public String Employer() {
		// getEmployees(id);
	//	System.out.println(ipadresstrack.getClientIp());

		System.out.println("sent");

		return "sucess";

	}
	@GetMapping("/test1")
	   public String getProductList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      String s=restTemplate.exchange("http://load/talcott/stfindall", HttpMethod.GET, entity, String.class).getBody();
     System.out.println(s);
	      return s;
	   }

}
