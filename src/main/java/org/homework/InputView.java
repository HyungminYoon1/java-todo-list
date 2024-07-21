package org.homework;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    private static String inputString;
    private static Options selectedOption;

    public int getOption() {
        System.out.println("--------- SELECT OPTIONS -----------");
        System.out.println("옵션을 선택하세요: 1. 추가, 2. 단건삭제, 3. 전체조회, 4. 완료처리, 5. 종료  [입력취소: -1]");
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

    public int getTodoId(String action) {
        System.out.print(action + "할 할 일의 ID를 입력하세요 >> ");
        inputString = scanner.nextLine();
        try{
            return Integer.parseInt(inputString);
        } catch (Exception e) {
            selectedOption = Options.INCORRECT;
            return selectedOption.getNumber();
        }
    }
}
