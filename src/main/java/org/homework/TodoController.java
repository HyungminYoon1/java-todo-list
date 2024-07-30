package org.homework;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TodoController {
    private final TodoService service = new TodoService();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        boolean running = true;
        outputView.showIntro();

        while (running) {
            try {
                Options option = Options.fromInput(inputView.getOption());
                switch (option) {
                    case INPUT_CANCEL:
                        outputView.showRestartOption();
                        break;
                    case ADD_WORK:
                        handleAddWork();
                        break;
                    case REMOVE_WORK:
                        handleRemoveWork();
                        break;
                    case DISPLAY_WORK:
                        handleDisplayWork();
                        break;
                    case KEYWORD_SEARCH:
                        handleKeywordSearch();
                        break;
                    case COMPLETE_WORK:
                        handleCompleteWork();
                        break;
                    case EXIT:
                        running = false;
                        outputView.showExit();
                        break;
                    default:
                        outputView.showIncorrectInput();
                        break;
                }
            } catch (InputValidationException e) {
                outputView.showMessage(e.getMessage());
            }
        }
    }

    private void handleAddWork() {
        try {
            String description = inputView.getTodoDescription(); // 할 일 입력받음
            String dueDate = inputView.getTodoDueDate();  // 마감일 입력을 받음(예외처리 포함)
            Todo output = service.addTodo(description, dueDate);
            outputView.reportCompleteAddingTodo(Optional.ofNullable(output));
        } catch (InputValidationException e) {
            outputView.showMessage(e.getMessage());
        }
    }

    private void handleRemoveWork() {
        try {
            if (service.getAllTodos().get().isEmpty()) {
                outputView.reportNoneRemovingTodo();
            } else {
                int deleteId = inputView.getTodoId(Actions.DELETE);
                Optional<Todo> result = service.removeTodoById(deleteId);
                outputView.reportResult(deleteId, result, Actions.DELETE);
            }
        } catch (InputValidationException e) {
            outputView.showMessage(e.getMessage());
        }
    }

    private void handleDisplayWork() {
        try {
            if (service.getAllTodos().get().isEmpty()) {
                outputView.reportEmptyTodos();
            } else {
                String dDay = inputView.askDDay();
                outputView.reportInputResult(dDay);
                if (dDay.equals(Actions.CANCEL.toString())) return;
                if (dDay.equals(Actions.ALL.getAction())) {
                    outputView.displayAllTodos(service.getAllTodos());
                } else {
                    int dDayNum = Integer.parseInt(dDay);
                    List<Todo> filteredAndSortedTodos = service.getFilterAndSortTodos(dDayNum);
                    outputView.displayTodosWithinDueDate(filteredAndSortedTodos, dDayNum);
                }
            }
        } catch (InputValidationException e) {
            outputView.showMessage(e.getMessage());
        }
    }

    private void handleKeywordSearch() {
        try {
            if (service.getAllTodos().get().isEmpty()) {
                outputView.reportNoneTodo();
            } else {
                String keyword = inputView.askKeyword();
                outputView.reportInputResult(keyword);
                List<Todo> searchResult = service.searchTodosByKeyword(keyword);
                outputView.displayTodosWithSearchWord(searchResult);
            }
        } catch (InputValidationException e) {
            outputView.showMessage(e.getMessage());
        }
    }

    private void handleCompleteWork() {
        try {
            Optional<Map<Integer, Todo>> optionalTodos = service.getAllTodos();
            if (service.getAllTodos().get().isEmpty()) {
                outputView.reportNoneTodo();
            } else {
                outputView.reportIncompleteTodo(optionalTodos);
                int completeId = inputView.getTodoId(Actions.COMPLETE);
                Optional<Todo> result = service.completeTodoById(completeId);
                outputView.reportResult(completeId, result, Actions.COMPLETE);
            }
        } catch (InputValidationException e) {
            outputView.showMessage(e.getMessage());
        }
    }
}
