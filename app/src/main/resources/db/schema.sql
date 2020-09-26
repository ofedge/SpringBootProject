create table t_user(
    id bigint(20) not null auto_increment,
    username varchar(255),
    password varchar(255),
    name varchar(255),
    email varchar(255),
    active char(1), -- 0: active, 1: deactivate
    create_time bigint(20),
    last_modify bigint(20)
);

create table t_role (
    id bigint(20) not null auto_increment,
    name varchar(255),
    description varchar(255)
);

create table t_user_role (
    user_id bigint(20) not null,
    role_id bigint(20) not null
);

create table t_authority (
    id bigint(20) not null,
    name varchar(255),
    description varchar(255),
    parent_id bigint(20)
);

create table t_role_authority (
    role_id bigint(20) not null,
    authority_id bigint(20) not null
);