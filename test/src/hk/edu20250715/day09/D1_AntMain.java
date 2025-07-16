package hk.edu20250715.day09;

import java.util.Scanner;

public class D1_AntMain {

	public static void main(String[] args) {
		
		D1_AntQuiz ant = new D1_AntQuiz();
		Scanner sc = new Scanner(System.in);
		System.out.println("출력할 항의 갯수 : ");
		
		int n = sc.nextInt();
		
		ant.antPrint(n);
	}

}
