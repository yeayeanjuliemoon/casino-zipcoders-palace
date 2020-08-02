package io.zipcoder.casino;

public enum LosingDiceCombo {
    LOSE1("123"),
    LOSE2("221"),
    LOSE3("331"),
    LOSE4("441"),
    LOSE5("551"),
    LOSE6("212"),
    LOSE7("313"),
    LOSE8("414"),
    LOSE9("515"),
    LOSE10("122"),
    LOSE11("133"),
    LOSE12("144"),
    LOSE13("155");

    private final String combo;

    LosingDiceCombo(String combo) {
        this.combo = combo;
    }

    public String getCombo() {
        return combo;
    }
}
