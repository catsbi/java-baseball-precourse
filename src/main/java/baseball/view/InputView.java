package baseball.view;

import baseball.dto.Request;

public interface InputView<T extends Request> {
    T requestNumbers();

    T requestHopePlaying();
}
