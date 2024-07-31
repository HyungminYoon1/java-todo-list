package org.homework;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    public void displayProgramIntro() {
        System.out.println("============== START ===============");
    }

    public void displayProgramExit() {
        System.out.println("프로그램을 종료합니다.");
        System.out.println("============= FINISH ===============");
    }

    public void displayIntro(){
        System.out.printf("%-8s %-20s %-20s %-10s%n", "ID", "내용", "상태", "마감일");
        System.out.println("-----------------------------------------------------------------");
    }

    public void displayEnd(){
        System.out.println("------------------------------END--------------------------------");
    }

    public void displayTodo(Todo todo) {
        System.out.println(todo);
    }

    public void displayAllTodos(Map<Integer, Todo> todos) {
        displayIntro();
        for (Todo todo : todos.values()) {
            displayTodo(todo);
        }
        displayEnd();
    }

    public void reportCompleteAddingTodo(Optional<Todo> optionalTodo) {
        Todo todo = optionalTodo.get();
        System.out.println("※ 할 일 추가 => " + "Id: " + todo.getId() + ", 내용: " + todo.getDescription() + ", 완료여부: " +
                (todo.isCompleted() ? Actions.COMPLETE.getAction() : Actions.INCOMPLETE.getAction()) + ", 마감일: " + todo.getDueDate());
    }
    public void reportMessage(Messages message) {
        System.out.println(message.getMessage());
    }

    public void showRestartOption() {
        System.out.println("리셋");
    }

    public void reportResult(int id, Actions action) {
        System.out.println("※ id: "+id+ " - " + action.getAction() + "처리 실시");
    }

    public void reportInputValue(String inputString) {
        System.out.println("*입력: "+inputString);
    }

    public void displayTodosWithinDueDate(List<Todo> filteredAndSortedTodos, int dDayNum) {
        displayIntro();
        for (Todo todo : filteredAndSortedTodos) {
            System.out.println(todo.toString());
        }
        displayEnd();
    }

    public void displayTodosWithSearchWord(List<Todo> todos) {
        displayIntro();
        for (Todo todo : todos) {
            System.out.println(todo.toString());
        }
        displayEnd();
    }
}
