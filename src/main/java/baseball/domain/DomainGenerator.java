package baseball.domain;

/**
 * 도메인을 생성해 제공한다.
 */
@FunctionalInterface
public interface DomainGenerator<T> {

    /**
     * 선언 된 정규타입 매개변수인 T 타입의 도메인을 제공한다.
     *
     * @return 생성된 도메인 타입 객체
     */
    T generate();
}
