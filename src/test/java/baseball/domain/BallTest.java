package baseball.domain;

import baseball.exception.InvalidBallValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Number 클래스")
class BallTest {

    private final List<Ball> ballList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int i = 1; i < 10; i++) {
            ballList.add(new Ball(i));
        }
    }

    @DisplayName("생성자는")
    @Nested
    class Describe_constructor {
        @DisplayName("인자가 1에서 9사이의 값일 경우, 정상적으로 생성된다.")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
        void createBallWithValidNumber(int number) {
            assertThatCode(() -> new Ball(number))
                    .doesNotThrowAnyException();
        }

        @DisplayName("인자가 1보다 작거나 9보다 클 경우, 예외를 던진다.")
        @ParameterizedTest
        @ValueSource(ints = {Integer.MIN_VALUE, -9999, -999, -99, 0, 10, 155, 2999, Integer.MAX_VALUE})
        void createBallWithInvalidNumber(int number) {
            assertThatThrownBy(() -> new Ball(number))
                    .isInstanceOf(InvalidBallValueException.class)
                    .hasMessage(String.format(InvalidBallValueException.DEFAULT_MESSAGE, number));
        }
    }

    @DisplayName("equals and hashcode 메서드는")
    @Nested
    class Describe_equals_and_hashCode {

        @DisplayName("동일성을 보장한다.")
        @Test
        void sameTest() {
            for (Ball ball : ballList) {
                assertThat(ball).isEqualTo(ball);
            }
        }

        @DisplayName("동등성을 보장한다.")
        @Test
        void equalTest() {
            for (int i = 0; i < ballList.size(); i++) {
                final Ball ball = ballList.get(i);
                assertThat(ball).isEqualTo(new Ball(i + 1));
            }
        }

    }
}
