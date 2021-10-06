package baseball.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BaseballGame 클래스")
public class BaseballGameTest {

    private BaseballGame baseballGame;

    @DisplayName("matches 메서드는")
    @Nested
    class Describe_matches {
        @DisplayName("컴퓨터의 수가 425일 경우")
        @Nested
        class Context_with_computer_have_425 {
            @BeforeEach
            void setUp() {
                baseballGame = BaseballGame.newInstance(() -> BallList.from("425"));
            }

            @DisplayName("사용자가 123을 제시하면, 1스트라이크를 반환한다.")
            @Test
            void matchesResultIsOneStrikeWithBallList123() {
                final BallList ballList = BallList.from("123");

                final BaseballGameRecords records = baseballGame.matches(ballList);

                assertThat(records.countByZone(Zone.STRIKE)).isEqualTo(1);
                assertThat(records.countByZone(Zone.BALL)).isZero();
                assertThat(records.countByZone(Zone.NOTHING)).isEqualTo(2);
            }

            @DisplayName("사용자가 456을 제시하면, 1스트라이크 1볼을 반환한다.")
            @Test
            void matchesResultIsOneStrikeOneBallWithBallList456() {
                final BallList ballList = BallList.from("456");

                final BaseballGameRecords records = baseballGame.matches(ballList);

                assertThat(records.countByZone(Zone.STRIKE)).isEqualTo(1);
                assertThat(records.countByZone(Zone.BALL)).isEqualTo(1);
                assertThat(records.countByZone(Zone.NOTHING)).isEqualTo(1);
            }

            @DisplayName("사용자가 789를 제시하면, 낫싱을 반환한다.")
            @Test
            void matchesResultIsNothingWithBallList789() {
                final BallList ballList = BallList.from("789");

                final BaseballGameRecords records = baseballGame.matches(ballList);

                assertThat(records.countByZone(Zone.STRIKE)).isZero();
                assertThat(records.countByZone(Zone.BALL)).isZero();
                assertThat(records.countByZone(Zone.NOTHING)).isEqualTo(3);
            }

        }
    }
}
