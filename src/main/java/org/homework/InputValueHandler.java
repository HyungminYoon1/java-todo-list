package org.homework;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValueHandler {
    public int inputOptionValidate(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input);
            if (value == Options.INPUT_CANCEL.getNumber() || value >= min && value <= max){
                return value;
            } else {
                throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
        }
    }

    public int inputIdValidate(String input) {
        try {
            if(Integer.parseInt(input) == Options.INPUT_CANCEL.getNumber()) {
                throw new InputValidationException(Actions.INPUT_CANCEL.getAction());
            }
            if(Integer.parseInt(input) <= -2) {
                throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
        }
    }

    public String dueDateValidate(String inputString) {
        if (inputString.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))) { // 입력취소
            throw new InputValidationException(Actions.INPUT_CANCEL.getAction());
        } else if (inputString.isBlank()) {
            return Actions.NONE_DUE_DATE.getAction(); // 마감일 없음
        } else {
            try {
                LocalDate.parse(inputString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 문자열을 LocalDate로 변환 시도
            } catch (DateTimeParseException e) { // 잘못된 날짜 입력
                throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
            }
        }
        return inputString;
    }

    public boolean isValidDueDate(String input) {
        if (input.equals(String.valueOf(Options.INPUT_CANCEL.getNumber())) || input.isBlank()) {
            return true;
        }
        try {
            LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String dDayValidate(String inputString) {
        if (inputString.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))) { // 입력취소
            return Actions.CANCEL.getAction();
        } else if (inputString.isBlank()) {
            return Actions.ALL.getAction();
        } else {
            try {
                int dDay = Integer.parseInt(inputString); // 문자열을 integer 변환 시도
                if (dDay <= -2) {
                    throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
                }
            } catch (NumberFormatException e) {
                throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
            }
            return inputString;
        }

    }

    public boolean isRunning(String resultString) {
        if (resultString.equals(Messages.WRONG_INPUT_TRY_AGAIN.getMessage())) {
            return true;
        }else {
            return false;
        }
    }

    public String inputDescription(String inputStr) {
        if(inputStr.isBlank()){
            throw new InputValidationException(Messages.EMPTY_INPUT.getMessage());
        }
        if(inputStr.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))) {
            throw new InputValidationException(Actions.INPUT_CANCEL.getAction());
        }else{
            return inputStr;
        }
    }


    public String inputKeywordValidate(String keyword) {
        if(keyword.isEmpty()) {
            throw new InputValidationException(Actions.INPUT_CANCEL.getAction());
        } else if(keyword.isBlank()){
            throw new InputValidationException(Messages.EMPTY_INPUT.getMessage());
        }else {
            return keyword;
        }
    }
}

