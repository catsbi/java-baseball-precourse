package baseball.dto;

import baseball.exception.DuplicateBallException;
import baseball.exception.InvalidBallListSizeException;
import baseball.exception.InvalidPlayCategoryException;

import java.util.Objects;

import static baseball.dto.BaseballGameRequest.PlayCategory.RESTART;
import static baseball.validator.BaseballGameValidators.validateBallListSize;
import static baseball.validator.BaseballGameValidators.validateDuplicateNumber;

public class BaseballGameRequest implements Request {

    enum PlayCategory {
        RESTART(1), STOP(2);

        private final int value;

        PlayCategory(int value) {
            this.value = value;
        }

        public static PlayCategory get(int playCategoryInt) {
            if (playCategoryInt == RESTART.value) {
                return RESTART;
            }

            if (playCategoryInt == STOP.value) {
                return STOP;
            }

            throw new InvalidPlayCategoryException(playCategoryInt);
        }

        public int value() {
            return value;
        }
    }

    private final String numbers;
    private final PlayCategory playCategory;

    private BaseballGameRequest(String numbers, PlayCategory playCategory) {
        checkNumberValidOrThrow(numbers, playCategory);

        this.numbers = numbers;
        this.playCategory = playCategory;
    }

    private void checkNumberValidOrThrow(String numbers, PlayCategory playCategory) {
        if (Objects.nonNull(playCategory)) {
            return;
        }

        if (!validateBallListSize(numbers)) {
            throw new InvalidBallListSizeException();
        }

        if (!validateDuplicateNumber(numbers)) {
            throw new DuplicateBallException();
        }
    }

    public static BaseballGameRequest from(String numbers) {
        return new BaseballGameRequest(numbers, null);
    }

    public static BaseballGameRequest from(int restarted) {
        return new BaseballGameRequest(null, PlayCategory.get(restarted));
    }

    public String getNumbers() {
        return numbers;
    }

    public boolean isRestarted() {
        return RESTART.equals(playCategory);
    }
}
