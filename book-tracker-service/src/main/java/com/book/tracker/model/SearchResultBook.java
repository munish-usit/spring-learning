package com.book.tracker.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResultBook {

	private String key;
    private String title;
    private List<String> author_name;
    private String cover_i;
    private int first_publish_year;
}
