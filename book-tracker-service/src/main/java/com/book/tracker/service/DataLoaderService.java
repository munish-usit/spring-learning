package com.book.tracker.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.tracker.config.BookTrackerConfig;
import com.book.tracker.entity.Author;
import com.book.tracker.entity.Book;
import com.book.tracker.repository.AuthorRepository;
import com.book.tracker.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataLoaderService {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookTrackerConfig config;
	
	public void initAuthors() {
		log.info("reading and loading authors data {}",config.getAuthorDumpLocation());
		Path path = Paths.get(config.getAuthorDumpLocation());
		try(Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> {
				// read and parse line
				// construct author object
				// persist using repository
				
				String jsonString = line.substring(line.indexOf("{"));
				try {
					JSONObject jsonObject = new JSONObject(jsonString);
					Author author = Author.builder().id(jsonObject.optString("key").replace("/authors/",""))
									.authorName(jsonObject.optString("name"))
									.personalName(jsonObject.optString("personal_name"))
									.build();
					log.info("saving author {}",author.getAuthorName());
					authorRepository.save(author);
				} catch (JSONException e) {
					log.error("error while reading author data line {}",line);
					log.error("error while reading author data  {}",e);
				}
				
			});
		} catch (IOException e) {
			log.error("error while reading author dump {}",e);
		}
	}
	
	public void initWorks() {
		log.info("reading and loading works data {}",config.getWorkDumpLocation());
		Path path = Paths.get(config.getWorkDumpLocation());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
		try(Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> {
				// read and parse line
				// construct author object
				// persist using repository
				
				String jsonString = line.substring(line.indexOf("{"));
				try {
					JSONObject jsonObject = new JSONObject(jsonString);
					Book book = Book.builder().id(jsonObject.optString("key").replace("/works/", ""))
								.name(jsonObject.optString("title"))
								.build();
					JSONObject descriptionObject = jsonObject.optJSONObject("description");
					if(descriptionObject != null) book.setDescription(descriptionObject.optString("value"));
					
					JSONObject publishedObject = jsonObject.optJSONObject("created");
					if(publishedObject != null) book.setPublishedDate(LocalDate.parse(publishedObject.optString("value"),formatter));
					
					JSONArray coverJsonArray = jsonObject.optJSONArray("covers");
					if(coverJsonArray != null) {
						List<String> covers = new ArrayList<String>();
						for(int i = 0; i<coverJsonArray.length();i++) {
							covers.add(coverJsonArray.getString(i));
						}
						book.setCoverIds(covers);
					}
					
					JSONArray authorJsonArray = jsonObject.optJSONArray("authors");
					if(authorJsonArray != null) {
						List<String> authorIds = new ArrayList<String>();
						List<String> authorNames = new ArrayList<String>();
						
						// fetching list of author ids
						for(int i=0;i<authorJsonArray.length();i++) {
							authorIds.add(authorJsonArray.getJSONObject(i)
									.getJSONObject("author")
									.getString("key").replace("/authors/",""));
						}
					
						// fetching list of author names from author ids
						authorIds.forEach(authorId -> {
							Optional<Author> authorOptional = authorRepository.findById(authorId);
							String authorName = "Uknown Author";
							if(authorOptional.isPresent()) {
								authorName = authorOptional.get().getAuthorName();
							}
							authorNames.add(authorName);
						});
						book.setAuthorIds(authorIds);
						book.setAuthorNames(authorNames);
					}
					
					log.info("saving book {}",book.getName());
					bookRepository.save(book);
				} catch (JSONException e) {
					log.error("error while reading book data line {}",line);
					log.error("error while reading book data  {}",e);
				}
				
			});
		} catch (IOException e) {
			log.error("error while reading author dump {}",e);
		}
	}
}
