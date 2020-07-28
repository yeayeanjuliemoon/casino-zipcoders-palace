package io.zipcoder.casino;

public interface Game {
    String GameName;

    Boolean GameState;

    void play();

    void nextTurn();

    Boolean checkGameState();

    String printGameStatus();

    String printGameRules();

    void exit();
}
