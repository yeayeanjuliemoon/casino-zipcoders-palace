package io.zipcoder.casino.card.games;

import io.zipcoder.casino.*;

import io.zipcoder.casino.card.utilities.BlackjackCardValues;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardGame;
import io.zipcoder.casino.card.utilities.CardRank;

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

    public Integer getHandValue(List<Card> cards) {
        Integer currentValue = 0;
        for(Card card: cards){
            currentValue += cardValueCalculator(card);
        }
        currentValue = adjustForAces(currentValue, cards);
        return currentValue;
    }

    public Integer adjustForAces(Integer value, List<Card> cards){
        Integer adjustedValue = value;
        for(Card card: cards) {
            if(adjustedValue > 21 && card.getRank() == CardRank.ACE) {
                    adjustedValue -= 10;
            }
        }
        return adjustedValue;
    }

    public void determinePlayerMove(Boolean choseToDrawCard){
            if(choseToDrawCard){ // Player chose to draw another card
                dealCard(activePlayer, deck.draw());
            } else { // Player chose not to draw another card
                gameState = false;
            }
    }

    public Boolean translatePlayerChoiceToBool(String playerInput) {
        if (playerInput.equals("yes")) {
            return true;
        } else if (playerInput.equals("no")) {
            return false;
        } else {
            console.print("Please enter \"yes\" or \"no\"\n");
            return null;
        }
    }

    public void parsePlayerMoveChoice(){
        console.print("Would you like another card? yes/no");
        String playerInput = console.getStringInput("");
        playerInput = playerInput.toLowerCase();
        try{
            Boolean playerInputAsBool = translatePlayerChoiceToBool(playerInput);
            determinePlayerMove(playerInputAsBool);
        } catch (NullPointerException e){
            parsePlayerMoveChoice();
        }
    }

    public Boolean playerBustTest(Integer playerHand) {
        return playerHand > 21;
    }

    public void dealerTurnSim(){
        console.println("Dealer hand: "+showHand(dealer));
        while(getHandValue(playerHands.get(dealer)) <=16){
            dealCard(dealer, deck.draw());
            pauseForReadability();
        }
    }

    public void takeBet(GamblingPlayer player, Integer wager) {
        if(sufficientFundsCheck(player, wager)) {
            (player).withdraw(wager); // Take money
            playerBets.put(activePlayer, wager);      // Deposit said money in bank
            playerBets.put(dealer, wager);             // implementation has dealer matching your bet
        } else {
            console.println("Insufficient funds!");
            takeBet(player, parseBet());
        }
    }

    public Boolean sufficientFundsCheck(GamblingPlayer player, int wager){
        return wager <= player.getBalance();
    }

    private Integer parseBet(){
        return console.getIntegerInput("Please place your wager:");
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
        gameSetup();
        // Play your turn
        playerPlaysTurn();
        // Provided you didn't get 21, game continues
        if(!playerGotTwentyOne(getHandValue(playerHands.get(activePlayer)))) {
            afterPlayerInput();
        }
        exit();
    }

    public void gameSetup(){
        // Print Rules
        console.println(printGameRules());
        // Get a player's bet
        takeBet((GamblingPlayer) this.activePlayer, parseBet());
        // Show dealer's hand
        console.println("Dealer has a "+ playerHands.get(dealer).get(0));
        pauseForReadability();
    }

    public void playerPlaysTurn(){
        while(checkGameState()){
            nextTurn();
            if(playerGotTwentyOne(getHandValue(playerHands.get(activePlayer)))) {
                endCasePlayerGotTwentyOne();
            }
        }
    }

    public void nextTurn() {
        console.println("Your hand: "+showHand(activePlayer)+"\n");
        parsePlayerMoveChoice();
        if(playerBustTest(getHandValue(playerHands.get(activePlayer)))){
            gameState = false;
        }
    }

    public void endCasePlayerGotTwentyOne(){
        console.println(activePlayer.toString() + " wins! You won $" + playerBets.get(activePlayer) + "\n");
        gameState = false;
        payout();
    }

    public void afterPlayerInput(){
        if (!playerBustTest(getHandValue(playerHands.get(activePlayer)))) {
            dealerTurnSim();
            tallyWinners();
        } else {
            console.println("Oh no! " + activePlayer.toString() + " went bust!");
            pauseForReadability();
        }
    }

    public void tallyWinners() {
        if (playerBustTest(getHandValue(playerHands.get(dealer)))) {
            console.println("Oh no! " + dealer.toString() + " went bust!");
            payout();
        } else if (endCaseWinningHand(getHandValue(playerHands.get(activePlayer)), getHandValue(playerHands.get(dealer)))) {
            console.println(activePlayer.toString() + " wins! You won $" + playerBets.get(activePlayer) + "\n");
            payout();
        } else if(endCaseDraw(getHandValue(playerHands.get(dealer)),getHandValue(playerHands.get(activePlayer)))) {
            console.println("Game ends in a draw.\n");
            payBack();
        } else {
            console.println("Dealer wins.\n");
        }
        pauseForReadability();
    }

    public Boolean playerGotTwentyOne(Integer handValue){
        return handValue.equals(21);
    }

    public Boolean endCaseWinningHand(Integer playerHand, Integer dealerHand){
        return(playerHand > dealerHand);
    }

    public Boolean endCaseDraw(Integer playerHand, Integer dealerHand){
        return playerHand.equals(dealerHand);
    }

    public int cardValueCalculator(Card card){
        return BlackjackCardValues.valueOfRank(card.getRank().toString());
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

    public void setPlayerHand(List<Card> hand){
        this.playerHands.put(this.activePlayer, hand);
    }

    public void setDealerHand(List<Card> hand) {
        this.playerHands.put(dealer, hand);
    }

    public List<Card> getDealerHand(){
        return this.playerHands.get(dealer);
    }

    public void exit() {
        console.println("Thank you for playing blackjack!\n\n\n\n");
        pauseForReadability();
    }
}
