package org.homework;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class TodoRepository {
    private final Map<Integer, Todo> todoMap = new HashMap<>();
    private int currentId = 0;


    public Todo addTodoWithDueDate(String description, String dueDate) { // Todo 생성 및 저장
        Todo newTodo = new Todo(currentId++, description, dueDate );
        todoMap.put(currentId, newTodo);
        return newTodo;
    }

    public Optional<Todo> removeTodoById(int id) {
        return Optional.ofNullable(todoMap.remove(id));
    } // Todo 삭제

    public Map<Integer, Todo> getAllTodos() {
        return todoMap;
    } // 전체 Todo 불러오기

    public Map<Integer, Todo> getAllTodosWithinDDay(int dDay) {
        Map<Integer, Todo> resultTodoMap = new HashMap<>();
        for (Todo todo : todoMap.values()) {
            if (isDueDateWithinDays(Optional.ofNullable(todo.getDueDate()), dDay)) {
                resultTodoMap.put(todo.getId(), todo);
            }
        }
        return resultTodoMap;
    }

    public boolean isDueDateWithinDays(Optional<String> dueDateString, int days) {
        return dueDateString
                .filter(date -> !date.isEmpty())
                .map(date -> {
                    try {
                        LocalDate dueDate = LocalDate.parse(date);
                        LocalDate today = LocalDate.now();
                        long daysBetween = ChronoUnit.DAYS.between(today, dueDate);
                        return daysBetween >= 0 && daysBetween <= days;
                    } catch (DateTimeParseException e) {
                        return false;
                    }
                })
                .orElse(false);
    }

    public List<Todo> filterAndSortTodos (int dDay) {
        Map<Integer, Todo> filteredTodos = getAllTodosWithinDDay(dDay);
        return filteredTodos.values().stream()
                .filter(todo -> isDueDateWithinDays(Optional.ofNullable(todo.getDueDate()), dDay))
                .sorted(Comparator.comparing(todo -> LocalDate.parse(todo.getDueDate())))
                .collect(Collectors.toList());
    }

    public List<Todo> searchTodosByKeyword(String keyword) {
        return todoMap.values().stream()
                .filter(todo -> todo.containsKeyword(keyword))
                .sorted(Comparator.comparing(todo -> LocalDate.parse(todo.getDueDate())))
                .collect(Collectors.toList());
    }

    public void completeTodo(int id) {
        getAllTodos().get(id).completeTodo();
    }
}
