package challenge_1.enums;

public enum Denomination {
    HUNDRED(100), FIFTY(50), TWENTY(20), TEN(10), FIVE(5);

    private Integer value;

    Denomination(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
