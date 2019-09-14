package com.duplicate.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
@Service
public class FileUpload {
	static List<String> columns = null;
	public static final String UTF8_BOM = "\uFEFF";

	public static List<Map<String, String>> readAll(File file) {
		List<Map<String, String>> allRecords = new ArrayList<>();
		try (FileInputStream fileInputStream = new FileInputStream(file);
				InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
				CSVReader reader = new CSVReader(inputStreamReader)) {
			
			String[] columnNames;
			if ((columnNames = reader.readNext()) != null) {
				if (columnNames[0].startsWith(UTF8_BOM)) {
					columnNames[0] = columnNames[0].substring(1);
				}
				columns = Arrays.asList(columnNames);
			}
			String[] next;
			int counter = 0;

			while ((next = reader.readNext()) != null) {
				List<String> list = Arrays.asList(next);
				Map<String, String> entry = new LinkedHashMap<>();
				entry.put("index", String.valueOf(++counter));
				for (int i = 0; i < list.size(); i++) {
					entry.put(columns.get(i), list.get(i).isEmpty() ? "" : list.get(i));
				}
				allRecords.add(entry);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allRecords;
	}
	
}
