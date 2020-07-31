package io.zipcoder.casino.card.games;

import io.zipcoder.casino.*;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardGame;
import io.zipcoder.casino.card.utilities.CardRank;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Blackjack extends CardGame implements GamblingGame {

    private Map<Player, Integer> playerBets;
    private Console console = new Console(System.in, System.out);


    public Blackjack(Player player) {
        super(2, player);
        playerBets = new HashMap<>();
    }

    public void dealCard(Player player) {
        Card card = deck.draw();
        console.print(player.toString()+" has drawn a "+ card.toString()+"\n");
        playerHands.get(player).add(card);
    }

    public Integer countHand(Player player) {
        Integer currentValue = 0;
        Boolean hasAce = false;
        for(Card card: playerHands.get(player)){
            currentValue += cardValueCalculator(card);
            if(card.getRank() == CardRank.ACE){
                hasAce = true;
            }
        }
        if(currentValue > 21 && hasAce){
            currentValue -=10;
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
            playerBets.replace(player,-1);
            pauseForReadability();
            return true;
        } else {
            return false;
        }
    }

    private void dealerTurn(){
        console.println("Dealer hand: "+showHand(dealer));
        while(countHand(dealer) <=16){
            dealCard(dealer);
            pauseForReadability();
        }
        if(playerBust(dealer)){
            playerBets.replace(dealer,-1);
        }
    }

    public void takeBet() {
        Integer wager = console.getIntegerInput("Please place your wager:");
        if(wager <= ((GamblingPlayer) this.activePlayer).getBalance()) {
            ((GamblingPlayer) this.activePlayer).withdraw(wager);
            playerBets.put(activePlayer, wager);
            playerBets.put(dealer, wager);
        } else {
            console.println("Insufficient funds!");
            takeBet();
        }
    }

    public void payout() {
        int winnings = playerBets.get(activePlayer)*2;
        ((GamblingPlayer) this.activePlayer).deposit(winnings);
    }

    public void payBack() {
        int wager = playerBets.get(activePlayer);
        ((GamblingPlayer) this.activePlayer).deposit(wager);
    }

    public void play() {
        console.println(printGameRules());
        takeBet();
        console.println("Dealer has a "+ playerHands.get(dealer).get(0));
        pauseForReadability();
        while(gameState){
            nextTurn();
        }
        if(playerTwentyOne()){
            payout();
        } else if(playerBets.get(activePlayer) >= 0) {
            dealerTurn();
            if(determineWinner()){
                payout();
            }
        }
        exit();

    }

    public void nextTurn() {
        console.println("Your hand: "+showHand(activePlayer));
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

    public Boolean playerTwentyOne(){
        if(countHand(activePlayer).equals(21)){
            console.println(activePlayer.toString() + " wins! You won " + playerBets.get(activePlayer) * 2);
            return true;
        }
        return false;
    }

    private Boolean determineWinner() {
        if ((countHand(activePlayer) > countHand(dealer)) || (playerBets.get(dealer) < 0)) {
            console.println(activePlayer.toString() + " wins! You won " + playerBets.get(activePlayer) * 2);
            pauseForReadability();
            return true;
        } else if(countHand(activePlayer) == countHand(dealer)){
            console.println("Game ends in a draw.");
            payBack();
            return false;
        }else{
            console.println("Dealer wins.");
            pauseForReadability();
            return false;
        }
    }

    public int cardValueCalculator(Card card){
        int returnValue;
        switch (card.getRank()){
            case ACE:
                returnValue = 11;
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
        return gameState;
    }

    public String printGameRules() {
        String rules = "* The goal of the game is to beat the dealer's hand without going over 21\n" +
                "* You and the dealer start with two cards. One of the dealer's cards is hidden until their turn.\n"+
                "* You can ask for additional cards until you want to stop or you go over 21.\n"+
                "* Cards Two through Ten are face value. Face cards are worth 10. Aces are worth 1 or 11.\n\n";
        return rules;
    }

    private void pauseForReadability(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            Logger logger = Logger.getLogger(Casino.class.getName());
            logger.log(Level.INFO, e.toString());
        }
    }
    public void exit() {
        console.println("Thank you for playing blackjack!");
        pauseForReadability();
    }
}
