DROP SCHEMA IF EXISTS `shopping`;
CREATE SCHEMA `shopping` DEFAULT CHARACTER SET utf8 ;
USE `shopping`;

drop table if exists user;
create table user (
  id int(8) not null auto_increment primary key,
  username varchar(128) not null,
  email varchar(128) not null,
  tel varchar(128),
  password varchar(128) not null,
  gender char(1) not null default '0',  -- 0 女	1 男
  create_date TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_type char(1) not null default '0', -- 0 普通用户  1 管理员
  del_flag char(1) not null default '0'   -- 0正常状态 9 删除状态
);

-- insert an admin user, username is 'admin@admin.com' , password is 'admin'
INSERT INTO shopping.user(username,email,tel,password,gender,user_type) VALUES ('admin','admin@admin.com','123123123','ISMvKXpXpadDiUoOSoAfww==','0','1');
INSERT INTO shopping.user(username,email,tel,password,gender,user_type) VALUES ('test','test@test.com','123123123','ISMvKXpXpadDiUoOSoAfww==','0','0');

DROP TABLE IF EXISTS good;
CREATE TABLE `good` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `picId` varchar(2048) NOT NULL,
  `name` varchar(128) NOT NULL,
  `discription` varchar(4396) NOT NULL,
  `price` double NOT NULL,
  `count` int(11) NOT NULL,
  `type` varchar(1) DEFAULT '0',
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  userId INT(8) NOT NULL,
  goodId INT(8) NOT NULL,
  num INT(8) NOT NULL DEFAULT 1,
  create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  status CHAR(1) NOT NULL DEFAULT '0',   -- 0 下单  1 已确认  2 配送中  3 已接收
  name VARCHAR(128) NOT NULL ,
  tel varchar(128) NOT NULL ,
  addr VARCHAR(2048) NOT  NULL
);

DROP TABLE IF EXISTS attachment;
CREATE TABLE attachment (
  id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  origin_name VARCHAR(1024) NOT NULL ,
  local_name VARCHAR(1024) NOT NULL ,
  create_date TIMESTAMP NOT null DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS comments;
CREATE TABLE comments(
  id INT(8) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  goodId INT(8) NOT NULL ,
  userId INT(8) NOT NULL ,
  content VARCHAR(4396) NOT NULL,
  username varchar(128) not null,
  create_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ;