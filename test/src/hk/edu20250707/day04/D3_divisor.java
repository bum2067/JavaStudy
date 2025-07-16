package hk.edu20250707.day04;

public class D3_divisor {

    public static void main(String[] args) {

        int a = 12;
        for (int i = 1; i <= a; i++) {
            if (a % i == 0) {
                System.out.print(i + " ");
            }
        }

        System.out.println();
        printLine();

        int b = 10;
        int c = 20;

        int gcd = getGCD(b, c);

        System.out.println("최대공약수 : " + gcd);
        printLine();

        int d = 2;
        int e = 4;

        int lcm = getLCM(c, d);

        System.out.println("최소공배수 : " + lcm);
        printLine();

        int f = 220;
        int g = 284;

        int sumF = getDivisior(f);
        int sumG = getDivisior(g);

        // 진약수 출력
        printDivisors(f);
        printDivisors(g);

        // 진약수의 합 출력
        System.out.println(f + "의 진약수 합 : " + sumF);
        System.out.println(g + "의 진약수 합 : " + sumG);

        // 친화수 판별
        if (isAmicable(f, g)) {
            System.out.println(f + "와 " + g + "는 친화수입니다.");
        } else {
            System.out.println(f + "와 " + g + "는 친화수가 아닙니다.");
        }
    }

    // 최대공약수 구하는 메서드
    public static int getGCD(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    // 최대공배수용 최대공약수 메서드
    public static int getGCD2(int d, int e) {
        while (e != 0) {
            int y = d % e;
            d = e;
            e = y;
        }
        return d;
    }

    // 최대공배수 구하는 메서드
    public static int getLCM(int d, int e) {
        return (d * e) / getGCD2(d, e);
    }

    // 구분선 출력
    public static void printLine() {
        System.out.println("------------------------------");
    }

    // 약수의 합(자기 자신 제외) 구하는 메서드
    public static int getDivisior(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    // 진약수들을 출력하는 메서드
    public static void printDivisors(int n) {
        System.out.print(n + "의 진약수들: ");
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    // 친화수인지 확인하는 메서드
    public static boolean isAmicable(int x, int y) {
        return (getDivisior(x) == y) && (getDivisior(y) == x);
    }
}
