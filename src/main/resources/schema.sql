create table if not exists event(
    id int primary key auto_increment,
    name varchar(255),
    date varchar(255)
);

create table if not exists sponsor(
    id int primary key auto_increment,
    name varchar(255),
    industry varchar(255)
);

create table if not exists event_sponsor(
    eventId int,
    sponsorId int,
    primary key (eventId,sponsorId),
    foreign key (eventId) references event(id),
    foreign key (sponsorId) references sponsor(id)
);
