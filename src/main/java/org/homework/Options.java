package org.homework;

public enum Options {
    INPUT_CANCEL(-1),
    INCORRECT(0),
    ADD_WORK(1),
    REMOVE_WORK(2),
    SEARCH_WORK(3),
    COMPLETE_WORK(4),
    EXIT(5);

    private final int number;


    Options(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static Options fromInput(int inputValue) {
        for (Options option : Options.values()) {
            if (option.getNumber() == inputValue) {
                return option;
            }
        }
        return INCORRECT;
    }

}
