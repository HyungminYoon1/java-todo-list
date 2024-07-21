package org.homework;

import java.util.Map;

public class OutputView {
    public void showIntro() {
        System.out.println("============== START ===============");
    }

    public void showExit() {
        System.out.println("프로그램을 종료합니다.");
        System.out.println("============= FINISH ===============");
    }



    public void displayTodos(Map<Integer, Todo> todos) {
        if (todos.isEmpty()) {
            System.out.println("할 일 목록이 비어 있습니다.");
        } else {
            for (Todo todo : todos.values()) {
                System.out.println(todo);
            }
        }
    }

    public void reportCompleteAddingTodo(Todo todo) {
        System.out.println("할 일 추가 => "+"Id: " + todo.getId() + ", 내용: "+ todo.getDescription() +", 완료여부: "+ (todo.isCompleted() ? "[완료]" : "[미완료]"));
    }

    public void reportCompleteRemovingTodo(int id, int resultNum) {
        System.out.println("삭제 완료 => Id: " + id );
    }

    public void reportNoneRemovingTodo() {
        System.out.println("삭제할 일이 없습니다.");
    }

    public void reportNoneTodo() {
        System.out.println("할 일 목록이 비어 있습니다.");
    }

    public void reportIncompleteTodo(Map<Integer, Todo> todos) {
        int notCompleteCount = 0;
        for (Todo todo : todos.values()) {
            if(!todo.isCompleted())
                notCompleteCount++;
        }
        if(notCompleteCount==0) {
            System.out.println("완료되지 않은 일이 없습니다.");
        }else {
            System.out.println("완료되지 않은 일이 "+notCompleteCount+"건 있습니다.");
        }
    }

    public void showIncorrectOption() {
        System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
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
            System.out.println("입력한 아이디 값에 할당된 일이 존재하지 않습니다.");
        }else {
            System.out.println("알 수 없는 오류");
        }
    }

    public void reporCompletedResult(int completeId, int resultNum) {
        if(resultNum == 1){
            System.out.println("id: "+completeId+" 완료처리 실시");
        }else if (resultNum == 0) {
            System.out.println("입력한 아이디 값에 할당된 일이 존재하지 않습니다.");
        }else {
            System.out.println("알 수 없는 오류");
        }
    }
}
