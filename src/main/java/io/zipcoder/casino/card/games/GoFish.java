package io.zipcoder.casino.card.games;

import io.zipcoder.casino.Console;
import io.zipcoder.casino.Player;
import io.zipcoder.casino.card.utilities.Card;
import io.zipcoder.casino.card.utilities.CardGame;
import io.zipcoder.casino.card.utilities.CardRank;

import java.util.*;

public class GoFish extends CardGame {

    private Map<Player, Integer> playerScores;
    private Integer booksScored = 0;
    private Console console = new Console(System.in, System.out);

    public GoFish(Integer handSize, Player activePlayer) {
        super(handSize, activePlayer);
        this.playerScores = new HashMap<Player, Integer>();
        playerScores.put(this.activePlayer, 0);
        playerScores.put(this.dealer, 0);
    }

    public void dealerTurn(){
        boolean hasGoneFish = false;
        Random rand = new Random();
        List<Card> dealerHand;
        while(!hasGoneFish){
            // BREAK THIS UP
            dealerHand = this.playerHands.get(this.dealer);
            Integer cardIndex = rand.nextInt(dealerHand.size() - 1);
            CardRank chosenRank = dealerHand.get(cardIndex).getRank();
            this.console.println("Dealer fishes for : " + chosenRank.toString());
            if(checkPlayerHand(chosenRank, this.activePlayer)){
                transferCards(this.activePlayer, this.dealer, chosenRank);
                checkForCardSets(this.dealer);
            }
            else{
                this.console.println("Dealer goes fish!");
                goFish(this.dealer);
                hasGoneFish = true;
            }
        }
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
                System.out.println("Set of 4: " + c.toString() + " for player: " + player.toString());
                removeSet(c, player);
                incrementScore(player);
            }
        }
    }

    private Map<CardRank, Integer> createRankMap(Player player){
        // Helper method to make the mapping for each rank to their respective counts
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
            return;
        }
        else{
            this.playerHands.get(player).add(this.deck.draw());
        }

    }

    private List<Card> removeSet(CardRank rank, Player player) {
        List<Card> playerHand = playerHands.get(player);
        List<Card> removedCards = new ArrayList<>();
        for(Card c : playerHand){
            if(c.getRank() == rank){
                removedCards.add(c);
            }
        }
        this.playerHands.get(player).removeAll(removedCards);
        return removedCards;
    }

    public void incrementScore(Player player) {
        Integer newScore = this.playerScores.get(player) + 1;
        this.playerScores.replace(player, newScore);
    }

    public Player determineWinner() {
        if(playerScores.get(this.activePlayer) > playerScores.get(this.dealer)){
            return this.activePlayer;
        }
        else if(playerScores.get(this.activePlayer) < playerScores.get(this.dealer)){
            return this.dealer;
        }
        else{
            return null;
        }
    }

    public void play() {
        while(this.gameState){
            nextTurn();
            if(gameState) {
                dealerTurn();
                this.gameState = checkGameState();
            }
        }
        determineWinner();
    }

    public void nextTurn() {
        // Show the players hand, ask for player input, take appropriate cards from dealer until go fish
        boolean hasGoneFish = false;
        while(!hasGoneFish){
            // Get the rank from the player -> check if possible -> transfer cards or go fish
            this.console.println(showHand(this.activePlayer));
            CardRank chosenRank = getPlayerInput();
            if(chosenRank == null){
                this.gameState = false;
                break;
            }
            else if(checkPlayerHand(chosenRank, this.dealer)){
                transferCards(this.dealer, this.activePlayer, chosenRank);
            }
            else {
                this.console.println("GO FISH!");
                goFish(this.activePlayer);
                this.console.println(showHand(this.activePlayer));
                hasGoneFish = true;
            }
            checkForCardSets(this.activePlayer);
        }
    }

    public Boolean checkGameState() {
        // Checks the game over conditions, return false if the game should stop
        if(this.booksScored == 13){
          return false;
        }
        else if(this.deck.getDeck().isEmpty()){
            // The deck is empty and one of the players has an empty hand
            if(playerHands.get(this.activePlayer).isEmpty() || playerHands.get(this.dealer).isEmpty()){
                return false;
            }
        }
        return true;
    }

    public String printGameStatus() {
        return null;
    }

    public String printGameRules() {
        return null;
    }

    public void exit() {
        // Print Exit Message
    }


    private CardRank getPlayerInput(){
        String userString = this.console.getStringInput("Enter the card rank you would like to fish for: ");
        if(userString.toLowerCase().equals("exit")){
            return null;
        }
        try {
            return parseCardRank(userString);
        } catch (IllegalArgumentException e){
            this.console.println("Invalid Rank Input");
            return getPlayerInput();
        }
    }

    public CardRank parseCardRank(String input) throws IllegalArgumentException{
        CardRank chosenRank = CardRank.valueOf(input.toUpperCase());
        if(!checkPlayerHand(chosenRank, this.activePlayer)){
            throw new IllegalArgumentException();
        }
        return chosenRank;
    }

    public static void main(String[] args){
        GoFish game = new GoFish(5, new Player("Frank"));
        game.play();
    }
}
