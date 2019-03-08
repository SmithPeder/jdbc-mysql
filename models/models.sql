drop DATABASE if exists wd;
create database wd;
use wd;

create table workout (
  id integer not null,
  date datetime,
  time time,
  duration time,
  shape integer not null,
  performance integer not null,

  primary key (id),
  constraint check (shape between 1 and 10),
  constraint check (performace between 1 and 10)
);

create table equipment (
  id integer not null,
  name varchar(50),
  description varchar(256),

  primary key (id)
);

create table note (
  id integer not null,
  purpose varchar(256),
  experiance varchar(256),
  additional_comment varchar(512),
  workout_id integer not null,

  primary key (id),
  foreign key (workout_id) references workout(id)
);

create table exercise (
  id integer not null,
  name varchar(50),
  equipment_id integer,
  description varchar(256),

  primary key (id),
  foreign key (equipment_id) references equipment(id)
);

create table workout_exercise (
  workout_id integer not null,
  exercise_id integer not null,

  primary key (workout_id, exercise_id),
  foreign key (workout_id) references workout(id),
  foreign key (exercise_id) references exercise(id)
);

create table exercise_group (
  id integer not null,
  name varchar(50),

  primary key (id)
);

create table exercise_exercise_group (
  exercise_id integer not null,
  exercise_group_id integer not null,

  primary key (exercise_id, exercise_group_id),
  foreign key (exercise_id) references exercise(id),
  foreign key (exercise_group_id) references exercise_group(id)
);
