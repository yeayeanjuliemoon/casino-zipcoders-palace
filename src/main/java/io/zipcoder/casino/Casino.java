package io.zipcoder.casino;

import io.zipcoder.casino.card.games.Blackjack;
import io.zipcoder.casino.card.games.GoFish;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Casino {

    private ArrayList<Player> listOfPlayers;
    public Player activePlayer;
    public Game currentGame;
    public Console console;
    private final boolean opFlag;

    public Casino() {
        this.console = new Console(System.in, System.out);
        this.listOfPlayers = new ArrayList<>();
        this.activePlayer = null;
        this.opFlag = true;
    }

    public void run(){
        while(opFlag) {
            console.println(printCasinoMenu());
            parseMenuInput();
        }
    }

    public Player createPlayer(String aName){
        if (getGamblingStatus()) {
            GamblingPlayer aGamblingPlayer = new GamblingPlayer(aName);
            aGamblingPlayer.deposit(console.getIntegerInput("Please enter a starting wallet balance."));
            this.listOfPlayers.add(aGamblingPlayer);
            return aGamblingPlayer;
        } else {
            Player aPlayer = new Player(aName);
            this.listOfPlayers.add(aPlayer);
            return aPlayer;
        }
    }

    public void selectGame(Game selectedGame) {
        this.currentGame = selectedGame;
        playGame();
    }

    private void playGame() {
        currentGame.play();
    }

    public String printCasinoMenu() {
        String menu = "******* ♖ ZipCoder's Palace ♖  *******\n" +
                "******* Please Enter A Number: *******\n"+
                "* 1: Login ~~~~~~~~~~~~~~~~~~~~~~~ *\n"+
                "* 2: Play GoFish ~~~~~~~~~~~~~~~~~ *\n"+
                "* 3: Play BlackJack ~~~~~~~~~~~~~~ *\n"+
                "* 4: Play Craps ~~~~~~~~~~~~~~~~~~ *\n"+
                "* 5: Play CeeLo ~~~~~~~~~~~~~~~~~~ *\n"+
                "* 6: Logout ~~~~~~~~~~~~~~~~~~~~~~ *\n"+
                "* 7: Leave ZipCoder's Palace ~~~~~ *\n"+
                "**** You must be logged in to play a game! ****\n";
        return menu;
    }

    public void playerLogin(String givenName) {
        if(isAPlayer(givenName) >= 0){
            this.activePlayer = listOfPlayers.get(isAPlayer(givenName));
        } else {
            this.activePlayer = createPlayer(givenName);
        }
        console.print(activePlayer.toString()+" is now logged in");
        pauseForReadability();
    }

    public Integer isAPlayer(String givenName){
        for(int i = 0; i < listOfPlayers.size(); i++){
            if(listOfPlayers.get(i).toString().equals(givenName)){
                return i;
            }
        }
        return -1;
    }

    public Boolean getGamblingStatus(){
        console.print("Are you a gambling player? yes/no");
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

    public void playerLogout() {
        this.activePlayer = null;
    }

    public String printPlayers() {
        StringBuilder players = new StringBuilder("Current Players:\n");
        if(listOfPlayers.size() != 0){
            for(Player player : listOfPlayers){
                players.append("    "+player.toString()+"\n");
            }
        } else{
            players.append("   None\n");
        }
        return players.toString();
    }

    public void parseMenuInput(){
        Integer input = console.getIntegerInput("Selection >");
        switch (input){
            case 1: //Login
                console.print(printPlayers());
                playerLogin(console.getStringInput("What is your name?"));
                break;
            case 2: //GoFish
                if(checkIfActivePlayer()){
                    selectGame(new GoFish(5,this.activePlayer));
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;
            case 3: //BlackJack
                if(checkIfActivePlayer()){
                    if(checkIfGamblingPlayer()){
                        addToPlayerBalance();
                        selectGame(new Blackjack(this.activePlayer));
                    }
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;
            case 4: // Craps
                if(checkIfActivePlayer()){
                    if(checkIfGamblingPlayer()){
                        addToPlayerBalance();
                        selectGame(new CrapsGame((GamblingPlayer) this.activePlayer));
                    }
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;
            case 5: // CeeLo
                if(checkIfActivePlayer()){
                    if(checkIfGamblingPlayer()){
                        addToPlayerBalance();
                        selectGame(new CeeLoGame((GamblingPlayer) activePlayer));
                    }
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;
            case 6: // Logout
                if(checkIfActivePlayer()){
                    playerLogout();
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;
            case 7: // Quit
                quit();
                break;
            default:
                console.println("Please enter a valid selection (1 -> 9)");
                pauseForReadability();
                break;
        }
    }

    public void addToPlayerBalance(){
        console.println("You currently have $"+ ((GamblingPlayer)this.activePlayer).getBalance() + " in your account");
        console.print("Would you like to add to your balance before your game begins? yes/no");
        while(true) {
            String userInput = console.getStringInput("");
            if (userInput.toLowerCase().equals("yes")) {
                Integer balanceAdd = console.getIntegerInput("How much would you like to add?");
                if(balanceAdd > 0){
                    ((GamblingPlayer)this.activePlayer).deposit(balanceAdd);
                }
                break;
            } else if (userInput.toLowerCase().equals("no")) {
                break;
            } else {
                console.print("Please enter \"yes\" or \"no\"");
            }
        }
    }

    private void pauseForReadability(){
        try{
            Thread.sleep(1500);
        } catch (InterruptedException e){
            Logger logger = Logger.getLogger(Casino.class.getName());
            logger.log(Level.INFO, e.toString());
        }

    }

    public Boolean checkIfActivePlayer() {
        if(this.activePlayer != null){
            return true;
        } else{
            return false;
        }
    }

    public Boolean checkIfGamblingPlayer(){
        if(activePlayer instanceof GamblingPlayer){
            return true;
        } else {
            console.print("Come back when you're older!\n");
            pauseForReadability();
            return false;
        }
    }

    public void quit() {
        console.println(" Thank you for playing at ZipCoder's Palace!");
        System.exit(0);
    }
}
