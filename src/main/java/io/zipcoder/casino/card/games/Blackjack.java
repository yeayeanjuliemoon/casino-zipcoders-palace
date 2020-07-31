package io.zipcoder.casino.card.games;

import io.zipcoder.casino.*;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardGame;
import io.zipcoder.casino.card.utilities.CardRank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public void dealCard(Player player, Card card) {
        playerHands.get(player).add(card);
        console.print(player.toString()+" has drawn a "+ card.toString()+"\n");
    }

    public Integer countHand(List<Card> cards) {
        Integer currentValue = 0;
        for(Card card: cards){
            currentValue += cardValueCalculator(card);
        }
        currentValue = aceTest(currentValue, cards);
        return currentValue;
    }

    public Integer aceTest(Integer value, List<Card> cards){
        if(value > 21){
            for(Card card: cards) {
                if (card.getRank() == CardRank.ACE) {
                    return value - 10;
                }
            }
        }
        return value;
    }


    public void determinePlayerMove(Boolean choice){
        try{
            if(choice){ // Player chose to draw another card
                dealCard(activePlayer, deck.draw());
            } else { // Player chose not to draw another card
                gameState = false;
            }
        } catch(NullPointerException e){ // Bad player input
            determinePlayerMove(translatePlayerChoice(parsePlayerChoice()));
        }
    }
    public Boolean translatePlayerChoice(String playerInput) {
        if (playerInput.equals("yes")) {
            return true;
        } else if (playerInput.equals("no")) {
            return false;
        } else {
            console.print("Please enter \"yes\" or \"no\"\n");
            return null;
        }
    }

    private String parsePlayerChoice(){
        console.print("Would you like another card? yes/no");
        String playerInput = console.getStringInput("");
        return playerInput.toLowerCase();
    }

    public Boolean playerBust(Integer playerHand) {
        if(playerHand > 21){


            pauseForReadability();
            return true;
        } else {
            return false;
        }
    }

    private void dealerTurn(){
        console.println("Dealer hand: "+showHand(dealer));
        while(countHand(playerHands.get(dealer)) <=16){
            dealCard(dealer, deck.draw());
            pauseForReadability();
        }
        if(playerBust(countHand(playerHands.get(dealer)))){
            console.println("Oh no! "+dealer.toString()+" went bust!");
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
            if(playerTwentyOne(countHand(playerHands.get(activePlayer)))) {
                gameState = false;
                payout();
            }
        }
        if(!playerTwentyOne(countHand(playerHands.get(activePlayer)))) {
            if (!playerBust(countHand(playerHands.get(activePlayer)))) {
                dealerTurn();
                if (determineWinner(countHand(playerHands.get(activePlayer)), countHand(playerHands.get(dealer)))) {
                    console.println(activePlayer.toString() + " wins! You won $" + playerBets.get(activePlayer) + "\n");
                    pauseForReadability();
                    payout();
                } else {
                    console.println("Dealer wins.\n");
                    pauseForReadability();
                }
            } else {
                console.println("Oh no! " + activePlayer.toString() + " went bust!");
            }
        }
        exit();

    }

    public void nextTurn() {
        console.println("Your hand: "+showHand(activePlayer)+"\n");
        determinePlayerMove(translatePlayerChoice(parsePlayerChoice()));

        if(playerBust(countHand(playerHands.get(activePlayer)))){
           gameState = false;
        }
    }

    public Boolean playerTwentyOne(Integer handValue){
        if(handValue.equals(21)){
            console.println(activePlayer.toString() + " wins! You won $" + playerBets.get(activePlayer));
            return true;
        }
        return false;
    }

    private Boolean determineWinner(Integer playerHand, Integer dealerHand) {
        if (endCaseWinningHand(playerHand, dealerHand)){
            return true;
        } else if(endCaseDraw(playerHand, dealerHand)){
            return false;
        }else{
            return false;
        }
    }

    public Boolean endCaseWinningHand(Integer playerHand, Integer dealerHand){
        if((playerHand > dealerHand) || (playerBust(dealerHand))) {
            pauseForReadability();
            return true;
        } else {
            return false;
        }
    }

    public Boolean endCaseDraw(Integer playerHand, Integer dealerHand){
        if(playerHand == dealerHand) {
            console.println("Game ends in a draw.\n");
            payBack();
            return true;
        } else {
            return false;
        }
    }

    // TODO - REFACTOR TO ENUM
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

    // TODO - Look into libraries for delaying system printing vs halting whole thread
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
