package baseball.validator;

import baseball.domain.Ball;
import baseball.domain.BallList;

import java.util.Objects;

public class BaseballGameValidators {
    public static final int ASCII_ZERO_DECIMAL = 48;

    private BaseballGameValidators() {}

    public static boolean validateBallListSize(String numbers) {
        return Objects.nonNull(numbers) && numbers.length() == BallList.BALL_LIMIT_COUNT;
    }

    public static boolean validateDuplicateNumber(String numbers) {
        int count = 0;
        for (int i = 0; i < numbers.length(); i++) {
            count += checkDuplicate(numbers, i);
        }

        return count <= 0;
    }

    private static int checkDuplicate(String numbers, int index) {
        if (numbers.indexOf(numbers.charAt(index)) == index) {
            return 0;
        }
        return 1;
    }

    public static boolean validateBallNumber(String numberStr) {
        if (Objects.isNull(numberStr) || numberStr.isEmpty()) {
            return false;
        }

        int invalidNumberCount = 0;

        for (char ch : numberStr.toCharArray()) {
            invalidNumberCount += validateBallNumber(ch);
        }

        return invalidNumberCount <= 0;
    }

    private static int validateBallNumber(char ch) {
        if (validateBallNumber(ch - ASCII_ZERO_DECIMAL)) {
            return 0;
        }

        return 1;
    }

    public static boolean validateBallNumber(int number) {
        return Ball.MIN_LIMIT <= number && Ball.MAX_LIMIT >= number;
    }
}
