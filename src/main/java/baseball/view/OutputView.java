package baseball.view;

import baseball.dto.Response;

public interface OutputView<T extends Response> {
    void drawScoreTable(T response);

    void drawResult();
}
