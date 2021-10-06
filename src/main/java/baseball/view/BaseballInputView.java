package baseball.view;

import baseball.dto.BaseballGameRequest;
import nextstep.utils.Console;

public class BaseballInputView implements InputView<BaseballGameRequest> {

    public static final String REQUEST_NUMBER_MESSAGE = "숫자를 입력해주세요 : ";
    public static final String REQUEST_RESTART_GAME = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";

    @Override
    public BaseballGameRequest requestNumbers() {
        System.out.print(REQUEST_NUMBER_MESSAGE);
        final String numbers = Console.readLine();

        return BaseballGameRequest.from(numbers);
    }

    @Override
    public BaseballGameRequest requestHopePlaying() {
        System.out.print(REQUEST_RESTART_GAME);
        final String numbers = Console.readLine();

        return BaseballGameRequest.from(Integer.parseInt(numbers));

    }
}
