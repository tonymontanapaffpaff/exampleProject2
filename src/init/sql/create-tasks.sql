create table `web-project`.tasks
(
    taskId      int auto_increment
        primary key,
    idUser      int         not null,
    description varchar(50) null,
    doDate      date        null,
    constraint tasks_users_userId_fk
        foreign key (idUser) references `web-project`.users (userId)
            on update cascade on delete cascade
);