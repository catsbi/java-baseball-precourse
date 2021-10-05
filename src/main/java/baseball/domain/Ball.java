package baseball.domain;

import baseball.exception.InvalidBallValueException;

import java.util.Objects;

/**
 * 야구 게임 숫자
 */
public class Ball {
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
        return value > 0 && value < 10;
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
