package io.zipcoder.casino;

public enum WinningDiceCombo {
    WIN1("456"),
    WIN2("111"),
    WIN3("222"),
    WIN4("333"),
    WIN5("444"),
    WIN6("555"),
    WIN7("666"),
    WIN8("116"),
    WIN9("226"),
    WIN10("336"),
    WIN11("446"),
    WIN12("556"),
    WIN13("161"),
    WIN14("262"),
    WIN15("363"),
    WIN16("464"),
    WIN17("565"),
    WIN18("611"),
    WIN19("622"),
    WIN20("633"),
    WIN21("644"),
    WIN22("655");

    private final String combo;

    WinningDiceCombo(String combo) {
        this.combo = combo;
    }

    public String getCombo() {
        return combo;
    }
}
