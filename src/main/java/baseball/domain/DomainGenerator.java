package baseball.domain;

/**
 * 도메인을 생성해 제공한다.
 */
@FunctionalInterface
public interface DomainGenerator<T> {
    T generate();
}
