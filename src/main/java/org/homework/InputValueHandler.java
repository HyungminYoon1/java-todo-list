package org.homework;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValueHandler {
    public int optionValidate(String input, int min, int max) {
        try {
            int value = Integer.parseInt(input);
            if (value == Options.INPUT_CANCEL.getNumber() || value >= min && value <= max){
                return value;
            }
            throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
        } catch (NumberFormatException e) {
            throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
        }
    }

    public int idValidate(String input) {
        try {
            if(Integer.parseInt(input) == Options.INPUT_CANCEL.getNumber()) {
                throw new InputValidationException(Messages.CANCEL_RESTART.getMessage());
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
            throw new InputValidationException(Messages.CANCEL_RESTART.getMessage());
        }
        if (inputString.isBlank()) {
            return Actions.NONE_DUE_DATE.getAction(); // 마감일 없음
        }
        try {
            LocalDate.parse(inputString, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // 문자열을 LocalDate로 변환 시도
        } catch (DateTimeParseException e) { // 잘못된 날짜 입력
            throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
        }
        return inputString;
    }

    public String dDayValidate(String inputString) {
        if (inputString.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))) { // 입력취소
            throw new InputValidationException(Messages.CANCEL_RESTART.getMessage());
        }
        if (inputString.isBlank()) {
            return Actions.ALL.getAction();
        }
        try {
            if (Integer.parseInt(inputString) <= -2) {
                throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new InputValidationException(Messages.WRONG_INPUT_TRY_AGAIN.getMessage());
        }
        return inputString;
    }

    public String inputDescriptionValidate(String inputStr) {
        if(inputStr.isBlank()){
            throw new InputValidationException(Messages.EMPTY_INPUT.getMessage());
        }
        if(inputStr.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))) {
            throw new InputValidationException(Messages.CANCEL_RESTART.getMessage());
        }
        return inputStr;
    }

    public String inputKeywordValidate(String keyword) {
        if(keyword.isEmpty()) {
            throw new InputValidationException(Messages.CANCEL_RESTART.getMessage());
        }
        return keyword;
    }
}