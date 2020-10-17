create table city (
    city_id int not null auto_increment,
    city_name varchar(128) not null,
    state varchar(128) not null,
    country_code varchar(3) not null,
    primary key(city_id),
    unique(city_name)
);

create table club (
    club_id int not null auto_increment,
    name varchar(128) not null,
    city_id int not null,
    pincode varchar(12) not null,
    address varchar(256) not null,
    description varchar(256) not null,
    primary key(club_id),
    foreign key (city_id) references city(city_id)
);

create table services (
    code int not null auto_increment,
    name varchar(128) not null,
    description varchar(256) not null,
    primary key(code)
);

create table court (
  court_id int not null auto_increment,
  club_id int not null,
  service_kind int not null,
  description varchar(256) not null,
  capacity int not null,
  cost int not null,
  primary key(court_id),
  foreign key (club_id) references club(club_id),
  foreign key(service_kind) references services(code)
);

create table user (
  user_id int not null auto_increment,
  email varchar(256) not null unique,
  full_name varchar (256) not null,
  phone_number varchar (12) not null,
  primary key(user_id)
);

create table bookings (
  id int not null auto_increment,
  user_id int not null,
  court_id int not null,
  service_code int not null,
  start_time datetime(6) not null,
  end_time datetime(6) not null,
  total_cost int not null,
  primary key(id),
  foreign key(user_id) references user(user_id),
  foreign key(court_id) references court(court_id),
  foreign key(service_code) references services(code)
);

--insert initial entries to city table

insert into city(city_id, city_name, state, country_code) values (null, 'Bengaluru', 'karnataka', 'in');
insert into city(city_id, city_name, state, country_code) values (null , 'Mysore', 'karnataka', 'in');
insert into city(city_id, city_name, state, country_code) values (null , 'New york', 'New york', 'us');

--insert initial data to clubs

insert into club (club_id, name, city_id, pincode, address, description) values (null , 'feathers smash arena', 1, 560066, 'Whitefield Kadugodi', 'includes facilities for indoor and outdoor games including football, tennis court');
insert into club (club_id, name, city_id, pincode, address, description) values (null , 'smash arena', 1, 560066, 'Whitefield ITPL', 'includes facilities for indoor and outdoor games including football, tennis court');
insert into club (club_id, name, city_id, pincode, address, description) values (null , 'arena sport cafe', 1, 560066, 'Whitefield Kadugodi', 'includes facilities for indoor and outdoor games including football, tennis court');


--insert initial data to services
insert into services (code, name, description)values (null, 'Foot ball', 'foot ball court');
insert into services (code, name, description)values (null, 'Table tennis', 'foot ball court');
insert into services (code, name, description)values (null, 'badminton', 'foot ball court');
insert into services (code, name, description)values (null, 'volley ball', 'foot ball court');

--insert initial data into courts table

insert into court (court_id, club_id, service_kind, description, capacity, cost) values (null, 1, 1, 'foot ball court', 100, 10000);
insert into court (court_id, club_id, service_kind, description, capacity, cost) values (null, 2, 2, 'tennis court', 10, 400);
insert into court (court_id, club_id, service_kind, description, capacity, cost) values (null, 3, 3, 'table tennis court', 5, 500);

-- insert initial data to users

insert into user (user_id, email, full_name, phone_number) values (null , 'Lavanya.pjain@gmail.com', 'Lavanya P', '7795793367');
insert into user (user_id, email, full_name, phone_number) values (null , 'Foo.boo@gmail.com', 'Foo boo', '7795789788');
insert into user (user_id, email, full_name, phone_number) values (null , 'foo.sn@gmail.com', 'Foo sn', '7795893367');

-- insert initial data for bookings
