package com.india.railway.utility;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;


@Component
public class Utilitys {

	

	// https://localhost:9191/railway/register
	// https://localhost:9191/railway/upload
	// https://localhost:9191/railway/authenticate
	// log file changes
	//
	// drl file computtaion
	
	public String getDateTimeBasedOnTimeZone(String timezone) {

		ZoneId zoneId = ZoneId.of("America/New_York");

		// Get the current date and time in the specified timezone
		ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

		// Define the date and time format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

		// Format the date and time
		String formattedDateTime = zonedDateTime.format(formatter);

		// Print the formatted date and time
		System.out.println("Current date and time in " + zoneId + ": " + formattedDateTime);

		return formattedDateTime;
	}
	



	    private static final String[] units = {
	        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", 
	        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", 
	        "Eighteen", "Nineteen"
	    };

	    private static final String[] tens = {
	        "",         // 0
	        "",         // 1
	        "Twenty",   // 2
	        "Thirty",   // 3
	        "Forty",    // 4
	        "Fifty",    // 5
	        "Sixty",    // 6
	        "Seventy",  // 7
	        "Eighty",   // 8
	        "Ninety"    // 9
	    };

	    public static String convertToIndianCurrency(double number) {
	        if (number == 0) {
	            return "Zero Rupees";
	        }

	        String snumber = new DecimalFormat("000000000.00").format(number);

	        int n = Integer.parseInt(snumber.substring(0, 9));  // Extract integer part
	        int paise = Integer.parseInt(snumber.substring(10, 12));  // Extract decimal part

	        String crores = convert((n / 10000000) % 100);
	        String lakhs = convert((n / 100000) % 100);
	        String thousands = convert((n / 1000) % 100);
	        String hundreds = convert((n / 100) % 10);
	        String rupees = convert(n % 100);

	        String result = "";

	        if (!crores.isEmpty()) {
	            result += crores + " Crore ";
	        }
	        if (!lakhs.isEmpty()) {
	            result += lakhs + " Lakh ";
	        }
	        if (!thousands.isEmpty()) {
	            result += thousands + " Thousand ";
	        }
	        if (!hundreds.isEmpty()) {
	            result += hundreds + " Hundred ";
	        }
	        if (!rupees.isEmpty()) {
	            result += (result.isEmpty() ? "" : "and ") + rupees;
	        }

	        result = result.trim() + " Rupees";

	        if (paise > 0) {
	            result += " and " + convert(paise) + " Paise";
	        }

	        return result;
	    }

	    private static String convert(int number) {
	        if (number == 0) {
	            return "";
	        }

	        if (number < 20) {
	            return units[number];
	        }

	        String word = tens[number / 10];
	        if (number % 10 != 0) {
	            word += " " + units[number % 10];
	        }

	        return word;
	    }

	   
	

	
	
}
