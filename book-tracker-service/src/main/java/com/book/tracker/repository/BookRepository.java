package com.book.tracker.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.book.tracker.entity.Book;

@Repository
public interface BookRepository extends CassandraRepository<Book, String> {

}
