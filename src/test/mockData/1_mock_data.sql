DELETE FROM tasks;
DELETE FROM users;

insert into users(full_name, email, password)
values
('John', 'john@gmail.com', 'john');

insert into tasks(title, description, created, done, todo_user)
values
('Buy tickets on plane', 'Tickets for all family on 05 april from Moscow to Abu-Dhabi', '28-03-24 10:00:00', false, 1),
('Book room for a week', 'Order room for vacation time - 7 nights, 1 double bed, 1 bed', '26-03-24 16:45:00', true, 1),
('Solve task_999999', 'Change SomeClass to interface with implementation', '29-03-24 11:15:00', false, 1),
('Fix bug #1278 from bugreport', 'Fix memory leak in webApp', '29-03-24 11:15:00', false, 1),
('Congrat my mom', 'Congratulation my mother by phone at 03 april. She will be 48', '30-03-24 19:45:00', false, 1);

