package hk.edu20250716.day10;

// 상속받는 문법 : extends, 다중상속 x
public class D1_Child extends D1_Parent {
	
	public D1_Child() {
		// super(), this()는 반드시 첫줄에 실행 --> 같이 작성될 수 없다.
//		super();	// 생략가능함
		this(4);
		System.out.println("자식생성자(default)");
	}
	
	public D1_Child(int a) {
		super(a);
		System.out.println("자식생성자(오버로딩)");
	}
	
	public void childMethod() {
		System.out.println("자식클래스에서만 정의한 메서드 : " + getClass());
	}
	
	@Override
	public void parentMethod() {
		System.out.println("자식 클래스에 맞게 기능을 재정의한다. : parentMethod()");
	}
	
	//Object 클래스에 toString()
	
	@Override
	public String toString() {
		// TODO 자동 생성된 메소드 스텁
		return super.toString();
	}
}

