package baseball.domain;

import baseball.exception.AlreadyGameExitedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("BaseballGameRecords 클래스")
class BaseballGameRecordsTest {

    @DisplayName("newInstance 정적 팩토리 메서드는 새로운 일급 컬렉션 인스턴스를 생성한다.")
    @RepeatedTest(50)
    void newInstance() {
        final BaseballGameRecords newBaseballGameRecords1 = BaseballGameRecords.newInstance();
        final BaseballGameRecords newBaseballGameRecords2 = BaseballGameRecords.newInstance();

        assertThat(newBaseballGameRecords1).isNotSameAs(newBaseballGameRecords2);

    }

    @DisplayName("recordResult 메서드는")
    @Nested
    class Describe_recordResult {
        private BaseballGameRecords records;
        private List<Zone> recordList;

        @BeforeEach
        void setUp() {
            records = BaseballGameRecords.newInstance();
            recordList = Arrays.asList(Zone.BALL, Zone.STRIKE, Zone.BALL);
        }


        @DisplayName("인자가 유효할 경우, 해당 인자를 키로 가지는 값을 증가시킨다.")
        @Test
        void recordResultWithValidKey() {
            for (Zone zone : recordList) {
                records.recordResult(zone);
            }

            assertThat(records.countByZone(Zone.BALL)).isEqualTo(2);
            assertThat(records.countByZone(Zone.STRIKE)).isEqualTo(1);
            assertThat(records.countByZone(Zone.NOTHING)).isZero();
        }

        @DisplayName("인자가 유효하지 않을 경우, 예외를 던진다.")
        @Test
        void recordResultWithInvalidKey() {
            for (int i = 0; i < 3; i++) {
                records.recordResult(Zone.BALL);
            }

            assertThatThrownBy(() -> records.recordResult(Zone.BALL))
                    .isInstanceOf(AlreadyGameExitedException.class);
        }
    }

    @DisplayName("countByZone 메서드는")
    @Nested
    class Describe_countByZone {
        private BaseballGameRecords records;

        @BeforeEach
        void setUp() {
            records = BaseballGameRecords.newInstance();

            records.recordResult(Zone.STRIKE);
            records.recordResult(Zone.NOTHING);
            records.recordResult(Zone.NOTHING);
        }

        @DisplayName("기록되었던 Key를 인자 값으로 호출할 경우, 해당 기록을 조회해 반환한다.")
        @Test
        void countByZoneWithRecorded() {
            assertThat(records.countByZone(Zone.STRIKE)).isEqualTo(1);
            assertThat(records.countByZone(Zone.NOTHING)).isEqualTo(2);
        }

        @DisplayName("기록되지 않은 Key를 인자 값으로 호출할 경우, 기본 값을 반환한다.")
        @Test
        void countByZoneWithoutRecorded() {
            assertThat(records.countByZone(Zone.BALL)).isEqualTo(BaseballGameRecords.DEFAULT_COUNT);
        }
    }

    @DisplayName("count 메서드는 해당 컬렉션의 기록된 총 카운트를 반환한다.")
    @Test
    void count() {
        final BaseballGameRecords records = BaseballGameRecords.newInstance();
        final List<Zone> zones = Arrays.asList(Zone.STRIKE, Zone.BALL, Zone.STRIKE);


        for (int i = 0; i < zones.size(); i++) {
            records.recordResult(zones.get(i));
            assertThat(records.count()).isEqualTo(i + 1);
        }
    }
}
