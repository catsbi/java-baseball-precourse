package baseball.validator;

import baseball.domain.Ball;

import java.util.Objects;

import static baseball.domain.BaseballGame.BALL_LIMIT_COUNT;

public class BaseballGameValidators {
    public static final int ASCII_ZERO_DECIMAL = 48;
    public static final int INVALID_POINT_ZERO = 0;
    public static final int ZERO = 0;
    public static final int VALID_POINT_ONE = 1;

    private BaseballGameValidators() {
    }

    public static boolean validateBallListSize(String numbers) {
        return Objects.nonNull(numbers) && numbers.length() == BALL_LIMIT_COUNT;
    }

    public static boolean validateDuplicateNumber(String numbers) {
        int count = ZERO;
        for (int i = ZERO; i < numbers.length(); i++) {
            count += checkDuplicate(numbers, i);
        }

        return count <= ZERO;
    }

    private static int checkDuplicate(String numbers, int index) {
        if (numbers.indexOf(numbers.charAt(index)) == index) {
            return INVALID_POINT_ZERO;
        }
        return VALID_POINT_ONE;
    }

    public static boolean validateBallNumber(String numberStr) {
        if (Objects.isNull(numberStr) || numberStr.isEmpty()) {
            return false;
        }

        int count = INVALID_POINT_ZERO;

        for (char ch : numberStr.toCharArray()) {
            count += validateBallNumber(ch);
        }

        return count <= INVALID_POINT_ZERO;
    }

    private static int validateBallNumber(char numCh) {
        if (validateBallNumber(numCh - ASCII_ZERO_DECIMAL)) {
            return INVALID_POINT_ZERO;
        }

        return VALID_POINT_ONE;
    }

    public static boolean validateBallNumber(int number) {
        return Ball.MIN_LIMIT <= number && Ball.MAX_LIMIT >= number;
    }
}
