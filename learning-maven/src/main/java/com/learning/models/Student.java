package com.learning.models;

import java.util.List;

import com.learning.test.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student {

	private String id;
	private String name;
	private List<Book> books;
}
