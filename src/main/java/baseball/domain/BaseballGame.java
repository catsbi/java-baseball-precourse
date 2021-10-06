package baseball.domain;

/**
 * 야구 게임 진행 정보
 */
public class BaseballGame {
    private final BallList ballListFromComputer;

    private BaseballGame(BallList ballListFromComputer) {
        this.ballListFromComputer = ballListFromComputer;
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

        for (int i = 0; i < ballList.size(); i++) {
            final Ball ball = ballList.get(i);

            records.recordResult(pitchResult(ball, i));
        }

        return records;
    }

    private Zone pitchResult(Ball ball, int index) {
        if (ballListFromComputer.contains(ball, index)) {
            return Zone.STRIKE;
        }

        if (ballListFromComputer.contains(ball)) {
            return Zone.BALL;
        }

        return Zone.NOTHING;
    }
}
