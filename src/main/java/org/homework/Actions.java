package org.homework;

public enum Actions {
    COMPLETE("완료"),
    INCOMPLETE("미완료"),
    DELETE("삭제"),
    CANCEL("취소"),
    INPUT_CANCEL("입력 취소"),
    NONE_DUE_DATE("마감일없음"),
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
