package baseball.controller;

import baseball.application.BaseballService;
import baseball.domain.BaseballGame;
import baseball.domain.BaseballGameRecords;
import baseball.dto.BaseballGameRequest;
import baseball.dto.GameRecordResponse;
import baseball.view.BaseballView;

/**
 * 야구 게임에 대한 요청을 처리한다.
 */
public class BaseballController {
    private final BaseballService baseballService;
    private final BaseballView view;

    public BaseballController(BaseballService baseballService, BaseballView baseballView) {
        this.baseballService = baseballService;
        this.view = baseballView;
    }

    /**
     * 숫자 야구 게임을 진행한다.
     */
    public void gameStart() {
        final BaseballGame baseballGame = baseballService.createGame();

        playLoopQuestion(baseballGame);

        if (requestRestarted()) {
            gameStart();
        }
    }

    private boolean requestRestarted() {
        final BaseballGameRequest request = view.requestHopePlaying();

        return request.isRestarted();
    }

    private void playLoopQuestion(BaseballGame baseballGame) {
        while (!baseballGame.isFinished()) {
            final BaseballGameRequest request = view.requestNumbers();
            BaseballGameRecords records = baseballService.play(baseballGame, request.getNumbers());
            view.drawScoreTable(GameRecordResponse.from(records));
        }

        view.drawResult();
    }


}
