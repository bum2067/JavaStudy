package hk.edu20250708.day05;

public class D1_ClassTestMain {

	public static void main(String[] args) {
		
		
		// 참조타입       객체명        생성자()
		D1_ClassTest classTest = new D1_ClassTest();  //Heap메모리에 생성(객체)
		classTest.methodTest(); //객체명.메서드() 호출
		
		D1_ClassTest.staticMethodTest(); //클래스명.메서드() 호출
		
		
		
		//또 만들 수 있다
		D1_ClassTest classTest2 = new D1_ClassTest();
		classTest2.methodTest();
		classTest2.number = 20;
		
		//인스턴스 멤버필드 : 각 객체에 대해 관리되고 있다. 서로 영향이 없다
		System.out.println(classTest.number+":"+classTest2.number);
		
		//클래스 멤버필드
		classTest.staticNumber=30;  
		System.err.println(classTest2.staticNumber); 
	}
}



//용어-	비유	설명
//클래스 (Class)-	붕어빵 틀	객체를 찍어내는 설계도
//인스턴스 (Instance)-	실제 붕어빵	클래스로 만든 실제 객체
//변수-	팥, 슈크림	객체가 가진 속성들
//메서드-	굽기, 포장	객체가 할 수 있는 행동들