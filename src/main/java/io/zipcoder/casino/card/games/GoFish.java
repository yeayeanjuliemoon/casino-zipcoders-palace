package io.zipcoder.casino.card.games;

import io.zipcoder.casino.Console;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardGame;
import io.zipcoder.casino.card.utilities.CardRank;

import java.util.ArrayList;
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

    public void transferCards(Player playerToTransfer, Player receivingPlayer, CardRank rank) {
        List<Card> cardSet = removeSet(rank, playerToTransfer);
        this.playerHands.get(receivingPlayer).addAll(cardSet);
    }

    public void checkForCardSets(Player player) {
        //Goes through a players hand, determines if there is a set of cards, removes that set
        Map<CardRank, Integer> rankMap = createRankMap(player);
        for(CardRank c : rankMap.keySet()){
            if(rankMap.get(c) == 4){
                removeSet(c, player);
                incrementScore(player);
            }
        }
    }

    private Map<CardRank, Integer> createRankMap(Player player){
        Map<CardRank, Integer> rankMap = new HashMap<>();
        List<Card> playerHand = playerHands.get(player);
        for(Card c: playerHand){
            if(rankMap.containsKey(c.getRank())){
                Integer newCount = rankMap.get(c.getRank()) + 1;
                rankMap.replace(c.getRank(), newCount);
            }
            else{
                rankMap.put(c.getRank(), 1);
            }
        }
        return rankMap;
    }

    public void goFish(Player player) {
        if(this.deck.getDeck().isEmpty()){
            this.gameState = false;
        }
        else{
            this.playerHands.get(player).add(this.deck.draw());
        }

    }

    public List<Card> removeSet(CardRank rank, Player player) {
        List<Card> playerHand = playerHands.get(player);
        List<Card> removedCards = new ArrayList<>();
        for(Card c : playerHand){
            if(c.getRank() == rank){
                removedCards.add(c);
                playerHands.get(player).remove(c);
            }
        }
        return removedCards;
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
            // Get the rank from the player -> check if possible -> transfer cards or go fish
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
