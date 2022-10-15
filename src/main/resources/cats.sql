create table cats (
id serial primary key,
	name varchar,
	age int,
	alive bool
);
insert into cats(name, age, alive) values('CatyCat', 3, 'false');
update cats set age=4;
delete from cats;