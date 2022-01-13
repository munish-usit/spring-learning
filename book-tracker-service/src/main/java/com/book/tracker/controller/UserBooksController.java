package com.book.tracker.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.book.tracker.entity.UserBooks;
import com.book.tracker.entity.UserBooksPrimaryKey;
import com.book.tracker.repository.BookRepository;
import com.book.tracker.repository.UserBooksRepository;

@Controller
public class UserBooksController {

	@Autowired 
	private UserBooksRepository userBooksRepository;

	@Autowired 
	private BookRepository bookRepository;

	@PostMapping("/addUserBook")
	public ModelAndView addBookForUser(	@RequestBody MultiValueMap<String,String> formData, 
			@AuthenticationPrincipal OAuth2User principal
			) {
		if (principal == null || principal.getAttribute("login") == null) {
			return null;
		}

		String userId = principal.getAttribute("login");
		
		String bookId = formData.getFirst("bookId");
		

		UserBooksPrimaryKey key = UserBooksPrimaryKey.builder().userId(userId).bookId(bookId).build();
		UserBooks userBooks  = new UserBooks();
		userBooks.setKey(key);
		int rating = Integer.parseInt(formData.getFirst("rating"));
		userBooks.setStartedDate(LocalDate.parse(formData.getFirst("startDate")));
		userBooks.setCompletedDate(LocalDate.parse(formData.getFirst("completedDate")));
		userBooks.setRating(rating);
		userBooks.setReadingStatus(formData.getFirst("readingStatus"));

		userBooksRepository.save(userBooks);


		return new ModelAndView("redirect:/books/" + bookId);

	}
}
