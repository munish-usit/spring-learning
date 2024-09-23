package com.learning.test;

import java.util.List;

import com.learning.models.Student;

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
public class Book {

    private String title;
    private String isbn;
    private long year;
    private List<String> authors;
}