package baseball.domain;

import baseball.exception.DuplicateBallException;
import baseball.exception.InvalidBallIndexException;
import baseball.exception.InvalidBallListSizeException;
import baseball.exception.InvalidBallValueException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static baseball.validator.BaseballGameValidators.validateBallListSize;
import static baseball.validator.BaseballGameValidators.validateBallNumber;
import static baseball.validator.BaseballGameValidators.validateDuplicateNumber;

/**
 * 야구 게임 숫자 일급 컬렉션
 */
public class BallList {
    public static final int ASCII_ZERO_DECIMAL = 48;
    public static final int ZERO = 0;
    private final List<Ball> store = new ArrayList<>();

    public BallList() {
    }

    public BallList(Collection<? extends Ball> balls) {
        store.addAll(balls);
    }

    public static BallList from(String numStrings) {
        checkValidateOrThrow(numStrings);

        final Collection<? extends Ball> newBalls = createBallList(numStrings);

        return new BallList(newBalls);
    }

    private static void checkValidateOrThrow(String numStrings) {
        if (!validateBallListSize(numStrings)) {
            throw new InvalidBallListSizeException();
        }

        if (!validateBallNumber(numStrings)) {
            throw new InvalidBallValueException(numStrings);
        }

        if (!validateDuplicateNumber(numStrings)) {
            throw new DuplicateBallException();
        }
    }

    private static Collection<? extends Ball> createBallList(String numStrings) {
        final Set<Ball> balls = new LinkedHashSet<>();

        for (int i = ZERO; i < numStrings.length(); i++) {
            balls.add(new Ball(numStrings.charAt(i) - ASCII_ZERO_DECIMAL));
        }

        return balls;
    }

    /**
     * 인자가 유효할 경우 해당 인덱스에 등록 된 볼 정보를 반환한다.
     *
     * @param index 조회 할 인덱스
     * @return 야구 숫자 객체
     * @throws InvalidBallIndexException 인자값이 음수이거나 컬렉션의 크기보다 큰 경우
     */
    public Ball get(int index) {
        if (index < ZERO || index > store.size()) {
            throw new InvalidBallIndexException(index);
        }
        return store.get(index);
    }

    /**
     * 해당 야구 숫자가 위치에 상관없이 존재 하는지 여부를 반환한다.
     *
     * @param target 야구 숫자 정보
     * @return 야구 숫자 일치 여부
     */
    public boolean contains(Ball target) {
        return store.contains(target);
    }

    /**
     * 해당 야구 숫자가 해당 위치에 존재 하는지를 확인한 뒤 결과를 반환한다.
     *
     * @param target 야구 숫자 정보
     * @param index  조회 할 인덱스
     * @return 야구 숫자 일치 여부
     */
    public boolean contains(Ball target, int index) {
        return get(index).equals(target);
    }

    /**
     * 현재 저장된 야구 숫자의 갯수를 반환한다.
     *
     * @return 저장 된 야구 숫자 갯수
     */
    public int size() {
        return store.size();
    }
}
