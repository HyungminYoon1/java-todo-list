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
                case RESTART:
                    outputView.showRestartOption();
                    break;
                case ADD_WORK:
                    String description = inputView.getTodoDescription();
                    if(description.equals(String.valueOf(Options.RESTART.getNumber()))){
                        outputView.showCancelOption(); // 입력 취소
                        break;
                    }else {
                        Todo output = service.addTodo(description);
                        outputView.reportCompleteAddingTodo(output);
                        break;
                    }

                case REMOVE_WORK:
                    if(service.getAllTodos().isEmpty()) {
                        outputView.reportNoneRemovingTodo();
                        break;
                    }else {
                        int deleteId = inputView.getTodoId("삭제");
                        if(deleteId == Options.RESTART.getNumber() ){
                            outputView.showCancelOption(); // 입력 취소
                            break;
                        }else {
                            int resultNum = service.removeTodoById(deleteId);
                            outputView.reportDeleteResult(deleteId, resultNum);
                        }
                    }
                    break;

                case SEARCH_WORK:
                    outputView.displayTodos(service.getAllTodos());
                    break;

                case COMPLETE_WORK:
                    if(service.getAllTodos().isEmpty()) {
                        outputView.reportNoneTodo();
                        break;
                    }else {
                        outputView.reportIncompleteTodo(service.getAllTodos());
                        int completeId = inputView.getTodoId("완료");
                        if(completeId == Options.RESTART.getNumber() ){
                            outputView.showCancelOption(); // 입력 취소
                            break;
                        }else {
                            int resultNum = service.completeTodoById(completeId);
                            outputView.reporCompletedResult(completeId, resultNum);
                            break;
                        }
                    }
                case EXIT:
                    running = false;
                    outputView.showExit();
                    break;
                default:
                    outputView.showIncorrectOption();
                    break;
            }
        }
    }
}
