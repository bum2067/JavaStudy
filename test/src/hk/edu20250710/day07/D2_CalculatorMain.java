package hk.edu20250710.day07;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class D2_CalculatorMain {

	public static void main(String[] args) {

		// Calculator 객체 생성
		D2_Calculator calc = new D2_Calculator();
		
		// Scanner 객체 생성
		Scanner sc = new Scanner(System.in);

		// 무한 반복 루프
		while(true) {
			System.out.println("계산 값을 입력하세요 (+, -, *, / 연산만 가능) 예: \"5+10\"");
			System.out.println("프로그램 종료를 원하면 9를 입력하세요.");
			System.out.print("입력: ");
			String s = sc.nextLine().trim();

			// 9 입력 시 종료
			if(s.equals("9")) {
				System.out.println("계산 프로그램을 종료해요~~");
				break;
			}

			// 정규표현식으로 "숫자 연산자 숫자" 패턴 확인
			Pattern pattern = Pattern.compile("^\\d+\\s*[-+*/]\\s*\\d+$");
			Matcher matcher = pattern.matcher(s);

			if(matcher.matches()) {
				// 정상 입력일 경우 계산 수행
				calc.calcu(s);
			} else {
				System.out.println("입력 형식이 잘못되었습니다. 예: 5+10, 7 * 8 등 올바른 형식으로 입력하세요.");
			}
		}

		// 스캐너 닫기
		sc.close();
	}
}
