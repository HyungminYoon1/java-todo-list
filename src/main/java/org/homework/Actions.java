package org.homework;

public enum Actions {
    COMPLETE("완료"),
    DELETE("삭제"),
    SEARCH("조회"),
    MODIFY("수정");

    private final String action;

    Actions(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    @Override
    public String toString() {
        return action;
    }
}
