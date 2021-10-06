package baseball.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BaseballGameValidators 클래스")
class BaseballGameValidatorsTest {

    @DisplayName("validateBallListSize 메서드는")
    @Nested
    class Describe_validateBallListSize {
        @DisplayName("숫자 여부와 상관 없이 길이가 3인 문자열을 검사 할 경우, 참을 반환한다.")
        @ParameterizedTest
        @ValueSource(strings = {"123", "456", "789", "999", "abc", "ddd", "9ac", "17h"})
        void validateBallListSizeWithStringLength3(String inputValue) {
            assertThat(BaseballGameValidators.validateBallListSize(inputValue)).isTrue();
        }

        @DisplayName("숫자 여부와 상관 없이 길이가 3이 되지 않거나 Null인 인자를 검사할 경우, 거짓을 반환한다.")
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"1", "12", "1234", "10583873", "aklsdhjf10293", "10219397a;sdfhj10293"})
        void validateBallListSizeWithInvalidLength(String inputValue) {
            assertThat(BaseballGameValidators.validateBallListSize(inputValue)).isFalse();
        }
    }

    @DisplayName("validateDuplicateNumber 메서드는")
    @Nested
    class Describe_validateDuplicateNumber {
        @DisplayName("숫자 여부와 상관 없이 인자 내에 중복된 값이 있을 경우, 거짓을 반환한다.")
        @ParameterizedTest
        @ValueSource(strings = {"111", "222", "333", "131", "233", "311", "646", "aaa"})
        void validateDuplicateNumberWithDuplicatedString(String inputValue) {
            assertThat(BaseballGameValidators.validateDuplicateNumber(inputValue)).isFalse();
        }

        @DisplayName("숫자 여부와 상관 없이 인자 내에 중복된 값이 없을 경우, 참을 반환한다.")
        @ParameterizedTest
        @ValueSource(strings = {"123", "456", "1234567", "13579", "245689", "a", "abc", "aldkf"})
        void validateDuplicateNumberWithoutDuplicatedString(String inputValue) {
            assertThat(BaseballGameValidators.validateDuplicateNumber(inputValue)).isTrue();
        }
    }

    @DisplayName("validateBallNumber 메서드는")
    @Nested
    class Describe_validateBallNumber {
        @DisplayName("인자가 Null이거나 공백일 경우 거짓을 반환한다.")
        @ParameterizedTest
        @NullAndEmptySource
        void validateBallNumberWithBlankString(String inputValue) {
            assertThat(BaseballGameValidators.validateBallNumber(inputValue)).isFalse();
        }

        @DisplayName("문자열의 길이나 중복에 상관없이")
        @Nested
        class Context_with_no_matter_string_length {
            @DisplayName("문자열의 각각의 문자가 모두 0에서 9사이일 경우, 참을 반환한다.")
            @ParameterizedTest
            @ValueSource(strings = {"1", "111", "234", "5893", "49692393", "91238471982347912384719234",
                    "817657346178451273864567234523714567213472135791649123846792138569713"})
            void validateBallNumberWithValidNumber(String inputValue) {
                assertThat(BaseballGameValidators.validateBallNumber(inputValue)).isTrue();
            }

            @DisplayName("문자열의 각각의 문자 중 0에서 9 사이에 포함되지 않는 값이 있을 경우, 거짓을 반환한다.")
            @ParameterizedTest
            @ValueSource(strings = {"0", "-1", "182a", "alskdfj", "8fj49s", "a9d9f9", "5960asdf12312834"})
            void validateBallNumberWithInvalidNumber(String inputValue) {
                assertThat(BaseballGameValidators.validateBallNumber(inputValue)).isFalse();
            }
        }
    }
}
