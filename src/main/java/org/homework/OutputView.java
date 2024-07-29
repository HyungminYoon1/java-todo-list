package org.homework;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void showIntro() {
        System.out.println("============== START ===============");
    }

    public void showExit() {
        System.out.println("프로그램을 종료합니다.");
        System.out.println("============= FINISH ===============");
    }


    public void displayAllTodos(Map<Integer, Todo> todos) {
        if (todos.isEmpty()) {
            System.out.println("※ 할 일 목록이 비어 있습니다.");
        } else {
            System.out.printf("%-8s %-20s %-20s %-10s%n", "ID", "내용", "상태", "마감일");
            System.out.println("-----------------------------------------------------------------");

            for (Todo todo : todos.values()) {
                displayTodo(todo);
            }
            System.out.println("------------------------------END--------------------------------");
        }
    }

    public void reportCompleteAddingTodo(Todo todo) {
        System.out.println("※ 할 일 추가 => "+"Id: " + todo.getId() + ", 내용: "+ todo.getDescription() +", 완료여부: "+ (todo.isCompleted() ? Actions.COMPLETE : Actions.INCOMPLETE) + ", 마감일: " + todo.getDueDate());
    }

    public void reportCompleteRemovingTodo(int id, int resultNum) {
        System.out.println("※ 삭제 완료 => Id: " + id );
    }

    public void reportNoneRemovingTodo() {
        System.out.println("※ 삭제할 일이 없습니다.");
    }

    public void reportNoneTodo() {
        System.out.println("※ 할 일 목록이 비어 있습니다.");
    }

    public void reportEmptyTodos() {
        System.out.println("※ 할 일 목록이 비어 있습니다.");
    }

    public void reportIncompleteTodo(Map<Integer, Todo> todos) {
        int notCompleteCount = 0;
        for (Todo todo : todos.values()) {
            if(!todo.isCompleted())
                notCompleteCount++;
        }
        if(notCompleteCount==0) {
            System.out.println("※ 완료되지 않은 일이 없습니다.");
        }else {
            System.out.println("※ 완료되지 않은 일이 "+notCompleteCount+"건 있습니다.");
        }
    }


    public void showIncorrectInput() {
        System.out.println("$ 잘못된 입력입니다. 다시 시도해주세요.");
    }

    public void showUnknownError() {
        System.out.println("$ 알 수 없는 오류");
    }

    public void showRestartOption() {
        System.out.println("리셋");
    }
    public void showCancelOption() {
        System.out.println("입력 취소");
    }

    public void reportDeleteResult(int id, int resultNum) {
        if(resultNum == 1){
            System.out.println("id: "+id+" 삭제 완료");
        }else if (resultNum == 0) {
            System.out.println("※ 입력한 아이디 값에 할당된 일이 존재하지 않습니다.");
        }else {
            showUnknownError();
        }
    }

    public void reportCompletedResult(int completeId, int resultNum) {
        if(resultNum == 1){
            System.out.println("※ id: "+completeId+" - 완료처리 실시");
        }else if (resultNum == 0) {
            System.out.println("※ 입력한 아이디 값에 할당된 일이 존재하지 않습니다.");
        }else {
            showUnknownError();
        }
    }

    public void reportInputResult(String inputString) {
        System.out.println("*입력: "+inputString);
    }

    public void displayTodo(Todo todo) {
        String formattedTodo = String.format("%-8d %-20s %-20s %-10s",
                todo.getId(),
                todo.getDescription(),
                (todo.isCompleted() ? Actions.COMPLETE : Actions.INCOMPLETE),
                todo.getDueDate()
        );
        System.out.println(formattedTodo);
    }

    public void displayTodosWithinDueDate(List<Todo> filteredAndSortedTodos, int dDayNum) {

        if (filteredAndSortedTodos.isEmpty()) {
            System.out.println("※ 지정된 기간 내에 마감되는 할 일이 없습니다.");
        } else {
            System.out.printf("%-8s %-20s %-20s %-10s%n", "ID", "내용", "상태", "마감일");
            System.out.println("-----------------------------------------------------------------");

            for (Todo todo : filteredAndSortedTodos) {
                System.out.printf("%-8d %-20s %-20s %-10s%n",
                        todo.getId(),
                        todo.getDescription(),
                        (todo.isCompleted() ? Actions.COMPLETE : Actions.INCOMPLETE),
                        todo.getDueDate());
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
                System.out.printf("%-8d %-20s %-20s %-10s%n",
                        todo.getId(),
                        todo.getDescription(),
                        (todo.isCompleted() ? Actions.COMPLETE : Actions.INCOMPLETE),
                        todo.getDueDate());
            }
        }
        System.out.println();
    }
}
