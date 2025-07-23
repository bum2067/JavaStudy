package hk.edu20250723.day15;

import java.util.ArrayList;
import java.util.List;

public class D2_CardGamePlayer {
	private List<D2_Card> hand;		// hand : 1명이 들고 있는 카드 목록

	public D2_CardGamePlayer() {
		hand = new ArrayList<>();	// 초기화해서 빈 카드 목록 생성
	}

	// 카드 받기
	public void receiveCard(D2_Card card) {		// 카드를 추가 --> D2_Card에서 호출
		hand.add(card);
	}

	// 손에 든 카드를 출력하는 메서드
	public void showCards(int playerNumber) {
		System.out.print("유저 " + playerNumber + ": ");
		for (D2_Card card : hand) {
			System.out.print(card + " ");
		}
		System.out.println();
		System.out.println("==================================");
	}

	public List<D2_Card> getHand() {	// 카드 리스트 반환 --> Card에서 가져올 수 있도록
		return hand;
	}
}
