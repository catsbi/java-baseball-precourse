package baseball.domain;

public enum Zone {
    STRIKE("스트라이크"),
    BALL("볼"),
    NOTHING("낫싱");

    final String name;

    Zone(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.name;
    }
}
