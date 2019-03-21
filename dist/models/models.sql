use wd;

create table if not exists note (
  id integer not null auto_increment,
  purpose varchar(25),
  experiance varchar(25),
  additional_comment varchar(25),

  primary key (id)
  
);

create table if not exists workout (
  id integer not null auto_increment,
  date datetime,
  duration time,
  shape integer not null,
  performance integer not null,
  note_id integer,

  primary key (id),
  constraint check (shape between 1 and 10),
  constraint check (performace between 1 and 10),
  foreign key (note_id) references note(id)
);

create table if not exists equipment (
  id integer not null auto_increment,
  name varchar(25),
  description varchar(25),

  primary key (id)
);

create table if not exists exercise (
  id integer not null auto_increment,
  name varchar(25),
  equipment_id integer,
  description varchar(25),

  primary key (id),
  foreign key (equipment_id) references equipment(id)
);

create table if not exists workout_exercise (
  workout_id integer not null,
  exercise_id integer not null,

  primary key (workout_id, exercise_id),
  foreign key (workout_id) references workout(id),
  foreign key (exercise_id) references exercise(id)
);

create table if not exists exercise_group (
  id integer not null auto_increment,
  name varchar(25),

  primary key (id)
);

create table if not exists exercise_exercise_group (
  exercise_id integer not null,
  exercise_group_id integer not null,

  primary key (exercise_id, exercise_group_id),
  foreign key (exercise_id) references exercise(id),
  foreign key (exercise_group_id) references exercise_group(id)
);
