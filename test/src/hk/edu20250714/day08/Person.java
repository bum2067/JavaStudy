package hk.edu20250714.day08;


// Cloneable 인터페이스
// --> 아무런 기능명세가 되어 있지 않은 인터페이스다.
// --> 표식 인터페이스로써 기능은 없고, 그냥 이런저런걸 할 수 있다는 표시
public class Person implements Cloneable {
	
	public String name;
	
	//Person person = new Person();	// 안됨 이거는
	public Person() {
		
	}
	
	public Person(String name) {
		this.name = name;
	}
	
	//clone()는 Object에 구현되어 있는 메서드
	//Override란? 부모의 메서드를 자식이 재정의 하는것 
	@Override
	public Person clone() {
		
		try {
			return (Person) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

}
