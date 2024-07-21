package org.homework;

import java.util.Map;

public class TodoService {
    private final TodoRepository repository = new TodoRepository();

    public Todo addTodo(String description) {
        return repository.addTodo(description);
    }

    public int removeTodoById(int id) {
        Todo todo = repository.findTodoById(id);
        if (todo != null) {
            repository.removeTodoById(id);
            return 1;
        }else {
            return 0;
        }
    }

    public Todo findTodoById(int id) {
        return repository.findTodoById(id);
    }

    public Map<Integer, Todo> getAllTodos() {
        return repository.getAllTodos();
    }

    public int completeTodoById(int id) {
        Todo todo = repository.findTodoById(id);
        if (todo != null) {
            todo.complete();
            return 1;
        }else {
            return 0;
        }
    }
}
