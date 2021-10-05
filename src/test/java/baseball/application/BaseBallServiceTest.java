package baseball.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("BaseBallService 클래스")
class BaseBallServiceTest {
    private BaseballService baseBallService;

    @BeforeEach
    void setUp() {
        baseBallService = new BaseballService();
    }

}
