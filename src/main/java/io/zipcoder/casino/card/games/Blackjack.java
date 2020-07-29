package io.zipcoder.casino.card.games;

import io.zipcoder.casino.Console;
import io.zipcoder.casino.GamblingGame;
import io.zipcoder.casino.GamblingPlayer;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardGame;

import java.util.Map;

public class Blackjack extends CardGame implements GamblingGame {

    private GamblingPlayer dealer;
    private Map<Player, Integer> playerBets;
    private Console console = new Console;
    private Integer prizeBalance = 0;

    public Blackjack(Player player) {
        super(2, player);
    }

    public void dealCard() {
        playerHands.get(activePlayer).add(deck.draw());
    }

    public Integer countDealerHand() {
        Integer currentValue = 0;
        for(Card card: playerHands.get(dealer)){
            currentValue += cardValueCalculator(card);
        }
        return currentValue;
    }

    public Boolean getPlayerChoice() {
        return null;
    }

    public Boolean playerBust(Player player) {
        return null;
    }

    public void dealerTurn() {
        while playerHands.get(dealer).
    }

    public void takeBet() {
        Integer wager = console.getIntegerInput("Would you like to place a wager? Please enter a value.");
        ((GamblingPlayer) this.activePlayer).withdraw(wager);
        prizeBalance += (wager *2);
    }

    public void payout() {
        ((GamblingPlayer) this.activePlayer).deposit(this.prizeBalance);
    }

    public void play() {
        console.println(printGameRules());
        takeBet();
        while(this.gameState){
            nextTurn();
        }
    }

    public void nextTurn() {

    }

    public int cardValueCalculator(Card card){
        switch (card.getRank()){
            case ACE:
                return 1;
                break;
            case TWO:
                return 2;
                break;
            case THREE:
                return 3;
                break;
            case FOUR:
                return 4;
                break;
            case FIVE:
                return 5;
                break;
            case SIX:
                return 6;
                break;
            case SEVEN:
                return 7;
                break;
            case EIGHT:
                return 8;
                break;
            case NINE:
                return 9;
                break;
            case TEN:
                return 10;
                break;
            case JACK:
                return 10;
                break;
            case QUEEN:
                return 10;
                break;
            case KING:
                return 10;
                break;
            default:
                return 0;
        }
    }

    public Boolean checkGameState() {
        return null;
    }

    public String printGameStatus() {
        return null;
    }

    public String printGameRules() {
        String rules = "* The goal of the game is to beat the dealer's hand without going over 21\n" +
                "* You and the dealer start with two cards. One of the dealer's cards is hidden until the end.\n"+
                "* You can ask for additional cards until you want to stop or go over 21."+
                "* Cards Two through Nine are face value. Face cards are worth 10. Aces are worth 1 or 11."+
                "* Any more rules?";
        return rules;
    }

    public void exit() {
        console.println("Thank you for playing blackjack!");
    }
}
