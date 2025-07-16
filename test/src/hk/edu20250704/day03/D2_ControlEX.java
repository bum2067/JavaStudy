package hk.edu20250704.day03;

import java.util.Random;
import java.util.Scanner;

public class D2_ControlEX {

	public static void main(String[] args) {
		
		//구구단 출력하기
		//2단을 출력하는 코드
		
		int d = 2;
		for (int i = 1; i <= 9; i++) {
			System.out.println(d + "x" + i + "=" + (d*i));
		}
		
		for (int i =2; i < 10; i++) {
			System.out.println(i+"단");
			for (int j = 1; j < 10; j++) {
				System.out.printf("%dx%d=%d\n", i,j,i*j);
			}
			System.out.println("-------------------");
		}
		
		//2~9단까지 짝수단만 출력
		
		for (int i =2; i < 10; i++) {
			
			System.out.println(i+"단");
			if (i %2 == 0) {
				
				for (int j = 1; j < 10; j++) {
					System.out.printf("%dx%d=%d\n", i,j,i*j);
				}
				System.out.println("-------------------");
			}
		}
		
		//2~9단까지 홀수단만 출력
		
		for (int i =2; i < 10; i++) {
						
			System.out.println(i+"단");
			if (i % 2 != 0) {
							
				for (int j = 1; j < 10; j++) {
					System.out.printf("%dx%d=%d\n", i,j,i*j);
				}
				System.out.println("-------------------");
			}
		}
		
		//1~100까지 합 출력
		int s = 0;
		
		for (int i = 1; i <= 100; i++) {
			s += i;
		}
		System.out.println("1부터 100까지 합은 : " + s);
		
		//1~100까지 4의 배수의 총합을 출력하기
		int s2 = 0;
		
		for (int i = 1; i <= 100; i++) {
			if (i % 4 == 0) {
				s2 += i;
			}
			
		}
		System.out.println("1부터 100까지 4의 배수의 합 : " + s2);
		
		//주사위 2개의 합이 5면 실행을 멈추고, 
		//5가 아니면 계속 실행되게 코드작성
		
		while (true) {
			
			Random random = new Random();
			int d1 = random.nextInt(6) + 1;
			int d2 = random.nextInt(6) + 1;
			
			System.out.println("첫번째 주사위 : " + d1);
			System.out.println("두번째 주사위 : " + d2);
			System.out.println("두 주사위 눈금의 합 : " + (d1+d2));
			
			if (d1 + d2 == 5) {
				System.out.println("주사위 합이 5! 종료합니다");
				break;
			}
		}
		
		//은행 프로그램
		
		boolean run = true;
		
		int choice;
		int balance = 0;
		int gold = 0;
		Scanner a = new Scanner(System.in);
		
		
		while (run) {
			System.out.println("-------------------------------------");
			System.out.println("1.예금 | 2.출금 | 3. 잔고 | 4.종료");
			System.out.println("-------------------------------------");
			System.out.println("선택 > ");
			
			choice = Integer.parseInt(a.nextLine());
			
			switch (choice) {
			case 1:
				System.out.println("예금액 > ");
				gold = Integer.parseInt(a.nextLine());
				balance += gold;
				continue;
						
			case 2:
				System.out.println("출금액 > ");
				gold = Integer.parseInt(a.nextLine());
				if (gold > balance) {
					System.out.println("잔고부족");
					continue;
				}
				balance -= gold;
				continue;
	
						
			case 3:
				System.out.println("잔고액 > " + balance);
				continue;
						
			case 4:
				System.out.println("프로그램 종료");
				run = false;
						break;
				
			default : System.out.println("다시 입력하세요.");
				break;
				
			
			}
		}
		
	}

}
