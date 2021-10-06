package baseball.domain;

import baseball.exception.AlreadyGameExitedException;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * 야구 게임 매칭 결과 일급 컬렉션
 */
public class BaseballGameRecords {
    public static final int DEFAULT_COUNT = 0;
    public static final int INCREMENT_VALUE = 1;
    public static final int DEFAULT_CAPACITY = 3;
    private static final int ZERO = 0;
    private final Map<Zone, Integer> records = new EnumMap<>(Zone.class);

    private BaseballGameRecords() {
    }

    public static BaseballGameRecords newInstance() {
        return new BaseballGameRecords();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseballGameRecords)) {
            return false;
        }
        BaseballGameRecords that = (BaseballGameRecords) o;
        return Objects.equals(records, that.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(records);
    }

    /**
     * 인자를 key로 사용하는 value의 값을 증가시킵니다.
     *
     * @param zone 기록 할 존
     * @throws AlreadyGameExitedException 현재 게임의 기록 횟수가 기본 범위를 벗어난 경우
     */
    public synchronized void recordResult(Zone zone) {
        records.merge(zone, INCREMENT_VALUE, this::merge);
    }

    private Integer merge(Integer value, Integer putValue) {
        if (count() >= DEFAULT_CAPACITY) {
            throw new AlreadyGameExitedException();
        }

        return value + INCREMENT_VALUE;
    }

    /**
     * 총 몇회 기록 되었는지를 반환합니다.
     *
     * @return 총 기록 횟수
     */
    public int count() {
        int sum = ZERO;

        for (Integer value : records.values()) {
            sum += value;
        }

        return sum;
    }

    /**
     * 해당 Zone에 기록된 횟수를 반환합니다.
     *
     * @param zone 조회할 Zone
     * @return 기록 횟수
     */
    public int countByZone(Zone zone) {
        return records.getOrDefault(zone, DEFAULT_COUNT);
    }
}
