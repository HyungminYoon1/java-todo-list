package org.homework;

public enum Actions {
    COMPLETE("완료"),
    INCOMPLETE("미완료"),
    DELETE("삭제"),
    SEARCH("조회"),
    MODIFY("수정"),
    CANCEL("취소"),
    INPUT_CANCEL("입력 취소"),
    NONE_DUE_DATE("마감일없음"),
    BACK("뒤로가기"),
    ESCAPE("탈출"),
    ALL("전체");

    private final String action;

    Actions(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return String.valueOf(action);
    }
}
