package baseball;

import baseball.config.BaseballConfig;
import baseball.controller.BaseballController;

public class Application {
    public static void main(String[] args) {
        final BaseballConfig config = BaseballConfig.getConfig();

        final BaseballController controller = config.baseballController();

        controller.gameStart();
    }
}
