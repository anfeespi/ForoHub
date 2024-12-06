/*create table topic
(

    idTopic bigint       not null auto_increment,
    title   varchar(100) not null,
    message varchar(256) not null,
    date    datetime     not null,
    status  tinyint      not null,
    idUser  bigint       not null,
    primary key (idTopic),
    constraint fk_topic_user_id foreign key (idUser) references user (idUser),

);*/