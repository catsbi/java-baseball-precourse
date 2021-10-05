package baseball.domain;

import nextstep.utils.Randoms;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 중복되지 않는 임의의 볼 리스트 일급 컬렉션을 생성한다.
 */
public class RandomBallListGenerator implements DomainGenerator<BallList> {
    @Override
    public BallList generate() {
        Set<Ball> ballSet = new LinkedHashSet<>();

        while (ballSet.size() < 3) {
            final int number = Randoms.pickNumberInRange(Ball.MIN_LIMIT, Ball.MAX_LIMIT);
            ballSet.add(new Ball(number));
        }

        return new BallList(ballSet);
    }
}
