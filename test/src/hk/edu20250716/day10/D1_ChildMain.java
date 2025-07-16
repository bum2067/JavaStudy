package hk.edu20250716.day10;

public class D1_ChildMain {

	public static void main(String[] args) {

		D1_Child child = new D1_Child();	//자식의 타입으로 자식을 생성
		
		int a = child.a;	// 부모의 멤버필드 a
		child.childMethod();	// 자식의 메서드
		child.parentMethod();	// 부모의 메서드
		
		D1_Parent parent = new D1_Parent();
		
		int aa = parent.a;
		parent.parentMethod();	// 부모의 메서드를 호출하면 자식이 호출됨
	}
	

}
