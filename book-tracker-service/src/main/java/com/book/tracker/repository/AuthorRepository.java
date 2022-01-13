package com.book.tracker.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.book.tracker.entity.Author;

@Repository
public interface AuthorRepository extends CassandraRepository<Author, String> {

}
