package hk.edu20250721.day13;

import java.util.ArrayList;
import java.util.List;

public class D3_GBox<T> {
	
	public static void main(String[] args) {
		D3_GBox<String> strBox = new D3_GBox<String>();
		strBox.set("hello Generic");
		System.out.println(strBox.get());
		
		D3_GBox<Integer>intBox = new D3_GBox<>();
		intBox.set(100);
		System.out.println(intBox.get());
		
		// 타입매개변수 규칙 T, E, K, V, N
//		List<E> list = new ArrayList<E>();
//		Map<K, V> map = new HashMap<>();
		
		// 와일드카드 : <? extends T> T의 하위타입 : 읽기전용
		List<? extends Number> numbers = new ArrayList<Integer>();
//		numbers.add(500);	// 추가, 수정작업을 할 수 없다.
		
		java.util.List<Integer> num = new ArrayList<Integer>();
		num.add(100); num.add(101); num.add(102); num.add(103);
		numbers = num;	// 와일드 카드로 선언된 변수에 참조한다.
//		numbers.add(9393);	// 추가안됨
		System.out.println(numbers.toString());
		
		//<? super T> : T 또는 상위타입 (쓰기도 가능)
		List<? super Integer> ints = new ArrayList<Number>();
	}
	
	private T item;
	
	public void set(T item) {
		this.item = item;
	}
	
	public T get() {
		return item;
		
	}

}
