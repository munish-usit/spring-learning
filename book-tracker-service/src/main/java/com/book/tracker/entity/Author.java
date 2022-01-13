package com.book.tracker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Table("authors")
@Data
@AllArgsConstructor
@Builder
public class Author {

	@Id
	@PrimaryKeyColumn(name = "author_id", ordinal=0, type = PrimaryKeyType.PARTITIONED)
	private String id;
	
	@Column("author_name")
	@CassandraType(type = Name.TEXT)
	private String authorName;
	
	@Column("personal_name")
	@CassandraType(type = Name.TEXT)
	private String personalName;
}
