package hk.edu20250717.day11;

public class D3_OddMagicSquare {
	
	public int[][] magic;
	
	public D3_OddMagicSquare(int n) {
		this.magic = new int[n][n];
	}
	
	public void make() {
		int n = magic.length;
		int x = 0;
		int y = n / 2;	// 3/2 = 1		5/2 = 2		0 1 2 3 4
		magic[x][y] = 1;	// 0번째 행에 중간인덱스 위치에 1값을 넣는다.
		
		// 2~9까지의 숫자를 배열에 넣기
		for (int i = 2; i <= n*n; i++) {
			// 값 변경 전에 원본값을 저장
			
			int tempX = x;
			int tempY = y;
			
			if (x-1 < 0) {	// -1 이동했는데 음수이면
				x = n-1;	// x인덱스의 최대값으로 이동
			}
			else {
				x--;		// 음수가 아니라면 -1을 적용하자
			}
			if (y-1 < 0) {
				y = n-1;
			}
			else {
				y--;
			}
			if(magic[x][y] != 0) {	// 이동한 위치에 값이 존재한다면
				x = tempX + 1;		// 원래 위치로 돌아와
				y = tempY;			// 원래 위치로 돌아오기
			}
			
			magic[x][y] = i;	// 최종 위치로 증가된 값을 적용
		}
		
	}
	
	// 마방진 확인 기능
	public boolean isMagicSquare() {
		int n = magic.length;
		int magicSum = n * (n*n+1) / 2 ;	// 마방진의 목표합
		
		// 행 검사
		for (int i = 0; i < n; i++) {
			
			int rowSum = 0;
			for (int j = 0; j < n; j++) {
				rowSum += magic[i][j];
			}
			if (rowSum != magicSum) {
				return false;
			}
			
		// 열 검사
		}
		for (int j = 0; j < n; j++) {
			
			int colSum = 0;
			for (int i = 0; i < n; i++) {
				colSum += magic[i][j];
			}
			if (colSum != magicSum) {
				return false;
			}
		}
		
		// 대각선 검사
		int diag1 = 0;
		int diag2 = 0;
		
		for (int i = 0; i < n; i++) {
			diag1 += magic[i][i];
		}
		if (diag1 != magicSum) {
			return false;
		}
		
		for (int i = 0; i < n; i++) {
			diag2 += magic[i][n-1-i];
		}
		if (diag2 != magicSum) {
			return false;
		}
		return true;
	}
	
	public void squareSumPrint() {
		
		int n = magic.length;
		int diag1 = 0;
		int diag2 = 0;
		
		System.out.println("\n 각 행의 합 : ");
		for (int i = 0; i < n; i++) {
			
			int rowSum = 0;
			for (int j = 0; j < n; j++) {
				rowSum += magic[i][j];
			}
			System.out.println("행" + (i+1) + "의 합 : " + rowSum);
		}
		
		System.out.println("\n 각 열의 합 : ");
		for (int j = 0; j < n; j++) {
			
			int colSum = 0;
			for (int i = 0; i < n; i++) {
				colSum += magic[i][j];
			}
			System.out.println("열" + (j+1) + "의 합 : " + colSum);
		}
		
		System.out.println("\n 대각선의 합:");
	    for (int i = 0; i < n; i++) {
	        diag1 += magic[i][i];
	        diag2 += magic[i][n - 1 - i];
	    }
	    System.out.println("왼쪽 위 → 오른쪽 아래 대각선 합: " + diag1);
	    System.out.println("오른쪽 위 → 왼쪽 아래 대각선 합: " + diag2);
	}

}
