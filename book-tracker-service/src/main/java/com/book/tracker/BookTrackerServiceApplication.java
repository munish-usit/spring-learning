/**
 * Better read is like clone of good reads.
 * Store millions of book meta data, author information, each user can save bookmark, progress and see book details
 * 
 * Use cases or screens
 * Book detail page
 * Author page
 * Login page
 * My Dashboard page
 * Search page
 * 
 * System Design Components 
 * HTML + Spring Boot + Spring Security + Apache Cassandra + Open Search Library API + Data Load Utility
 * Cassandra is a NO-SQL DB, similar to dynamodb, good for look up use cases in huge data set.
 * Cassandra can't handle all search use cases. For search we need to use elastic or solr or other search engine.
 * For this project, we will be using open library api for search.
 * 
 * Data Modelling
 * get book by book ID
 * get all books by Author ID
 * get book status or progress by user ID and book ID
 * get all books by user ID
 */
package com.book.tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookTrackerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookTrackerServiceApplication.class, args);
	}

}