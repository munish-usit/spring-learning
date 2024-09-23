package com.learning.test;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class GsonTesting {

	public static void createJson(Book book) {
		String json = new Gson().toJson(book);
		System.out.println(json);
	}
	public static void writeJson(Book book) {
		Gson gson = new Gson();
		Writer writer;
		try {
			writer = Files.newBufferedWriter(Paths.get("src/test/resources/book.json"));
			gson.toJson(book, writer);
			writer.close();
			System.out.println("writing done");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void readJson() {
		Gson gson = new Gson();
		try {
			Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/book.json"));
		    // convert JSON string to Book object
		    Book book = gson.fromJson(reader, Book.class);
		    // print book
		    String json = gson.toJson(book);
		    System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		//Book book = new Book("Thinking in Java2", "978-0131872486", 1998, new String[] { "Bruce Eckel" });
		//writeJson(book);
		readJson();
	}
}
