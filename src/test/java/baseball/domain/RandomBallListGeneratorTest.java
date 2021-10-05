package baseball.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RandomBallListGenerator 클래스")
class RandomBallListGeneratorTest {

    private DomainGenerator<BallList> generator;

    @BeforeEach
    void setUp() {
        generator = new RandomBallListGenerator();
    }

    @DisplayName("중복되지 않는 볼 3개를 생성한다.")
    @RepeatedTest(50)
    void generateBallListWithNoDuplicatesBall() {
        final BallList createdBallList = generator.generate();
        final Set<Ball> ballSet = new HashSet<>();

        for (int i = 0; i < createdBallList.size(); i++) {
            ballSet.add(createdBallList.get(i));
        }

        assertThat(createdBallList.size()).isEqualTo(BallList.BALL_LIMIT_COUNT);
        assertThat(ballSet).hasSize(BallList.BALL_LIMIT_COUNT);
    }


}
