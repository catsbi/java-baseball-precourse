package baseball.dto;

import baseball.domain.BaseballGameRecords;
import baseball.domain.Zone;

public class GameRecordResponse implements Response {
    private final int countStrike;
    private final int countBall;

    public GameRecordResponse(int countStrike, int countBall) {
        this.countStrike = countStrike;
        this.countBall = countBall;
    }

    public static GameRecordResponse from(BaseballGameRecords records) {
        final int countStrike = records.countByZone(Zone.STRIKE);
        final int countBall = records.countByZone(Zone.BALL);

        return new GameRecordResponse(countStrike, countBall);
    }

    public int getCountStrike() {
        return countStrike;
    }

    public int getCountBall() {
        return countBall;
    }
}
