package baseball.view;

import baseball.dto.BaseballGameRequest;
import baseball.dto.GameRecordResponse;

public class BaseballViewImpl implements BaseballView {
    private final InputView<BaseballGameRequest> inputView;
    private final OutputView<GameRecordResponse> outputView;

    public BaseballViewImpl(InputView<BaseballGameRequest> inputView,
                            OutputView<GameRecordResponse> outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    @Override
    public BaseballGameRequest requestNumbers() {
        return inputView.requestNumbers();
    }

    @Override
    public BaseballGameRequest requestHopePlaying() {
        return inputView.requestHopePlaying();
    }

    @Override
    public void drawScoreTable(GameRecordResponse response) {
        outputView.drawScoreTable(response);
    }

    @Override
    public void drawResult() {
        outputView.drawResult();
    }
}
