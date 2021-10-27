insert into users values('user','user',true);
insert into users values('admin','admin',true);

insert into authorities values('user','ROLE_USER');
insert into authorities values('admin','ROLE_ADMIN');

/*
INSERT INTO user_profile (name, email, password, enabled)
  values ('user',
    'user@email.com',
    'user',
    1);

INSERT INTO user_authorities (email, authority)
  values ('user@email.com', 'ROLE_USER');
  
  INSERT INTO user_profile (name, email, password, enabled)
  values ('admin',
    'admin@email.com',
    'admin',
    1);

INSERT INTO user_authorities (email, authority)
  values ('admin@email.com', 'ROLE_ADMIN');
*/