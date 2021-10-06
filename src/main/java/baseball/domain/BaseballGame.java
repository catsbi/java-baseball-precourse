package baseball.domain;

/**
 * 야구 게임 진행 정보
 */
public class BaseballGame {
    public static final int BALL_LIMIT_COUNT = 3;
    private static final int ZERO = 0;
    private final BallList ballListFromComputer;
    private boolean finished;

    private BaseballGame(BallList ballListFromComputer) {
        this.ballListFromComputer = ballListFromComputer;
        this.finished = false;
    }

    public static BaseballGame newInstance(DomainGenerator<BallList> generator) {
        return new BaseballGame(generator.generate());
    }

    /**
     * 인자로 받은 볼 목록을 컴퓨터 볼 목록과 비교해 결과를 반환한다.
     *
     * @param ballList 비교 할 볼 목록
     * @return 게임 결과
     */
    public BaseballGameRecords matches(BallList ballList) {
        final BaseballGameRecords records = BaseballGameRecords.newInstance();

        for (int i = ZERO; i < ballList.size(); i++) {
            final Ball ball = ballList.get(i);

            records.recordResult(pitchResult(ball, i));
        }

        if (records.countByZone(Zone.STRIKE) == BALL_LIMIT_COUNT) {
            complete();
        }

        return records;
    }

    private void complete() {
        this.finished = true;
    }

    private Zone pitchResult(Ball ball, int index) {
        if (isStrike(ball, index)) {
            return Zone.STRIKE;
        }

        if (isBall(ball)) {
            return Zone.BALL;
        }

        return Zone.NOTHING;
    }

    private boolean isBall(Ball ball) {
        return ballListFromComputer.contains(ball);
    }

    private boolean isStrike(Ball ball, int index) {
        return ballListFromComputer.contains(ball, index);
    }

    /**
     * 야구 게임의 종료 여부를 반환한다.
     *
     * @return 게임 종료 여부에 대한 논리값
     */
    public boolean isFinished() {
        return finished;
    }
}
