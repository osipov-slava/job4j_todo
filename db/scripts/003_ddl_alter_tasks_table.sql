ALTER TABLE tasks
    ADD todo_user int not null references users(id);

