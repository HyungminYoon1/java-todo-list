package org.homework;

import java.util.List;

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
                        outputView.reportMessage(Messages.CANCEL_RESTART);
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
                outputView.reportString(e.getMessage());
            }
            catch (Exception e) {
                outputView.reportMessage(Messages.UNEXPECTED_ERROR);
            }
        }
    }
    private void checkEmptyTodoList() {
        if(service.getAllTodos().isEmpty()){
            throw new InputValidationException(Messages.EMPTY_TODO_LIST.getMessage());
        }
    }
    private void checkTodoByIdExists(int id) {
        if(service.getTodoById(id).isEmpty()) {
            throw new InputValidationException(Messages.NONE_TODO_FOR_ID.getMessage());
        }
    }

    private void checkEmptySearchResult(List<Todo> searchResult) {
        if(searchResult.isEmpty()){
            throw new InputValidationException(Messages.EMPTY_SEARCH_RESULT.getMessage());
        }
    }

    private void checkTodoDeleted(int deleteId) {
        if(service.getTodoById(deleteId).isEmpty()){
            throw new InputValidationException(Messages.COMPLETED_DELETE.getMessage());
        }
        outputView.reportMessage(Messages.FAILED_DELETE);
    }

    private void addTodo() {
        String description = inputView.getTodoDescription(); // 할 일 입력받음
        String dueDate = inputView.getTodoDueDate();  // 마감일 입력을 받음(예외처리 포함)
        Todo newTodo = service.addTodo(description, dueDate);
        outputView.reportCompleteAddingTodo(newTodo);
    }

    private void removeTodo() {
        checkEmptyTodoList();
        int deleteId = inputView.getTodoIdToDelete();
        checkTodoByIdExists(deleteId);
        service.removeTodoById(deleteId);
        checkTodoDeleted(deleteId);
    }



    private void displayAllTodos() {
        checkEmptyTodoList();
        String dDay = inputView.askDDay(); // dDay 입력받음
        outputView.reportInputValue(dDay);
        // 마감날짜를 고려하지 않은 전체 목록 출력
        if (dDay.equals(Messages.DISPLAY_ALL.getMessage())) {
            outputView.displayAllTodos(service.getAllTodos());
            return;
        }
        // 마감날짜를 고려할 경우
        List<Todo> filteredAndSortedTodos = service.getFilterAndSortTodos(Integer.parseInt(dDay));
        checkEmptySearchResult(filteredAndSortedTodos);
        outputView.displayTodosWithinDueDate(filteredAndSortedTodos);
    }

    private void keywordSearch() {
        checkEmptyTodoList();
        String keyword = inputView.askKeyword();
        outputView.reportInputValue(keyword);
        List<Todo> searchResult = service.searchTodosByKeyword(keyword);
        checkEmptySearchResult(searchResult);
        outputView.displayTodosWithSearchWord(searchResult);
    }

    private void completeTodo() {
        checkEmptyTodoList();
        int id = inputView.getTodoIdToComplete();
        checkTodoByIdExists(id);
        outputView.displayCompleteResult(id, service.completeTodoById(id));
    }
}
