package com.learning.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.learning.models.Student;


public class ReportingTest {
	
	private static void testListSorting() {
		Student a3 = new Student("1","a3",null);
		Student a2 = new Student("1","a2",null);
		Student a1 = new Student("1","a1",null);
		
		Student b3 = new Student("2","b3",null);
		Student b2 = new Student("2","b2",null);
		Student b1 = new Student("2","b1",null);
		
		
		List<Student> list = Arrays.asList(a2,a3,a1,b2,b3,b1);
		
		System.out.println(list);
		
		Map<String,List<Student>> map= new HashMap<>();
	
		
		for(Student row : list) {
			//System.out.println(row);
			if(map.containsKey(row.getId())) {
				List<Student> objectList = map.get(row.getId());
				//System.out.println(objectList);
				objectList.add(row);
				map.put(row.getId(), objectList);
			} else {
				map.put(row.getId(), new ArrayList<>(Arrays.asList(row)));
			}
		}
		
		System.out.println(map);
		
		for(String key : map.keySet()) {
			List<Student> list2 = map.get(key);
			list2.sort(Comparator.comparing(Student::getName).reversed());
		}
		
		System.out.println(map);
		
	}
	
	public static void main(String [] args) {
		testListSorting();
	}
}
