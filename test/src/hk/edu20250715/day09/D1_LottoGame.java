package hk.edu20250715.day09;

import java.util.Arrays;
import java.util.Random;

public class D1_LottoGame {
    private int[] numbers; // 로또 번호 6개 저장 배열

    public D1_LottoGame() {
        this.numbers = new int[6];
        Random r = new Random();
        int count = 0;

        while (count < 6) {
            int num = r.nextInt(45) + 1;
            boolean duplicate = false;

            for (int i = 0; i < count; i++) {
                if (this.numbers[i] == num) {
                    duplicate = true;
                    break;
                }
            }

            if (!duplicate) {
                this.numbers[count] = num;
                count++;
            }
        }

        Arrays.sort(this.numbers); // 정렬
    }

    public void print() {
        for (int n : this.numbers) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    public int getMatchCount(int[] winning) {
        int match = 0;
        for (int n : this.numbers) {
            for (int w : winning) {
                if (n == w) {
                    match++;
                }
            }
        }
        return match;
    }

    public int[] getNumbers() {
        return this.numbers;
    }

    // 등수 계산 메서드 (main에서 옮김)
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
