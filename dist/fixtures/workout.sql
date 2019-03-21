use wd;

insert ignore into workout(id, date, duration, shape, performance, note_id) values
	(1, '2019-03-10 12:00:00', '00:30:00', 8, 7, 1);
insert ignore into workout(id, date, duration, shape, performance, note_id) values
	(2, '2019-03-10 13:00:00', '02:00:00', 5, 9, 2);
insert ignore into workout(id, date, duration, shape, performance) values
	(3, '2019-03-09 12:30:00', '01:30:00', 2, 4);
insert ignore into workout(id, date, duration, shape, performance, note_id) values
	(4, '2019-03-09 13:00:00', '00:45:00', 10, 10 , 3);
