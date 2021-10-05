package baseball.controller;

import baseball.application.BaseBallService;

/**
 * 야구 게임에 대한 요청을 처리한다.
 */
public class BaseballController {
    private final BaseBallService baseBallService;

    public BaseballController(BaseBallService baseBallService) {
        this.baseBallService = baseBallService;
    }


}
