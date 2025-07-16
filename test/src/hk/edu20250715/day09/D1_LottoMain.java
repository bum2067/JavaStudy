package hk.edu20250715.day09;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class D1_LottoMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 사용자에게 매수 입력받기
        System.out.print("구매할 로또의 장수를 입력하세요: ");
        int count = sc.nextInt();

        // 로또 여러 장 생성
        D1_LottoStore store = new D1_LottoStore(count);
        System.out.println("\n[구매한 로또 번호]");
        store.printAll();

        // 당첨 번호 생성
        int[] win = generateWinningNumbers();
        System.out.print("\n[당첨 번호] ");
        for (int n : win) {
            System.out.print(n + " ");
        }
        System.out.println();

        // 당첨 결과 출력
        System.out.println("\n[당첨 결과]");
        D1_LottoGame[] myLottos = store.getLottos();
        for (int i = 0; i < myLottos.length; i++) {
            int match = myLottos[i].getMatchCount(win);
            int rank = getRank(match);

            System.out.print((i + 1) + "번 로또: ");
            myLottos[i].print();
            System.out.println("→ 일치 수: " + match + "개, " + (rank == 0 ? "꽝입니다." : rank + "등 당첨!"));
            System.out.println();
        }

        sc.close();
    }

    // 당첨 번호 생성
    public static int[] generateWinningNumbers() {
        int[] win = new int[6];
        Random r = new Random();
        int count = 0;

        while (count < 6) {
            int num = r.nextInt(45) + 1;
            boolean d = false;

            for (int i = 0; i < count; i++) {
                if (win[i] == num) {
                    d = true;
                    break;
                }
            }

            if (!d) {
                win[count] = num;
                count++;
            }
        }

        Arrays.sort(win);
        return win;
    }

    // 등수 계산
    public static int getRank(int match) {
        return switch (match) {
            case 6 -> 1;
            case 5 -> 2;
            case 4 -> 3;
            case 3 -> 4;
            case 2 -> 5;
            default -> 0;
        };
    }
}
