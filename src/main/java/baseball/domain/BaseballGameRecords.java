package baseball.domain;

import java.util.EnumMap;
import java.util.Map;

/**
 * 야구 게임 매칭 결과 일급 컬렉션
 */
public class BaseballGameRecords {
    private final Map<Zone, Integer> records = new EnumMap<>(Zone.class);

    private BaseballGameRecords() {
    }

    public static BaseballGameRecords newInstance() {
        return new BaseballGameRecords();
    }


}
