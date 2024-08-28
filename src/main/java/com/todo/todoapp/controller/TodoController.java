package com.todo.todoapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class TodoController {

    private List<Todo> todos = new ArrayList<Todo>();
    private AtomicInteger idCounter = new AtomicInteger();

    @GetMapping("/api/todos")
    public List<Todo> getTodos() {
        return todos;
    }
    @GetMapping("/api/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
        return todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/todos")
    public ResponseEntity<Todo> createTodo(@RequestParam String title,
                                           @RequestParam String description,
                                           @RequestParam(defaultValue = "false") boolean completed) {

        int id = idCounter.incrementAndGet();
        Todo newTodo = new Todo(id, title, description, completed);

        todos.add(newTodo);

        return new ResponseEntity<>(newTodo, HttpStatus.CREATED);
    }



}


class Todo {
    private int id;
    private String title;
    private String description;
    private boolean completed;

    public Todo(int id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
