package baseball.domain;

import baseball.exception.DuplicateBallException;
import baseball.exception.InvalidBallIndexException;
import baseball.exception.InvalidBallListSizeException;
import baseball.exception.InvalidBallValueException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static baseball.validator.BaseballGameValidators.validateBallListSize;
import static baseball.validator.BaseballGameValidators.validateBallNumber;
import static baseball.validator.BaseballGameValidators.validateDuplicateNumber;

/**
 * 야구 게임 숫자 일급 컬렉션
 */
public class BallList {
    public static final int BALL_LIMIT_COUNT = 3;
    public static final int ASCII_ZERO_DECIMAL = 48;
    private final List<Ball> store = new ArrayList<>();

    public BallList() {
    }

    public BallList(Collection<? extends Ball> balls) {
        store.addAll(balls);
    }

    public static BallList from(String numStrings) {
        checkValidateOrThrow(numStrings);

        final Collection<? extends Ball> newBalls = createBallList(numStrings);

        return new BallList(newBalls);
    }

    private static void checkValidateOrThrow(String numStrings) {
        if (!validateBallListSize(numStrings)) {
            throw new InvalidBallListSizeException();
        }

        if (!validateBallNumber(numStrings)) {
            throw new InvalidBallValueException(numStrings);
        }

        if (!validateDuplicateNumber(numStrings)) {
            throw new DuplicateBallException();
        }
    }

    private static Collection<? extends Ball> createBallList(String numStrings) {
        final Set<Ball> balls = new LinkedHashSet<>();

        for (int i = 0; i < numStrings.length(); i++) {
            balls.add(new Ball(numStrings.charAt(i) - ASCII_ZERO_DECIMAL));
        }

        return balls;
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
