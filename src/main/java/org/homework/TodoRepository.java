package org.homework;


import java.util.HashMap;
import java.util.Map;

import static org.homework.Utils.daysBetweenTodayAnd;


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

    public Todo findTodoById(int id) {
        return todoMap.get(id);
    } // 단건 Todo 불러오기

    public void removeTodoById(int id) {
        todoMap.remove(id);
    } // Todo 삭제

    public Map<Integer, Todo> getAllTodos() {
        return todoMap;
    } // 전체 Todo 불러오기

    public Map<Integer, Todo> getAllTodosWithinDDay(int dDay) {
        Map<Integer, Todo> resultTodoMap = new HashMap<>();
        for(Todo todo : todoMap.values()){
            if(daysBetweenTodayAnd(todo.getDueDate()) <= dDay) {
                resultTodoMap.put(todo.getId(), todo);
            }
        }
        return resultTodoMap;
    }
}
