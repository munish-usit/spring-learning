package com.learning.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.learning.models.Hosting;
import com.learning.models.Student;

public class JavaStreamTesting {

	private static void testing1() {
		List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "mkyong.com", 1));

        // key = name, value - websites , but the key 'linode' is duplicated!?
        Map<String, Long> result1 = list.stream().collect(
                Collectors.toMap(Hosting::getName, Hosting::getWebsites));
        
        Map<String, Long> result3 = list.stream().collect(
        		Collectors.toMap(
        				x -> getKey(x)
        				, 
        				x -> getValue(x)
        				)
        		);

        System.out.println("Result 3 : " + result1);
	}
	
	private  static String getKey(Hosting host) {
		return host.getName();
	}
	
	private static Long getValue(Hosting host) {
		return host.getWebsites();
	}
	
	private static void testing2() {
		List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "mkyong.com", 1));
        
        List<Student> studentList = list.stream()
        	.map(host -> getStudent(host))
        	.collect(Collectors.toList());
        System.out.println(studentList);
	}
	
	private static void testing3() {
		Book b1 = Book.builder().title("maths").authors(Arrays.asList("rd1","rd2")).build();
		Book b2 = Book.builder().title("science").authors(Arrays.asList("dr lal1","dr lal2")).build();
		
		Book b3 = Book.builder().title("ds algo").authors(Arrays.asList("robert1","rober2")).build();
		Book b4 = Book.builder().title("java").authors(Arrays.asList("oriely1","oriely2")).build();
		
		Student s1 = Student.builder().name("rahul").books(Arrays.asList(b1,b2)).build();
		Student s2 = Student.builder().name("rohit").books(Arrays.asList(b3,b4)).build();
		
		List<Student> studentList = Arrays.asList(s1,s2);
		
		List<Book> books = studentList.stream().flatMap(student -> student.getBooks().stream()).collect(Collectors.toList());

		System.out.println(books);
	}
	
	private static Student getStudent(Hosting host) {
		// TODO Auto-generated method stub
		return Student.builder().name(host.getName()).build();
	}

	public static void main(String [] args) {
		testing3();
	}
}
