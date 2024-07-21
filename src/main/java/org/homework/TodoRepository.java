package org.homework;

import java.util.HashMap;
import java.util.Map;

public class TodoRepository {
    private final Map<Integer, Todo> todoMap = new HashMap<>();
    private int currentId = 0;

    public Todo addTodo(String description) {
        currentId++;
        Todo newTodo = new Todo(currentId, description);
        todoMap.put(currentId, newTodo);
        return newTodo;
    }

    public int checkTodoMapSize(Map<Integer, Todo> todos) {
        return todos.keySet().size();
    }

    public Todo findTodoById(int id) {
        return todoMap.get(id);
    }

    public void removeTodoById(int id) {
        todoMap.remove(id);
    }

    public Map<Integer, Todo> getAllTodos() {
        return todoMap;
    }
}
