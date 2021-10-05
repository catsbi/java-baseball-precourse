package baseball.exception;

public class InvalidBallIndexException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "유효하지 않은 볼 인덱스입니다. 입력: [%d]";

    public InvalidBallIndexException(int index) {
        super(String.format(DEFAULT_MESSAGE, index));
    }
}
