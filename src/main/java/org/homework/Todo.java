package org.homework;

public class Todo {
    private int id;
    private String description;
    private boolean isCompleted;

    public Todo(int id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
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

    public void complete() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Description: " + description + ", Status: " + (isCompleted ? "[완료]" : "[미완료]");
    }
}
