package baseball.exception;

public class InvalidResponseTypeException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "유효하지 않은 응답 데이터 타입입니다.";

    public InvalidResponseTypeException() {
        super(DEFAULT_MESSAGE);
    }
}
