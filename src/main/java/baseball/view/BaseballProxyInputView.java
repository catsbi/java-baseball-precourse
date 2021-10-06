package baseball.view;

import baseball.dto.BaseballGameRequest;

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
        } catch (RuntimeException e) {
            printException(e);
            return this.requestNumbers();
        }
    }

    @Override
    public BaseballGameRequest requestHopePlaying() {
        try {
            return inputView.requestHopePlaying();
        } catch (RuntimeException e) {
            printException(e);
            return this.requestHopePlaying();
        }
    }

    private void printException(RuntimeException e) {
        System.out.println(ERROR_SUFFIX + e.getMessage());
    }
}
