package org.homework;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputExceptionHandler {
    public int inputOptionValidate(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input);
            if (value == Options.INPUT_CANCEL.getNumber()){
                return value;
            }
            if (value >= min && value <= max) {
                return value;
            }
        } catch (NumberFormatException e) {
            return Options.INCORRECT.getNumber();
        }
        return Options.INCORRECT.getNumber();
    }
    
    public int inputIdValidate(String input){
        try{
            return Integer.parseInt(input);
        } catch (Exception e) {
            return Options.INCORRECT.getNumber();
        }
    }

    public String dueDateValidate(String inputString) {
        if(inputString.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))){ // 입력취소
            return Actions.CANCEL.getAction();
        }else if(inputString.isBlank()){
            return Actions.NONE_DUE_DATE.getAction(); // 마감일 없음
        }else {
            try {
                LocalDate.parse(inputString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 문자열을 LocalDate로 변환 시도
            } catch (DateTimeParseException e) { // 잘못된 날짜 입력
                return Messages.WRONG_INPUT_TRY_AGAIN.getMessage();
            }
        }
        return inputString;
    }

    public String dDayValidate(String inputString) {
        if(inputString.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))){ // 입력취소
            return Actions.CANCEL.getAction();
        }else if(inputString.isBlank()){
            return Actions.ALL.getAction();
        }else {
            try{
                int dDay = Integer.parseInt(inputString); // 문자열을 integer 변환 시도
                if(dDay <= -2) {
                    return Messages.WRONG_INPUT_TRY_AGAIN.getMessage();
                }
            }catch (NumberFormatException e){
                return Messages.WRONG_INPUT_TRY_AGAIN.getMessage();
            }
        }
        return inputString;
    }

    public boolean isRunning(String resultString) {
        if (resultString.equals(Messages.WRONG_INPUT_TRY_AGAIN.getMessage())) {
            return true;
        }else {
            return false;
        }
    }
}

