package baseball.exception;

public class InvalidPlayCategoryException extends RuntimeException {
    public static final String DEFAULT_MESSAGE_FORMAT = "유효하지 않은 입력 값 입니다. 입력: [%s]";

    public InvalidPlayCategoryException(int input) {
        super(String.format(DEFAULT_MESSAGE_FORMAT, input));
    }
}
