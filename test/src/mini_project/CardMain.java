package mini_project;
import java.util.*;

public class CardMain {
    private static final int PLAYER_COUNT = 3;

    private static void printCards(List<Card> cards) {
        for (Card c : cards) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private static void printBlankLines(int n) {
        for (int i = 0; i < n; i++) System.out.println();
    }
    
    // 화면 클리어용 메서드 (50줄 빈줄 출력)
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CardCase deck = new CardCase();

        // 1. 플레이어 등록
        List<Card_Player> players = new ArrayList<>();
        for (int i = 1; i <= PLAYER_COUNT; i++) {
            players.add(new Card_Player("Player" + i));
        }

        // 2. 처음에 4장씩 배분
        for (Card_Player p : players) {
            for (int i = 0; i < 4; i++) {
                p.receiveCard(deck.drawCard());
            }
        }

        // 3. 카드 버리기
        for (Card_Player p : players) {
            clearScreen();  // 각 플레이어 차례마다 화면 클리어
            System.out.println(p.getName() + "의 초기 카드:");
            printCards(p.getHand());

            System.out.print("버릴 카드 인덱스 (1~4): ");
            int index = Integer.parseInt(sc.nextLine()) - 1;  // 인덱스 보정
            p.getHand().remove(index);
        }

        Map<Card_Player, Card> openCards = new HashMap<>();

        // 공개할 카드 선택
        for (Card_Player p : players) {
            clearScreen();  // 카드 공개 선택 전 화면 클리어
            System.out.println(p.getName() + "의 현재 카드:");
            printCards(p.getHand());

            System.out.print("공개할 카드 인덱스 선택 (1~3): ");
            int openIndex = Integer.parseInt(sc.nextLine()) - 1;  // 인덱스 보정
            Card open = p.getHand().get(openIndex); // 가져오기만!
            openCards.put(p, open);
        }


        // 5. 공개 카드 높은 순으로 플레이어 정렬
        players.sort((a, b) -> {
            Card ca = openCards.get(a);
            Card cb = openCards.get(b);
            return Integer.compare(
                PokerHandEvaluator.rankToInt(cb.getRank()),
                PokerHandEvaluator.rankToInt(ca.getRank())
            );
        });

        List<Card_Player> bettingPlayers = new ArrayList<>(players);
        Map<Card_Player, PokerHandEvaluator.HandResult> handResults = new HashMap<>();

        // [4~5번째 카드] 한 번에 지급
        System.out.println("\n===== [4~5번째 카드 지급] =====");
        for (Card_Player p : bettingPlayers) {
        	clearScreen();
            p.receiveCard(deck.drawCard()); // 4번째
            p.receiveCard(deck.drawCard()); // 5번째

            printBlankLines(1);
            clearScreen();
            System.out.println(p.getName() + "의 현재 카드 (5장):");
            printCards(p.getHand());
        }

        // [5장 받은 후] 베팅 여부 확인
        Iterator<Card_Player> iter = bettingPlayers.iterator();
        while (iter.hasNext()) {
            Card_Player p = iter.next();
            printBlankLines(2);
            clearScreen();
            System.out.println(p.getName() + "의 현재 카드 (5장):");
            printCards(p.getHand());

            System.out.print("계속 베팅하시겠습니까? (y/n): ");
            String input = sc.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                iter.remove();
            }
        }

        if (bettingPlayers.isEmpty()) {
        	clearScreen();
            System.out.println("⚠ 아무도 베팅하지 않아 게임 종료");
            sc.close();
            return;
        }

        // [6번째 카드 지급]
        System.out.println("\n===== [6번째 카드 지급] =====");
        for (Card_Player p : bettingPlayers) {
        	clearScreen();
        	p.receiveCard(deck.drawCard());
        }

        // [6장 받은 후] 베팅 여부 확인
        iter = bettingPlayers.iterator();
        while (iter.hasNext()) {
            Card_Player p = iter.next();
            printBlankLines(2);
            clearScreen();
            System.out.println(p.getName() + "의 현재 카드 (6장):");
            printCards(p.getHand());

            System.out.print("계속 베팅하시겠습니까? (y/n): ");
            String input = sc.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                iter.remove();
            }
        }

        if (bettingPlayers.isEmpty()) {
        	clearScreen();
            System.out.println("⚠ 아무도 베팅하지 않아 게임 종료");
            sc.close();
            return;
        }

        // [7번째 카드 지급]
        System.out.println("\n===== [7번째 카드 지급] =====");
        for (Card_Player p : bettingPlayers) {
        	clearScreen();
            p.receiveCard(deck.drawCard());

            printBlankLines(1);
            clearScreen();
            System.out.println(p.getName() + "의 최종 카드 (7장):");
            printCards(p.getHand());
        }
        
        // [7장 받은 후] 최종 베팅 여부 확인
        iter = bettingPlayers.iterator();
        while (iter.hasNext()) {
            Card_Player p = iter.next();
            printBlankLines(2);
            clearScreen();
            System.out.println(p.getName() + "의 최종 카드 (7장):");
            printCards(p.getHand());

            System.out.print("최종 베팅하시겠습니까? (y/n): ");
            String input = sc.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                iter.remove();
            }
        }

        if (bettingPlayers.isEmpty()) {
            clearScreen();
            System.out.println("⚠ 아무도 베팅하지 않아 게임 종료");
            sc.close();
            return;
        }


        // 9. 족보 평가
        
        System.out.println("\n=== 최종 카드 및 족보 평가 ===");
        clearScreen();
        
        Card_Player winner = null;
        PokerHandEvaluator.HandResult best = null;

        for (Card_Player p : bettingPlayers) {
            System.out.println(p.getName() + "의 전체 카드:");
            printCards(p.getHand());

            PokerHandEvaluator.HandResult result = PokerHandEvaluator.evaluate(p.getHand());
            handResults.put(p, result);
            System.out.println("족보: " + result.getRank());

            if (best == null || result.compareTo(best) > 0) {
                best = result;
                winner = p;
            }

            System.out.println("---------------------------------------");
        }

        // 10. 결과
        if (winner != null) {
            System.out.printf("\n🎉 승자: %s (%s)\n", winner.getName(), best.getRank());
        } else {
            System.out.println("⚠ 베팅한 플레이어가 없어 승자 없음");
        }

        sc.close();
        System.out.println("\n게임 종료");
    }
}
