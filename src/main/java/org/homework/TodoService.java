package org.homework;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoService {
    private final TodoRepository repository = new TodoRepository();

    public Todo addTodo(String description, String dueDate) {
        return repository.addTodoWithDueDate(description, dueDate);
    }

    public int removeTodoById(int id) {
        return repository.removeTodoById(id);
    }

    public Map<Integer, Todo> getAllTodos() {
        return repository.getAllTodos();
    }

    public Map<Integer, Todo> getAllTodosWithinDDay(int dDay) {
        return repository.getAllTodosWithinDDay(dDay);
    }

    public int completeTodoById(int id) {
        return repository.findTodoById(id)
                .map(todo -> {
                    todo.completeTodo();
                    return 1;
                })
                .orElse(0);
    }

    public int getIncompleteTodoCount() {
        Map<Integer, Todo> todos = repository.getAllTodos();
        int notCompleteCount = 0;
        for (Todo todo : todos.values()) {
            if(!todo.isCompleted())
                notCompleteCount++;
        }
        return notCompleteCount;
    }

    public List<Todo> getFilterAndSortTodos(int dDay) {
        return repository.filterAndSortTodos(dDay);
    }

    public List<Todo> searchTodosByKeyword(String keyword) {
        return repository.searchTodosByKeyword(keyword);
    }
}
