package baseball.exception;

public class InvalidBallListSizeException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "볼은 3개여야 합니다.";

    public InvalidBallListSizeException() {
        super(DEFAULT_MESSAGE);
    }
}
