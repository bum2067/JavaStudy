package mini_project;

import java.util.*;

public class GameManager {
    private List<Card_Player> players;
    private CardCase deck;
    private final Scanner sc = new Scanner(System.in);
    private final CardPrinter printer = new CardPrinter();
    private final BettingRoundHandler bettingHandler = new BettingRoundHandler();

    public void play() {
        deck = new CardCase();
        initPlayers();			
        dealInitialCards();
        discardAndReveal();
//      reorderPlayers();
        dealAdditionalCards();

        Card_Player winner = determineWinner();
        announceWinner(winner);
    }

    private void initPlayers() {		// 플레이어 수 입력받기 및 플레이어 생성
        int playerCount = 0;
        while (playerCount < 2 || playerCount > 7) {
            System.out.print("플레이어 수를 입력하세요 (2~7): ");
            try {													// 예외처리
                playerCount = Integer.parseInt(sc.nextLine());
                if (playerCount < 2 || playerCount > 7)
                    System.out.println("⚠ 2명 이상 7명 이하만 가능합니다.");
            } catch (NumberFormatException e) {
                System.out.println("⚠ 숫자로 입력하세요.");
            }
        }

        players = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            players.add(new Card_Player("Player" + i));
        }
    }

    private void dealInitialCards() {		// 플레이어에게 카드를 분배 및 버릴 카드 선택
        for (Card_Player p : players) {
            for (int i = 0; i < 4; i++) p.receiveCard(deck.drawCard());
        }

        for (Card_Player p : players) {		// 향상된 for문 사용
            CardPrinter.clearScreen();
            System.out.println(p.getName() + "의 초기 카드:");
            printer.printCards(p, p);
            System.out.print("버릴 카드 인덱스 (1~4): ");
            int index = Integer.parseInt(sc.nextLine()) - 1;
            p.getHand().remove(index);
        }
    }

    private void discardAndReveal() {		// 카드를 공개 및 플레이어 순서 지정
        Map<Card_Player, Card> openCards = new HashMap<>();
        for (Card_Player p : players) {
            CardPrinter.clearScreen();
            System.out.println(p.getName() + "의 현재 카드:");
            printer.printCards(p, p);
            System.out.print("공개할 카드 인덱스 선택 (1~3): ");
            int openIndex = Integer.parseInt(sc.nextLine()) - 1;
            openCards.put(p, p.getHand().get(openIndex));
        }

        players.sort((a, b) -> {		// 람다식 사용, sort 함수 -> 리스트 정렬
            Card ca = openCards.get(a), cb = openCards.get(b);
            return Integer.compare(
                PokerHandEvaluator.rankToInt(cb.getRank()),
                PokerHandEvaluator.rankToInt(ca.getRank())
            );
        });
    }

    private void dealAdditionalCards() {		// 카드를 추가로 나눠주고 라운드마다 베팅 수행
        for (Card_Player p : players)
            p.receiveCard(deck.drawCard());
        for (Card_Player p : players)
            p.receiveCard(deck.drawCard());

        printer.printAllPlayerCards(players, sc);
        players = bettingHandler.bettingRound(sc, players, 5);

        for (Card_Player p : players)
            p.receiveCard(deck.drawCard());
        printer.printAllPlayerCards(players, sc);
        players = bettingHandler.bettingRound(sc, players, 6);

        for (Card_Player p : players)
            p.receiveCard(deck.drawCard());
        printer.printAllPlayerCards(players, sc);
        players = bettingHandler.bettingRound(sc, players, 7);
    }

    private Card_Player determineWinner() {		// 우승 플레이어 결정
        CardPrinter.clearScreen();
        Card_Player winner = null;
        PokerHandEvaluator.HandResult best = null;

        for (Card_Player p : players) {
            System.out.println(p.getName() + "의 전체 카드:");
            printer.printCards(p, p);
            var result = PokerHandEvaluator.evaluate(p.getHand());
            System.out.println("족보: " + result.getRank());
            System.out.println("----------------------------------");

            if (best == null || result.compareTo(best) > 0) {
                best = result;
                winner = p;
            }
        }

        int totalPot = 0;
        for (Card_Player p : players)
            totalPot += p.getBettingSystem().getCurrentBet();

        if (winner != null) {
            winner.getBettingSystem().winPot(totalPot);
            for (Card_Player p : players)
                if (p != winner) p.getBettingSystem().loseBet();
            System.out.printf("\n🎉 승자: %s (%s)\n", winner.getName(), best.getRank());
            System.out.printf("💰 상금: %,d원\n", totalPot);
        }

        return winner;
    }

    private void announceWinner(Card_Player winner) {
        if (winner == null) System.out.println("⚠ 베팅한 플레이어가 없어 승자 없음");
    }
}
