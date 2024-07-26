package org.homework;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class TodoService {
    private final TodoRepository repository = new TodoRepository();

    public Todo addTodo(String description, String dueDate) {
        return repository.addTodoWithDueDate(description, dueDate);
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

    public Map<Integer, Todo> getAllTodosWithinDDay(int dDay) {
        return repository.getAllTodosWithinDDay(dDay);
    }

    public int completeTodoById(int id) {
        Todo todo = repository.findTodoById(id);
        if (todo != null) {
            todo.completeTodo();
            return 1;
        }else {
            return 0;
        }
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


}
