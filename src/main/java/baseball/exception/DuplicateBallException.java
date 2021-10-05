package baseball.exception;

import baseball.domain.Ball;

public class DuplicateBallException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "볼 숫자는 중복되선 안됩니다. 입력: [%s]";

    public DuplicateBallException(Ball ball) {
        super(String.format(DEFAULT_MESSAGE, ball));
    }
}
