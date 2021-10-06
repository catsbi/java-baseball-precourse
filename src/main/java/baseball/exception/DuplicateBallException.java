package baseball.exception;

public class DuplicateBallException extends RuntimeException {
    public static final String DEFAULT_MESSAGE = "야구 숫자는 중복 되선 안됩니다.";

    public DuplicateBallException() {
        super(DEFAULT_MESSAGE);
    }
}
