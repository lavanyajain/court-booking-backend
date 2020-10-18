create table court (
  court_id int not null auto_increment,
  name varchar (20) not null,
  city varchar(20) not null,
  game varchar(20) not null,
  min_cost int,
  max_cost int,
  primary key(court_id)
);

create table slots (
    slot_id int not null  auto_increment,
    court_id int not null,
    start_time datetime not null,
    end_time datetime not null,
    status varchar(20) default 'Available',
    primary key(slot_id),
    foreign key(court_id) references court(court_id)
);

--insert initial data to court

insert into court(court_id, name, city, game, min_cost, max_cost) values (null, 'Play Mania', 'Bangalore', 'Badminton', 1000, 1200);
insert into court(court_id, name, city, game, min_cost, max_cost) values (null, 'Playo', 'Bangalore', 'football', 700, 900);
insert into court(court_id, name, city, game, min_cost, max_cost) values (null, 'PlayZooo', 'Delhi', 'Badminton', 500, 700);

--insert initial data to slots

insert into slots(slot_id, court_id, start_time, end_time) values (null , 1, '2020-12-18 13:17:17', '2020-12-18 13:17:17');
insert into slots(slot_id, court_id, start_time, end_time, status) values (null , 1, '2020-11-18 13:17:17', '2020-11-18 13:17:17', 'Not Available');

