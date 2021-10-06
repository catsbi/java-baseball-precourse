package baseball.config;

import baseball.application.BaseballService;
import baseball.controller.BaseballController;
import baseball.domain.BallList;
import baseball.domain.DomainGenerator;
import baseball.domain.RandomBallListGenerator;
import baseball.dto.BaseballGameRequest;
import baseball.dto.GameRecordResponse;
import baseball.view.BaseballInputView;
import baseball.view.BaseballOutputView;
import baseball.view.BaseballProxyInputView;
import baseball.view.BaseballView;
import baseball.view.BaseballViewImpl;
import baseball.view.InputView;
import baseball.view.OutputView;

/**
 * 야구 게임에 필요한 객체 등록을 관리한다.
 */
public class BaseballConfig {
    public static final BaseballConfig config = new BaseballConfig();

    private BaseballConfig() {
    }

    public static BaseballConfig getConfig() {
        return config;
    }

    public BaseballController baseballController() {
        return new BaseballController(baseballService(), baseballView());
    }

    private BaseballView baseballView() {
        return new BaseballViewImpl(inputView(), outputView());
    }

    private OutputView<GameRecordResponse> outputView() {
        return new BaseballOutputView(new StringBuilder());
    }

    private InputView<BaseballGameRequest> inputView() {
        return new BaseballProxyInputView(new BaseballInputView());
    }

    private BaseballService baseballService() {
        return new BaseballService(domainGenerator());
    }

    private DomainGenerator<BallList> domainGenerator() {
        return new RandomBallListGenerator();
    }
}
