package baseball.exception;

/**
 * 이미 게임이 종료되었을 경우 발생하는 예외
 */
public class AlreadyGameExitedException extends RuntimeException{
    public static final String DEFAULT_MESSAGE = "이번 게임은 이미 종료 되었습니다.";

    public AlreadyGameExitedException() {
        super(DEFAULT_MESSAGE);
    }
}
