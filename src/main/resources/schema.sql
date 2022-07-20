create table animal (
    id              IDENTITY        NOT NULL PRIMARY KEY,
    nickname        varchar(64)     not null,
    birthday        DATE            not null,
    gender          varchar(16)     not null
);

create table user(
    id              IDENTITY        NOT NULL PRIMARY KEY,
    email           varchar(64)     not null,
    password        varchar(64)     not null,
    name            varchar(32)     not null
);