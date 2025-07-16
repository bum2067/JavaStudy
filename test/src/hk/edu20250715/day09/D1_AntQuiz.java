package hk.edu20250715.day09;

public class D1_AntQuiz {
	
	public String antMake(String s) {
		
		//	"11" --> "12"로 변환해주는 메서드 구현
		//	11 --> 12 --> 12 1121 정수보다는 문자열이 적합
		
		char c = s.charAt(0);	//문자열의 0번째 값 구하기
		int count = 1;
		String t = "";	// t + 1 + 2 -> t + c + count
		
		for (int i = 1; i < s.length(); i++) {
			
			if (c == s.charAt(i)) {	//문자를 비교하자 : 앞문자 == 뒷문자
				count ++;
			}
			else {
				t = t + c + count;
				count = 1;	// 1로 초기화
				c = s.charAt(i);	// 비교할 새로운 값을 저장
			}
		}
		t = t + c + count;
		
		return t;
	}
	
	// 개미수열을 출력해주는 메서드 구현
	public void antPrint(int num) {
		
		String s = "1"; // 시작 문자열

	    for (int i = 1; i <= num; i++) {
	        System.out.println(i + "번째 : " + s); // 현재 항 출력
	        s = antMake(s); // 다음 항 생성
	    }
	}

}
