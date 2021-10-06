package baseball.domain;

import baseball.exception.InvalidBallValueException;

import java.util.Objects;

import static baseball.validator.BaseballGameValidators.validateBallNumber;

/**
 * 야구 게임 숫자
 */
public class Ball {
    public static final int MIN_LIMIT = 1;
    public static final int MAX_LIMIT = 9;
    public static final String EMPTY_STR = "";

    private final int value;

    public Ball(int value) {
        if (!validateBallNumber(value)) {
            throw new InvalidBallValueException(value);
        }
        this.value = value;
    }

    public Ball(String number) {
        this(Integer.parseInt(number));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ball)) {
            return false;
        }
        Ball number = (Ball) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value + EMPTY_STR;
    }
}
