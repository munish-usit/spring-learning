package com.book.tracker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.book.tracker.entity.Book;
import com.book.tracker.entity.UserBooks;
import com.book.tracker.entity.UserBooksPrimaryKey;
import com.book.tracker.repository.BookRepository;
import com.book.tracker.repository.UserBooksRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BookController {

	private final String COVER_IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired 
	private UserBooksRepository userBooksRepository;

	@GetMapping(value = "/books/{bookId}")
	public String getBook(@PathVariable String bookId, Model model,@AuthenticationPrincipal OAuth2User principal) {
		log.info("book  request for id {}",bookId);
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			String coverImageUrl = "/images/no-image.png";
			if (book.getCoverIds() != null && book.getCoverIds().size() > 0) {
				coverImageUrl = COVER_IMAGE_ROOT + book.getCoverIds().get(0) + "-L.jpg";
			}
			model.addAttribute("coverImage", coverImageUrl);
			model.addAttribute("book", book);
			log.info("book found for id {} book {}",bookId,book);
			if (principal != null && principal.getAttribute("login") != null) {
				String userId = principal.getAttribute("login");
				log.info("login user {} , fetching book {} status",userId,bookId);
				model.addAttribute("loginId", userId);
				UserBooksPrimaryKey key = new UserBooksPrimaryKey();
				key.setBookId(bookId);
				key.setUserId(userId);
				Optional<UserBooks> userBooks = userBooksRepository.findById(key);
				if (userBooks.isPresent()) {
					log.info("login user {} book {} found",userId,bookId);
					model.addAttribute("userBooks", userBooks.get());
				} else {
					log.info("login user {} book {} not found",userId,bookId);
					model.addAttribute("userBooks", new UserBooks());
				}
			}
			return "book";
		}
		log.info("book not found for id {}",bookId);
		return "book-not-found";
	}
}
