package org.homework;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OutputView {
    public void showIntro() {
        System.out.println("============== START ===============");
    }

    public void showExit() {
        System.out.println("프로그램을 종료합니다.");
        System.out.println("============= FINISH ===============");
    }


    public void displayAllTodos(Optional<Map<Integer, Todo>> optionalTodos) {
        if (optionalTodos.isEmpty()) {
            System.out.println(Messages.EMPTY_TODO_LIST.getMessage());
        } else {
            Map<Integer, Todo> todos = optionalTodos.get();
            System.out.printf("%-8s %-20s %-20s %-10s%n", "ID", "내용", "상태", "마감일");
            System.out.println("-----------------------------------------------------------------");

            for (Todo todo : todos.values()) {
                displayTodo(todo);
            }
            System.out.println("------------------------------END--------------------------------");
        }
    }

    public void reportCompleteAddingTodo(Optional<Todo> optionalTodo) {
        if (optionalTodo.isEmpty()) {
            System.out.println(Messages.UNKNOWN_ERROR.getMessage() + " 할 일 추가 실패.");
        } else {
            Todo todo = optionalTodo.get();
            System.out.println("※ 할 일 추가 => " + "Id: " + todo.getId() + ", 내용: " + todo.getDescription() + ", 완료여부: " +
                    (todo.isCompleted() ? Actions.COMPLETE.getAction() : Actions.INCOMPLETE.getAction()) + ", 마감일: " + todo.getDueDate());
        }
    }

    public void reportCompleteRemovingTodo(int id, int resultNum) {
        System.out.println("※ 삭제 완료 => Id: " + id );
    }

    public void reportNoneRemovingTodo() {
        System.out.println("※ 삭제할 일이 없습니다.");
    }

    public void reportNoneTodo() {
        System.out.println(Messages.EMPTY_TODO_LIST.getMessage());
    }

    public void reportEmptyTodos() {
        System.out.println(Messages.EMPTY_TODO_LIST.getMessage());
    }

    public void reportIncompleteTodo(Optional<Map<Integer, Todo>> optionalTodoMap) {
        int notCompleteCount = 0;
        for (Todo todo : optionalTodoMap.get().values()) {
            if(!todo.isCompleted()) notCompleteCount++;
        }
        if(notCompleteCount==0) {
            System.out.println("※ 완료되지 않은 일이 없습니다.");
        }else {
            System.out.println("※ 완료되지 않은 일이 "+notCompleteCount+"건 있습니다.");
        }
    }


    public void showIncorrectInput() {
        System.out.println(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
    }

    public void showUnknownError() {
        System.out.println(Messages.UNKNOWN_ERROR.getMessage());
    }

    public void showRestartOption() {
        System.out.println("리셋");
    }
    public void showCancelOption() {
        System.out.println(Actions.INPUT_CANCEL.getAction());
    }

    public void reportResult(int id, Optional<Todo> result, Actions action) {
        if(result.isEmpty()){
            System.out.println("※ 입력한 아이디 값에 할당된 일이 존재하지 않습니다.");
        }else  {
            System.out.println("※ id: "+id+ " - " + action.getAction() + "처리 실시");
        }
    }


    public void reportInputResult(String inputString) {
        System.out.println("*입력: "+inputString);
    }

    public void displayTodo(Todo todo) {
        System.out.println(todo);
    }

    public void displayTodosWithinDueDate(List<Todo> filteredAndSortedTodos, int dDayNum) {

        if (filteredAndSortedTodos.isEmpty()) {
            System.out.println("※ 지정된 기간 내에 마감되는 할 일이 없습니다.");
        } else {
            System.out.printf("%-8s %-20s %-20s %-10s%n", "ID", "내용", "상태", "마감일");
            System.out.println("-----------------------------------------------------------------");

            for (Todo todo : filteredAndSortedTodos) {
                System.out.println(todo.toString());
            }
            System.out.println("------------------------------END--------------------------------");
        }

        System.out.println();
    }

    public void displayTodosWithSearchWord(List<Todo> todos) {

        if (todos.isEmpty()) {
            System.out.println("※ 검색 결과가 없습니다.");
        } else {
            System.out.printf("%-8s %-20s %-20s %-10s%n", "ID", "내용", "상태", "마감일");
            System.out.println("-----------------------------------------------------------------");

            for (Todo todo : todos) {
                System.out.println(todo.toString());
            }
        }
        System.out.println();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
