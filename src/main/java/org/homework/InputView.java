package org.homework;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    private final InputExceptionHandler inputExceptionHandler = new InputExceptionHandler();


    public int getOption() {
        System.out.println("--------- SELECT OPTIONS -----------");
        System.out.println("옵션을 선택하세요: 1. 추가, 2. 단건삭제, 3. 조회, 4. 검색, 5. 완료처리, 6. 종료  [입력취소: -1]");
        System.out.print("숫자(1~6) 입력 >> ");
        return inputExceptionHandler.inputOptionValidate(scanner.nextLine(), 1, 6);
    }

    public String getTodoDescription() {
        System.out.print("할 일을 입력하세요 >> ");
        return scanner.nextLine();
    }

    public int getTodoId(Actions action) {
        System.out.print(action + "할 할 일의 ID를 입력하세요 >> ");
        return inputExceptionHandler.inputIdValidate(scanner.nextLine());
    }

    public String getTodoDueDate() {
        System.out.println("마감일을 입력하세요. (예) 2024-10-30   [마감일이 없을 경우: 엔터 / 입력취소: -1]");
        String resultString = "";
        boolean running = true;
        while(running){
            System.out.print("마감일 입력 >> ");
            resultString = inputExceptionHandler.dueDateValidate(scanner.nextLine());
            System.out.println(resultString);
            running = inputExceptionHandler.isRunning(resultString);
        }
        return resultString;
    }


    public String askDDay() {
        System.out.println("오늘로부터 마감일까지 남은 일수를 입력하세요.(0~N) [마감일 무관 전체출력: 엔터  / 입력취소: -1]");
        String resultString = "";
        boolean running = true;
        while(running) {
            System.out.print("마감일까지 남은 일수 >> ");
            resultString = inputExceptionHandler.dDayValidate(scanner.nextLine());
            System.out.println(resultString);
            running = inputExceptionHandler.isRunning(resultString);
        }
        return resultString;
    }

    public String askKeyword() {
        System.out.print("검색할 단어를 입력하세요. [입력취소: 엔터] >> ");
        return scanner.nextLine();
    }
}
