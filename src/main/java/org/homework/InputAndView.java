package org.homework;

import java.util.Scanner;

public class InputAndView {

    private static final Storage storage = new Storage();
    private static final Scanner scanner = new Scanner(System.in);
    private static String inputString = "";
    private static Options selectedOption;

    public void show_main() {
        show_intro();
        while(selectedOption != Options.EXIT){
            show_options();
        }
    }

    private void show_intro() {
        System.out.println("============== START ===============");
    }

    private void show_options(){
        System.out.println("--------- SELECT OPTIONS -----------");
        System.out.println("옵션을 선택하세요:  1. 추가, 2. 삭제, 3. 조회, 4. 종료");
        System.out.print("숫자(1~4) 입력 >> ");
        inputString = scanner.nextLine();

        try{
            int inputNum =Integer.parseInt(inputString);
            selectedOption = Options.fromInput(inputNum);
        } catch (Exception e) {
            selectedOption = Options.INCORRECT;
        }


        switch (selectedOption) {
            case ADD_WORK:
                show_select_add_work();
                break;
            case REMOVE_WORK:
                show_select_remove_work();
                break;
            case SEARCH_WORK:
                show_select_search_work();
                break;
            case EXIT:
                show_select_exit();
                break;
            case INCORRECT:
                System.out.println("----------------------------------");
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }


    private void show_select_add_work() {
        System.out.println("----------------------------------");
        System.out.print("할 일을 추가하시겠습니까? (Y/N) >> ");
        String YN_select =  scanner.nextLine();

        if(YN_select.equalsIgnoreCase("y")){
            System.out.print("할 일을 입력해주세요 >> ");
            String work = scanner.nextLine();
            storage.addWork(work);
        }else if (YN_select.equalsIgnoreCase("n")) {
            show_options();
        }else{
            System.out.println("잘못된 입력입니다. Y 또는 N 으로 답변해주세요.");
            show_select_add_work();
        }
    }

    private void show_select_remove_work() {
        System.out.println("----------------------------------");
        System.out.print("할 일을 삭제하시겠습니까? (Y/N) >> ");
        String YN_select = scanner.nextLine();
        if(YN_select.equalsIgnoreCase("y")){
            if(storage.displayWorksNum()==0) {
                System.out.println("삭제할 일이 없습니다.");
                show_options();
            }else{
                System.out.print("삭제할 업무의 ID값을 입력해주세요 (취소: 0) >> ");
                String workID = scanner.nextLine();
                try {
                    Integer ID = Integer.parseInt(workID);
                    if(ID ==0) show_select_remove_work();
                    else storage.removeWork(ID);
                }catch(Exception e) {
                    //만약 workID가 int 값이 아닐 경우
                    System.out.println("잘못된 입력입니다.");
                    show_select_remove_work();
                }
            }

        }else if (YN_select.equalsIgnoreCase("n")) {
            show_options();
        }else{
            System.out.println("잘못된 입력입니다. Y 또는 N 으로 답변해주세요.");
            show_select_remove_work();
        }
    }

    private void show_select_search_work() {
        System.out.println("----------------------------------");
        System.out.print("할 일을 조회하시겠습니까? (Y/N) >> ");
        String YN_select = scanner.nextLine();
        if(YN_select.equalsIgnoreCase("y")){
            storage.displayAllWorks();
        }else if (YN_select.equalsIgnoreCase("n")) {
            show_options();
        }else{
            System.out.println("잘못된 입력입니다. Y 또는 N 으로 답변해주세요.");
            show_select_search_work();
        }
    }

    private void show_select_exit() {
        System.out.println("----------------------------------");
        System.out.println("프로그램을 종료합니다.");
        System.out.println("============= FINISH ===============");
    }
}

