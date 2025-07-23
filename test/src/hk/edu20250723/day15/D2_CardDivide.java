package hk.edu20250723.day15;

import java.util.ArrayList;
import java.util.List;

public class D2_CardDivide {
	private List<D2_CardGamePlayer> players;	// 모든 유저 저장

	public D2_CardDivide(int numberOfPlayers, int cardsPerPlayer, D2_CardCase cardCase) {	// 유저 수, 유저당 카드 수
		players = new ArrayList<>();
		List<D2_Card> cards = cardCase.getCards();		// 카드분배

		for (int i = 0; i < numberOfPlayers; i++) {
			D2_CardGamePlayer player = new D2_CardGamePlayer();		// 유저 수만큼 player 객체 생성 (3명)
			for (int j = 0; j < cardsPerPlayer; j++) {				// j = 유저 한명에게 카드 cardPerplayer 장을 나눠줌
				if (cards.size() > 0) {								// 리스트가 비어있지 않으면
					player.receiveCard(cards.remove(0));			// 카드 리스트의 앞장부터 나눠주고, 리스트에서 제거되므로 중복 방지 --> remove(0)
				}
			}
			players.add(player);
		}
	}
	
	// 모든 플레이어의 카드 출력
	public void showPlayersCards() {			
		for (int i = 0; i < players.size(); i++) {
			players.get(i).showCards(i + 1);
		}
	}

	// 플레이어 리스트 반환
	public List<D2_CardGamePlayer> getPlayers() {
		return players;
	}
}
