package com.book.tracker.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchResult {

	private int numFound;
    private List<SearchResultBook> docs;
}
