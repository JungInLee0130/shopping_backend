--insert into user (username, password, nickname, activated) values ('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
--insert into user (username, password, nickname, activated) values ('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

--insert into authority (authority_name) values ('ROLE_USER');
--insert into authority (authority_name) values ('ROLE_ADMIN');

--insert into user_authority (user_id, authority_name) values (1, 'ROLE_USER');
--insert into user_authority (user_id, authority_name) values (1, 'ROLE_ADMIN');
--insert into user_authority (user_id, authority_name) values (2, 'ROLE_USER');

insert into MEMBERS (member_name, email, road_address, address_detail) values ('member1', 'member1@naver.com', '경기도 성남시 수정구 11', '100-203');
insert into PRODUCTS (product_name, seller_id, price, quantity) values ('product1', 1, 5000, 1);
insert into ORDERS (product_id, purchaser_id) values (1, 1);