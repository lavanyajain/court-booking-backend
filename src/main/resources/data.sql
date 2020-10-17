create table court (
  court_id int not null auto_increment,
  name varchar (20) not null,
  city varchar(20) not null,
  game varchar(20) not null,
  min_cost int,
  max_cost int,
  primary key(court_id)
);

--insert initial data to court

insert into court(court_id, name, city, game, min_cost, max_cost) values (null, 'Play Mania', 'Bangalore', 'Badminton', 1000, 1200);
insert into court(court_id, name, city, game, min_cost, max_cost) values (null, 'Playo', 'Bangalore', 'football', 700, 900);
insert into court(court_id, name, city, game, min_cost, max_cost) values (null, 'PlayZooo', 'Delhi', 'Badminton', 500, 700);
