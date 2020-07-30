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

    // Not Testable
    private void dealerTurn(){
        boolean hasGoneFish = false;
        Random rand = new Random();
        List<Card> dealerHand;
        while(!hasGoneFish){
            dealerHand = this.playerHands.get(this.dealer);
            Integer cardIndex = rand.nextInt(dealerHand.size() - 1);
            CardRank chosenRank = dealerHand.get(cardIndex).getRank();
            this.console.println("Dealer fishes for : " + chosenRank.toString());
            hasGoneFish = handleDealerInput(chosenRank);
        }
    }

    // Not testable
    private boolean handleDealerInput(CardRank chosenRank){
        if(checkPlayerHand(chosenRank, this.activePlayer)){
            transferCards(this.activePlayer, this.dealer, chosenRank);
            checkForCardSets(this.dealer);
            return false;
        }
        else{
            this.console.println("Dealer goes fish!");
            goFish(this.dealer);
            return true;
        }
    }

    // Tested
    public Boolean checkPlayerHand(CardRank rank, Player player) {
        List<Card> playerHand = playerHands.get(player);
        for(Card c : playerHand){
            if(c.getRank() == rank){
                return true;
            }
        }
        return false;
    }


    // Tested
    public void transferCards(Player playerToTransfer, Player receivingPlayer, CardRank rank) {
        List<Card> cardSet = removeSet(rank, playerToTransfer);
        this.playerHands.get(receivingPlayer).addAll(cardSet);
    }

    // Tested
    public void checkForCardSets(Player player) {
        //Goes through a players hand, determines if there is a set of cards, removes that set
        Map<CardRank, Integer> rankMap = createRankMap(player);
        for(CardRank c : rankMap.keySet()){
            if(rankMap.get(c) >= 4){
                System.out.println("Set of 4: " + c.toString() + " for player: " + player.toString());
                removeSet(c, player);
                incrementScore(player);
            }
        }
    }

    //Tested
    public Map<CardRank, Integer> createRankMap(Player player){
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

    // Tested
    public void goFish(Player player) {
        if(this.deck.getDeck().isEmpty()){
            return;
        }
        else{
            Card fishedCard = this.deck.draw();
            this.playerHands.get(player).add(fishedCard);
            if(player.equals(this.activePlayer)) {
                console.println(player.toString() + " drew: " + fishedCard.toString());
            }
        }

    }

    // Tested
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

    // Tested
    public void incrementScore(Player player) {
        Integer newScore = this.playerScores.get(player) + 1;
        this.playerScores.replace(player, newScore);
        this.booksScored += 1;
    }

    // Tested
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


    // Can't Test
    public void play() {
        console.println(printGameRules());
        while(this.gameState){
            console.println(printScores());
            nextTurn();
            if(this.gameState) {
                dealerTurn();
                this.gameState = checkGameState();
            }
        }
        console.println("The Game is Over!");
        try {
            console.println("And the winner is..." + determineWinner().toString());
        } catch (NullPointerException e){
            console.println("It's a Tie!");
        }
        console.println("Final " + printScores());
    }

    // Can'T Test
    public void nextTurn() {
        // Show the players hand, ask for player input, take appropriate cards from dealer until go fish
        boolean hasGoneFish = false;
        while(!hasGoneFish){
            // Get the rank from the player -> check if possible -> transfer cards or go fish
            this.console.println("YOUR HAND : ");
            this.console.println(showHand(this.activePlayer));
            CardRank chosenRank = getPlayerInput();
            hasGoneFish = handleUserInput(chosenRank);
            checkForCardSets(this.activePlayer);
        }
    }

    // Tested
    public boolean handleUserInput(CardRank chosenRank){
        if(chosenRank == null){
            exit();
            return true;
        }
        else if(checkPlayerHand(chosenRank, this.dealer)){
            transferCards(this.dealer, this.activePlayer, chosenRank);
            return false;
        }
        else {
            this.console.println("GO FISH!");
            goFish(this.activePlayer);
            this.console.println("YOUR HAND :");
            this.console.println(showHand(this.activePlayer));
            return true;
        }

    }

    // Tested
    public Boolean checkGameState() {
        // Checks the game over conditions, return false if the game should stop
        if(this.playerScores.get(this.dealer) == 5 || this.playerScores.get(this.activePlayer) == 5){
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


    //Tested
    public String printGameRules() {
        return "=======Welcome to Go Fish=======\n" +
        "How to Play: \n" +
        "- Ask the other player if they have any cards that match the rank\n of a card in your hand\n" +
        "- If they do, you take all cards of that rank from their hand, and\n can ask again"+
                "- If they don't, you Go Fish! draw a card, and your turn is over\n" +
                "- Each time you get a set of four cards from the same rank, you \n remove those cards from your hand and gain a point\n" +
        "- First player to 5 points wins! \n\n" + "Enter 'exit' at any time to end the game. Have Fun!";
    }

    // Cannot Test
    public void exit() {
        // Print Exit Message
        this.gameState = false;
        this.console.println("Thank you for playing Go Fish!");
    }

    // Cannot Test
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

    // Tested
    public CardRank parseCardRank(String input) throws IllegalArgumentException{
        CardRank chosenRank = CardRank.valueOf(input.toUpperCase());
        if(!checkPlayerHand(chosenRank, this.activePlayer)){
            throw new IllegalArgumentException();
        }
        return chosenRank;
    }

    private String printScores(){
        StringBuilder sb = new StringBuilder();
        sb.append("Scores :  ");
        sb.append(this.activePlayer.toString() + " : " + this.playerScores.get(this.activePlayer) + "    ");
        sb.append(this.dealer.toString() + " : " + this.playerScores.get(this.dealer) + "\n");
        return sb.toString();
    }
}
