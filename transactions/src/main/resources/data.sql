
delete from account_transaction;
delete from transaction_account;
delete from account;
delete from transaction;

insert into account (id, account_number) values (1, 1);
insert into account (id, account_number) values (2, 2);
insert into account (id, account_number) values (3, 3);
insert into account (id, account_number) values (4, 4);

insert into transaction (id, amount, date) values (101, 1000, '2024-04-11T10:00');
insert into transaction (id, amount, date) values (102, 900, '2024-05-12T10:00');
insert into transaction (id, amount, date) values (103, 810, '2024-04-12T10:00');
insert into transaction (id, amount, date) values (104, 729, '2024-01-01T10:00');

insert into account_transaction (account_id, transaction_id) values (1, 101);
insert into account_transaction (account_id, transaction_id) values (2, 102);
insert into account_transaction (account_id, transaction_id) values (3, 103);
insert into account_transaction (account_id, transaction_id) values (4, 104);

insert into transaction_account (transaction_id, account_id) values (101, 2);
insert into transaction_account (transaction_id, account_id) values (102, 3);
insert into transaction_account (transaction_id, account_id) values (103, 4);
insert into transaction_account (transaction_id, account_id) values (104, 1);