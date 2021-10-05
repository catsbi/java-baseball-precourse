package baseball.domain;

import baseball.exception.InvalidBallValueException;

import java.util.Objects;

/**
 * 야구 게임 숫자
 */
public class Ball {
    public static final int MIN_LIMIT = 1;
    public static final int MAX_LIMIT = 9;

    private final int value;

    public Ball(int value) {
        if (!isValidValue(value)) {
            throw new InvalidBallValueException(value);
        }
        this.value = value;
    }

    public Ball(String number) {
        this(Integer.parseInt(number));
    }

    private boolean isValidValue(int value) {
        return value >= MIN_LIMIT && value <= MAX_LIMIT;
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
        return value + "";
    }
}
