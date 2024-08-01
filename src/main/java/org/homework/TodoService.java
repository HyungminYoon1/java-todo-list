package org.homework;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TodoService {
    private final TodoRepository repository = new TodoRepository();

    public Todo addTodo(String description, String dueDate) {
        return repository.addTodoWithDueDate(description, dueDate);
    }

    public void removeTodoById(int id) {
        repository.removeTodoById(id);
    }
    public Optional<Todo> getTodoById(int id) {
        return Optional.ofNullable(repository.getAllTodos().get(id));
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

    public boolean completeTodoById(int id) {
        return repository.completeTodo(id);
    }
}
