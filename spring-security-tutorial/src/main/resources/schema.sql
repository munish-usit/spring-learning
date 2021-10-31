/* spring security defautl schema */
create table users(
	username varchar(50) not null primary key,
	password varchar(50) not null,
	enabled boolean not null
);

create table authorities (
	username varchar(50) not null,
	authority varchar(50) not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

/*
/* spring security custom schema */
CREATE TABLE user_profile (
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (email)
);
  
CREATE TABLE user_authorities (
  email VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (email) REFERENCES user_profile(email)
);

CREATE UNIQUE INDEX user_ix_auth_email on user_authorities (email,authority);
*/