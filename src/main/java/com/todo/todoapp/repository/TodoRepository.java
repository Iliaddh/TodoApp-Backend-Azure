package com.todo.todoapp.repository;

import com.todo.todoapp.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  TodoRepository extends MongoRepository<Todo,String> {
}
