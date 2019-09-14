package com.duplicate.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class LevenshteinService {
	
	private Set<String[]> setDuplicate = new HashSet<String[]>();
	private Set<String[]> setNonDuplicate = new HashSet<String[]>();
	
	public Set<String[]> getSetDuplicate() {
		return setDuplicate;
	}
	public Set<String[]> getSetNonDuplicate() {
		return setNonDuplicate;
	}

	public void findDuplicate(List<Map<String, String>> list) {
		
		List<Map<String, String>> allData  = list;
		
		int dataSize = allData.size();
		
		for(int i = 0; i < dataSize -1; i++) {
			Map<String, String> record = allData.get(i);
			for(int j =i+1; j < dataSize ; j++) {
			Map<String, String> nextrecord = allData.get(j);
			
			String firstRecord[] = new String[record.size()];
			String secondRecord[] = new String[record.size()];
			int var1  =  0, var2 = 0;
				for(Map.Entry<String, String> entry : record.entrySet()) {
					firstRecord[var1] = entry.getValue();
					var1++;
					
				}
				for(Map.Entry<String, String> entry : nextrecord.entrySet()) {
					secondRecord[var2] = entry.getValue();
					var2++;
				}
			int column = 0;	
			int distance = Integer.MIN_VALUE;
				
			for(int k = 2; k <=6; k++) {
				distance = LevenshteinDistance.calculate(firstRecord[k].toLowerCase(), secondRecord[k].toLowerCase());
				if(distance <= 3) {
					column++;
				}
			}
			
			if(column >=3) {
				setDuplicate.add(firstRecord);
				break;
			}
			else {
				setNonDuplicate.add(firstRecord);
				break;
				
			}
			}
		}
		
	}
}
