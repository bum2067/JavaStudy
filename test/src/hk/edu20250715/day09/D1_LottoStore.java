package hk.edu20250715.day09;

import java.util.Arrays;
import java.util.Random;

public class D1_LottoStore {
    private D1_LottoGame[] lottos;

    public D1_LottoStore(int count) {
        this.lottos = new D1_LottoGame[count];
        for (int i = 0; i < count; i++) {
            this.lottos[i] = new D1_LottoGame();
        }
    }

    public void printAll() {
        for (int i = 0; i < lottos.length; i++) {
            System.out.print((i + 1) + "번 로또: ");
            lottos[i].print();
        }
    }

    public D1_LottoGame[] getLottos() {
        return this.lottos;
    }

    // 당첨 번호 생성 메서드 (main에서 옮김)
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
}
