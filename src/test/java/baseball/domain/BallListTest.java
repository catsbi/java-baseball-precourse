package baseball.domain;

import baseball.exception.InvalidBallIndexException;
import baseball.exception.InvalidBallListSizeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("BallList 클래스")
class BallListTest {
    private List<Ball> balls;

    @BeforeEach
    void setUp() {
        balls = Arrays.asList(new Ball(1), new Ball(2), new Ball(3));
    }


    @DisplayName("생성자는")
    @Nested
    class Describe_constructor {

        @DisplayName("인자가 없을 경우, 내용이 없는 볼 목록을 생성한다.")
        @Test
        void createBallListWithoutArgs() {
            final BallList ballList = new BallList();

            assertThat(ballList.size()).isZero();
        }

        @DisplayName("인자가 유효하면, 볼 목록을 생성한다.")
        @Test
        void createBallListWithValidArgs() {
            BallList ballList = new BallList(balls);

            assertThat(ballList.size()).isEqualTo(3);

            for (int i = 0; i < balls.size(); i++) {
                assertThat(ballList.get(i)).isEqualTo(balls.get(i));
            }
        }

    }

    @DisplayName("from 메서드는")
    @Nested
    class Describe_from {
        @DisplayName("인자가 있을 경우")
        @Nested
        class Context_with_argument {
            @DisplayName("3자리일 경우")
            @Nested
            class Context_with_digit3 {
                @DisplayName("동일한 숫자가 없으면, 정상적으로 생성된다.")
                @ParameterizedTest
                @ValueSource(strings = {"123", "234", "465", "987", "297", "197", "385"})
                void createBallListWithValidNumber(String numbers) {
                    final BallList ballList = BallList.from(numbers);

                    assertThat(ballList.size()).isEqualTo(3);
                    for (String number : numbers.split("")) {
                        assertThat(ballList.contains(new Ball(number))).isTrue();
                    }
                }
            }

            @DisplayName("3자리가 아닐 경우, 예외를 던진다.")
            @ParameterizedTest
            @NullAndEmptySource
            @ValueSource(strings = {"1", "12", "1234", "12345", "123456", "1234567", "12345678"})
            void createBallListWithNot(String numbers) {
                assertThatThrownBy(() -> BallList.from(numbers))
                        .isInstanceOf(InvalidBallListSizeException.class)
                        .hasMessage(InvalidBallListSizeException.DEFAULT_MESSAGE);
            }
        }
    }

    @DisplayName("get 메서드는")
    @Nested
    class Describe_get {
        private BallList ballList;

        @BeforeEach
        void setUp() {
            ballList = new BallList(balls);
        }

        @DisplayName("유효한 인덱스로 조회하면, 적절한 볼 객체를 반환한다.")
        @Test
        void getWithValidIndex() {
            for (int i = 0; i < 3; i++) {
                final Ball ball = ballList.get(i);
                assertThat(ball).isEqualTo(new Ball(i + 1));
            }
        }

        @DisplayName("유효하지 않은 인덱스를 조회하면, 예외를 던진다.")
        @ParameterizedTest
        @ValueSource(ints = {-10, -5, -1, 4, 5, 6})
        void getWithInvalidIndex(int invalidIndex) {
            assertThatThrownBy(() -> ballList.get(invalidIndex))
                    .isInstanceOf(InvalidBallIndexException.class)
                    .hasMessage(String.format(InvalidBallIndexException.DEFAULT_MESSAGE, invalidIndex));
        }
    }

    @DisplayName("contains 메서드는")
    @Nested
    class Describe_contains {
        private BallList ballList;
        private List<Ball> notExistsBalls = new ArrayList<>();

        @BeforeEach
        void setUp() {
            ballList = new BallList(balls);

            for (int i = 4; i < 10; i++) {
                notExistsBalls.add(new Ball(i));
            }
        }

        @DisplayName("등록된 볼을 인자로 호출할 경우, 참을 반환한다.")
        @Test
        void containsWithExistsBall() {
            for (Ball ball : balls) {
                assertThat(ballList.contains(ball)).isTrue();
            }
        }

        @DisplayName("등록되지 않은 볼을 인자로 호출할 경우, 거짓을 반환한다.")
        @Test
        void containsWithNotExistsBall() {
            for (Ball ball : notExistsBalls) {
                assertThat(ballList.contains(ball)).isFalse();
            }
        }

        @DisplayName("등록된 볼과 다른 위치의 인덱스를 전달할 경우, 거짓을 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"3,1", "3,0", "2,2", "2,0", "1,2", "1,1"}, delimiter = ',')
        void containsWithExistsBallAndOtherPositionIndex(int ballNumber, int index) {
            assertThat(ballList.contains(new Ball(ballNumber), index)).isFalse();
        }

        @DisplayName("등록된 볼과 같은 위치의 인덱스를 전달할 경우, 참을 반환한다.")
        @Test
        void containsWithExistsBallAndMatchPositionIndex() {
            for (int i = 0; i < ballList.size(); i++) {
                assertThat(ballList.contains(new Ball(i + 1), i)).isTrue();
            }
        }

        @DisplayName("등록되지 않은 볼과 다른 위치의 인덱스를 전달할 경우, 거짓을 반환한다.")
        @Test
        void containsWithNotExistsAndOtherPositionIndex() {
            for (int i = ballList.size() + 1; i < 9; i++) {
                assertThat(ballList.contains(new Ball(i), 1)).isFalse();
            }
        }
    }
}
