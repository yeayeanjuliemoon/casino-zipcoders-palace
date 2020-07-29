package io.zipcoder.casino.card.games;

import io.zipcoder.casino.Console;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardGame;
import io.zipcoder.casino.card.utilities.CardRank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoFish extends CardGame {

    private Map<Player, Integer> playerScores;

    public GoFish(Integer handSize, Player activePlayer) {
        super(handSize, activePlayer);
        this.playerScores = new HashMap<Player, Integer>();
        playerScores.put(this.activePlayer, 0);
        playerScores.put(this.dealer, 0);
    }

    public boolean playerTurn(){


        return false;
    }

    public Boolean checkPlayerHand(CardRank rank, Player player) {
        List<Card> playerHand = playerHands.get(player);
        for(Card c : playerHand){
            if(c.getRank() == rank){
                return true;
            }
        }
        return false;
    }

    public void transferCard(Player playerToTransfer) {

    }

    public List<Card> checkForCardSets(Player player) {
        //Goes through a players hand and returns a list of all the cards
        Map<CardRank, Integer> rankMap = new HashMap<>();
        List<Card> playerHand = playerHands.get(player);
    }

    public void goFish(Player player) {
        if(this.deck.getDeck().isEmpty()){
            this.gameState = false;
        }
        else{
            this.playerHands.get(player).add(this.deck.draw());
        }

    }

    public void removeSet(CardRank rank) {

    }

    public void incrementScore(Player player) {
        Integer newScore = this.playerScores.get(player) + 1;
        this.playerScores.replace(player, newScore);
    }

    public Player determineWinner() {
        return null;
    }

    public void play() {
        while(this.gameState){
            nextTurn();
        }
        determineWinner();
    }

    public void nextTurn() {
        // Show the players hand, ask for player input, take appropriate cards from dealer until go fish
        boolean hasGoneFish = false;
        while(!hasGoneFish){
            goFish(this.activePlayer);
            hasGoneFish = true;
        }

    }

    public Boolean checkGameState() {
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

    private CardRank getPlayerCardRank(){
        Console console = new Console(System.in, System.out);

        return null;
    }
}
