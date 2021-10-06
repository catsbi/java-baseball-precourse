package baseball.exception;

public class InvalidBallValueException extends RuntimeException {
    public static final String DEFAULT_MESSAGE_FORMAT = "해당 숫자[%s]는 유효하지 않습니다.";

    public InvalidBallValueException(int value) {
        super(String.format(DEFAULT_MESSAGE_FORMAT, value));
    }

    public InvalidBallValueException(String value) {
        super(String.format(DEFAULT_MESSAGE_FORMAT, value));
    }
}
