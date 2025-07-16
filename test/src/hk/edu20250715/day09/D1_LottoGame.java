package hk.edu20250715.day09;

import java.util.Arrays;
import java.util.Random;

public class D1_LottoGame {
    private int[] numbers; // 로또 번호 6개 저장 배열

    // 생성자: 중복 없는 로또 번호 생성
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

    // 번호 출력
    public void print() {
        for (int n : this.numbers) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    // 당첨 번호와 일치하는 개수 반환
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

    // 로또 번호 반환
    public int[] getNumbers() {
        return this.numbers;
    }
}