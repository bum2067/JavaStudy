package mini_project;

import java.util.*;

public class CardMain {

    private static void printCards(Card_Player viewer, Card_Player owner) {
        List<Card> cards = owner.getHand();
        int cardCount = cards.size();

        for (int i = 0; i < cardCount; i++) {
            String out;
            if (viewer == owner) {
                out = cards.get(i).toString();
            } else {
                boolean isHidden = false;
                if (cardCount == 5 || cardCount == 6) {
                    if (i == 0 || i == 1) isHidden = true;
                } else if (cardCount == 7) {
                    if (i == 0 || i == 1 || i == 6) isHidden = true;
                }
                out = isHidden ? "[??]" : cards.get(i).toString();
            }
            System.out.printf("%-8s", out);
        }
        System.out.println();
    }

    private static void printBlankLines(int n) {
        for (int i = 0; i < n; i++) System.out.println();
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    private static void printAllPlayerCards(List<Card_Player> players, Scanner sc) {
        for (Card_Player viewer : players) {
            clearScreen();
            System.out.println("[" + viewer.getName() + " 시점]");
            List<Card_Player> ordered = new ArrayList<>();
            ordered.add(viewer);
            for (Card_Player p : players) {
                if (p != viewer) ordered.add(p);
            }
            for (Card_Player target : ordered) {
                System.out.print(target.getName() + ": ");
                printCards(viewer, target);
            }
            System.out.print("Enter로 계속...");
            sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CardCase deck = new CardCase();

        // ✅ 유저에게 플레이어 수 입력받기 (2~7명)
        int playerCount = 0;
        while (playerCount < 2 || playerCount > 7) {
            System.out.print("플레이어 수를 입력하세요 (2~7): ");
            try {
                playerCount = Integer.parseInt(sc.nextLine());
                if (playerCount < 2 || playerCount > 7) {
                    System.out.println("⚠ 2명 이상 7명 이하만 가능합니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ 숫자로 입력하세요.");
            }
        }

        List<Card_Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            players.add(new Card_Player("Player" + i));
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
        }

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

        for (Card_Player p : bettingPlayers) {
            clearScreen();
            p.receiveCard(deck.drawCard());
            p.receiveCard(deck.drawCard());
        }

        printAllPlayerCards(players, sc);

        int currentMaxBet = 0;

        // 🔁 5장 베팅 라운드
        bettingPlayers = bettingRound(sc, bettingPlayers, currentMaxBet, 5);

        // 6장 배분
        for (Card_Player p : bettingPlayers) {
            p.receiveCard(deck.drawCard());
        }

        printAllPlayerCards(players, sc);

        // 🔁 6장 베팅 라운드
        bettingPlayers = bettingRound(sc, bettingPlayers, currentMaxBet, 6);

        // 7장 배분
        for (Card_Player p : bettingPlayers) {
            p.receiveCard(deck.drawCard());
        }

        printAllPlayerCards(players, sc);

        // 🔁 7장 베팅 라운드
        bettingPlayers = bettingRound(sc, bettingPlayers, currentMaxBet, 7);

        // 🏆 승자 평가
        clearScreen();
        Card_Player winner = null;
        PokerHandEvaluator.HandResult best = null;

        for (Card_Player p : bettingPlayers) {
            System.out.println(p.getName() + "의 전체 카드:");
            printCards(p, p);
            PokerHandEvaluator.HandResult result = PokerHandEvaluator.evaluate(p.getHand());
            handResults.put(p, result);
            System.out.println("족보: " + result.getRank());
            System.out.println("----------------------------------");

            if (best == null || result.compareTo(best) > 0) {
                best = result;
                winner = p;
            }
        }

        int totalPot = 0;
        for (Card_Player p : bettingPlayers) {
            totalPot += p.getBettingSystem().getCurrentBet();
        }

        if (winner != null) {
            winner.getBettingSystem().winPot(totalPot);
            for (Card_Player p : bettingPlayers) {
                if (p != winner) p.getBettingSystem().loseBet();
            }
            System.out.printf("\n🎉 승자: %s (%s)\n", winner.getName(), best.getRank());
            System.out.printf("💰 상금: %,d원\n", totalPot);
        } else {
            System.out.println("⚠ 베팅한 플레이어가 없어 승자 없음");
        }
    }

    // 🪙 베팅 라운드를 메서드로 분리 (코드 중복 제거)
    private static List<Card_Player> bettingRound(Scanner sc, List<Card_Player> players, int currentMaxBet, int cardCount) {
        Iterator<Card_Player> iter = players.iterator();
        while (iter.hasNext()) {
            Card_Player p = iter.next();
            clearScreen();
            System.out.printf("%s의 현재 카드 (%d장):\n", p.getName(), cardCount);
            printCards(p, p);
            System.out.println("현재 잔액: " + p.getBettingSystem().getBalance() + "원");

            System.out.print("계속 베팅하시겠습니까? (y/n): ");
            String input = sc.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                iter.remove();
                continue;
            }

            System.out.println("베팅 옵션: 1) 쿼터  2) 하프  3) 올인  4) 콜  5) 다이");
            System.out.print("선택 (1~5): ");
            int choice = Integer.parseInt(sc.nextLine());

            int balance = p.getBettingSystem().getBalance();
            int myCurrentBet = p.getBettingSystem().getCurrentBet();
            int toCall = currentMaxBet - myCurrentBet;
            int betAmount = 0;

            switch (choice) {
                case 1 -> betAmount = balance / 4;
                case 2 -> betAmount = balance / 2;
                case 3 -> betAmount = balance;
                case 4 -> betAmount = Math.min(toCall, balance);
                case 5 -> {
                    System.out.println("다이 선택. 탈락합니다.");
                    iter.remove();
                    continue;
                }
                default -> {
                    System.out.println("잘못된 선택. 탈락합니다.");
                    iter.remove();
                    continue;
                }
            }

            if (p.getBettingSystem().placeBet(betAmount)) {
                currentMaxBet = Math.max(currentMaxBet, p.getBettingSystem().getCurrentBet());
            } else {
                System.out.println("베팅 실패. 자동 탈락.");
                iter.remove();
            }
        }
        return players;
    }
}
