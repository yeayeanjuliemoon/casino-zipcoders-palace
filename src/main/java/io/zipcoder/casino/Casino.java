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
        this.listOfPlayers = new ArrayList<Player>();
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

    public void resetBalance(){

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

    public void playerLogout(String givenName) {
        this.activePlayer = null;
    }

    public String printActivePlayers() {
        return listOfPlayers.toString();
    }

    public void parseMenuInput(){
        Integer input = console.getIntegerInput("Selection >");

        switch (input){
            case 1: //Login
                playerLogin(console.getStringInput("What is your name?"));
                break;
            case 2: //GoFish
                if(checkIfActivePlayer()){
                    selectGame(new GoFish());
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;

            case 3: //BlackJack
                if(checkIfActivePlayer()){
                    selectGame(new Blackjack());
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;
            case 4: // Craps
                if(checkIfActivePlayer()){
                    selectGame(new CrapsGame());
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;
            case 5: // CeeLo
                if(checkIfActivePlayer()){
                    selectGame(new CeeLoGame());
                } else {
                    console.println("No player currently logged in");
                    pauseForReadability();
                }
                break;
            case 6: // Logout
                if(checkIfActivePlayer()){
                    String aPlayer = this.activePlayer.toString();
                    playerLogout(aPlayer); // Needs to be re-looked at
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

    public void quit() {
        console.println(" Thank you for playing at ZipCoder's Casino!");
        System.exit(0);
    }
}
