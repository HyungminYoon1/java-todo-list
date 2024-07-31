package org.homework;

public enum Messages {

    CANCEL_RESTART("$ 입력 취소. 다시 시작합니다."),
    WRONG_INPUT_TRY_AGAIN("$ 잘못된 입력. 다시 시도해주세요."),
    UNKNOWN_ERROR("$ 알 수 없는 오류!"),
    EMPTY_TODO_LIST("※ 할 일 목록이 비어 있습니다."),
    EMPTY_INPUT("※ 입력값이 비어 있습니다."),
    EMPTY_TO_REMOVE("※ 삭제할 일이 없습니다.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
