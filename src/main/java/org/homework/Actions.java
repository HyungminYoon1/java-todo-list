package org.homework;

public enum Actions {
    COMPLETE("완료"),
    DELETE("삭제"),
    SEARCH("조회"),
    MODIFY("수정"),
    CANCEL("취소"),
    NONE_DUEDATE("마감일없음"),
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
        return action;
    }
}
