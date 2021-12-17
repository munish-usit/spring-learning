package com.book.tracker.entity;

import java.time.LocalDate;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("user_books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserBooks {

	@PrimaryKey
	private UserBooksPrimaryKey key;

	@Column("started_date")
	@CassandraType(type = Name.DATE)
	private LocalDate startedDate;

	@Column("completed_date")
	@CassandraType(type = Name.DATE)
	private LocalDate completedDate;

	@Column("reading_status")
	@CassandraType(type = Name.TEXT)
	private String readingStatus;

	@Column("rating")
	@CassandraType(type = Name.INT)
	private int rating;

}
