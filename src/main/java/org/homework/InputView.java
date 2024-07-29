package org.homework;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    private static String inputString;
    private static Options selectedOption;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public int getOption() {
        System.out.println("--------- SELECT OPTIONS -----------");
        System.out.println("옵션을 선택하세요: 1. 추가, 2. 단건삭제, 3. 조회, 4. 검색, 5. 완료처리, 6. 종료  [입력취소: -1]");
        System.out.print("숫자(1~5) 입력 >> ");
        inputString = scanner.nextLine();

        try{
            int inputNum =Integer.parseInt(inputString);
            selectedOption = Options.fromInput(inputNum);
        } catch (Exception e) {
            selectedOption = Options.INCORRECT;
        }

        return selectedOption.getNumber();
    }

    public String getTodoDescription() {
        System.out.print("할 일을 입력하세요 >> ");
        return scanner.nextLine();
    }

    public int getTodoId(Actions action) {
        System.out.print(action + "할 할 일의 ID를 입력하세요 >> ");
        inputString = scanner.nextLine();
        try{
            return Integer.parseInt(inputString);
        } catch (Exception e) {
            selectedOption = Options.INCORRECT;
            return selectedOption.getNumber();
        }
    }

    public String getTodoDueDate() {
        System.out.println("마감일을 입력하세요. (예) 2024-10-30   [마감일이 없을 경우: 엔터 / 입력취소: -1]");
        boolean running = true;
        while(running){
            System.out.print("마감일 입력 >> ");
            inputString = scanner.nextLine();
            if(inputString.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))){ // 입력취소
                inputString = Actions.CANCEL.getAction();
                running = false;
            }
            else if(inputString.isBlank()){
                inputString = Actions.NONE_DUEDATE.getAction(); // 마감일 없음
                running = false;
            }else {
                try {
                    LocalDate.parse(inputString, DATE_FORMATTER); // 문자열을 LocalDate로 변환 시도
                    running = false;
                } catch (DateTimeParseException e) { // 잘못된 날짜 입력
                    System.out.println("$ 잘못된 입력. 다시 입력해주세요.");
                }
            }
        }
        return inputString;
    }


    public String askDDay() {
        System.out.println("오늘로부터 마감일까지 남은 일수를 입력하세요.(0~N) [마감일 무관 전체출력: 엔터  / 입력취소: -1]");
        while(true) {
            System.out.print("마감일까지 남은 일수 >> ");
            inputString = scanner.nextLine();
            if(inputString.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))){ // 입력취소
                return Actions.CANCEL.getAction(); // 루프 종료
            }else if(inputString.isBlank()){
                inputString = Actions.ALL.getAction(); // 마감일 무관 전체 출력
                return inputString; // 루프 종료
            }else {
                try{
                    int dDay = Integer.parseInt(inputString); // 문자열을 integer 변환 시도
                    if(dDay <= -2) {
                        System.out.println("$ 잘못된 입력. 다시 입력해주세요.");
                    }else {
                        return inputString; // 루프 종료
                    }
                }catch (NumberFormatException e){
                    System.out.println("$ 잘못된 입력. 다시 입력해주세요.");
                }
            }
        }

    }

    public String askKeyword() {
        System.out.print("검색할 단어를 입력하세요. [입력취소: 엔터] >> ");
        inputString = scanner.nextLine();
        if(inputString.isBlank()) {
            return "";
        } else{
            return inputString;
        }
    }
}
