package com.india.railway.utility;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewFileWithKeyValuePair {
    public static void main(String[] args) {
    	 // Create a new Map to store key-value pairs
        Map<String, List<String>> keyValuePairs = new HashMap<>();

        // Add key-value pairs (including multiple values for a single key)
        addKeyValuePair(keyValuePairs, "key1", "value1,value3");
        addKeyValuePair(keyValuePairs, "key2", "value2");
        addKeyValuePair(keyValuePairs, "key3", "value3");
        addKeyValuePair(keyValuePairs, "key1", "value4"); // Adding multiple values for key1

        // Specify the file path
        String filePath = "newKeyValuePairs.txt";

        // Write properties to a new file
        try (OutputStream outputStream = new FileOutputStream(filePath)) {
            writeKeyValuePairsToFile(keyValuePairs, outputStream);
            System.out.println("New key-value pairs successfully added to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error adding new key-value pairs to file: " + e.getMessage());
            e.printStackTrace();
        }
        addKeyValuePair(keyValuePairs, "key1", "value4"); // Adding multiple values for key1


       /* // Specify the key you want to access
        String keyToAccess = "key1";

        // Read the specified key and its values from the file
        List<String> values = readValuesForKey(filePath, keyToAccess);

        // Print the values
        if (values != null) {
            System.out.println("Values for key '" + keyToAccess + "': " + values);
        } else {
            System.out.println("Key '" + keyToAccess + "' not found in the file.");
        }*/
    }

    // Method to add a key-value pair (including multiple values for a single key)
    private static void addKeyValuePair(Map<String, List<String>> map, String key, String value) {
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    // Method to write key-value pairs to the output stream
	private static void writeKeyValuePairsToFile(Map<String, List<String>> map, OutputStream outputStream)
			throws IOException {
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<String> values = entry.getValue();
			for (String value : values) {
				String keyValueString = key + "=" + value + System.lineSeparator();
				outputStream.write(keyValueString.getBytes());
			}
		}
	}
	
	 private static List<String> readValuesForKey(String filePath, String key) {
	        List<String> values = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split("=", 2);
	                if (parts.length == 2) {
	                    String fileKey = parts[0].trim();
	                    String value = parts[1].trim();
	                    if (fileKey.equals(key)) {
	                        values.add(value);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            System.err.println("Error reading values for key '" + key + "' from file: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return values.isEmpty() ? null : values;
	    }
 }
