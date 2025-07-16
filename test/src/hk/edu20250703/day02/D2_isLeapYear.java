package hk.edu20250703.day02;

import java.time.Year;
import java.util.*;

//윤년 : 1년은 365일 --> 366일(윤년), 2월의 마지막날 29일
//콘솔에 입력된 값을 자바코드로 전달해서 사용할 수 있음 --> Scanner 클래스

public class D2_isLeapYear {

	public static void main(String[] args) {
		
		System.out.println("연도를 입력하세요 : ");
		Scanner sc = new Scanner(System.in);
		int y = sc.nextInt();
		

	if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)) {
		System.out.println("윤년입니다.");
	}
		else {
			System.out.println("평년입니다.");
		}
	}
}