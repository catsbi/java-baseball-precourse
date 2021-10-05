package baseball.domain;

import baseball.exception.InvalidBallIndexException;
import baseball.exception.InvalidBallListSizeException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 야구 게임 숫자 일급 컬렉션
 */
public class BallList {
    public static final String DELIMITER = "";
    public static final int BALL_LIMIT_COUNT = 3;
    private final List<Ball> store = new ArrayList<>();

    public BallList() {
    }

    public BallList(Collection<? extends Ball> balls) {
        store.addAll(balls);
    }

    public static BallList from(String numStrings) {
        if (Objects.isNull(numStrings) || numStrings.length() != BALL_LIMIT_COUNT) {
            throw new InvalidBallListSizeException();
        }
        final String[] numbers = numStrings.split(DELIMITER);
        Set<Ball> balls = new LinkedHashSet<>();

        for (String number : numbers) {
            balls.add(new Ball(number));
        }

        return new BallList(balls);
    }

    public Ball get(int index) {
        if (index < 0 || index > store.size()) {
            throw new InvalidBallIndexException(index);
        }
        return store.get(index);
    }

    public boolean contains(Ball target) {
        return store.contains(target);
    }

    public boolean contains(Ball target, int index) {
        return get(index).equals(target);
    }

    public int size() {
        return store.size();
    }
}
