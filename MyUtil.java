package exam.day3.board;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyUtil {
	
	// static 써라 마라.
	// static은 기본적으로는 쓰지않음.
	// 예외적으로 사용하는 경우가 있음.
	// 누가봐도 공유데이터일 때(로그인데이터)
	// 지역변수만 사용하는 함수에 국한해서 -> 유틸 함수같은 경우 static을 붙이고, 왠만해선 붙이지 않아야한다.
	
	public static String getCurrentDate() {
		// 오늘 날짜 구하기
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		String formatedNow = now.format(formatter);
		
		return formatedNow;
	}
	
}
