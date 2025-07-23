package hk.edu20250723.day15;

import java.util.*;

public class D2_CardHandEvaluator {

    public static String evaluateHand(List<D2_Card> hand) {
        // 숫자와 무늬 분리
        List<String> suits = new ArrayList<>();
        List<String> numbers = new ArrayList<>();

        for (D2_Card card : hand) {
            String raw = card.getCard();
            suits.add(raw.substring(0, 1));
            numbers.add(raw.substring(1));
        }

        Map<String, Integer> numCount = new HashMap<>();
        for (String n : numbers) {
            numCount.put(n, numCount.getOrDefault(n, 0) + 1);
        }

        if (numCount.containsValue(4)) return "포카드";
        if (numCount.containsValue(3) && numCount.containsValue(2)) return "풀하우스";
        if (numCount.containsValue(3)) return "트리플";

        int pairCount = 0;
        for (int v : numCount.values()) {
            if (v == 2) pairCount++;
        }

        if (pairCount == 2) return "투페어";
        if (pairCount == 1) return "원페어";

        return "하이카드";
        
    }
    
    
    // 족보를 점수화하여 비교에 사용
    public static int getHandRankScore(String handName) {
        switch (handName) {
            case "포카드": return 7;
            case "풀하우스": return 6;
            case "트리플": return 3;
            case "투페어": return 2;
            case "원페어": return 1;
            default: return 0; // 하이카드
        }
    }
}
