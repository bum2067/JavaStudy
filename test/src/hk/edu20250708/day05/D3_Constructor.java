package hk.edu20250708.day05;

public class D3_Constructor {
	
	//티비객체
	private int size = 0;	//중요한 데이터 -> private 선언
	public String color = "검정색";	//색상
	
	//default 생성자 : 단독으로 사용한다면 생략이 가능 --> 오버로딩을 하면 생략 못함
	public D3_Constructor() {
//		super();	//부모생성자 호출 -> super(), this() 둘다 생성자를 호출하기 때문에 첫줄에
		this(60);	//반복 코드를 줄이기 위해 this() 사용하기도 함
		this.size = 60;
	}
		
	public D3_Constructor (int size) {
		super();	//super(), this()같이 사용할 수 없다.
		this.size = size;
	}
		
	public D3_Constructor(int size, String color) {
		this.size = size;
		this.color = color;
	}
	
	//값을 가져오는 메서드
	public int getSize() {
		
//		if(pw == 100) {
//		return size;
//		}
//		else {
//			return 0;
//		}
		return size;
	}
	
	//값을 저장하는 메서드
	public void setSize(int size) {
		this.size = size;
	}

}
