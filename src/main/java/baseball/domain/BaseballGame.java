package baseball.domain;

/**
 * 야구 게임 진행 정보
 */
public class BaseballGame {
    private final BallList ballListFromComputer;
    private final BaseballGameRecords record;

    private BaseballGame(BallList ballListFromComputer) {
        this.ballListFromComputer = ballListFromComputer;
        this.record = BaseballGameRecords.newInstance();
    }

    public static BaseballGame newInstance(DomainGenerator<BallList> generator) {
        return new BaseballGame(generator.generate());
    }


}
