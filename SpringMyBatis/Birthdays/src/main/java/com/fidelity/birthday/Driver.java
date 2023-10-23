package com.fidelity.birthday;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Driver {

	public static void main(String[] args) {
		Map<String, Birthday> birthdayMap = new HashMap<>();
		
		birthdayMap.put("Alexander Hamilton", new Birthday(LocalDate.of(1757, 1, 11), "Charlestown, Saint Kitts and Nevis"));
		birthdayMap.put("Winston Churchill", new Birthday(LocalDate.of(1874, 11, 30), "Blenheim Palace, Woodstock, United Kingdom"));
		birthdayMap.put("Jawaharlal Nehru", new Birthday(LocalDate.of(1889, 11, 14), "Allahabad, India"));
		birthdayMap.put("Nelson Mandela", new Birthday(LocalDate.of(1918, 7, 18), "Mvezo, South Africa"));
		birthdayMap.put("Charlemagne", new Birthday(LocalDate.of(742, 4, 02), "Aachen, Germany"));
		
		for(Map.Entry<String, Birthday> entry : birthdayMap.entrySet()) {
			System.out.println("NAME : " + entry.getKey() + " BIRTH DATE : " + entry.getValue().getDateOfBirth() + " PLACE OF BIRTH : " + entry.getValue().getPlaceOfBirth());
		}
	}
}
