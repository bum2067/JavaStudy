package hk.edu20250707.day04;

public class D1_StarView {

	public static void main(String[] args) {
		
		/*		★
				★★
				★★★
				★★★★
				★★★★★		>> i(별 갯수), j(행마다 별 증가
		*/
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < i+1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		printLine();
		
		/*
		 		☆☆☆☆★		공백을 출력한 뒤 별을 출력
		 		☆☆☆★★		공백 : 43210
		 		☆☆★★★		
		 		☆★★★★
		 		★★★★★
		 */
		for (int i = 0; i < 5; i++) {
			//공백
			for (int j = 0; j < 4-i; j++) {
				System.out.print(" ");
			}
			//별찍기
			for (int j = 0; j < i+1; j++) {
				System.out.print("★");
				
			}
			System.out.println();
		}
		printLine();
		
		/*
		 		  ★
				 ★★★
				★★★★★
			   ★★★★★★★
			  ★★★★★★★★★
		 */
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i+1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		printLine();
		
		/*
		 	  ★★★★★★★★★
			   ★★★★★★★
				★★★★★
				 ★★★
				  ★
		 */
		
		for (int i = 4; i >= 0; i--) {
			for (int j = 0; j < 4-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i+1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		printLine();
		
		/*
				 ★★★★★
				 ★★★★
				 ★★★
				 ★★
				 ★
		 */
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5-i; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		printLine();
		
		/*
				 ★★★★★
				  ★★★★
				   ★★★
				    ★★
				     ★
		 */
		
		for (int i = 4; i >= 0; i--) {
			//공백
			for (int j = 0; j < 4-i; j++) {
				System.out.print(" ");
			}
			//별찍기
			for (int j = 0; j < i+1; j++) {
				System.out.print("★");
				
			}
			System.out.println();
		}
		printLine();
		
		/*
				  ★
				 ★★★
				★★★★★
			   ★★★★★★★
			  ★★★★★★★★★
			   ★★★★★★★
				★★★★★
				 ★★★
				  ★
		 */
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i+1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		for (int i = 4; i >= 0; i--) {
			//공백
			for (int j = 0; j < 5-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i-1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		printLine();
		
		/*
		 		int값을 지정해서 마름모 출력
		 */
		
		int num = 7;
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num-1-i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i+1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		for (int i = 0; i < num - 1; i++) {
			//공백
			for (int j = 0; j < i + 1; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*(num-i-2) + 1; j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		printLine();
		
		/*
 				절대값 활용해서 마름모 구현 (홀수값만 가능)
		 */
		
		int num2 = 9;	//	행의 갯수
		
		for (int i = 0; i < num2; i++) {
			for (int j = 0; j < Math.abs(num2/2-i) ; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < num2 - Math.abs(num2-1-i*2); j++) {
				System.out.print("★");
			}
			System.out.println();
		}
		printLine();
	}

	public static void printLine() {
	    System.out.println("------------------------------");
	}
}
