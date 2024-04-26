insert into users(full_name, email, password, timezone)
values
    ('John', 'john@gmail.com', 'john', 'Europe/Moscow');

insert into tasks(title, description, created, done, todo_user, priority_id)
values
    ('Buy tickets on plane', 'Tickets for all family on 05 april from Moscow to Abu-Dhabi', '28-03-24 10:00:00', false, 1, 1),
    ('Book room for a week', 'Order room for vacation time - 7 nights, 1 double bed, 1 bed', '26-03-24 16:45:00', true, 1, 1),
    ('Solve task_999999', 'Change SomeClass to interface with implementation', '29-03-24 11:15:00', false, 1, 2),
    ('Fix bug #1278 from bugreport', 'Fix memory leak in webApp', '29-03-24 11:15:00', false, 1, 1),
    ('Congrat my mom', 'Congratulation my mother by phone at 03 april. She will be 48', '30-03-24 19:45:00', false, 1, 2);

insert into junc_tasks_categories (task_id, category_id)  values (1, 3);
insert into junc_tasks_categories (task_id, category_id)  values (1, 5);
insert into junc_tasks_categories (task_id, category_id)  values (2, 3);
insert into junc_tasks_categories (task_id, category_id)  values (5, 5);
insert into junc_tasks_categories (task_id, category_id)  values (3, 1);
insert into junc_tasks_categories (task_id, category_id)  values (4, 1);
insert into junc_tasks_categories (task_id, category_id)  values (5, 2);