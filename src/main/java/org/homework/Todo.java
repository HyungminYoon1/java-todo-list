package org.homework;

public class Todo {
    private int id;
    private String description;
    private boolean isCompleted;
    private String dueDate;

    public Todo(int id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
    }

    public Todo(int id, String description, boolean isCompleted, String dueDate) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
        this.dueDate = dueDate;
    }

    public Todo(int id, String description, String dueDate) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
    public String getDueDate() {
        return dueDate;
    }

    public void completeTodo() {
        this.isCompleted = true;
    }


    public boolean containsKeyword(String keyword) {
        return this.toString().contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("%-8d %-20s %-20s %-10s",
                id,
                description,
                (isCompleted ? Actions.COMPLETE.getAction() : Actions.INCOMPLETE.getAction()),
                dueDate);
    }
}
