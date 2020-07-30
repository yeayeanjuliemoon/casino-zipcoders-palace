package io.zipcoder.casino.card.games;

import io.zipcoder.casino.GamblingGame;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.card.utilities.CardGame;

import java.util.Map;

public class Blackjack extends CardGame implements GamblingGame {

    private Player dealer;
    private Map<Player, Integer> playerBets;

    public Blackjack() {

    }

    public void dealCard() {

    }

    public Integer countDealerHand() {
        return null;
    }

    public Boolean getPlayerChoice() {
        return null;
    }

    public Boolean playerBust(Player player) {
        return null;
    }

    public void dealerTurn() {

    }

    public void takeBet() {

    }

    public void takeBet(Integer bet) {

    }

    public void payout() {

    }

    public void play() {

    }

    public void nextTurn() {

    }

    public Boolean checkGameState() {
        return null;
    }

    public String printScore() {
        return null;
    }

    public String printGameStatus() {
        return null;
    }

    public String printGameRules() {
        return null;
    }

    public void exit() {

    }
}
