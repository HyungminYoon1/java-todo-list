package org.homework;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Utils {

    // 오늘 날짜를 기준으로 특정 날짜와의 차이를 일 단위로 계산하는 메서드
    public static int daysBetweenTodayAnd(String dateStr) {
        LocalDate today = LocalDate.now(); // 현재 날짜 가져오기
        LocalDate targetDate = LocalDate.parse(dateStr); // 입력된 문자열을 LocalDate 객체로 변환
        long daysBetween = ChronoUnit.DAYS.between(today, targetDate); // 두 날짜 사이의 차이를 일 단위로 계산
        return (int) daysBetween; // long 값을 int로 변환하여 반환
    }

}
