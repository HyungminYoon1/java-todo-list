package org.homework;

import java.util.List;
import java.util.Map;

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
        System.out.println();
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

    public void reportCompleteAddingTodo(Todo todo) {
        System.out.println("※ 할 일 추가 => " + "Id: " + todo.getId() + ", 내용: " + todo.getDescription() + ", 완료여부: " +
                (todo.isCompleted() ? "완료" : "미완료") + ", 마감일: " + todo.getDueDate());
    }
    public void reportString(String txt) {
        System.out.println(txt);
    }
    public void reportMessage(Messages message) {
        System.out.println(message.getMessage());
    }

    public void reportInputValue(String inputString) {
        System.out.println("*입력: "+inputString);
    }

    public void displayTodosWithinDueDate(List<Todo> filteredAndSortedTodos) {
        displayIntro();
        for (Todo todo : filteredAndSortedTodos) {
            System.out.println(todo.toString());
        }
        displayEnd();
    }

    // 위의 displayTodosWithinDueDate 매서드와 구조가 같으나 차후 매서드의 변경가능성을 고려하여 통합하지 않았습니다.
    public void displayTodosWithSearchWord(List<Todo> todosWithSearchWord) {
        displayIntro();
        for (Todo todo : todosWithSearchWord) {
            System.out.println(todo.toString());
        }
        displayEnd();
    }

    public void displayCompleteResult(int id, boolean result) {
        System.out.println("ID: "+id +", 처리결과: "+ (result ? "완료" : "미완료"));
    }

}
