package org.homework;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private static int ID;
    private static Map<Integer, String> workList = new HashMap<>();


    protected void addWork(String work) {
        ID++;
        workList.put(ID, work);
        System.out.println("저장된 ID값: "+ID);
    }

    protected void removeWork(Integer ID) {
        String result="";
        if(workList.get(ID)==null){
            result = "== 해당 ID 값에 할당된 할 일이 없습니다. ==";
            System.out.println(result);
        }else {
            workList.remove(ID);
        }
    }

    protected String searchWork(Integer ID) {
        String result="";
        if(workList.get(ID)==null) {
            result = "== 해당 ID 값에 할당된 할 일이 없습니다. ==";
        }else {
            result = workList.get(ID);
        }
        return result;
    }

    protected void displayAllWorks() {
        //각각의 key 값에 대해 value 값을 출력한다.
        if(workList.keySet().isEmpty()) {
            System.out.println("할 일 목록에 할 일이 없습니다.");
        }else {
            for( Integer ID : workList.keySet()){
                System.out.println("ID=="+ID+", work=="+workList.get(ID) );
            }
        }
    }

    protected int displayWorksNum() {
        int result = 0;
        result = workList.keySet().size();
        return result;
    }


}
