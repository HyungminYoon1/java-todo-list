package org.homework;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TodoController {
    private final TodoService service = new TodoService();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        boolean running = true;
        outputView.displayProgramIntro();
        while (running) {
            Options option = Options.fromInput(inputView.getOption());
            try {
                switch (option) {
                    case INPUT_CANCEL:
                        outputView.showRestartOption();
                        break;
                    case ADD_WORK:
                        addTodo();
                        break;
                    case REMOVE_WORK:
                        removeTodo();
                        break;
                    case DISPLAY_WORK:
                        displayAllTodos();
                        break;
                    case KEYWORD_SEARCH:
                        keywordSearch();
                        break;
                    case COMPLETE_WORK:
                        completeTodo();
                        break;
                    case EXIT:
                        running = false;
                        outputView.displayProgramExit();
                        break;
                    default:
                        outputView.reportMessage(Messages.WRONG_INPUT_TRY_AGAIN);
                        break;
                }
            } catch (InputValidationException e) {
                outputView.reportMessage(Messages.valueOf(e.getMessage()));  // 예외 메시지를 출력합니다.
                if(e.getMessage().equals(Messages.CANCEL_RESTART.getMessage()) ){ // 입력 취소
                    outputView.showRestartOption();
                }
            } catch (NoSuchElementException e) {
                outputView.reportMessage(Messages.EMPTY_TODO_LIST);
            } catch (IllegalArgumentException e) {
                outputView.reportMessage(Messages.WRONG_INPUT_TRY_AGAIN);
            }
        }
    }

    private void addTodo() {
        String description = inputView.getTodoDescription(); // 할 일 입력받음
        String dueDate = inputView.getTodoDueDate();  // 마감일 입력을 받음(예외처리 포함)
        Todo newTodo = service.addTodo(description, dueDate);
        outputView.reportCompleteAddingTodo(Optional.ofNullable(newTodo));
    }

    private void removeTodo() {
        int deleteId = inputView.getTodoId(Actions.DELETE);
        service.removeTodoById(deleteId);
    }

    private void displayAllTodos() {
        String dDay = inputView.askDDay(); // dDay 입력받음
        outputView.reportInputValue(dDay);
        if (dDay.equals(Actions.CANCEL.toString())) return;
        if (dDay.equals(Actions.ALL.getAction())) {
            outputView.displayAllTodos(service.getAllTodos());
        } else {
            int dDayNum = Integer.parseInt(dDay);
            List<Todo> filteredAndSortedTodos = service.getFilterAndSortTodos(dDayNum);
            outputView.displayTodosWithinDueDate(filteredAndSortedTodos, dDayNum);
        }
    }

    private void keywordSearch() {
        String keyword = inputView.askKeyword();
        outputView.reportInputValue(keyword);
        List<Todo> searchResult = service.searchTodosByKeyword(keyword);
        outputView.displayTodosWithSearchWord(searchResult);
    }

    private void completeTodo() {
        int id = inputView.getTodoId(Actions.COMPLETE);
        service.completeTodoById(id);
    }
}
