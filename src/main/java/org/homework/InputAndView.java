package org.homework;

import java.util.Scanner;

public class InputAndView {

    private static final Storage storage = new Storage();
    private static final Scanner scanner = new Scanner(System.in);
    private static String option_select = "";

    public void show_main() {
        show_intro();
        while(!option_select.equals("4")){
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
        option_select = scanner.nextLine();
        switch (option_select) {
            case "1":
                show_select_1();
                break;
            case "2":
                show_select_2();
                break;
            case "3":
                show_select_3();
                break;
            case "4":
                show_select_4();
                break;
            default:
                System.out.println("----------------------------------");
                System.out.println("잘못된 입력입니다.");
                break;
        }
    }


    private void show_select_1() {
        System.out.println("----------------------------------");
        System.out.print("할 일을 추가하시겠습니까? (Y/N) >> ");
        String YN_select = scanner.nextLine();
        if(YN_select.equals("y") || YN_select.equals("Y")){
            System.out.print("할 일을 입력해주세요 >> ");
            String work = scanner.nextLine();
            storage.addWork(work);
        }else if (YN_select.equals("n") || YN_select.equals("N")) {
            show_options();
        }else{
            System.out.println("잘못된 입력입니다. Y 또는 N 으로 답변해주세요.");
            show_select_1();
        }
    }

    private void show_select_2() {
        System.out.println("----------------------------------");
        System.out.print("할 일을 삭제하시겠습니까? (Y/N) >> ");
        String YN_select = scanner.nextLine();
        if(YN_select.equals("y") || YN_select.equals("Y")){
            if(storage.displayWorksNum()==0) {
                System.out.println("삭제할 일이 없습니다.");
                show_options();
            }else{
                System.out.print("삭제할 업무의 ID값을 입력해주세요 (취소: 0) >> ");
                String workID = scanner.nextLine();
                try {
                    Integer ID = Integer.parseInt(workID);
                    if(ID ==0) show_select_2();
                    else storage.removeWork(ID);
                }catch(Exception e) {
                    //만약 workID가 int 값이 아닐 경우
                    System.out.println("잘못된 입력입니다.");
                    show_select_2();
                }
            }

        }else if (YN_select.equals("n") || YN_select.equals("N")) {
            show_options();
        }else{
            System.out.println("잘못된 입력입니다. Y 또는 N 으로 답변해주세요.");
            show_select_2();
        }
    }

    private void show_select_3() {
        System.out.println("----------------------------------");
        System.out.print("할 일을 조회하시겠습니까? (Y/N) >> ");
        String YN_select = scanner.nextLine();
        if(YN_select.equals("y") || YN_select.equals("Y")){
            storage.displayAllWorks();
        }else if (YN_select.equals("n") || YN_select.equals("N")) {
            show_options();
        }else{
            System.out.println("잘못된 입력입니다. Y 또는 N 으로 답변해주세요.");
            show_select_3();
        }
    }

    private void show_select_4() {
        System.out.println("----------------------------------");
        System.out.println("프로그램을 종료합니다.");
        System.out.println("============= FINISH ===============");
    }
}

