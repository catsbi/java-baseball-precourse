package baseball.view;

import baseball.dto.BaseballGameRequest;
import baseball.dto.GameRecordResponse;

/**
 * UI를 관리한다.
 */
public interface BaseballView extends InputView<BaseballGameRequest>, OutputView<GameRecordResponse> {
}
