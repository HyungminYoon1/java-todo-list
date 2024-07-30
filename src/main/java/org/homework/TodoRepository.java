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
        currentId++;
        Todo newTodo = new Todo(currentId, description, dueDate );
        saveTodo(newTodo);
        return newTodo;
    }

    public void saveTodo(Todo todo) { // Todo 저장
        todoMap.put(currentId, todo);
    }

    public int checkTodoMapSize(Map<Integer, Todo> todos) {
        return todos.keySet().size();
    }

    public Optional<Todo> findTodoById(int id) {
        return Optional.ofNullable(todoMap.get(id));
    } // 단건 Todo 불러오기

    public int removeTodoById(int id) {
        Todo removedTodo = todoMap.remove(id);
        return removedTodo != null ? 1 : 0;
    } // Todo 삭제

    public Map<Integer, Todo> getAllTodos() {
        return todoMap;
    } // 전체 Todo 불러오기


    public Map<Integer, Todo> getAllTodosWithinDDay(int dDay) {
        Map<Integer, Todo> resultTodoMap = new HashMap<>();
        for (Todo todo : todoMap.values()) {
            if (isDueDateWithinDays(todo.getDueDate(), dDay)) {
                resultTodoMap.put(todo.getId(), todo);
            }
        }
        return resultTodoMap;
    }

    public boolean isDueDateWithinDays(String dueDateString, int days) {
        if (dueDateString == null || dueDateString.isEmpty()) {
            return false;
        }
        try {
            LocalDate dueDate = LocalDate.parse(dueDateString);
            LocalDate today = LocalDate.now();
            long daysBetween = ChronoUnit.DAYS.between(today, dueDate);
            return daysBetween >= 0 && daysBetween <= days;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public List<Todo> filterAndSortTodos (int dDay) {
        Map<Integer, Todo> filteredTodos = getAllTodosWithinDDay(dDay);
        return filteredTodos.values().stream()
                .filter(todo -> isDueDateWithinDays(todo.getDueDate(), dDay))
                .sorted(Comparator.comparing(todo -> LocalDate.parse(todo.getDueDate())))
                .collect(Collectors.toList());
    }

    public List<Todo> searchTodosByKeyword(String keyword) {
        Map<Integer, Todo> todos = getAllTodos();
        return todos.values().stream()
                .filter(todo -> todo.containsKeyword(keyword))
                .sorted(Comparator.comparing(todo -> LocalDate.parse(todo.getDueDate())))
                .collect(Collectors.toList());
    }
}
