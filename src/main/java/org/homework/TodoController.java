package org.homework;

public class TodoController {
    private final TodoService service = new TodoService();
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        boolean running = true;
        outputView.showIntro();

        while (running) {
            Options option = Options.fromInput(inputView.getOption());
            switch (option) {
                case INPUT_CANCEL:
                    outputView.showRestartOption();
                    break;
                case ADD_WORK:
                    String description = inputView.getTodoDescription();
                    if(description.equals(String.valueOf(Options.INPUT_CANCEL.getNumber()))){
                        outputView.showCancelOption(); // 입력 취소
                        break;
                    }else {
                        String dueDate = inputView.getTodoDueDate();
                        if(dueDate.equals(Actions.CANCEL.getAction())){
                            outputView.showCancelOption(); // 입력 취소
                        }else {
                            Todo output = service.addTodo(description, dueDate);
                            outputView.reportCompleteAddingTodo(output);
                        }
                        break;
                    }

                case REMOVE_WORK:
                    if(service.getAllTodos().isEmpty()) {
                        outputView.reportNoneRemovingTodo();
                        break;
                    }else {
                        int deleteId = inputView.getTodoId(Actions.DELETE);
                        if(deleteId == Options.INPUT_CANCEL.getNumber() ){
                            outputView.showCancelOption(); // 입력 취소
                            break;
                        }else {
                            int resultNum = service.removeTodoById(deleteId);
                            outputView.reportDeleteResult(deleteId, resultNum);
                        }
                    }
                    break;

                case SEARCH_WORK:
                    if(service.getAllTodos().isEmpty()){
                        outputView.reportEmptyTodos();
                        break;
                    }
                    String dueDate = inputView.askDueDate();
                    outputView.reportInputResult(dueDate);
                    if(dueDate.equals(Actions.ALL.getAction())) {
                        outputView.displayAllTodos(service.getAllTodos()); // 모든 리스트 출력
                    }else{
                        try{
                            int dueDateNum = Integer.parseInt(dueDate);
                            outputView.displayTodosWithinDueDate(dueDateNum);
                        } catch (NumberFormatException e) {
                            outputView.showUnknownError();
                        }
                    }

                    break;

                case COMPLETE_WORK:
                    if(service.getAllTodos().isEmpty()) { // 등록된 일이 없을 경우
                        outputView.reportNoneTodo();
                        break;
                    }else {
                        outputView.reportIncompleteTodo(service.getAllTodos());
                        int incompleteTodoCount = service.getIncompleteTodoCount();
                        if(incompleteTodoCount==0) { // 미완료된 일이 없을 경우
                            break;
                        }else {
                            int completeId = inputView.getTodoId(Actions.COMPLETE); // 완료처리할 일을 입력받음
                            if(completeId == Options.INPUT_CANCEL.getNumber() ){
                                outputView.showCancelOption(); // 입력 취소
                                break;
                            }else {
                                int resultNum = service.completeTodoById(completeId);
                                outputView.reportCompletedResult(completeId, resultNum);
                                break;
                            }
                        }
                    }
                case EXIT:
                    running = false;
                    outputView.showExit();
                    break;
                default:
                    outputView.showIncorrectInput();
                    break;
            }
        }
    }
}
