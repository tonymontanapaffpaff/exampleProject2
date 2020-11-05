create table `web-project`.users
(
    userId   int auto_increment
        primary key,
    name     varchar(50) null,
    password varchar(8)  null
);