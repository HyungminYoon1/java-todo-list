package org.homework;

public enum Messages {

    CANCEL_RESTART("$ 입력 취소. 다시 시작합니다."),
    WRONG_INPUT_TRY_AGAIN("$ 잘못된 입력. 다시 시도해주세요."),
    WRONG_DATE_INPUT_TRY_AGAIN("$ 잘못된 날짜 입력. 다시 시도해주세요."),
    UNEXPECTED_ERROR("$ 알 수 없는 오류!"),
    EMPTY_TODO_LIST("※ 할 일 목록이 비어 있습니다."),
    NONE_TODO_FOR_ID("※ 해당 ID에 할당된 일이 없습니다."),
    EMPTY_INPUT("※ 입력값이 비어 있습니다."),
    EMPTY_SEARCH_RESULT("※ 검색 결과가 없습니다."),
    COMPLETED_DELETE("※ 삭제 완료"),
    FAILED_DELETE("※ 삭제 실패"),
    NONE_DUEDATE("마감일없음"),
    DISPLAY_ALL("전체 출력");

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
