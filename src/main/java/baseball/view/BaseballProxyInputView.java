package baseball.view;

import baseball.dto.BaseballGameRequest;
import baseball.exception.DuplicateBallException;
import baseball.exception.InvalidBallListSizeException;
import baseball.exception.InvalidPlayCategoryException;

public class BaseballProxyInputView implements InputView<BaseballGameRequest> {
    public static final String ERROR_SUFFIX = "[ERROR]";
    private final BaseballInputView inputView;

    public BaseballProxyInputView(BaseballInputView inputView) {
        this.inputView = inputView;
    }

    @Override
    public BaseballGameRequest requestNumbers() {
        try {
            return inputView.requestNumbers();
        } catch (InvalidBallListSizeException | DuplicateBallException e) {
            System.out.println(ERROR_SUFFIX + e.getMessage());
            return this.requestNumbers();
        }
    }

    @Override
    public BaseballGameRequest requestHopePlaying() {
        try {
            return inputView.requestHopePlaying();
        } catch (NumberFormatException | InvalidPlayCategoryException e) {
            System.out.println(ERROR_SUFFIX + e.getMessage());
            return this.requestHopePlaying();
        }
    }
}
