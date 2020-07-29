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

    public Boolean checkPlayerHand(String rank) {
        return null;
    }

    public void transferCard(Player playerToTransfer) {

    }

    public List<Card> checkForCardSets(Player player) {
        return null;
    }

    public void goFish(Player player) {

    }

    public void removeSet(String rank) {

    }

    public void incrementScore(Player player) {
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
