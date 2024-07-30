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

    public Optional<Map<Integer, Todo>> getAllTodos() {
        return repository.getAllTodos();
    }

    public Map<Integer, Todo> getAllTodosWithinDDay(int dDay) {
        return repository.getAllTodosWithinDDay(dDay);
    }

    public Optional<Todo> completeTodoById(int id) {
        return repository.findTodoById(id)
                .map(todo -> {
                    todo.completeTodo();
                    return todo;
                });
    }

    public int getIncompleteTodoCount() {
        return repository.getAllTodos()
                .map(todos -> (int) todos.values().stream()
                        .filter(todo -> !todo.isCompleted())
                        .count())
                .orElse(0);
    }

    public List<Todo> getFilterAndSortTodos(int dDay) {
        return repository.filterAndSortTodos(dDay);
    }

    public List<Todo> searchTodosByKeyword(String keyword) {
        return repository.searchTodosByKeyword(keyword);
    }
}
