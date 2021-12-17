package com.book.tracker.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import com.book.tracker.entity.UserBooks;
import com.book.tracker.entity.UserBooksPrimaryKey;

@Repository
public interface UserBooksRepository extends CassandraRepository<UserBooks, UserBooksPrimaryKey> {

}
