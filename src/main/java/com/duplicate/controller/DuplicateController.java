package com.duplicate.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duplicate.helper.FileUpload;
import com.duplicate.service.LevenshteinService;
import com.google.gson.GsonBuilder;

@RestController
public class DuplicateController {
	
	@Autowired
	FileUpload fileUpload;
	
	@Autowired
	LevenshteinService levenshteinService; 
	
	@GetMapping("/viewResult")
	public ResponseEntity<String> handleCSV() {
		
		File uploaded = new File("C:\\Users\\harsh\\Downloads\\Validity_take_home_exercise\\normal.csv");
		List<Map<String, String>> data = fileUpload.readAll(uploaded);
	
		
		levenshteinService.findDuplicate(data);
		Set<String[]> setDuplicate  = levenshteinService.getSetDuplicate();
		Set<String[]> setNonDuplicate  = levenshteinService.getSetNonDuplicate();
		StringBuilder sb = new StringBuilder();
		  
        JSONObject json = new JSONObject();
        JSONArray jar = new JSONArray();
        jar.put("Duplicate");
        
        for (String str[] : setDuplicate) { 
        	sb.setLength(0);
        	json = new JSONObject();
        	for(String innerArray : str) {
        		sb.append(innerArray);
        		sb.append(",");
        	}
        	try {
				json.put(sb.toString(), 0);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	jar.put(json);
        }
        jar.put("Non Duplicate");
        for (String str[] : setNonDuplicate) { 
        	sb.setLength(0);
        	json = new JSONObject();
        	for(String innerArray : str) {
        		sb.append(innerArray);
        		sb.append(",");
        	}
        	try {
				json.put(sb.toString(), 0);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	jar.put(json);
        	
        }
        
		String jsonAttachment=new GsonBuilder().setPrettyPrinting().create().toJson(jar.toString());
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<String>(jsonAttachment, responseHeaders, HttpStatus.ACCEPTED);
		
	}

}
