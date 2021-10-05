package baseball.domain;

/**
 * 야구 게임 진행 정보
 */
public class BaseballGame {
    private BallList ballListFromComputer;

    private BaseballGame(BallList ballListFromComputer) {
        this.ballListFromComputer = ballListFromComputer;
    }

    public static BaseballGame newInstance(DomainGenerator<BallList> generator) {
        return new BaseballGame(generator.generate());
    }
}
