package hk.edu20250715.day09;

public class D1_LottoStore {
    private D1_LottoGame[] lottos;

    // 로또 여러 장 생성
    public D1_LottoStore(int count) {
        this.lottos = new D1_LottoGame[count];
        for (int i = 0; i < count; i++) {
            this.lottos[i] = new D1_LottoGame();
        }
    }

    // 모든 로또 출력
    public void printAll() {
        for (int i = 0; i < lottos.length; i++) {
            System.out.print((i + 1) + "번 로또: ");
            lottos[i].print();
        }
    }

    public D1_LottoGame[] getLottos() {
        return this.lottos;
    }
}
