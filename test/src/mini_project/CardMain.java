package mini_project;
import java.util.*;

public class CardMain {
    private static final int PLAYER_COUNT = 3;

    // 상대방 카드 공개용 인덱스 저장
    private static final Map<Card_Player, Set<Integer>> visibleCardIndices = new HashMap<>();

    private static void printCards(Card_Player viewer, Card_Player owner) {
        List<Card> cards = owner.getHand();
        Set<Integer> visible = visibleCardIndices.getOrDefault(owner, new HashSet<>());

        for (int i = 0; i < cards.size(); i++) {
            if (viewer == owner || visible.contains(i)) {
                System.out.print(cards.get(i) + " ");
            } else {
                System.out.print("[? ?] ");
            }
        }
        System.out.println();
    }

    private static void printAllPlayersCards(Card_Player viewer, List<Card_Player> players) {
        for (Card_Player target : players) {
            System.out.println(target.getName() + "의 현재 카드 (" + target.getHand().size() + "장):");
            printCards(viewer, target);
            System.out.println("=====================");
        }
    }

    private static void printBlankLines(int n) {
        for (int i = 0; i < n; i++) System.out.println();
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CardCase deck = new CardCase();

        List<Card_Player> players = new ArrayList<>();
        for (int i = 1; i <= PLAYER_COUNT; i++) {
            Card_Player p = new Card_Player("Player" + i);
            players.add(p);
            visibleCardIndices.put(p, new HashSet<>());
        }

        for (Card_Player p : players) {
            for (int i = 0; i < 4; i++) {
                p.receiveCard(deck.drawCard());
            }
        }

        for (Card_Player p : players) {
            clearScreen();
            System.out.println(p.getName() + "의 초기 카드:");
            printCards(p, p);
            System.out.print("버릴 카드 인덱스 (1~4): ");
            int index = Integer.parseInt(sc.nextLine()) - 1;
            p.getHand().remove(index);
        }

        Map<Card_Player, Card> openCards = new HashMap<>();
        for (Card_Player p : players) {
            clearScreen();
            System.out.println(p.getName() + "의 현재 카드:");
            printCards(p, p);
            System.out.print("공개할 카드 인덱스 선택 (1~3): ");
            int openIndex = Integer.parseInt(sc.nextLine()) - 1;
            Card open = p.getHand().get(openIndex);
            openCards.put(p, open);
            visibleCardIndices.get(p).add(openIndex);
        }

        players.sort((a, b) -> Integer.compare(
                PokerHandEvaluator.rankToInt(openCards.get(b).getRank()),
                PokerHandEvaluator.rankToInt(openCards.get(a).getRank())
        ));

        List<Card_Player> bettingPlayers = new ArrayList<>(players);
        Map<Card_Player, PokerHandEvaluator.HandResult> handResults = new HashMap<>();

        System.out.println("\n===== [4~5번째 카드 지급] =====");
        for (Card_Player p : bettingPlayers) {
            clearScreen();
            p.receiveCard(deck.drawCard());
            visibleCardIndices.get(p).add(3);
            p.receiveCard(deck.drawCard());
            visibleCardIndices.get(p).add(4);

            System.out.println(p.getName() + "의 현재 카드 (5장):");
            printCards(p, p);
            System.out.print("엔터를 누르면 다음으로...");
            sc.nextLine();
        }

        Iterator<Card_Player> iter = bettingPlayers.iterator();
        while (iter.hasNext()) {
            Card_Player p = iter.next();
            clearScreen();
            printAllPlayersCards(p, bettingPlayers);
            System.out.print("계속 베팅하시겠습니까? (y/n): ");
            String input = sc.nextLine();
            if (!input.equalsIgnoreCase("y")) iter.remove();
        }

        if (bettingPlayers.isEmpty()) {
            clearScreen();
            System.out.println("⚠ 아무도 베팅하지 않아 게임 종료");
            sc.close();
            return;
        }

        System.out.println("\n===== [6번째 카드 지급] =====");
        for (Card_Player p : bettingPlayers) {
            clearScreen();
            p.receiveCard(deck.drawCard());
            visibleCardIndices.get(p).add(5);
        }

        iter = bettingPlayers.iterator();
        while (iter.hasNext()) {
            Card_Player p = iter.next();
            clearScreen();
            printAllPlayersCards(p, bettingPlayers);
            System.out.print("계속 베팅하시겠습니까? (y/n): ");
            String input = sc.nextLine();
            if (!input.equalsIgnoreCase("y")) iter.remove();
        }

        if (bettingPlayers.isEmpty()) {
            clearScreen();
            System.out.println("⚠ 아무도 베팅하지 않아 게임 종료");
            sc.close();
            return;
        }

        System.out.println("\n===== [7번째 카드 지급] =====");
        for (Card_Player p : bettingPlayers) {
            clearScreen();
            p.receiveCard(deck.drawCard());
            System.out.println(p.getName() + "의 최종 카드 (7장):");
            printCards(p, p);
            System.out.print("엔터를 누르면 다음으로...");
            sc.nextLine();
        }

        iter = bettingPlayers.iterator();
        while (iter.hasNext()) {
            Card_Player p = iter.next();
            clearScreen();
            printAllPlayersCards(p, bettingPlayers);
            System.out.print("최종 베팅하시겠습니까? (y/n): ");
            String input = sc.nextLine();
            if (!input.equalsIgnoreCase("y")) iter.remove();
        }

        if (bettingPlayers.isEmpty()) {
            clearScreen();
            System.out.println("⚠ 아무도 베팅하지 않아 게임 종료");
            sc.close();
            return;
        }

        for (Card_Player p : bettingPlayers) {
            for (int i = 0; i < p.getHand().size(); i++) {
                visibleCardIndices.get(p).add(i);
            }
        }

        System.out.println("\n=== 최종 카드 및 족보 평가 ===");
        clearScreen();

        Card_Player winner = null;
        PokerHandEvaluator.HandResult best = null;

        for (Card_Player p : bettingPlayers) {
            System.out.println(p.getName() + "의 전체 카드:");
            printCards(p, p);

            PokerHandEvaluator.HandResult result = PokerHandEvaluator.evaluate(p.getHand());
            handResults.put(p, result);
            System.out.println("족보: " + result.getRank());
            System.out.println("---------------------------------------");

            if (best == null || result.compareTo(best) > 0) {
                best = result;
                winner = p;
            }
        }

        if (winner != null) {
            System.out.printf("\n🎉 승자: %s (%s)\n", winner.getName(), best.getRank());
        } else {
            System.out.println("⚠ 베팅한 플레이어가 없어 승자 없음");
        }

        sc.close();
        System.out.println("\n게임 종료");
    }
}
