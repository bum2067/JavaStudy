package hk.edu20250717.day11;

import java.util.Arrays;

public class D3_MagicSquareMain {

	public static void main(String[] args) {
		D3_OddMagicSquare oddMagic = new D3_OddMagicSquare(11);
		oddMagic.make();
		int [][] magic = oddMagic.magic;
		for (int i = 0; i < magic.length; i++) {
			for (int j = 0; j < magic.length; j++) {
				System.out.print((magic[i][j]) + "\t");
			}
			System.out.println();
		}
		System.out.println("마방진 검증 결과: " + (oddMagic.isMagicSquare() ? "성공" : "실패"));
		oddMagic.squareSumPrint();
	}

}

