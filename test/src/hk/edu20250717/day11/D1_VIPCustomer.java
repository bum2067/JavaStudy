package hk.edu20250717.day11;

public class D1_VIPCustomer extends D1_Customer{
	
	// 다시 동일한 낸용을 작성할 필요 없이 부모의 멤버필드르 사용하면 된다.
//	private int customerID;	// 고객ID
//	private String customerName;	//고객이름
//	private String customerGrade;	// 고객등급
//	private int bonusPoint;		// 보너스 포인트
//	private double bonusRatio;	// 보너스 적립률

	private int agentID;	// 담당 상담원 ID
	private double saleRatio;	// 할인률
	
	public D1_VIPCustomer() {
		customerGrade = "VIP";
		bonusRatio = 0.05;
		saleRatio = 0.1;
	}
	
	public D1_VIPCustomer(int customerID, String customerName, int agentID) {
		
		// 부모의 생성자를 통해서 ID, Name 멤버필드 초기화
//		super(customerID, customerName);
		super.customerID = customerID;
		super.customerName = customerName;
		super.customerGrade = "VIP";
		super.bonusRatio = 0.05;
		
		this.saleRatio = 0.1;
		this.agentID = agentID;
	}
	
	@Override
	public int calcPrice(int price) {
		bonusPoint += price * bonusRatio;	// 보너스 적립
		return price - (int)(price * saleRatio);	// 할인이 적용된 가격 반환
	}
	
	@Override
	public String toString() {
		return customerName+"님의 등급은 "+customerGrade
				           +"이며, 보너스 포인트는 "+bonusPoint+"입니다.";
	}
}
