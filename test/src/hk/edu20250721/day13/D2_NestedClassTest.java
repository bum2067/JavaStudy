package hk.edu20250721.day13;

public class D2_NestedClassTest {
	
	public static void main(String[] args) {
		StaticInnerClass sIc = new StaticInnerClass("정적내부클래스");
		System.out.println(sIc.getResult());
		
		InnerClass inner = new D2_NestedClassTest()
						  .new InnerClass("내부클래스");
				   System.out.println(inner.getResult());
	}
	
	public int a= 5;
	public int b = 10;
	
	public static int aa = 7;
	public static int bb = 3;
	
	// 정적 내부 클래스 : 독립적으로 사용가능
	static class StaticInnerClass {
		public String message;
		
		public StaticInnerClass(String message) {
			this.message = message;
		}
		
		public int getResult() {
//			int result = a + b;	//static 필드만 접근 가능
			int result = aa + bb;
			return result;
		}
	}
	
	// 인스턴스 내부 클래스 : 외부 클래스를 객체생성해야 사용가능
	class InnerClass{
		public String mesaage;
		
		public InnerClass(String message) {
			this.mesaage = message;
		}
		
		public int getResult() {
			int result = a+b;
			return result;
		}
	}
	
	public void nestedMethod() {
		int c = 5;
		class LocalInnerClass {
			public int number;
			public LocalInnerClass(int number) {
				this.number = number;
			}
			public int getLic() {
				int ss = c;
				return ss + number;
			}
		}
		// 지역 내부 클래스 종료
//		c = 50;
		LocalInnerClass licObj = new LocalInnerClass(100);
		System.out.println("지역내부클래스 : " + licObj.getLic());
		
	}// 메서드 종료
}
