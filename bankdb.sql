-- create database bank;
use bank;
-- drop table kyc;
-- create table kyc (
-- account_number bigint primary key,
-- first_name varchar(20) not null,
-- middle_name varchar(20) not null,
-- last_name varchar(20) not null,
-- mother_name varchar(20) not null,
-- dob varchar(20) not null,
-- gender varchar(10) not null,
-- email_id varchar(100) not null,
-- adhar_number bigint not null,
-- address varchar(100) not null,
-- city varchar(40) not null,
-- state varchar(40) not null,
-- pincode int not null );
-- delete from kyc where account_number= 50409301938;
select * from kyc;
-- drop table user_login;
-- create table user_login(
--   account_number bigint  references kyc(account_number),
--  username varchar(20) primary key,
--   password varchar(20) not null);
select * from user_login;
-- drop table user_balance;
--   create table user_balance(
--   account_number bigint primary key references kyc(account_number),
--  balance decimal(12,2) not null);
select * from user_balance;
-- drop table transactionstatement;
--  create table transactionstatement(
-- transaction_id int primary key auto_increment,
-- tr_date_time timestamp not null,
-- tr_amount decimal(12,2) not null,
-- tr_type varchar(20) not null,
-- account_number bigint references user_balance(account_number),
-- final_balance decimal(12,2) not null);
 select * from transactionstatement ;
-- select * from transactionstatement where account_number=50409309700;

--  create table transactionstatement_interest(
-- transaction_id int primary key auto_increment,
-- tr_date_time timestamp not null,
-- tr_amount decimal(12,2) not null,
-- tr_type varchar(20) not null,
-- account_number bigint references user_balance(account_number),
-- final_balance decimal(12,2) not null);
select * from transactionstatement_interest;

-- insert into transactionstatement_interest values(0,'2024-02-18 12:30:00',0,'credit',50409304186,5000);
-- update transactionstatement_interest set transaction_id=0 where transaction_id=3;
-- select count(*) from transactionstatement where account_number=50409303294;
-- update user_balance set balance = 2000 where account_number =  50409303294 ;
-- select * from kyc;

select * from user_balance where account_number = 123;

-- select * from transactionstatement where tr_date_time between '2024-03-20 00:00:00' and '2024-03-25 23:59:59' ;