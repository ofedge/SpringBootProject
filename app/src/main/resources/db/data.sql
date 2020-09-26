-- t_user
-- admin/123456
insert into t_user(id, username, password, name, email, active, create_time, last_modify) values (1, 'admin', '5fcee29f07c9f45635443e207f35dcee', 'Administrator', 'admin@lyd.app', '1', 1600000000000, 1600000000000);
insert into t_user(id, username, password, name, email, active, create_time, last_modify) values (2, 'mike', '5fcee29f07c9f45635443e207f35dcee', 'Mike Hillyer', 'mike@lyd.app', '1', 1600000000000, 1600000000000);
insert into t_user(id, username, password, name, email, active, create_time, last_modify) values (3, 'joe', '5fcee29f07c9f45635443e207f35dcee', 'Jon Stephens', 'joe@lyd.app', '1', 1600000000000, 1600000000000);

-- t_role
insert into t_role(id, name, description) values (1, 'Administrator', 'Top player');
insert into t_role(id, name, description) values(2, 'Guest', 'Normal player');

-- t_user_role
insert into t_user_role(user_id, role_id) values (1, 1);
insert into t_user_role(user_id, role_id) values (1, 2);
insert into t_user_role(user_id, role_id) values (2, 2);
insert into t_user_role(user_id, role_id) values (3, 2);

-- t_authority
insert into t_authority(id, name, description, parent_id) values (1, 'System management', 'Manage users and roles', null);
insert into t_authority(id, name, description, parent_id) values (2, 'Users', 'View users', 1);
insert into t_authority(id, name, description, parent_id) values (3, 'Manage users', 'View and manage users', 2);
insert into t_authority(id, name, description, parent_id) values (4, 'Roles', 'View roles', 1);
insert into t_authority(id, name, description, parent_id) values (5, 'Manage roles', 'View and manage roles', 4);

-- t_role_authority
insert into t_role_authority(role_id, authority_id) values(1, 1);
insert into t_role_authority(role_id, authority_id) values(1, 2);
insert into t_role_authority(role_id, authority_id) values(1, 3);
insert into t_role_authority(role_id, authority_id) values(1, 4);
insert into t_role_authority(role_id, authority_id) values(1, 5);
insert into t_role_authority(role_id, authority_id) values(2, 1);
insert into t_role_authority(role_id, authority_id) values(2, 2);
insert into t_role_authority(role_id, authority_id) values(2, 4);