package mini_project;

import java.util.*;

// 카드 출력을 담당

public class CardPrinter {
	
	// 특정 플레이어의 카드 출력
    public static void printCards(Card_Player viewer, Card_Player owner) {
        List<Card> cards = owner.getHand();
        for (int i = 0; i < cards.size(); i++) {		// 카드가 ??(비공개) 처리되어야 할 조건 -> 삼항 연산자
            boolean isHidden = viewer != owner && (
                (cards.size() == 5 || cards.size() == 6) && (i == 0 || i == 1)		// 카드가 5, 6장이면 인덱스 0, 1
                || (cards.size() == 7) && (i == 0 || i == 1 || i == 6)				// 카드가 7장이면 인덱스 0, 1, 6
            );
            String out = isHidden ? "[??]" : cards.get(i).toString();
            System.out.printf("%-8s", out);
        }
        System.out.println();
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

// 모든 플레이어의 카드를 각 플레이어 시점에서 출력
    
    public void printAllPlayerCards(List<Card_Player> players, Scanner sc) {
        for (Card_Player viewer : players) {
            clearScreen();
            System.out.println("[" + viewer.getName() + " 시점]");
            for (Card_Player target : players) {
                System.out.print(target.getName() + ": ");
                printCards(viewer, target);
            }
            System.out.print("Enter로 계속...");
            sc.nextLine();
        }
    }
}
