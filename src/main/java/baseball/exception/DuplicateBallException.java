package baseball.exception;

import baseball.domain.Ball;

public class DuplicateBallException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "볼 숫자는 중복되선 안됩니다.";
    public static final String DEFAULT_MESSAGE_FORMAT = DEFAULT_MESSAGE + " 입력: [%s]";

    public DuplicateBallException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicateBallException(Ball ball) {
        super(String.format(DEFAULT_MESSAGE_FORMAT, ball));
    }
}
