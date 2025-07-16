package hk.edu20250715.day09;

import java.util.Scanner;

public class D1_LottoMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("구매할 로또의 장수를 입력하세요: ");
        int count = sc.nextInt();

        D1_LottoStore store = new D1_LottoStore(count);
        System.out.println("\n[구매한 로또 번호]");
        store.printAll();

        int[] win = D1_LottoStore.generateWinningNumbers();
        System.out.print("\n[당첨 번호] ");
        for (int n : win) {
            System.out.print(n + " ");
        }
        System.out.println();

        System.out.println("\n[당첨 결과]");
        D1_LottoGame[] myLottos = store.getLottos();
        for (int i = 0; i < myLottos.length; i++) {
            int match = myLottos[i].getMatchCount(win);
            int rank = D1_LottoGame.getRank(match);

            System.out.print((i + 1) + "번 로또: ");
            myLottos[i].print();
            System.out.println("→ 일치 수: " + match + "개, " + (rank == 0 ? "꽝입니다." : rank + "등 당첨!"));
            System.out.println();
        }

        sc.close();
    }
}
