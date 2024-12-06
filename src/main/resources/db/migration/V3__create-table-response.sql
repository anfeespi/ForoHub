/*create table response
(

    idResponse bigint       not null auto_increment,
    message    varchar(100) not null,
    idTopic    bigint       not null,
    createdAt  datetime     not null,
    author     bigint       not null,
    solution   varchar(256) not null,
    primary key (idTopic),
    constraint fk_response_topic_id foreign key (idTopic) references topic (idTopic),
    constraint fk_response_user_id foreign key (author) references user (idUser)

);*/