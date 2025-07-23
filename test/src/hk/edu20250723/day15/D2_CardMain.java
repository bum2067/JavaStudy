package hk.edu20250723.day15;

import java.util.List;
import java.util.Scanner;

public class D2_CardMain {

	public static void main(String[] args) {
//		D2_Card card = new D2_Card();
//		System.out.println(card);
		Scanner sc = new Scanner(System.in);
		

		while (true) {
			
			D2_CardCase cardCase = new D2_CardCase();
			List<D2_Card> cards = cardCase.getCards();
			
			for (int i = 0; i < cards.size(); i++) {
				System.out.print(cards.get(i) + "\t");
				if ((i + 1) % 5 == 0) {
					System.out.println();
					
				}
			}
			System.out.println();
			System.out.println("=======================================");
			
			D2_CardDivide divide = new D2_CardDivide(3, 5, cardCase);	// 유저 3명, 각각 5장씩 배분
			System.out.println("유저별 카드 : ");
			divide.showPlayersCards();
			
			List<D2_CardGamePlayer> players = divide.getPlayers();
			int winnerIndex = 0;
			String bestHand = "";
			int bestScore = -1;

			for (int i = 0; i < players.size(); i++) {
				List<D2_Card> hand = players.get(i).getHand();
				String handName = D2_CardHandEvaluator.evaluateHand(hand); // 족보 이름
				int score = D2_CardHandEvaluator.getHandRankScore(handName); // 족보 점수

			    System.out.println("유저 " + (i + 1) + " 족보: " + handName);

			    if (score > bestScore) {
			        bestScore = score;
			        winnerIndex = i;
			        bestHand = handName;
			    }
			}
			
			
			
			System.out.println("승자는 유저 " + (winnerIndex + 1) + " (" + bestHand + ")!");
			
			System.out.print("\n게임 다시하기 (y/n) : ");
			String input = sc.nextLine();
			
			if (!input.equals("y")) {
				System.out.println("게임을 종료합니다.");
				break;
			}
		}
	}
}
