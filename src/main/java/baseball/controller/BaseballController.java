package baseball.controller;

import baseball.application.BaseballService;

/**
 * 야구 게임에 대한 요청을 처리한다.
 */
public class BaseballController {
    private final BaseballService baseballService;

    public BaseballController(BaseballService baseballService) {
        this.baseballService = baseballService;
    }


}
