package baseball.view;

import baseball.domain.Zone;
import baseball.dto.GameRecordResponse;

public class BaseballOutputView implements OutputView<GameRecordResponse> {
    public static final int ZERO = 0;
    public static final String EMPTY_STR = "";
    public static final String WHITESPACE = " ";
    private final StringBuilder stringBuilder;

    public BaseballOutputView(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public void drawScoreTable(GameRecordResponse response) {
        String message = createResultMessage(response);

        System.out.println(message);

    }

    private String createResultMessage(GameRecordResponse response) {
        final int strike = response.getCountStrike();
        final int ball = response.getCountBall();

        clearStringBuilder();

        appendStringOrPass(strike);
        appendBallOrPass(ball);
        appendNothingOrPass(strike, ball);

        return stringBuilder.toString();
    }

    private void appendNothingOrPass(int strike, int ball) {
        if (strike + ball == ZERO) {
            stringBuilder.append(Zone.NOTHING);
        }
    }

    private void appendBallOrPass(int ball) {
        if (ball > ZERO) {
            stringBuilder.append(ball)
                    .append(Zone.BALL)
                    .append(WHITESPACE);
        }
    }

    private void appendStringOrPass(int strike) {
        if (strike > ZERO) {
            stringBuilder.append(strike)
                    .append(Zone.STRIKE)
                    .append(WHITESPACE);
        }
    }

    private void clearStringBuilder() {
        stringBuilder.replace(ZERO, stringBuilder.length(), EMPTY_STR);
    }

    @Override
    public void drawResult() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }
}
