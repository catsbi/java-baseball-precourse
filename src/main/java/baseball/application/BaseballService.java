package baseball.application;

import baseball.domain.BallList;
import baseball.domain.BaseballGame;
import baseball.domain.BaseballGameRecords;
import baseball.domain.DomainGenerator;

/**
 * 야구 게임에 대한 서비스를 제공한다.
 */
public class BaseballService {
    private final DomainGenerator<BallList> ballListGenerator;

    public BaseballService(DomainGenerator<BallList> ballListGenerator) {
        this.ballListGenerator = ballListGenerator;
    }

    /**
     * 새로운 숫자 야구 게임 도메인을 만들어 반환한다.
     *
     * @return 야구 게임
     */
    public BaseballGame createGame() {
        return BaseballGame.newInstance(ballListGenerator);
    }

    /**
     * 숫자 야구 게임을 진행하고 진행 결과를 반환한다.
     *
     * @param baseballGame 숫자 야구 게임
     * @param numStrings 사용자가 입력한 숫자 문자열
     * @return 숫자 야구 게임 결과
     */
    public BaseballGameRecords play(BaseballGame baseballGame, String numStrings) {
        final BallList ballList = BallList.from(numStrings);

        return baseballGame.matches(ballList);
    }

}
