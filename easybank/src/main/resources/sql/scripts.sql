--- Table creation
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);
create table customer (
id int not null AUTO_INCREMENT,
email varchar(50) not null,
pwd varchar(200) not null,
role varchar(50) not null,
primary key(id)
);

--- Dummy values
INSERT IGNORE INTO `users` values ('sj15', '{bcrypt}$2a$12$u2x1sEh0jI.X4Osjn0E9nuSFhz.4JKe7WTQyLw0EhiqM1YVrOCsb2', '1');
INSERT IGNORE INTO `authorities` values('sj15', 'admin');

INSERT IGNORE INTO `users` values ('user', '{noop}User@712401', '1');
INSERT IGNORE INTO `authorities` values('user', 'read');