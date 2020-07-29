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
    private Console console = new Console(System.in, System.out);
    private Integer prizeBalance = 0;

    public Blackjack(Player player) {
        super(2, player);
    }

    public void dealCard(Player player) {
        Card card = deck.draw();
        console.print(player.toString()+" has drawn a "+ card.toString()+"\n");
        playerHands.get(player).add(card);
    }

    public Integer countDealerHand(){
        return countHand(dealer);
    }
    public Integer countHand(Player player) {
        Integer currentValue = 0;
        for(Card card: playerHands.get(player)){
            currentValue += cardValueCalculator(card);
        }
        return currentValue;
    }

    public Boolean getPlayerChoice() {
        console.print("Would you like another card? yes/no");
        while(true) {
            String userInput = console.getStringInput("");
            if (userInput.toLowerCase().equals("yes")) {
                return true;
            } else if (userInput.toLowerCase().equals("no")) {
                return false;
            } else {
                console.print("Please enter \"yes\" or \"no\"");
            }
        }
    }

    public Boolean playerBust(Player player) {
        if(countHand(player) > 21){
            console.println("Oh no! "+player.toString()+" went bust!");
            return true;
        } else {
            return false;
        }
    }

    public void dealerTurn(){
        while(countHand(dealer) >=16){
            dealCard(dealer);
        }
        if(playerBust(dealer)){

        }
        //while playerHands.get(dealer).
    }

    public void takeBet() {
        Integer wager = console.getIntegerInput("Would you like to place a wager? Please enter a value.");
        ((GamblingPlayer) this.activePlayer).withdraw(wager);
        prizeBalance += (wager *2);
    }

    public void payout() {
        int prizeBalance = 0;
        //for (Player player : playerBets)
        ((GamblingPlayer) this.activePlayer).deposit(this.prizeBalance);
    }

    public void play() {
        console.println(printGameRules());
        takeBet();
        while(gameState){
            nextTurn();
        }
        dealerTurn();
        //checkwinner
        exit();

    }

    public void nextTurn() {
        console.println(showHand(activePlayer));
        Boolean choice = getPlayerChoice();
        if(choice){
            dealCard(activePlayer);
        } else {
            gameState = false;
        }
        if(playerBust(activePlayer)){
           gameState = false;
        }
    }

    public int cardValueCalculator(Card card){
        int returnValue;
        switch (card.getRank()){
            case ACE:
                returnValue = 1;
                break;
            case TWO:
                returnValue = 2;
                break;
            case THREE:
                returnValue = 3;
                break;
            case FOUR:
                returnValue = 4;
                break;
            case FIVE:
                returnValue = 5;
                break;
            case SIX:
                returnValue = 6;
                break;
            case SEVEN:
                returnValue = 7;
                break;
            case EIGHT:
                returnValue = 8;
                break;
            case NINE:
                returnValue = 9;
                break;
            case TEN:
                returnValue = 10;
                break;
            case JACK:
                returnValue = 10;
                break;
            case QUEEN:
                returnValue = 10;
                break;
            case KING:
                returnValue = 10;
                break;
            default:
                returnValue = 0;
        }
        return returnValue;
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
                "* You can ask for additional cards until you want to stop or go over 21.\n"+
                "* Cards Two through Nine are face value. Face cards are worth 10. Aces are worth 1 or 11.\n"+
                "* Any more rules?";
        return rules;
    }

    public void exit() {
        console.println("Thank you for playing blackjack!");
    }
}
