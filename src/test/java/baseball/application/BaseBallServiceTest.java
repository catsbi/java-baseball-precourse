package baseball.application;

import baseball.domain.BallList;
import baseball.domain.BaseballGame;
import baseball.domain.BaseballGameRecords;
import baseball.domain.DomainGenerator;
import baseball.domain.RandomBallListGenerator;
import baseball.domain.Zone;
import baseball.exception.DuplicateBallException;
import baseball.exception.InvalidBallListSizeException;
import baseball.exception.InvalidBallValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("BaseBallService 클래스")
class BaseBallServiceTest {
    private BaseballService baseBallService;

    @BeforeEach
    void setUp() {
        DomainGenerator<BallList> generator = mock(RandomBallListGenerator.class);
        baseBallService = new BaseballService(generator);


        given(generator.generate()).willReturn(BallList.from("123"));
    }

    @DisplayName("createGame 메서드는, 새로운 야구 게임 객체를 만들어 반환한다.")
    @Test
    void createGame() {
        final BaseballGame game = baseBallService.createGame();
        final BaseballGameRecords records = game.matches(BallList.from("123"));

        assertThat(game).isInstanceOf(BaseballGame.class);
        assertThat(records.countByZone(Zone.STRIKE)).isEqualTo(3);
    }

    @DisplayName("play 메서드는 ")
    @Nested
    class Describe_play {
        private BaseballGame baseballGame;

        @BeforeEach
        void setUp() {
            baseballGame = baseBallService.createGame();
        }

        @DisplayName("인자가 유효 할 경우, 인자 값을 비교해 비교 결과를 반환한다.")
        @ParameterizedTest
        @CsvSource(value = {"123:3", "142:1", "153:2", "921:1", "723:2"}, delimiter = ':')
        void playWithValidData(String numStrings, int strikeCount) {
            final BaseballGameRecords records = baseBallService.play(baseballGame, numStrings);

            assertThat(records.countByZone(Zone.STRIKE)).isEqualTo(strikeCount);

        }

        @DisplayName("사용자의 입력 값이 3자리가 아닌 경우, 예외를 던진다. ")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"1234", "5678", "12", "1", "999999999999"})
        void playWithInvalidLengthNumbStrings(String numStrings) {

            assertThatThrownBy(() -> baseBallService.play(baseballGame, numStrings))
                    .isInstanceOf(InvalidBallListSizeException.class);
        }

        @DisplayName("사용자의 입력 문자열의 각각의 문자가 숫자가 아닌 경우, 예외를 던진다.")
        @ParameterizedTest
        @ValueSource(strings = {"12A", "abc", "9c5", "aaa", "3gh", "5d1", "93d"})
        void playWithInvalidTypeNumStrings(String numStrings) {
            assertThatThrownBy(() -> baseBallService.play(baseballGame, numStrings))
                    .isInstanceOf(InvalidBallValueException.class);
        }

        @DisplayName("사용자의 입력 문자열에 중복된 숫자가 있을 경우, 예외를 던진다.")
        @ParameterizedTest
        @ValueSource(strings = {"151", "244", "336", "811", "999", "111", "272"})
        void playWithDuplicatedNumStrings(String numStrings) {
            assertThatThrownBy(() -> baseBallService.play(baseballGame, numStrings))
                    .isInstanceOf(DuplicateBallException.class);
        }
    }

}
