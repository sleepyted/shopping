CREATE SCHEMA `shopping` DEFAULT CHARACTER SET utf8 ;

drop table if exists user;
create table user (
  id int(8) not null auto_increment primary key,
  username varchar(128) not null,
  email varchar(128) not null,
  tel varchar(128),
  password varchar(128) not null,
  gender char(1) not null default '0',  -- 0 女	1 男
  create_date TIMESTAMP not null,
  user_type char(1) not null default '0', -- 0 普通用户  1 管理员
  del_flag char(1) not null default '0'   -- 0正常状态 9 删除状态
);

-- insert an admin user, username is 'admin@admin.com' , password is 'admin'
INSERT INTO shopping.user(username,email,tel,password,gender,user_type) VALUES ('admin','admin@admin.com','123123123','ISMvKXpXpadDiUoOSoAfww==','0','1');

DROP TABLE IF EXISTS good;
CREATE TABLE good(
  id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  picId VARCHAR(2048) NOT NULL,
  name VARCHAR(128) NOT NULL ,
  discription VARCHAR(4396) NOT NULL ,
  price DOUBLE NOT NULL,
  count int(11) not NULL

);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  userId INT(8) NOT NULL,
  goodId INT(8) NOT NULL,
  create_date TIMESTAMP NOT NULL ,
  status CHAR(1) NOT NULL DEFAULT '0',   -- 0 下单  1 已确认  2 配送中  3 已接收
  tel varchar(128) NOT NULL ,
  addr VARCHAR(2048) NOT  NULL
);

DROP TABLE IF EXISTS attachment;
CREATE TABLE attachment (
  id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  origin_name VARCHAR(1024) NOT NULL ,
  local_name VARCHAR(1024) NOT NULL ,
  create_date TIMESTAMP NOT NULL
);
