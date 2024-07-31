package org.homework;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TodoService {
    private final TodoRepository repository = new TodoRepository();

    public Todo addTodo(String description, String dueDate) {
        return repository.addTodoWithDueDate(description, dueDate);
    }

    public Optional<Todo> removeTodoById(int id) {
        return repository.removeTodoById(id);
    }
    public Todo getTodoById(int id) {
        return repository.getAllTodos().get(id);
    }

    public Map<Integer, Todo> getAllTodos() {
        return repository.getAllTodos();
    }

    public List<Todo> getFilterAndSortTodos(int dDay) {
        return repository.filterAndSortTodos(dDay);
    }

    public List<Todo> searchTodosByKeyword(String keyword) {
        return repository.searchTodosByKeyword(keyword);
    }

    public void completeTodoById(int id) {
        repository.completeTodo(id);
    }
}
